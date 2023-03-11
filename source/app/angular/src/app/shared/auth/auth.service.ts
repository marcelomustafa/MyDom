import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "@environments/environment";
import * as moment from "moment";
import { BehaviorSubject, Observable, map } from "rxjs";
import { AuthUser } from "@auth/auth-user";

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" }),
};

@Injectable({
  providedIn: "root",
})
export class AuthService {

  protected getUrl(): string {
    return `${environment.apiUrl}/api/auth`;
  }

  private userSubject: BehaviorSubject<AuthUser>;
  public authUser: Observable<AuthUser>;

  constructor(private router: Router, private http: HttpClient) {
    this.userSubject = new BehaviorSubject(
      JSON.parse(localStorage.getItem("authUser")!)
    );
    this.authUser = this.userSubject.asObservable();
  }

  public get getAuthUser(): AuthUser {
    return this.userSubject.value;
  }

  login(username: string, password: string): Observable<AuthUser> {
    console.log('pass for: login');
    console.log('url: ' + `${this.getUrl()}/signin`);
    

    const login = {
      username: username,
      password: password,
      authTokenType: environment.authTokenType,
    };

    console.log(`username: ' ${login.username} password: ${login.password} authTokenType: ${login.authTokenType}`);

    return this.http
      .post<any>(`${this.getUrl()}/signin`, login)
      .pipe(
        map((user) => {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem("authUser", JSON.stringify(user));
          this.userSubject.next(user);
          return user;
        })
      );
  }

  private setSession(authResult: any){
    const expiresAt = moment().add(authResult.expiresIn,'second');
    localStorage.setItem('idToken', authResult.idToken);
    localStorage.setItem('expiresAt', JSON.stringify(expiresAt.valueOf));
  }

  logout(): Observable<any> {
    const response = this.http.post(`${this.getUrl()}/signout`, { }, httpOptions);

    // remove user from local storage to log user out
    localStorage.removeItem('authUser');
    this.userSubject.next(null!);
  
    this.router.navigate(['/login']);
  
    return response;
  }

  register(username: string, email: string, password: string): Observable<any> {
    return this.http.post(`${this.getUrl()}/signup`,
      {
        username,
        email,
        password,
      },
      httpOptions
    );
  }

  getExpiration() {
    const expiration = localStorage.getItem("expiresAt");
    const expiresAt = JSON.parse(expiration || "");
    return moment(expiresAt);
  }

  isLoggedOut() {
    return !this.isLoggedIn;
  }

  private isLoggedIn() {
    return moment().isBefore(this.getExpiration());
  }
}

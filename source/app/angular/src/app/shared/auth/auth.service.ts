import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { environment } from "@environments/environment";
import { BehaviorSubject, Observable, map } from "rxjs";
import { AuthUser } from "@auth/auth-user";
import { AuthStoregeService } from "@auth/auth-storege.service";

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

  constructor(
    private router: Router, 
    private http: HttpClient,
    private authStorege: AuthStoregeService
  ) {
    this.userSubject = new BehaviorSubject(authStorege.getUser());
    this.authUser = this.userSubject.asObservable();
  }

  public get getAuthUser(): AuthUser {
    return this.userSubject.value;
  }

  login(username: string, password: string): Observable<AuthUser> {

    const login = {
      username: username,
      password: password,
      authTokenType: environment.authTokenType,
    };

    return this.http
      .post<any>(`${this.getUrl()}/signin`, login)
      .pipe(
        map((user) => {

          // store user details and jwt token in local storage to keep user logged in between page refreshes
          this.authStorege.setSession(user);
          this.userSubject.next(user);
          return user;

        })
      );
  }

  logout(): Observable<any> {
    const response = this.http.post(`${this.getUrl()}/signout`, { }, httpOptions);

    // remove user from local storage to log user out
    this.authStorege.clean();
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

  public isLoggedIn(): boolean{
    return this.authStorege.isLoggedIn() ;
  }
  
}

import { Injectable } from '@angular/core';


const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class AuthStoregeService {

  clean(): void {
    //window.sessionStorage.clear();
    try {
      localStorage.clear();
    } catch (e) {}

  }

  public saveUser(user: any): void {
    // window.sessionStorage.removeItem(USER_KEY);
    // window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
    try {
      localStorage.removeItem(USER_KEY);
      localStorage.setItem(USER_KEY, JSON.stringify(user));
    } catch (e) {}
  }

  public getUser(): any {
    //const user = window.sessionStorage.getItem(USER_KEY);
    const user = localStorage.getItem(USER_KEY);

    if (user) {
      return JSON.parse(user);
    }

    return {};
  }

  public isLoggedIn(): boolean {
    //const user = window.sessionStorage.getItem(USER_KEY);
    const user = localStorage.getItem(USER_KEY);
    
    if (user) {
      return true;
    }

    return false;
  }  
}

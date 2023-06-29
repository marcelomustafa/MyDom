import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import * as moment from 'moment';


const USER_KEY = 'authUser';

@Injectable({
  providedIn: 'root'
})
export class AuthStoregeService {

  public clean(): void {
    //window.sessionStorage.clear();
    try {
      localStorage.clear();
    } catch (e) {}

  }

  public setSession(user: any){

    // window.sessionStorage.removeItem(USER_KEY);
    // window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
    try {
      //user.expiresAt = moment().add(user.expiresIn,'second');
      user.expiresAt = moment().add(environment.tokenExpiresIn ,'millisecond');

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

  private getExpiration() {
    const user = this.getUser();
    return moment(user.expiresAt || -1);
  }

  public isLoggedOut() {
    return !this.isLoggedIn;
  }

  public isLoggedIn() {
    return moment().isBefore(this.getExpiration());
  }

}
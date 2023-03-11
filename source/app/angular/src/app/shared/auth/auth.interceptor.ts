import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "@environments/environment";
import { catchError, Observable } from "rxjs";
import { AuthService } from "./auth.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(
    private authService: AuthService
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add auth header with jwt if user is logged in and request is to the api url
    const authUser = this.authService.getAuthUser;
    const isLoggedIn = this.authService.isLoggedIn() && authUser?.token;
    const isApiUrl = request.url.startsWith(environment.apiUrl);

    if (isLoggedIn && isApiUrl) {
      request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${authUser?.token}`
      }});
    }

    return next.handle(request).pipe(catchError(err => {
      if ([401, 403].includes(err.status)) {
        // auto logout if 401 Unauthorized or 403 Forbidden response returned from api
        this.authService.logout();
        location.reload();
      } 

      const error = err.error.message || err.statusText;
      throw new Error(error);
      //return throwError(error);

    }));
  }
}
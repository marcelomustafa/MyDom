import { Component } from '@angular/core';
import { AuthUser } from '@shared/auth/auth-user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'MyDom';
  user?: AuthUser;


  // private roles: string[] = [];
  // isLoggedIn = false;
  // showAdminBoard = false;
  // showModeratorBoard = false;
  // username?: string;

  // constructor(
  //   private router: Router,
  //   private storageService: StorageService, 
  //   private authService: AuthenticationService
  // ) { 
  //   this.authService.user.subscribe(x => this.user = x);
  // }  

  // constructor(
  //   private authService: AuthService
  // ) { 
  //   this.authService.authUser.subscribe(x => this.user = x);
  // }


  ngOnInit(): void {
    // this.isLoggedIn = this.storageService.isLoggedIn();

    // if (this.isLoggedIn) {
    //   const user = this.storageService.getUser();
    //   this.roles = user.roles;

    //   this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
    //   this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');

    //   this.username = user.username;
    // }
  }

  logout(): void {
    // this.authService.logout().subscribe({
    //   next: res => {
    //     console.log(res);
    //     this.storageService.clean();

    //     window.location.reload();
    //   },
    //   error: err => {
    //     console.log(err);
    //   }
    // });

    // this.authService.logout();
    // this.router.navigate(['/login']);

  }
}

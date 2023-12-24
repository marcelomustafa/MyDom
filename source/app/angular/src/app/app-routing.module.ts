import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import { AuthGuard } from '@shared/auth/auth.guard';
// import { PersonFormComponent } from './page/person/person-form/person-form.component';

const routes: Routes = [

  

  { path: 'register', loadChildren: () => import('@page/register/register.module').then(m => m.RegisterModule)},
  { path: 'login', loadChildren: () => import('@page/login/login.module').then(m => m.LoginModule)},

  //{ path: 'main', loadChildren: () => import('@page/home/home.module').then(m => m.HomeModule), canActivate: [AuthGuard]},

//  { path: 'persons', loadChildren: () => import('@page/person/person.module').then(m => m.PersonModule), canActivate: [AuthGuard]},
//  { path: 'users', loadChildren: () => import('@page/user/user.module').then(m => m.UserModule), canActivate: [AuthGuard]},

//  { path: 'board-admin', loadChildren: () => import('src/app/page/form/board-admin/board-admin.module').then(m => m.BoardAdminModule), canActivate: [AuthGuard]},
//  { path: 'board-moderator', loadChildren: () => import('src/app/page/form/board-moderator/board-moderator.module').then(m => m.BoardModeratorModule), canActivate: [AuthGuard]},
//  { path: 'board-user', loadChildren: () => import('src/app/page/form/board-user/board-user.module').then(m => m.BoardUserModule), canActivate: [AuthGuard]},
//  { path: 'profile', loadChildren: () => import('src/app/page/form/profile/profile.module').then(m => m.ProfileModule), canActivate: [AuthGuard]},


  // otherwise redirect to home
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  // { path: 'main', redirectTo: 'home', pathMatch: 'full' },

]; 

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

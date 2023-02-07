import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'register', loadChildren: () => import('src/app/page/main/register/register.module').then(m => m.RegisterModule)},
  { path: 'login', loadChildren: () => import('src/app/page/main/login/login.module').then(m => m.LoginModule)},
  { path: 'home', loadChildren: () => import('src/app/page/main/home/home.module').then(m => m.HomeModule)},
  { path: 'persons', loadChildren: () => import('src/app/page/form/person/person.module').then(m => m.PersonModule)},
  { path: 'users', loadChildren: () => import('src/app/page/form/user/user.module').then(m => m.UserModule)},
  { path: 'board-admin', loadChildren: () => import('src/app/page/form/board-admin/board-admin.module').then(m => m.BoardAdminModule)},
  { path: 'board-moderator', loadChildren: () => import('src/app/page/form/board-moderator/board-moderator.module').then(m => m.BoardModeratorModule)},
  { path: 'board-user', loadChildren: () => import('src/app/page/form/board-user/board-user.module').then(m => m.BoardUserModule)},
  { path: 'profile', loadChildren: () => import('src/app/page/form/profile/profile.module').then(m => m.ProfileModule)},

  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRootRoutingModule { }

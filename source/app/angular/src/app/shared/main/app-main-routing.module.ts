import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from 'src/app/page/home/home/home.component';
 

const routes: Routes = [
  // {
  //   path: '',
  //   component: HomeComponent
  // },
  { path: 'login', loadChildren: () => import('src/app/page/login/login.module').then(m => m.LoginModule)},
  { path: 'home', loadChildren: () => import('src/app/page/home/home.module').then(m => m.HomeModule)},
  { path: 'persons', loadChildren: () => import('src/app/page/form/person/person.module').then(m => m.PersonModule)},
  { path: 'users', loadChildren: () => import('src/app/page/form/user/user.module').then(m => m.UserModule)},
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

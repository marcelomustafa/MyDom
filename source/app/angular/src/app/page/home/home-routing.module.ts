import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeFormComponent } from './home-form/home-form.component';
import { PersonFormComponent } from '../person/person-form/person-form.component';
import { AuthGuard } from '@app/shared/auth/auth.guard';
import { UserFormComponent } from '../user/user-form/user-form.component';
import { DashboardHomeFormComponent } from '../dashboard/dashboard-home/dashboard-home-form/dashboard-home-form.component';
import { PersonListComponent } from '../person/person-list/person-list.component';
import { UserListComponent } from '../user/user-list/user-list.component';

const routes: Routes = [
  {
    path: '',
    component: HomeFormComponent,
    children:[
      { path: 'home', component: DashboardHomeFormComponent , canActivate: [AuthGuard]},
      { path: 'persons', component: PersonListComponent , canActivate: [AuthGuard]},
      { path: 'users', component: UserListComponent , canActivate: [AuthGuard]},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }

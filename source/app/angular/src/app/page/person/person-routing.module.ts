import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonListComponent } from './person-list/person-list.component';
import { PersonFormComponent } from './person-form/person-form.component';
import { AuthGuard } from '@app/shared/auth/auth.guard';

const routes: Routes = [
  {
    path: 'persons',
    component: PersonListComponent,
    children:[
      { path: 'persons/new', component: PersonFormComponent , canActivate: [AuthGuard]},
      { path: 'persons/{id}', component: PersonFormComponent , canActivate: [AuthGuard]},
    ]    
  }
  // {
  //   path: 'persons/new',
  //   component: PersonFormComponent
  // },
  // {
  //   path: 'persons/{id}',
  //   component: PersonFormComponent
  // }    
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PersonRoutingModule { }

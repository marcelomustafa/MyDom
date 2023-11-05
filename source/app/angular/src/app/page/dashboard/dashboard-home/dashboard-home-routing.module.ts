import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardHomeFormComponent } from './dashboard-home-form/dashboard-home-form.component';

const routes: Routes = [
  {
    path: '',
    component: DashboardHomeFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardHomeRoutingModule { }

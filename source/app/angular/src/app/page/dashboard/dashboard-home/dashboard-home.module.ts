import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardHomeRoutingModule } from './dashboard-home-routing.module';
import { DashboardHomeFormComponent } from './dashboard-home-form/dashboard-home-form.component';


@NgModule({
  declarations: [
    DashboardHomeFormComponent
  ],
  imports: [
    CommonModule,
    DashboardHomeRoutingModule
  ]
})
export class DashboardHomeModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeFormComponent } from './home-form/home-form.component';
// import { AuthInterceptor } from '@auth/auth.interceptor';
// import { LocalDateTimePipe } from '@shared/pipe/local-date-time.pipe';
// import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    HomeFormComponent
  ],
  providers: [
    //LocalDateTimePipe,
    //AuthInterceptor
  ],  
  imports: [
    CommonModule,
    HomeRoutingModule,
    //FormsModule,
    //ReactiveFormsModule,
    //HttpClientModule
  ]
})
export class HomeModule { }

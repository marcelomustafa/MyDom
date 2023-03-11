import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LoginRoutingModule } from './login-routing.module';
import { LoginFormComponent } from './login-form/login-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// import { AuthInterceptor } from '@auth/auth.interceptor';
// import { LocalDateTimePipe } from '@shared/pipe/local-date-time.pipe';
// import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    LoginFormComponent
  ],
  providers: [
    //LocalDateTimePipe,
    //AuthInterceptor
  ],   
  imports: [
    CommonModule,
    LoginRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    //HttpClientModule,
  ]
})
export class LoginModule { }

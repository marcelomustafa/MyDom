import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRootRoutingModule } from './app-root-routing.module';
import { AppRootComponent } from './root/app-root.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { LocalDateTimePipe } from 'src/app/shared/pipe/local-date-time.pipe';
import { httpInterceptorProviders } from 'src/app/auth/helpers/http.interceptors';


@NgModule({
  declarations: [
    AppRootComponent,
    LocalDateTimePipe
  ],
  imports: [
    BrowserModule,
    AppRootRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [
    LocalDateTimePipe,
    httpInterceptorProviders
  ],
  bootstrap: [
    AppRootComponent
  ]
})
export class AppRootModule { }

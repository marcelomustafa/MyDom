import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRootRoutingModule } from './app-main-routing.module';
import { AppRootComponent } from './root/app-root.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { LocalDateTimePipe } from '../pipe/local-date-time.pipe';

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
    LocalDateTimePipe
  ],
  bootstrap: [
    AppRootComponent
  ]
})
export class AppRootModule { }

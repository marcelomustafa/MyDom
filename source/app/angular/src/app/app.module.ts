import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LocalDateTimePipe } from './shared/pipe/local-date-time.pipe';
import { HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from './shared/auth/auth.interceptor';
import { HomeModule } from './page/home/home.module';


@NgModule({
  declarations: [
    AppComponent,
    LocalDateTimePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    NgbModule,
    HomeModule
  ],
  exports:[
    
  ],
  providers: [
    AuthInterceptor 
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }

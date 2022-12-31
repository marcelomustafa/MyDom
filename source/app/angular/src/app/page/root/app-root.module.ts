import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRootRoutingModule } from './app-root-routing.module';
import { AppRootComponent } from './app-root.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppRootComponent
  ],
  imports: [
    BrowserModule,
    AppRootRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppRootComponent]
})
export class AppRootModule { }

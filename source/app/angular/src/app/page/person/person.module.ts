import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PersonRoutingModule } from './person-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PersonListComponent } from './person-list/person-list.component';
import { PersonFormComponent } from './person-form/person-form.component';
import { MyComZeroComponent } from './my-com-zero/my-com-zero.component';


@NgModule({
  declarations: [
    PersonListComponent,
    PersonFormComponent,
    MyComZeroComponent
  ],
  imports: [ 
    CommonModule,
    PersonRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class PersonModule { }

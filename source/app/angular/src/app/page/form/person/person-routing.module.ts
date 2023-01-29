import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { PersonListComponent } from "./person-list/person-list.component";
import { PersonFormComponent } from "./person-form/person-form.component";

const routes: Routes = [
  {
    path: '',
    component: PersonListComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PersonRoutingModule { }
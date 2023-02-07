import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { BoardModeratorComponent } from "./board-moderator/board-moderator.component";


const routes: Routes = [
  {
    path: '',
    component: BoardModeratorComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BoardModeratorRoutingModule { }
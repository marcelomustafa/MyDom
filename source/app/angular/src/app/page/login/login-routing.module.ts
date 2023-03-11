import { NgModule } from "@angular/core";
import { RouterModule, Routes, CanActivate } from "@angular/router";
import { LoginFormComponent } from "./login-form/login-form.component";
import { AuthGuard } from "../../shared/auth/auth.guard";

const routes: Routes = [
  {
    path: "",
    component: LoginFormComponent
    
    //path: "",
    // children: [
    //   {
    //     path: "login",
    //     component: LoginFormComponent,
    //     //canActivate: [AuthGuard],
    //     data: {
    //       help: "login",
    //       breadcrumb: "Login",
    //       roles: ["ROLE_LOGIN"],
    //     },
    //   },
    //   {
    //     path: "login",
    //     loadChildren: () =>
    //       import("@page/login/login.module").then((m) => m.LoginModule),
    //     data: { breadcrumb: "Login" },
    //   },
    // ]



  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LoginRoutingModule {}

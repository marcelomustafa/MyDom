import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { AuthStoregeService } from "@app/shared/auth/auth-storege.service";
import { AuthService } from "@app/shared/auth/auth.service";
import { first } from "rxjs";

@Component({
  selector: "app-login-form",
  templateUrl: "./login-form.component.html",
  styleUrls: ["./login-form.component.scss"],
})
export class LoginFormComponent implements OnInit {

  public loginForm: FormGroup;
  loading = false;
  submitted = false;
  error = "";

  form: any = {
    username: null,
    password: null,
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = "";
  roles: string[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private storageService: AuthStoregeService
  ) {

    // redirect to home if already logged in
    if (this.authService.getAuthUser) {
      this.router.navigate(["/"]);
    }

    this.loginForm = this.formBuilder.group({
      username: ["", Validators.required],
      password: ["", Validators.required],
    });

  }

  ngOnInit(): void {
    //throw new Error('Method not implemented.');

    // if (this.storageService.isLoggedIn()) {
    //   this.isLoggedIn = true;
    //   this.roles = this.storageService.getUser().roles;
    // }

    // this.loginForm = this.formBuilder.group({
    //   username: ["", Validators.required],
    //   password: ["", Validators.required],
    // });
  }

  // convenience getter for easy access to form fields
  get getLoginForm() {
    return this.loginForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    console.log('onSubmit executed');

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      console.log('stop here if form is invalid');
      return;
    }

    this.error = "x";
    this.loading = true;
    this.authService
      .login(this.getLoginForm['username'].value , this.getLoginForm['password'].value)
      .pipe(first())
      .subscribe({
        next: () => {
          // get return url from route parameters or default to '/'
          const returnUrl = this.route.snapshot.queryParams["returnUrl"] || "/";
          this.router.navigate([returnUrl]);
        },
        error: (error) => {
          this.error = error;
          this.loading = false;
        },
      });



      // const { username, password } = this.form;

      // this.authService.login(username, password).subscribe({
      //   next: data => {
      //     this.storageService.saveUser(data);
  
      //     this.isLoginFailed = false;
      //     this.isLoggedIn = true;
      //     this.roles = this.storageService.getUser().roles;
      //     this.reloadPage();
      //   },
      //   error: err => {
      //     this.errorMessage = err.error.message;
      //     this.isLoginFailed = true;
      //   }
      // });



  }

  reloadPage(): void {
    window.location.reload();
  }  
}

import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { first } from 'rxjs/operators';
import { AlertService, alertLen } from '../services/alert.service';

@Component({
  templateUrl: 'login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  submitted = false;
  error = false;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private alertService: AlertService
  ) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', Validators.required]
    });
    if (this.authService.isLoggedIn()) {
      this.authService.logout();
      this.router.navigateByUrl('/login');
      this.alertService.success('Succesfully logged out!', alertLen.Short);
    }
  }

  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  onSubmit() {
    this.submitted = true;
    this.error = false;
    const val = this.loginForm.value;

    if (val.login && val.password) {
      this.authService.login(val.login, val.password).pipe(first()).subscribe(
          data => {
            this.router.navigateByUrl('/browsebooks');
          },
          error => {
            this.alertService.error('Wrong login or password!', alertLen.Medium);
            this.error = true;
            this.submitted = false;
            this.loginForm = this.formBuilder.group({
              login: ['', Validators.required],
              password: ['', Validators.required]
            });
          });
    }
  }
}

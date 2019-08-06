import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { AlertService, alertLen } from 'src/app/services/alert.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-panel-email',
  templateUrl: './user-panel-email.component.html',
  styleUrls: ['./user-panel-email.component.css']
})
export class UserPanelEmailComponent implements OnInit {

  changeEmailForm: FormGroup;
  submitted = false;
  validForm = false;
  userLogin;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private authService: AuthService,
    private alertService: AlertService,
    private router: Router) { }

  ngOnInit() {
    this.changeEmailForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  get f() { return this.changeEmailForm.controls; }

  onSubmit() {
    this.submitted = true;
    const val = this.changeEmailForm.value;

    if (this.changeEmailForm.valid) {
      this.validForm = true;
    } else {
      this.validForm = false;
      return;
    }

    if (this.validForm) {
      this.userLogin = this.authService.getLogin();
      this.userService.authenticate(this.userLogin, val.password).subscribe(
        () => {
          this.userService.updateEmail(this.authService.getId(), val.email).subscribe(
            () => { this.alertService.success('Email changed!', alertLen.Short); },
            error => { this.alertService.error('Failed to change email', alertLen.Short); }
          );
          this.router.navigateByUrl('/userpanel/profile');
        },
        error => this.alertService.error('Authorization error - wrong password!', alertLen.Short));
    }
  }
}

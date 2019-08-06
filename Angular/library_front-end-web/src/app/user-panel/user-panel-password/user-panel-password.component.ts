import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RepeatedPasswordValidator } from 'src/app/register/repeated-password.validator';
import { UserService } from 'src/app/services/user.service';
import { AlertService, alertLen } from 'src/app/services/alert.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-panel-password',
  templateUrl: './user-panel-password.component.html',
  styleUrls: ['./user-panel-password.component.css']
})
export class UserPanelPasswordComponent implements OnInit {

  changePasswordForm: FormGroup;
  submitted = false;
  validForm = false;
  userLogin;
  password;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private authService: AuthService,
    private alertService: AlertService,
    private router: Router) { }

  ngOnInit() {
    this.changePasswordForm = this.formBuilder.group({
      currentPassword: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      repeatedPassword: ['', [Validators.required, Validators.minLength(8)]]
    }, {
        validator: RepeatedPasswordValidator.MatchPassword
      });
  }

  get f() { return this.changePasswordForm.controls; }

  onSubmit() {
    this.submitted = true;
    const val = this.changePasswordForm.value;

    if (this.changePasswordForm.valid) {
      this.validForm = true;
    } else {
      this.validForm = false;
      return;
    }

    if (this.validForm) {
      this.userLogin = this.authService.getLogin();
      this.userService.authenticate(this.userLogin, val.currentPassword).subscribe(
        () => {
          this.userService.updatePassword(this.authService.getId(), val.password).subscribe(
            () => { this.alertService.success('Password changed!', alertLen.Short); },
            error => { this.alertService.error('Failed to change password', alertLen.Short); }
          );
          this.router.navigateByUrl('/userpanel/profile');
        },
        error => this.alertService.error('Authorization error - wrong password!', alertLen.Short));
    }
  }
}

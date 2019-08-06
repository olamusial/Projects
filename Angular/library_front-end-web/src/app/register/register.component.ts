import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, EmailValidator } from '@angular/forms';
import { UserService } from '../services/user.service';
import { User } from '../shared/models/user';
import { first, tap, map } from 'rxjs/operators';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { RepeatedPasswordValidator } from './repeated-password.validator';
import { AlertService, alertLen } from '../services/alert.service';
import { DictionariesService } from '../services/dictionaries.service';
import { City } from '../shared/models/city';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['../login/login.component.css', './register.component.css']
})

export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  submitted = false;
  validForm = false;
  citiesDictionary: Array<City> = [];
  filteredCitiesDictionary: Array<City> = [];

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private authService: AuthService,
    private dictionariesService: DictionariesService,
    private router: Router,
    private alertService: AlertService) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.pattern('[a-zA-Z]*')]],
      surname: ['', [Validators.required, Validators.pattern('[a-zA-Z\-]*')]],
      pesel: ['', [Validators.minLength(11), Validators.maxLength(11), Validators.pattern('[0-9]*')]],
      dateOfBirth: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      city: ['', [Validators.required, Validators.pattern('[a-zA-Z \-]*')]],
      street: ['', [Validators.required, Validators.pattern('[a-zA-Z \-]*')]],
      number: ['', Validators.required],
      login: ['', [Validators.required, Validators.pattern('[^ ]*')]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      repeatedPassword: ['', [Validators.required, Validators.minLength(8)]]
    }, {
        validator: RepeatedPasswordValidator.MatchPassword
      });

    this.dictionariesService.getCitiesDictionary().subscribe(data => {
      this.citiesDictionary = data;
    });

  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  doFilter() {
    this.filteredCitiesDictionary = this.citiesDictionary.filter(
      city => city.name.toLowerCase().includes(this.registerForm.value.city.toLowerCase()));
  }

  onSubmit() {
    this.submitted = true;
    const val = this.registerForm.value;

    if (this.registerForm.valid) {
      this.validForm = true;
      console.log('valid');
    } else {
      this.validForm = false;
      console.log('NOT valid - wrong data in the form');
      return;
    }
    if (this.validForm) {
      this.userService.register(val.password,
        new User(val.login, val.name, val.surname, val.dateOfBrith, val.email, val.street, val.number, val.city, val.pesel))
        .pipe(first())
        .subscribe(
          data => {
            this.router.navigateByUrl('/login');
            this.alertService.success('Account created successfully!', alertLen.Medium);
          },
          error => {
            this.alertService.error('Failed to create an account!', alertLen.Medium);
          });
    }
  }

}

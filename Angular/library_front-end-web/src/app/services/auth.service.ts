import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SessionUser } from '../shared/models/session-user';
import { tap } from 'rxjs/operators';
import { config } from '../config';

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) {
  }

  getToken() {
    if (localStorage.getItem('currentUser') != null) {
      return JSON.parse(localStorage.getItem('currentUser')).token;
    } else {
      return '';
    }
  }

  getId() {
    if (localStorage.getItem('currentUser') != null) {
      return JSON.parse(localStorage.getItem('currentUser')).id;
    } else {
      return '';
    }
  }

  getLogin() {
    if (localStorage.getItem('currentUser') != null) {
      return JSON.parse(localStorage.getItem('currentUser')).login;
    } else {
      return '';
    }
  }

  isLoggedIn() {
    return localStorage.getItem('currentUser') !== null;
  }

  login(Login: string, Password: string) {
    return this.http.post<SessionUser>(`${config.apiUrl}/users/authenticate`, { Password, Login }).pipe(tap(user => {
      localStorage.setItem('currentUser', JSON.stringify(user));
      return user;
    }));
  }

  logout() {
    localStorage.removeItem('currentUser');
  }
}

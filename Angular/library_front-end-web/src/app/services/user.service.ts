import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { config } from '../config';
import { User } from '../shared/models/user';
import { Observable } from 'rxjs';
import { HoldModel } from '../shared/models/hold-model';

@Injectable()
export class UserService {
    constructor(private http: HttpClient) { }

    register(password: string, user: User) {
        return this.http.post(`${config.apiUrl}/users/register`, {Password: password, User: user});
    }

    getUser(id): Observable<User> {
        return this.http.get<User>(`${config.apiUrl}/users/${id}`);
    }

    authenticate(login, password) {
        return this.http.post(`${config.apiUrl}/users/authenticate`, {Login: login, Password: password});
    }

    updatePassword(id, password) {
        return this.http.post(`${config.apiUrl}/Users/update/${id}`, {Password: password});
    }

    updateEmail(id, email) {
        return this.http.post(`${config.apiUrl}/Users/update/${id}`, {Email: email});
    }

    getOrders(id): Observable<any> {
        return this.http.get(`${config.apiUrl}/api/Order/orders/${id}`);
    }

    getPastOrders(id) {
        return this.http.get<Array<HoldModel>>(`${config.apiUrl}/api/Order/pastorders/${id}`);
    }

    getHolds(id) {
        return this.http.get<Array<HoldModel>>(`${config.apiUrl}/api/Order/holds/${id}`);
    }
}

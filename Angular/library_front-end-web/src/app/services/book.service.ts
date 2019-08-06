import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { config } from '../config';
import { Book } from '../shared/models/book';
import { Observable } from 'rxjs';
import { Page } from '../shared/models/page';
import 'rxjs/add/operator/map';
import { Filter } from '../shared/models/filter';
import { first } from 'rxjs/operators';
import { AuthService } from './auth.service';
import { PenaltyModel } from '../shared/models/penalty-model';

@Injectable()
export class BookService {

  constructor(private http: HttpClient, private authService: AuthService) {
  }

  getList(): Observable<any> {
    return this.http.get(`${config.apiUrl}/books`);
  }

  get(id): Observable<Book> {
    return this.http.get<Book>(`${config.apiUrl}/books/${id}`);
  }

  getListForPage(page: Page, filter?: Filter): Observable<any> {
    return this.http.post(`${config.apiUrl}/books/`, { FilterParams: filter, PagingRequest: page });
  }

  tryHold(bookId) {
    return this.http.get<number>(`${config.apiUrl}/api/Order/TryHold?userId=${this.authService.getId()}&bookId=${bookId}`);
  }

  hold(bookId, count) {
    return this.http.get(`${config.apiUrl}/api/Order/Hold?userid=${this.authService.getId()}&bookid=${bookId}&count=${count}`);
  }

  removeHold(holdId) {
    return this.http.delete(`${config.apiUrl}/api/Order/Hold/${holdId}`);
  }

  prolongate(orderId) {
    return this.http.get(`${config.apiUrl}/api/Order/prolongate/${orderId}`);
  }

  getCopies(bookId) {
    return this.http.get<Array<{condition, isAvailable, book, bookId, order, id}>>(`${config.apiUrl}/books/${bookId}/copies`);
  }

  payPenalty(penaltyId, text): Observable<any> {
    return this.http.post(`${config.apiUrl}/api/Penalty/pay/${penaltyId}`, {Text: text});
  }

  getPenalties(userId) {
    return this.http.get<Array<PenaltyModel>>(`${config.apiUrl}api/Penalty/${userId}`);
  }
}

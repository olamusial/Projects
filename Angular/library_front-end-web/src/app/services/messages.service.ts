import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { config } from '../config';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {

  constructor(private http: HttpClient) { }

  sendMessage(id, text) {
    return this.http.post(`${config.apiUrl}/api/Message/${id}`, {Text: text});
   }

   getMessages(id): Observable<any> {
     return this.http.get(`${config.apiUrl}/api/Message/${id}`);
   }
}



import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { config } from '../config';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationsService {

  constructor(private http: HttpClient) { }

   getNotifications(id): Observable<any> {
     return this.http.get(`${config.apiUrl}/api/Notification/${id}`);
   }

   getAllNotifications(id) {
    return this.http.get<Notification[]>(`${config.apiUrl}/api/Notification/all/${id}`);
   }
}



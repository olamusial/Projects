import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { config } from '../config';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DictionariesService {

  constructor(private http: HttpClient) { }

   getCitiesDictionary(): Observable<any> {
     return this.http.get(`${config.apiUrl}/api/Dictionaries/cities`);
   }
}

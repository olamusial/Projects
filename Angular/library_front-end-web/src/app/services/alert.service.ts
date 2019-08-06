import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

export enum alertLen {
  Short = 2000,
  Medium = 4000,
  Long = 8000
}

@Injectable()
export class AlertService {
    private subject = new Subject<any>();

    constructor() { }

    success(message: string, len: alertLen) {
        this.subject.next({ type: 'success', text: message, length: len });
    }

    error(message: string, len: alertLen) {
        this.subject.next({ type: 'error', text: message, length: len });
    }

    info(message: string, len: alertLen) {
        this.subject.next({ type: 'info', text: message, length: len });
    }

    getMessage(): Observable<any> {
        return this.subject.asObservable();
    }
}

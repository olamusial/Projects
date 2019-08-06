import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { AlertService } from '../services/alert.service';
import { isUndefined } from 'util';


@Component({
    selector: 'app-alert',
    templateUrl: 'alert.component.html',
    styleUrls: ['./alert.component.css']
})

export class AlertComponent implements OnInit, OnDestroy {

    private subscription: Subscription;
    message: any;
    ended = false;
    messageVisible = false;

    constructor(private alertService: AlertService) { }

    ngOnInit() {
        this.subscription = this.alertService.getMessage().subscribe(message => {
            if (!this.messageVisible) {
                this.messageVisible = true;
                this.message = message;
                setTimeout(() => {
                    this.ended = true;
                    setTimeout(() => {
                        this.message = undefined;
                        this.ended = false;
                        this.messageVisible = false;
                    }, 400);
                }, message.length);
            }
        });
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }
}

import { Component, OnInit } from '@angular/core';
import { Router, RouterEvent } from '@angular/router';
import { AuthService } from './services/auth.service';
import { isUndefined } from 'util';
import { AlertService, alertLen } from './services/alert.service';
import { NotificationsService } from './services/notifications.service';
import { first } from 'rxjs/operators';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {

  showBar = true;
  showLoginButton = false;
  private notifications: Array<Notification> = [];

  constructor(
    private router: Router,
    private authService: AuthService,
    private alertService: AlertService,
    private notifService: NotificationsService) {
    this.router.events.subscribe((event: RouterEvent) => {
      if ((event.url === '/')) {
        this.getNotifications();
        this.showLoginButton = true;
      } else if (!isUndefined(event.url)) {
        this.showLoginButton = false;
      }
    });
  }

  ngOnInit(): void {
    this.getNotifications();
  }

  redirectIndex() {
    this.router.navigateByUrl('/');
  }

  logout() {
    this.authService.logout();
    this.alertService.success('Logged out succesfully!', alertLen.Short);
  }

  getNotifications() {
    if (this.authService.isLoggedIn()) {
      this.notifService.getAllNotifications(this.authService.getId()).pipe(first()).subscribe(
        data => {
          this.notifications = data.slice(data.length - 5, data.length).reverse();
        }
      );
    }
  }
}

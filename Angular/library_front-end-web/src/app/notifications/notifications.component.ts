import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { NotificationsService } from '../services/notifications.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {

  private userID;
  private notifications: Array<string> = [];
  @Output() hasNotifications = new EventEmitter<boolean>();

  constructor(
    private notificationsService: NotificationsService,
    private authService: AuthService) { }

  ngOnInit() {
    this.userID = this.authService.getId();
    if (this.userID >= 1) {
      this.notificationsService.getNotifications(this.userID).subscribe(data => {
        data.length === 0 ? this.hasNotifications.emit(false) : this.hasNotifications.emit(true);
        this.notifications = data;
      });
    }
  }

  onOkClicked() {
    this.hasNotifications.emit(false);
    this.notifications = [];
  }

}

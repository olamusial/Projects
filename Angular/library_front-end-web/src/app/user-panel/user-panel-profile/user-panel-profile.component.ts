import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { AlertService, alertLen } from 'src/app/services/alert.service';

@Component({
  selector: 'app-user-panel-profile',
  templateUrl: './user-panel-profile.component.html',
  styleUrls: ['./user-panel-profile.component.css']
})
export class UserPanelProfileComponent implements OnInit {

  currentUser: User;
  userID;

  constructor(private userService: UserService,
              private authService: AuthService,
              private alertService: AlertService) { }

  ngOnInit() {
    this.userID = this.authService.getId();
    this.userService.getUser(this.userID).subscribe(data => { this.currentUser = data; },
      error => { this.alertService.error('Failed to get information about user', alertLen.Medium); });
  }

}

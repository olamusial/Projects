import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { MessagesService } from '../services/messages.service';
import { async } from 'q';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  private newMessageForm: FormGroup;
  private submitted = false;
  private messageText: string;
  private userID;
  private messages: Array<string>;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private messagesService: MessagesService) { }

  ngOnInit() {
    this.newMessageForm = this.formBuilder.group({
      message: ['', [Validators.required, Validators.maxLength(200)]]
    });

    this.userID = this.authService.getId();
    this.displayMessages();
  }

  displayMessages() {
    this.messagesService.getMessages(this.userID).subscribe(data => {
      this.messages = data;
    });
  }

  get f() { return this.newMessageForm.controls; }

  onSend() {
    this.submitted = true;

    if (this.newMessageForm.valid) {
      this.messageText = 'User: ' + this.newMessageForm.value.message;
      this.messagesService.sendMessage(this.userID, this.messageText).subscribe(
        () => {
          this.displayMessages();
          this.newMessageForm.reset();
          this.submitted = false;
        },
        () => {
          this.displayMessages();
          this.newMessageForm.reset();
          this.submitted = false;
        }
      );
    }
  }
}

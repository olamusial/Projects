import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { BookService } from 'src/app/services/book.service';
import { AlertService, alertLen } from 'src/app/services/alert.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-my-books-payment',
  templateUrl: './my-books-payment.component.html',
  styleUrls: ['./my-books-payment.component.css']
})
export class MyBooksPaymentComponent implements OnInit {

  paymentForm: FormGroup;
  private showWindow = true;
  @Input() penaltyID;
  @Output() confirmationSignal = new EventEmitter<boolean>();
  constructor(private bookService: BookService,
              private alertService: AlertService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.paymentForm = this.formBuilder.group({
      title: ['', [Validators.required]]
    });
  }

  clickConfirmButton() {

    this.bookService.payPenalty(this.penaltyID, this.paymentForm.value.title).subscribe(
      data => { },
      error => {
        this.alertService.error('Failed to confirm bank transfer title!', alertLen.Short);
      }

    );
    this.confirmationSignal.emit(false);
    console.log(this.paymentForm.value.title);

  }

  clickCancelButton() {
    this.confirmationSignal.emit(false);
  }
}

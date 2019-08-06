import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Book } from 'src/app/shared/models/book';
import { BookService } from 'src/app/services/book.service';
import { first } from 'rxjs/operators';
import { alertLen, AlertService } from 'src/app/services/alert.service';

@Component({
  selector: 'app-browse-books-confirm-res',
  templateUrl: './browse-books-confirm-res.component.html',
  styleUrls: ['./browse-books-confirm-res.component.css']
})
export class BrowseBooksConfirmResComponent implements OnInit {

  @Input() book: Book;
  @Output() confirmationSignal = new EventEmitter<boolean>();


  constructor(private bookService: BookService, private alertService: AlertService) { }

  ngOnInit() {
  }

  bookToArray(): Array<string> {
    return JSON.stringify(this.book).split(',');
  }

  clickConfirmButton() {
    this.bookService.tryHold(this.book.id).pipe(first()).subscribe(
        data => {
          this.bookService.hold(this.book.id, data).pipe(first()).subscribe(
            holdData => {
              this.confirmationSignal.emit(true);
            },
            error => {
              this.alertService.error('Failed to complete the task!', alertLen.Short);
              this.confirmationSignal.emit(false);
            });
        },
        error => {
          if (error.error !== '') {
            this.alertService.error(`Error: ${error.error}`, alertLen.Short);
          } else {
            this.alertService.error('Failed to complete the task!', alertLen.Short);
          }
          this.confirmationSignal.emit(false);
        });
  }

  clickCancelButton() {
    this.confirmationSignal.emit(false);
  }
}

import { Component, OnInit, ViewChildren, QueryList, ElementRef, ChangeDetectorRef, AfterViewChecked } from '@angular/core';
import { isUndefined } from 'util';
import { HoldModel } from '../shared/models/hold-model';
import { AlertService, alertLen } from '../services/alert.service';
import { BookService } from '../services/book.service';
import { UserService } from '../services/user.service';
import { first } from 'rxjs/operators';
import { AuthService } from '../services/auth.service';
import { BookTable } from '../shared/models/book-table';
import { OrderModel } from '../shared/models/order-model';
import { PastOrderModel } from '../shared/models/past-order-model';
import { PenaltyModel } from '../shared/models/penalty-model';



@Component({
  selector: 'app-my-books',
  templateUrl: './my-books.component.html',
  styleUrls: ['./my-books.component.css']
})
export class MyBooksComponent implements OnInit, AfterViewChecked {

  private hoveredTableRow: number;
  private selectedTableRow: number;
  private selectedTableName: string;
  private showPayment = false;
  private penaltyId;
  private closeWindow = false;

  private tables: Array<BookTable> = [
    new BookTable(
      'holds',
      new Map<string, boolean>([
        ['Title', true],
        ['Hold Id', true],
        ['Place in queue', true],
        ['Hold date', true]]),
      new Array<HoldModel>(),
      false),
    new BookTable(
      'orders',
      new Map<string, boolean>([
        ['Title', true],
        ['Id', true],
        ['Date', true],
        ['Return', true],
        ['Renewals', true]]),
      new Array<OrderModel>(),
      false),
    new BookTable(
      'pastOrders',
      new Map<string, boolean>([
        ['Title', true],
        ['Id', true],
        ['Date', true],
        ['Checkout', true],
        ['Penalty', true]]),
      new Array<PastOrderModel>(),
      false)
  ];

  @ViewChildren('holdsTableEntry')
  holdsTableEntries: QueryList<ElementRef>;
  @ViewChildren('ordersTableEntry')
  ordersTableEntries: QueryList<ElementRef>;
  @ViewChildren('pastOrdersTableEntry')
  pastOrdersTableEntries: QueryList<ElementRef>;

  constructor(
    private cd: ChangeDetectorRef,
    private alertService: AlertService,
    private bookService: BookService,
    private authService: AuthService,
    private userService: UserService) { }

  ngOnInit() {
    this.loadTables('holds', 'orders', 'pastOrders');
  }

  loadTables(...tableNames: string[]) {
    tableNames.forEach(name => {
      switch (name) {
        case 'holds':
          this.userService.getHolds(this.authService.getId()).pipe(first()).subscribe(
            data => {
              this.tables.find(x => x.name === name).data = data;
            },
            x => { this.alertService.error('Couldn\'t connect to the server!', alertLen.Medium); });
          break;
        case 'orders':
          this.userService.getOrders(this.authService.getId()).pipe(first()).subscribe(
            data => {
              this.tables.find(x => x.name === name).data = data;
              this.tables.find(x => x.name === name).data.forEach(book => {
                if (isUndefined(book.bookName)) {
                  this.bookService.get(book.bookId).pipe(first()).subscribe(bookInfo => {
                    book.bookName = bookInfo.title;
                  });
                }
              });
            },
            x => { this.alertService.error('Couldn\'t connect to the server!', alertLen.Medium); });
          break;
        case 'pastOrders':
          this.userService.getPastOrders(this.authService.getId()).pipe(first()).subscribe(
            data => {
              this.tables.find(x => x.name === name).data = data;
              this.tables.find(x => x.name === name).data.forEach(book => {
                if (isUndefined(book.bookName)) {
                  this.bookService.get(book.bookId).pipe(first()).subscribe(bookInfo => {
                    book.bookName = bookInfo.title;
                  });
                }
                if (book.isPenaltyPaid === false) {
                    book.isPenaltyPaid = book.penalty.isPaid;
                  }
              });
            },
            x => { this.alertService.error('Couldn\'t connect to the server!', alertLen.Medium); });
          break;
      }
    });
  }


  ngAfterViewChecked() {
    this.cd.detectChanges();
  }

  highlightTableEntry(didHover: boolean) {
    if (this.selectedTableRow !== this.hoveredTableRow) {
      this.tables.forEach(table => {
        if (table.isSelected) {
          let entriesArray;
          switch (table.name) {
            case 'holds':
              entriesArray = this.holdsTableEntries.toArray();
              break;
            case 'orders':
              entriesArray = this.ordersTableEntries.toArray();
              break;
            case 'pastOrders':
              entriesArray = this.pastOrdersTableEntries.toArray();
              break;
          }
          if (didHover) {
            entriesArray[this.hoveredTableRow].nativeElement.setAttribute('style', 'background-color:#dddddd');
          } else {
            entriesArray[this.hoveredTableRow].nativeElement.setAttribute('style', 'background-color:var(--theme-color-lightgray1)');
          }
        }
      });
    }
  }

  selectTableEntry() {
    this.tables.forEach(table => {
      if (table.isSelected) {
        let entriesArray;
        switch (table.name) {
          case 'holds':
            entriesArray = this.holdsTableEntries.toArray();
            break;
          case 'orders':
            entriesArray = this.ordersTableEntries.toArray();
            break;
          case 'pastOrders':
            entriesArray = this.pastOrdersTableEntries.toArray();
            break;
        }
        if (isUndefined(this.selectedTableName) || this.selectedTableName === table.name) {
          if (!isUndefined(entriesArray[this.selectedTableRow])) {
            entriesArray[this.selectedTableRow].nativeElement.setAttribute('style', 'background-color:var(--theme-color-lightgray1)');
          }
          if (this.selectedTableRow !== this.hoveredTableRow) {
            this.selectedTableRow = this.hoveredTableRow;
            entriesArray[this.selectedTableRow].nativeElement.setAttribute('style', 'background-color:var(--theme-color-yellow)');
            this.selectedTableName = table.name;
          } else {
            this.selectedTableRow = undefined;
            this.selectedTableName = undefined;
          }
        } else {
          let selectedEntryArray;
          switch (this.selectedTableName) {
            case 'holds':
              selectedEntryArray = this.holdsTableEntries.toArray();
              break;
            case 'orders':
              selectedEntryArray = this.ordersTableEntries.toArray();
              break;
            case 'pastOrders':
              selectedEntryArray = this.pastOrdersTableEntries.toArray();
              break;
          }
          selectedEntryArray[this.selectedTableRow].nativeElement.setAttribute('style', 'background-color:var(--theme-color-lightgray1)');
          this.selectedTableRow = this.hoveredTableRow;
          entriesArray[this.selectedTableRow].nativeElement.setAttribute('style', 'background-color:var(--theme-color-yellow)');
          this.selectedTableName = table.name;
        }
      }
    });
  }

  resetSelection() {
    this.selectedTableRow = undefined;
    this.selectedTableName = undefined;
  }

  removeHold(holdIdx) {
    this.bookService.removeHold(this.tables.find(x => x.name === 'holds').data[holdIdx].id).pipe(first()).subscribe(
      data => {
        this.alertService.success('Success!', alertLen.Short);
        this.resetSelection();
        this.loadTables('holds');
      },
      error => {
        this.alertService.error('Couldn\'t connect to the server!', alertLen.Medium);
      });
  }

  payPenalty(holdIdx) {
    const penalty = this.tables.find(x => x.name === 'pastOrders').data[holdIdx].penalty;
    if (penalty === null || penalty.isPaid === true) {
      this.closeWindow = true;
      setTimeout(() => {
        this.showPayment = false;
        this.closeWindow = false;
        this.loadTables('pastOrders');
        this.alertService.success('There is no penalty to be paid', alertLen.Medium);
      }, 200);
      this.resetSelection();
      this.showPayment = false;
    } else {
      this.penaltyId = this.tables.find(x => x.name === 'pastOrders').data[holdIdx].penalty.id;
      this.showPayment = true;
    }
  }

  prolongate(holdIdx) {
    this.bookService.prolongate(this.tables.find(x => x.name === 'orders').data[holdIdx].id).subscribe(
      data => {
        this.alertService.success('Success!', alertLen.Medium);
        this.resetSelection();
        this.loadTables('orders');
      },
      () => {
        this.alertService.error('You reached maximum amount of renewals', alertLen.Medium);
      }
    );
  }

  updateConfirmationSignal($event) {
    this.closeWindow = true;
    setTimeout(() => {
      this.showPayment = false;
      this.closeWindow = false;
      if ($event) {
        this.alertService.success('Success!', alertLen.Short);
      }
    }, 200);
    this.resetSelection();
  }

}

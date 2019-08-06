import {
  Component, OnInit, AfterViewChecked,
  ChangeDetectorRef, ChangeDetectionStrategy,
  ViewChildren, ElementRef,
  QueryList,
  Output
} from '@angular/core';
import { Book } from '../shared/models/book';
import { isUndefined } from 'util';
import { BookFilterPipe } from '../shared/pipes/book-filter.pipe';
import { AlertService, alertLen } from '../services/alert.service';
import { BookService } from '../services/book.service';
import { first } from 'rxjs/operators';
import { PageEvent } from '@angular/material';
import { Page } from '../shared/models/page';
import { Filter } from '../shared/models/filter';
import { AuthService } from '../services/auth.service';

@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  selector: 'app-browse-books',
  templateUrl: './browse-books.component.html',
  styleUrls: ['./browse-books.component.css']
})
export class BrowseBooksComponent implements OnInit, AfterViewChecked {


  private tableColumns;
  private tableColumnsKeys: Array<string>;
  private hoveredTableRow: number;
  private selectedTableRow: number;
  private searchText: string;
  private selectedSortMode = { prop: 'title', desc: false };
  private sortModes: Array<{ showName: string, className: string }> = [
    { showName: 'Title', className: 'title' },
    { showName: 'Author', className: 'author' },
    { showName: 'Publisher', className: 'publisher' },
    { showName: 'Genres', className: 'genres' },
    { showName: 'Release Date', className: 'releaseDate' },
    { showName: 'ISBN number', className: 'isbn' },
    { showName: 'Available copies', className: 'availableCopies' },
    { showName: 'Total copies', className: 'totalCopies' }
  ];

  showConfirmResWindow = false;
  closeWindow = false;
  @Output() private showSearchWindow = true;
  @Output() private filterParams: Filter;
  @Output() private hasNotifications: boolean;

  private totalBooksCount: number;
  private pageSize = 15;
  private pageIndex = 0;

  @ViewChildren('tableEntry')
  tableEntries: QueryList<ElementRef>;

  private books: Array<Book> = [];
  pageEvent: PageEvent;

  constructor(
    private cd: ChangeDetectorRef,
    private alertService: AlertService,
    private bookService: BookService,
    private authService: AuthService) {
    this.tableColumns = new Map<string, boolean>([
      ['Photo', true],
      ['Title', true],
      ['Author', true],
      ['Publisher', false],
      ['Genres', false],
      ['Release Date', false],
      ['ISBN number', false],
      ['Available copies', true]
    ]);

    this.tableColumnsKeys = Array.from(this.tableColumns.keys());
  }

  ngOnInit() { }

  getBooksForCurrentPage() {

    this.bookService.getListForPage(
      new Page(this.pageSize, this.pageIndex, this.selectedSortMode.prop, this.selectedSortMode.desc),
      this.filterParams).pipe(first()).subscribe(
        data => {
          this.books = data.result;

          this.books.forEach(element => {
            this.bookService.getCopies(element.id).pipe(first()).subscribe(copies => {
              element.totalCopies = copies.length;
              element.availableCopies = copies.filter(x => x.isAvailable).length;
            });
          });

          this.totalBooksCount = data.totalCount;
          if (this.totalBooksCount === 0) {
            this.alertService.info('No matching results.', alertLen.Short);
          }
        },
        error => {
          this.alertService.error('Error occured!', alertLen.Medium);
        });
  }

  onPageChange(event) {
    this.pageIndex = event.pageIndex;
    this.getBooksForCurrentPage();

  }

  ngAfterViewChecked() {
    this.cd.detectChanges();
  }

  toggletableColumns(columnName: string) {

    if (this.tableColumns.has(columnName)) {
      if (this.tableColumns.get(columnName)) {
        this.tableColumns.set(columnName, false);
        if (columnName === 'Photo') {
          this.pageSize *= 2;
          if (this.pageIndex !== 0) {
            this.pageIndex -= 1;
          }
          this.getBooksForCurrentPage();
        }
      } else {
        this.tableColumns.set(columnName, true);
        if (columnName === 'Photo') {
          this.pageSize /= 2;
          if (this.pageIndex !== 0) {
            this.pageIndex += 1;
          }
          this.getBooksForCurrentPage();
        }
      }
    }
    this.tableColumnsKeys = Array.from(this.tableColumns.keys());
  }


  highlightTableEntry(didHover: boolean) {
    if (this.selectedTableRow !== this.hoveredTableRow) {
      if (didHover) {
        this.tableEntries
          .toArray()[this.hoveredTableRow].nativeElement.setAttribute('style', 'background-color:#dddddd');
      } else {
        this.tableEntries
          .toArray()[this.hoveredTableRow].nativeElement.setAttribute('style', 'background-color:var(--theme-color-lightgray1)');
      }
    }
  }

  selectTableEntry() {
    if (!isUndefined(this.tableEntries.toArray()[this.selectedTableRow])) {
      this.tableEntries
        .toArray()[this.selectedTableRow].nativeElement.setAttribute('style', 'background-color:var(--theme-color-lightgray1)');
    }
    if (this.selectedTableRow !== this.hoveredTableRow) {
      this.selectedTableRow = this.hoveredTableRow;
      this.tableEntries.toArray()[this.selectedTableRow].nativeElement.setAttribute('style', 'background-color:var(--theme-color-yellow)');
    } else {
      this.selectedTableRow = undefined;
    }
  }

  sortTable(target) {
    this.selectedSortMode = JSON.parse(target);
    this.getBooksForCurrentPage();
  }

  checkSearchBox(value) {
    if (value === '' || isUndefined(value)) {
      this.searchText = '';
    }
  }

  updateShowSearchWindow($event) {
    this.showSearchWindow = $event;
  }

  updateFilterParams($event) {
    this.filterParams = $event;
    this.getBooksForCurrentPage();
  }

  updateHasNotifications($event) {
    this.hasNotifications = $event;
  }

  resetSelection() {
    if (!isUndefined(this.tableEntries.toArray()[this.selectedTableRow])) {
      this.tableEntries
        .toArray()[this.selectedTableRow].nativeElement.setAttribute('style', 'background-color:var(--theme-color-lightgray1)');
    }
    this.selectedTableRow = undefined;
  }

  updateConfirmationSignal($event) {
    this.closeWindow = true;
    setTimeout(() => {
      this.showConfirmResWindow = false;
      this.closeWindow = false;
      if ($event) {
        this.alertService.success('Success!', alertLen.Short);
      }
    }, 200);
    this.resetSelection();
  }

  clickResButton(bookidx) {
    this.showConfirmResWindow = true;
  }
}

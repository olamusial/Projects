import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Filter } from 'src/app/shared/models/filter';

@Component({
  selector: 'app-browse-books-search',
  templateUrl: './browse-books-search.component.html',
  styleUrls: ['./browse-books-search.component.css']
})
export class BrowseBooksSearchComponent implements OnInit {

  searchForm: FormGroup;

  @Output() filterParams = new EventEmitter<Filter>();
  @Output() showSearchWindow = new EventEmitter<boolean>();

  private searchInProgress = false;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      title: [''],
      authorName: [''],
      authorSurname: [''],
      publisher: [''],
      isbn: [''],
      genres: ['']
    });
  }


  applySearch() {
    const val = this.searchForm.value;

    let genresArray = [];
    if (val.genres !== '') {
      genresArray = val.genres.split(/\s|,\s|,|\s,|\s,\s/);
      const tempArray = [];
      genresArray.forEach(element => {
        if (element !== '' && element !== ',' && element !== ' ' && element !== ' ') {
          tempArray.push(element);
        }
      });
      genresArray = tempArray;
    }
    this.filterParams.emit(new Filter(
      val.title,
      val.isbn,
      val.publisher,
      val.authorName,
      val.authorSurname,
      genresArray
    ));

    setTimeout( () => this.showSearchWindow.emit(false), 100);

  }

  setSearch() {
    this.searchInProgress = true;
  }
}

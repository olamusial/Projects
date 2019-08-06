import { Pipe, PipeTransform } from '@angular/core';
import { Book } from '../models/book';

@Pipe({
  name: 'bookFilter'
})
export class BookFilterPipe implements PipeTransform {

  transform(bookArr: Book[], searchText: string, tableColumns: Map<string, boolean>): Book[] {
    if (!bookArr) { return []; } else if (!searchText) { return bookArr; } else if (!tableColumns) { return bookArr; }

    searchText = searchText.toLowerCase();
    return bookArr.filter(book => {
      let accept = false;

      tableColumns.forEach((value: boolean, key: string) => {
        switch (key) {
          case 'Title':
          if (value && !accept) {
            accept = book.title.toLowerCase().includes(searchText);
          }
          break;
          case 'Author':
          if (value && !accept) {
            accept = book.author.toLowerCase().includes(searchText);
          }
          break;
          case 'Publisher':
          if (value && !accept) {
            accept = book.publisher.toLowerCase().includes(searchText);
          }
          break;
          /*case 'Genre':
          if (value && !accept) {
            book.genre.forEach(genre => {
              if (genre.toLowerCase().includes(searchText)) {
                accept = true;
              }
            });
          }
          break;*/
          case 'ISBN number':
          if (value && !accept) {
            accept = book.isbn.toLowerCase().includes(searchText);
          }
          break;
        }
      });
      return accept;
    });
  }
}

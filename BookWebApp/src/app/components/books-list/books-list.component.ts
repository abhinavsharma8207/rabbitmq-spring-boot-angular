import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

enum CheckBoxType { SEARCH_BY_TITLE , SEARCH_BY_AUTHOR, NONE };

@Component({
  selector: 'app-books-list',
  templateUrl: './books-list.component.html',
  styleUrls: ['./books-list.component.css']
})
export class BooksListComponent implements OnInit {

  books?: Book[];
  currentBook: Book = {};
  currentIndex = -1;
  title = '';
  check_box_type = CheckBoxType;
  currentlyChecked: CheckBoxType;
  

  constructor(private bookService: BookService) { }
  
  ngOnInit(): void {
    this.retrieveBooks();
  }

  retrieveBooks(): void {
    this.bookService.getAll()
      .subscribe(
        data => {
          this.books = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.retrieveBooks();
    this.currentBook = {};
    this.currentIndex = -1;
  }

  setActiveBook(book: Book, index: number): void {
    this.currentBook = book;
    this.currentIndex = index;
  }

  selectCheckBox(targetType: CheckBoxType) {
    // If the checkbox was already checked, clear the currentlyChecked variable
    if(this.currentlyChecked === targetType) {
      this.currentlyChecked = CheckBoxType.NONE;
      return;
    }
    this.currentlyChecked = targetType;
  }

  searchTitle(): void {
    this.currentBook = {};
    this.currentIndex = -1;
    if (this.currentlyChecked === CheckBoxType.SEARCH_BY_TITLE || this.currentlyChecked === CheckBoxType.NONE )
    {
    this.bookService.findByTitle(this.title)
      .subscribe(
        data => {
          this.books = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });

      }
      else if (this.currentlyChecked === CheckBoxType.SEARCH_BY_AUTHOR && this.title)
    {
    this.bookService.findByAuthor(this.title)
      .subscribe(
        data => {
          this.books = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });

      }

      else{
        this.refreshList();
      }

      

  }

}

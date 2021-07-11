import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../models/book.model';

const baseUrl = 'http://localhost:8080/api/books';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Book[]> {
    return this.http.get<Book[]>(baseUrl);
  }

  findByTitle(title: any): Observable<Book[]> {
    return this.http.get<Book[]>(`${baseUrl}?title=${title}`);
  }

  findByAuthor(author: any): Observable<Book[]> {
    return this.http.get<Book[]>(`${baseUrl}/author?author=${author}`);
  }



}

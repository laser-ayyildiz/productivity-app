import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';

const TODO_API = 'http://localhost:8080/todo-service/task';
const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };
@Injectable({
  providedIn: 'root'
})
export class TodoService {

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) { }

  getAll(pageSize: number = 10, pageNumber: number = 0): Observable<any> {
    return this.http.get(`${TODO_API}?pageNumber=${pageNumber}&pageSize=${pageSize}`, httpOptions);
  }
}

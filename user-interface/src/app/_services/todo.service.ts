import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';

const TASK_API = 'http://localhost:8080/todo-service/task';
const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };
@Injectable({
  providedIn: 'root'
})
export class TodoService {

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService) { }

  getAll(pageSize: number = 10, pageNumber: number = 0): Observable<any> {
    return this.http.get(`${TASK_API}?pageNumber=${pageNumber}&pageSize=${pageSize}`, httpOptions);
  }

  create(category: string, title: string, habitId: number, deadline: Date,
    description: string, priority: Number, color: string): Observable<any> {
    console.log("servsteyim")
    return this.http.post(TASK_API, {
      category, title, habitId, deadline, description, priority, color
    }, httpOptions);
  }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TodoService } from './todo.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {
  errorMessage: string = "";
  todos: any = {}
  page: number = 0;
  total: number = 0;
  newTodo: any = {
    category: "",
    title: "",
    habitId: 0,
    deadline: null,
    description: "",
    priority: 0,
    color: ""
  };

  constructor(private route: ActivatedRoute, private todoService: TodoService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.todos = this.fetchTodos();
  }

  onSubmit(): void {
    const { category, title, habitId, deadline, description, priority, color } = this.newTodo;
    this.todoService.create(category, title, habitId, deadline,
      description, priority, color).subscribe({
        next: data => {
          this.fetchTodos();
          this.toastr.success("New TODO created!");
        },
        error: err => {
          this.errorMessage = err.error.message;
        }
      });
  }

  fetchTodos(page: number = 0, pageSize: number = 10) {
    this.todoService.getAll(page, pageSize).subscribe({
      next: data => {
        this.todos = data.content;
        this.total = data.totalElements;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.toastr.error('We have a problem Houston!');
      }
    });
  }

  pageChangeEvent(event: number) {
    this.page = event;
    this.fetchTodos(this.page - 1, 10);
  }
}

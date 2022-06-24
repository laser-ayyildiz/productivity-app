import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TodoService } from '../_services/todo.service';


@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {
  errorMessage: string = "";

  todos: any = {}

  constructor(private route: ActivatedRoute, private todoService: TodoService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.todos = this.todoService.getAll().subscribe({
      next: data => {
        this.todos = data;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.toastr.error('Control your credentials!');
      }
    });;
  }
}

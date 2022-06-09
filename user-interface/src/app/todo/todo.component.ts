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
    this.todos = this.todoService.getAll().subscribe({
      next: data => {
        this.todos = data;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.toastr.error('We have a problem Houston!');
      }
    });
  }

  onSubmit(): void {
    const { category, title, habitId, deadline, description, priority, color } = this.newTodo;
    console.log("componentteyim")
    this.todoService.create(category, title, habitId, deadline,
      description, priority, color).subscribe({
        next: data => {
          console.log(data);
        },
        error: err => {
          this.errorMessage = err.error.message;
          this.toastr.error('Control your inputs!');
        }
      });
  }
}

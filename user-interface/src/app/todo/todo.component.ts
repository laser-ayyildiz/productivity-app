import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  id: Number = 0;
  constructor(private route: ActivatedRoute) { 
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
  }

  tempFunc = () => {
    return "abc"
  }

}

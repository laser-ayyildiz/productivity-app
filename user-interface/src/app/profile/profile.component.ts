import { Component, OnInit } from '@angular/core';
import { Profile } from './profile';
import { ProfileService } from './profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: Profile = {
    name: "",
    surname: "",
    username: "",
    email: ""
  }

  constructor(private profileService: ProfileService) { }

  ngOnInit(): void {
    console.log("aaaaaa")
    this.profileService.get().subscribe({
      next: data => {
        this.user = data;
        console.log(this.user);
      },
      error: err => {
        console.log(err);
      }
    });
  }
}

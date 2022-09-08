import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {
    name: null,
    surname: null,
    username: null,
    email: null,
    password: null,
    rePassword: null
  };
  isSuccessful: boolean = false;
  isRegisterFailed: boolean = false;
  errorMessage = '';
  constructor(private authService: AuthService, private router: Router, private toastr: ToastrService) { }

  ngOnInit(): void { }

  onSubmit(): void {
    const { name, surname, username, email, password, rePassword } = this.form;
    this.authService.register(name, surname, username, email, password, rePassword).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isRegisterFailed = false;
        this.toastr.success('Registered successfully!');
        this.router.navigate(['/login']);
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isRegisterFailed = true;
        this.toastr.error('Control your credentials!');
      }
    });
  }
}

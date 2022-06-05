import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from '../user';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null,
  };
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router, private tokenStorage: TokenStorageService, private toastr: ToastrService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.router.navigate(["/dashboard"]);
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;
    this.authService.login(username, password).subscribe({
      next: data => {
        this.tokenStorage.saveToken(data.jwtToken);
        this.tokenStorage.saveUser(new User(data.id, data.username));
        this.toastr.success('Successfully logged in!');
        this.router.navigate(['/dashboard']).then(() => {
          this.reloadPage();
        });
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.toastr.error('Control your credentials!');
      }
    });
  }

  reloadPage(): void {
    window.location.reload();
  }
}

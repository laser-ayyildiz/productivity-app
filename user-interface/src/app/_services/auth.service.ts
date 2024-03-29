import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8080/auth/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username,
      password
    }, httpOptions);
  }

  register(name: string, surname: string, username: string, email: string, password: string, rePassword: string):
    Observable<any> {
    if (password !== rePassword) {
      throw new Error("password not equal rePassword");
    }
    return this.http.post(AUTH_API + 'signup', {
      name,
      surname,
      username,
      email,
      password
    }, httpOptions);
  }
}
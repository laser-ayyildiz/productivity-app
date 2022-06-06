import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../_services/token-storage.service';

const USER_API = 'http://localhost:8080/user-service/user';
const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
    providedIn: 'root'
})
export class ProfileService {

    constructor(private http: HttpClient, private tokenStorage: TokenStorageService) { }

    get(): Observable<any> {
        return this.http.get(`${USER_API}/${this.tokenStorage.getUser().id}`, httpOptions);
    }
}
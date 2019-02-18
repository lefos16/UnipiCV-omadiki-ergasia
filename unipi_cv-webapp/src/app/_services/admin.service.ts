import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../enviroments/enviroment';
import {RequestOptions, Request, RequestMethod} from '@angular/http';
import { Student, User } from '@/_models';
import { stringify } from '@angular/compiler/src/util';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AdminService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<User[]>(environment.baseUrl + '/getUsers');
    }

    saveStudent(user:User){
       return this.http.post<Student>(environment.baseUrl + "/createUser", user);
    }

    deleteStudent(username:string){
    
        return this.http.delete<any>(environment.baseUrl + '/deleteUser/' + username)

    }

    search(student:Student){
        return this.http.post<Student[]>(environment.baseUrl + '/search',student);
    }
}
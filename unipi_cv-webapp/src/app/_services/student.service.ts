import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../enviroments/enviroment';
;
import { Student, Keyword } from '@/_models';
import { Language } from '@/_models';
import { Platforms } from '@/_models';
import { map } from 'rxjs/operators';
import { RequestOptions,Headers, ResponseContentType } from '@angular/http';

import { Observable } from 'rxjs';


// const HttpUploadOptions = {
//     headers: new HttpHeaders({ "Content-Type": "multipart/form-data" })
//   }
@Injectable({ providedIn: 'root' })
export class StudentService {
    
    constructor(private http: HttpClient) { }
    getLanguage(){
        return this.http.get<Language[]>(environment.baseUrl + '/languages');
    }
    getKeyword(){
        return this.http.get<Keyword[]>(environment.baseUrl + '/keywords');
    }
    getPlatform(){
        return this.http.get<Platforms[]>(environment.baseUrl + '/platforms');
    }
    getProfile(){
        return this.http.get<Student>(environment.baseUrl + '/getProfile');
    }
    setProfile(name: string , surname:string , thesis:string , grade:string , email:string , phone:string , platforms:Platforms[],languages:Language[] , keywords:Keyword[]){
        let student:Student;
        student = new Student();
        let headers = new HttpHeaders().set('Content-Type', 'application/json')
        student.name=name;
        student.surname=surname;
        student.thesis=thesis;
        student.grade=grade;
        student.email=email;
        student.phone=phone;
        student.platformEntities=platforms;
        student.languageEntities=languages;
        student.keywordEntities=keywords;
        return this.http.post<any>(environment.baseUrl + '/setProfile',student,{headers:headers}).pipe(map(student=>{return student}));
    }
    uploadFile(file:FormData){      
        return this.http.post<any>(environment.baseUrl + '/file/cv',file);        
}

    uploadAvatar(file:FormData){
        return this.http.post<any>(environment.baseUrl + '/file/avatar',file);
    }

    getFile(file:string):Observable<Blob>{
        let params = new HttpParams().set('file', file);
       
        return this.http.get(environment.baseUrl + '/getFile',{ responseType: 'blob',params });
    }
   
}
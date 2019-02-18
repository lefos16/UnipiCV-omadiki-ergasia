import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TableDataSource, ValidatorService } from 'angular4-material-table';
import { TableValidatorService } from '@/_services';
import { Student, User } from '@/_models';
import { AdminService } from '@/_services';
import { MatTableDataSource } from '@angular/material';

@Component({
    providers: [
        { provide: ValidatorService, useClass: TableValidatorService }
    ],
    styleUrls: ['admin.component.scss'],
    templateUrl: 'admin.component.html',
})
export class AdminComponent implements OnInit {
    users: User[] = [];
    displayedColumns: string[] = ['username', 'password' , 'actionsColumn'];
    dataSource: TableDataSource<User>;
    constructor(private adminService: AdminService,
        private studentValidator: ValidatorService
    ) { }

    @Output() userListChange = new EventEmitter<User[]>();

    ngOnInit() {
        this.getUsers();
    
    }
    getUsers(){
        this.adminService.getAll().pipe().subscribe(users => {
            this.users = users;
            this.dataSource = new TableDataSource<any>(this.users, User, this.studentValidator);

            this.dataSource.datasourceSubject.subscribe(userList => this.userListChange.emit(userList));
        });
    }

    saveStudent(id:number, username:string, password:string){
        if(username!=="" && password!==""){
        console.log(this.users[id]);
        let updatedUser : User ;
        updatedUser = new User();

        if(id < this.users.length){
            updatedUser = this.users[id];
        }
        updatedUser.username = username;
        updatedUser.password = password;
        this.adminService.saveStudent(updatedUser).subscribe((response) => {
            console.log("update or create :P");
            this.getUsers(); 
          },
          error => {
            this.getUsers(); 

          });
        }
    }


    delete(row,i) {
        if(i < this.users.length){
      this.adminService.deleteStudent(row).pipe().subscribe((response) => {
        console.log("deleted");
        this.getUsers(); 
      });
    }
        }

}

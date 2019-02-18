import { Component, Output, EventEmitter } from '@angular/core';

import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { TableDataSource, ValidatorService } from 'angular4-material-table';
import { User, Role, Language, Platforms, Student, Keyword } from '@/_models';
import { AdminService, StudentService  } from '@/_services';
import { Router } from '@angular/router';

@Component({
    styleUrls: ['search.component.css'],
    templateUrl: 'search.component.html'
 })
export class SearchComponent {
    customCollapsedHeight: string = '15px';
    customExpandedHeight: string = '15px';
    student:Student;
    students:Student[]=[];
    languages:Language[] = [];
    platforms: Platforms[] = [];
    keywords: Keyword[] = [];
    registerForm: FormGroup;
    selectedLanguage: Language[] = [];
    selectedKeyword: Keyword[] = [];
    selectedPlatform: Platforms[] = [];
    dropdownSettingsLang = {};
    dropdownSettingsKey = {};
    // dorpdownStudentLang = {};
    dropdownSettingsPlat = {};
    displayedColumns: string[] = ['name', 'surname','email','phone','grade','thesis','cv','languageEntities', 'platformEntities', 'keywordEntities' ];
    dataSource: TableDataSource<Student>;
    constructor(
        private formBuilder: FormBuilder,
        private adminService: AdminService,
        private studentService: StudentService
    ) { }

    @Output() studentListChange = new EventEmitter<Student[]>();

    ngOnInit() {
        this.studentService.getKeyword().pipe().subscribe(keywords =>{
            this.keywords = keywords;
        })

        this.studentService.getLanguage().pipe().subscribe(language =>{
            this.languages=language;});

        this.studentService.getPlatform().pipe().subscribe(platform =>{
                this.platforms=platform;});

                this.registerForm = this.formBuilder.group({
                    grade: ['', ],
                    language: [[],],
                    platform: [[], ],
                    keyword: [[],]
                });
                this.dropdownSettingsLang = {
                        singleSelection: false,
                        idField: 'idLanguages',
                        textField: 'name',
                        selectAllText: 'Select All',
                        unSelectAllText: 'UnSelect All',
                        allowSearchFilter: true
                        
                };
                this.dropdownSettingsPlat = {
                    singleSelection: false,
                    idField: 'idPlatforms',
                    textField: 'name',
                    selectAllText: 'Select All',
                    unSelectAllText: 'UnSelect All',
                    allowSearchFilter: true
                    
            };
            this.dropdownSettingsKey = {
                singleSelection: false,
                idField: 'idKeyword',
                textField: 'name',
                selectAllText: 'Select All',
                unSelectAllText: 'UnSelect All',
                allowSearchFilter: true
                
        };
    }
    reset(){
        this.registerForm.patchValue({grade:''});
        this.selectedLanguage=[];
        this.selectedPlatform=[];
        this.selectedKeyword=[];
        this.students=[];
        this.dataSource = new TableDataSource<any>(this.students, Student);
        this.dataSource.datasourceSubject.subscribe(studentList => this.studentListChange.emit(studentList));
    }

    getCv(cv:string){
         this.studentService.getFile(cv).subscribe(res=> {
            let url=window.URL.createObjectURL(res);
            var link=document.createElement('a');
            link.setAttribute('href',url);
            link.setAttribute('download',cv);
            document.body.appendChild(link);
            link.click();});
         
    }
     
    onSubmit(){

        this.student=new Student();
        this.student.languageEntities=this.selectedLanguage;
        this.student.platformEntities=this.selectedPlatform;
        this.student.keywordEntities=this.selectedKeyword;
        this.student.grade=this.registerForm.value.grade;
        this.adminService.search(this.student).pipe().subscribe(students =>{
             this.students=students;
            this.dataSource = new TableDataSource<any>(this.students, Student);
             this.dataSource.datasourceSubject.subscribe(studentList => this.studentListChange.emit(studentList));
           
            });
        

    }
}
    
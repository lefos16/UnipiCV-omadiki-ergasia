import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { User, Role, Student, Language, Platforms, Keyword } from '@/_models';
import{ AuthenticationService, StudentService } from '../_services';
import { environment } from '../../enviroments/enviroment';
import { getCurrentView } from '@angular/core/src/render3';
import { DomSanitizer } from '@angular/platform-browser';


@Component({templateUrl: 'profile.component.html'})
export class ProfileComponent implements OnInit {
    registerForm: FormGroup;
    loading = false;
    submitted = false;
    currentUser: User;
    student : Student ;
    name : FormControl;
    keywords: Keyword[] = [];
    selectedKeyword: Keyword[] = [];
    languages: Language[] = [];
    selectedLanguage: Language[] = [];
    platforms: Platforms[] = [];
    selectedPlatform: Platforms[] = [];
    selectedFile: File = null;
    selectedImg: any;
    cvLink: string;
    dropdownSettingsLang = {};
    dropdownSettingsPlat = {};
    dropdownSettingsKey= {};
    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private authenticationService: AuthenticationService,
        private studentService: StudentService,
        private sanitizer: DomSanitizer
        ) 
        { this.currentUser = this.authenticationService.currentUserValue; }
        
    ngOnInit() {
        if(this.currentUser.role === Role.Admin)
        this.router.navigate(["/admin"]);

        this.studentService.getKeyword().pipe().subscribe(keywords =>{
            this.keywords=keywords;
        })


        this.studentService.getLanguage().pipe().subscribe(language =>{
             this.languages=language;
        });

        this.studentService.getPlatform().pipe().subscribe(platform =>{
            this.platforms=platform;
        });

        this.studentService.getProfile().pipe().subscribe(student => {
            this.student = student;
             this.registerForm.controls['name'].setValue(this.student.name);
             this.registerForm.controls['surname'].setValue(this.student.surname);
             this.registerForm.controls['thesis'].setValue(this.student.thesis);
             this.registerForm.controls['grade'].setValue(this.student.grade);
             this.registerForm.controls['email'].setValue(this.student.email);
             this.registerForm.controls['phone'].setValue(this.student.phone);
             this.selectedLanguage = this.student.languageEntities;
             this.selectedPlatform = this.student.platformEntities;
             this.selectedKeyword = this.student.keywordEntities;
             this.getAv();
        });                                                           
        
        this.registerForm = this.formBuilder.group({
            name: ['', Validators.required],
            surname: ['', Validators.required],
            thesis: ['', Validators.required],
            grade: ['', Validators.required],
            email: ['', Validators.required],
            phone: ['', Validators.required],
            language: [[], Validators.required],
            platform: [[], Validators.required],
            keyword: [[],Validators.required],
            cv: [null, Validators.required]
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

      onAvChanged(event){
        this.selectedFile = <File>event.target.files[0]
        const fb = new FormData();  
        fb.append('file', this.selectedFile);
        console.log(this.selectedFile.name);
        this.studentService.uploadAvatar(fb).subscribe(res => {
            console.log(res)});


      }
     
    onFileChanged(event) {
        this.selectedFile = <File>event.target.files[0]
        const fb = new FormData();  
        fb.append('file', this.selectedFile);
        console.log(this.selectedFile.name);
        this.studentService.uploadFile(fb).subscribe(res =>{ 
            console.log(res)});
        
      }
    getAv(){
        if(this.student.avatar!=null){
            this.studentService.getFile(this.student.avatar).subscribe(res =>{
                let urlCreator = window.URL;
                this.selectedImg = this.sanitizer.bypassSecurityTrustUrl(
            urlCreator.createObjectURL(res));
            });
        }
    }
    getCv(){
        if(this.student.cv!=null){
         this.studentService.getFile(this.student.cv).subscribe(res=> {
            let url=window.URL.createObjectURL(res);
            var link=document.createElement('a');
            link.setAttribute('href',url);
            link.setAttribute('download',this.student.cv);
            document.body.appendChild(link);
            link.click();});
         }     
    }

   
    get f() { return this.registerForm.controls; }

    onSubmit() {
        this.submitted = true;
       
        
        if (this.registerForm.invalid) {
            return;
        }

        this.loading = true;
        
            

        this.studentService.setProfile(this.f.name.value , this.f.surname.value ,
             this.f.thesis.value , this.f.grade.value , this.f.email.value , this.f.phone.value,this.selectedPlatform,this.selectedLanguage,this.selectedKeyword).pipe().subscribe(student =>{
                 if(student.name!=null){
                    this.loading= false;
                 }
             });
            window.location.reload();
    }
    
}

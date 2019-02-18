import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatDialogModule } from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
    MatAutocompleteModule,
  MatBadgeModule,
  MatBottomSheetModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDividerModule,
  MatGridListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatStepperModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatTreeModule,
     MatInputModule, MatOptionModule, MatSelectModule,MatListModule,
     MatButtonModule,MatIconModule,MatFormFieldModule, MatTableModule } from "@angular/material";
import {MatExpansionModule} from '@angular/material/expansion';
import { HttpConfigInterceptor } from './interceptor/http.interceptor';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import { routing } from './app.routing';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { ErrorDialogService } from './error-dialog/errordialog.service';
import { SearchComponent } from './search';
import { AdminComponent } from './admin';
import { LoginComponent } from './login';
import { ProfileComponent } from './profile';

import { ErrorDialogComponent } from './error-dialog/errordialog.component';

import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import 'hammerjs';

//import { MaterialModule } from './material.module';

@NgModule({
    imports: [
        
        MatFormFieldModule,
        MatAutocompleteModule,
  MatBadgeModule,
  MatBottomSheetModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatDividerModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatStepperModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatTreeModule,
        MatExpansionModule,
        MatListModule,
        MatSelectModule,
        MatOptionModule,
        FormsModule,
        MatIconModule,
        MatButtonModule,
        CommonModule,
        BrowserModule,
        ReactiveFormsModule,
        HttpClientModule,
        routing,
        MatDialogModule,
        BrowserAnimationsModule,
        NgMultiSelectDropDownModule.forRoot(),
        MatInputModule,
        MatTableModule       ,
        NoopAnimationsModule
    ],
    declarations: [
        AppComponent,
        SearchComponent,
        AdminComponent,
        LoginComponent,
        ProfileComponent,
        ErrorDialogComponent    ],
    providers: [
        ErrorDialogService,
        { provide: HTTP_INTERCEPTORS, useClass: HttpConfigInterceptor, multi: true }

        
    ],
    entryComponents: [ErrorDialogComponent],
    bootstrap: [AppComponent]})

export class AppModule { }
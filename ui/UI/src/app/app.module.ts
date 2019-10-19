import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { ReviewCardComponent } from './review-card/review-card.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ReviewFormComponent } from './review-form/review-form.component';
import { HospitalDisplayComponent } from './hospital-display/hospital-display.component';
import { HttpService } from './services/httpservice.service';
import { ReviewsByHospitalService } from './services/reviews-by-hospital.service';
import { ReviewByHospitalComponent } from './review-by-hospital/review-by-hospital.component';
import { ResetpasswordComponent } from './resetpassword/resetpassword.component';
import { UpdatePasswordComponent } from './update-password/update-password.component';

@NgModule({
  declarations: [
    AppComponent,
    ReviewCardComponent,
    RegisterFormComponent,
    LoginFormComponent,
    NavbarComponent,
    HomeComponent,
    ReviewFormComponent,
    HospitalDisplayComponent,
    ReviewByHospitalComponent,
    ResetpasswordComponent,
    UpdatePasswordComponent,
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
      },
      {
        path: 'home',
        component: HomeComponent
      },
      {
        path: 'hospitals',
        component: HospitalDisplayComponent
      },
      {
        path: 'reviews',
        component: ReviewCardComponent
      },
      {
        path: 'register',
        component: RegisterFormComponent
      },
      {
        path: 'login',
        component: LoginFormComponent
      },
      {
        path: 'reviewForm/:hospital',
        component: ReviewFormComponent
      },
      {
        path: 'findByHospital/:hospital',
        component: ReviewByHospitalComponent
      },
      {
        path: 'resetpassword',
        component: ResetpasswordComponent
      },
      {
        path: 'updatepword',
        component: UpdatePasswordComponent
      }
    ]),
  ],
  providers: [
    HttpService,
    ReviewsByHospitalService
  ],
  bootstrap: [
    AppComponent],
  entryComponents: [
  ]
})
export class AppModule { }

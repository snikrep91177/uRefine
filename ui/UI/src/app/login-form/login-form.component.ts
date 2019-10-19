import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { LoginService } from '../services/login.service';

@Component({
// tslint:disable-next-line: component-selector
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  myForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private loginServ: LoginService
  ) {
    this.createForm();
    }

  createForm(){
     this.myForm = this.formBuilder.group({
      username: '',
      password: ''
     });
  }

  private login() {
    // console.log(this.myForm.value);
    this.loginServ.doLogin(this.myForm.value);
  }

  ngOnInit() {
  }

}

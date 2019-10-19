import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../services/login.service';

@Component({
// tslint:disable-next-line: component-selector
  selector: 'register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {

  myForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private loginServ: LoginService
  ) {
    this.createForm();
    }

  createForm() {
    this.myForm = this.formBuilder.group({
      username: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      resetPending: false
    });
  }

  submitForm() {
    // console.log(this.myForm.value);
    this.loginServ.doRegister(this.myForm.value);
  }

}

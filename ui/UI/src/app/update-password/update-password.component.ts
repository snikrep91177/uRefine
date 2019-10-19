import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent implements OnInit {

  myForm: FormGroup;
  error: boolean;

  constructor(private formBuilder: FormBuilder, private loginServ: LoginService) {
    this.createForm();
    this.error = false;
   }

  ngOnInit() {
  }

  createForm() {
    this.myForm = this.formBuilder.group({
      password: ['', Validators.required],
      password2: ['', Validators.required]
    });
  }

  submitForm() {
    console.log(this.myForm.value);
    if (this.myForm.value.password === this.myForm.value.password2) {
      this.loginServ.updatePassword(JSON.parse(localStorage.getItem('currentUser')), this.myForm.value.password);
    } else {
      this.error = true;
    }
  }

}

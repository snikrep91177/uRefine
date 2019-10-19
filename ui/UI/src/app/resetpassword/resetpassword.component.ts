import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css']
})
export class ResetpasswordComponent implements OnInit {

  myForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private loginServ: LoginService) {
    this.createForm();
   }

  ngOnInit() {
  }

  createForm() {
    this.myForm = this.formBuilder.group({
      username: '',
    });
  }

  submitForm() {
    console.log(this.myForm.value);
    this.loginServ.resetPassword(this.myForm.value);
  }

}

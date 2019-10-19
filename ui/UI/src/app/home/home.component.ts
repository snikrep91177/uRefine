import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { User } from '../classes/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Urefine';
  currentUser: User;

  constructor(private loginServ: LoginService) { }

  ngOnInit() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

}

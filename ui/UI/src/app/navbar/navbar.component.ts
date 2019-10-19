import { Component, OnInit } from '@angular/core';
import { UseExistingWebDriver } from 'protractor/built/driverProviders';
import { User } from '../classes/user';

@Component({
// tslint:disable-next-line: component-selector
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  currentUser: User;

  constructor() { }

  ngOnInit() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  logout() {
    localStorage.removeItem('currentUser');
    window.location.reload();
  }

}

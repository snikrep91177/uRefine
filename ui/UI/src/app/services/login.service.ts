import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../classes/user';
import { Router, RouterLinkWithHref } from '@angular/router';


interface Mes {
  message: string;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private currentUser: User;

  constructor(private httpCli: HttpClient, private router: Router) { }

  getCurrentUser() {
    return this.currentUser;
  }

  doLogin(creds) {
    this.authenticate(creds).subscribe((data: Mes) => {
      if (data) {
        const token = data.message;

        this.authCurrentUser(token).subscribe((user: User) => {
          if (user) {
            this.currentUser = user;
            localStorage.setItem('currentUser', JSON.stringify(user));
            if (user.resetPending) {
              window.location.href = '/updatepword';
            } else {
              window.location.href = '/home';
            }
          }
        });
      } else {
        alert('Incorrect Username/Password');
      }
    });
  }

  doRegister(creds) {
    this.httpCli.post('http://localhost:8765/review-service/users/register', creds).subscribe(data => {
      if (data) {
        console.log(data);
        this.router.navigate(['/login']);
      } else {
        console.log("Uh oh");
      }
    });
  }

  authenticate(creds) {
    return this.httpCli.post('http://localhost:8765/auth-service/auth/login', creds);
  }

  authCurrentUser(token) {
    const head = new HttpHeaders({
      'token': token
    });
    return this.httpCli.get('http://localhost:8765/review-service/users/getCurrentUser', {headers: head});
  }

  resetPassword(user) {
    // console.log(user);
    this.httpCli.post('http://localhost:8765/review-service/users/resetPassword', user).subscribe(data => {
      if (data) {
        this.router.navigate(['/login']);
      }
    });
  }

  updatePassword(user, password) {
    user.password = password;
   // console.log(user);
    this.httpCli.post('http://localhost:8765/review-service/users/updatePassword', user).subscribe(data => {
      console.log(data);
      this.router.navigate(['login']);
    });
  }
}

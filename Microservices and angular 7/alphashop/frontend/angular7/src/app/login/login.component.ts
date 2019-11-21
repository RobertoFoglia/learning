import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userid = '';
  password = '';
  autenticato = false;
  connectIsClicked = false;
  errorMsg = 'Spiacente, la userid o la password sono errati';

  constructor(private router: Router) { }

  ngOnInit() {
  }

  authenticationManagement() {
    console.log('userid = ' + this.userid);
    console.log('password = ' + this.password);
    this.connectIsClicked = true;
    if (this.userid === 'Roberto' && this.password === '123') {
      this.autenticato = true;
      this.router.navigate(['welcome', this.userid]);
    } else {
      this.autenticato = false;
    }
  }
}

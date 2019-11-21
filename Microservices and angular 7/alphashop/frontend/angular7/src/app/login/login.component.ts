import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userid = '';
  password = '';

  constructor() { }

  ngOnInit() {
  }

  authenticationManagement() {
    console.log('userid = ' + this.userid);
    console.log('password = ' + this.password);
  }
}

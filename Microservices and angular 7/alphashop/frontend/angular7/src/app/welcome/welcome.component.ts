import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  messaggio = 'Saluti sonon il componente welcome';

  constructor() { }

  ngOnInit() {
    // this is called before the component is startedng
    console.log(this.messaggio);
  }

}

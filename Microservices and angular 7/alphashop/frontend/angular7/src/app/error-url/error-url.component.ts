import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-error-url',
  templateUrl: './error-url.component.html',
  styleUrls: ['./error-url.component.css']
})
export class ErrorUrlComponent implements OnInit {

  errorMsg = 'Spiacente la pagina ricercata non esiste';

  constructor() { }

  ngOnInit() {
  }

}

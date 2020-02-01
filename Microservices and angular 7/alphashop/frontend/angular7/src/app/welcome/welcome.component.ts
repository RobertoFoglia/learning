import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  messaggio = 'Saluti sonon il componente welcome';
  saluti = 'Benvenuti nel sito Alphashop';
  titolo2 = 'Seleziona gli articoli da acquistare';

  utente = '';

  // @@@ routing parameters
  constructor(private activatedRoute: ActivatedRoute) {

   }

  ngOnInit() {
    // this is called before the component is startedng
    console.log(this.messaggio);
    // the parameters is in the URL
    this.utente = this.activatedRoute.snapshot.params['userid'];
  }

}

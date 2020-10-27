import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticoliDataService } from '../services/data/articoli-data.service';

export class Articoli {

  constructor(
    public codart: string,
    public descrizione: string,
    public um: string,
    public pzcart: number,
    public peso: number,
    public prezzo: number,
    public isactive: boolean,
    public data: Date

  ) { }

}

export class ApiMsg {

  constructor(
    public code: string,
    public message: string
  ) {}
}


@Component({
  selector: 'app-articoli',
  templateUrl: './articoli.component.html',
  styleUrls: ['./articoli.component.css']
})
export class ArticoliComponent implements OnInit {

  NumArt = 0;

  pagina = 1;
  righe = 10;
 
  apiMsg: ApiMsg;
  messaggio: string;

  filter: string = '';
  articolo: Articoli;
  articoli : Articoli[];
  
  constructor(private route:ActivatedRoute, private articoliService: ArticoliDataService) { }

  ngOnInit() {

    this.filter = this.route.snapshot.params['filter']

    if (this.filter != undefined) {
      this.getArticoli(this.filter);
    }
  
  }

  refresh() {
    this.getArticoli(this.filter);
    }

  public getArticoli(filter: string) {

    this.articoliService.getArticoliByCodArt(filter).subscribe(
      response => {

        this.articoli = [];

        console.log('Ricerchiamo articoli per codart con filtro ' + filter);

        this.articolo = response;
        console.log(this.articolo);

        this.articoli.push(this.articolo);
        this.NumArt = this.articoli.length
        console.log(this.articoli.length);
       
      },
      error => {
        //console.log(error);
        console.log(error.error.messaggio);
    
        console.log('Ricerchiamo per descrizione con filtro ' + filter);
        this.articoliService.getArticoliByDescription(filter).subscribe(
          response => {

            this.articoli = response;
            console.log(this.articoli);
            
            this.NumArt = this.articoli.length
            console.log(this.articoli.length);

          },
          error => {
            
          }
        )
      } 
    )
  }

  Elimina(CodArt: string) {
    console.log(`Eliminazione articolo ${CodArt}`);

    this.articoliService.delArticoloByCodArt(CodArt).subscribe(
      response => {
        
        this.apiMsg = response;
        this.messaggio = this.apiMsg.message;
        this.refresh();

      }
    )
    
  }

}

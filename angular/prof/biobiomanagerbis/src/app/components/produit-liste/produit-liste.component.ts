import { Component, OnInit } from '@angular/core';
import { ProduitService } from "../../services/produit.service";
import { Observable } from "rxjs/Observable";
import { Produit } from "../../metier/Produit";
import { Subject } from "rxjs/Subject";
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
  selector: 'app-produit-liste',
  templateUrl: './produit-liste.component.html',
  styleUrls: ['./produit-liste.component.css']
})
export class ProduitListeComponent implements OnInit {

  public searchSubject : Subject<string>;
  public produits : Observable<Produit[]>;
  public searchterm : string = "";


  constructor(private produitService: ProduitService) {
    this.searchSubject=new Subject<string>();
   }

  ngOnInit() {
    this.produits = this.produitService.getProduitsObservable();
    this.produitService.refreshListe();
    this.searchSubject.debounceTime(300)
                        .distinctUntilChanged()
                        .subscribe(st=>{
                          console.log("search: "+st);  
                          this.produitService.setSearchTerm(st) ;                       
                        });
  }

  suppression(id: number) : void {
    this.produitService.delete(id)
          .then(result=>{
            console.log(result);
            this.produitService.refreshListe();
          });
  }
  searchChanged(newValue: string) {
    this.searchSubject.next(newValue);
  }
}

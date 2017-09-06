import { Component, OnInit } from '@angular/core';
import { ProduitService } from "../../services/produit.service";
import { Observable } from "rxjs/Observable";
import { Produit } from "../../metier/Produit";

@Component({
  selector: 'app-produit-liste',
  templateUrl: './produit-liste.component.html',
  styleUrls: ['./produit-liste.component.css']
})
export class ProduitListeComponent implements OnInit {

  public produits : Observable<Produit[]>;
  public searchterm : string = "";

  constructor(private produitService: ProduitService) { }

  ngOnInit() {
    this.produits = this.produitService.getProduitsObservable();
  }

  suppression(id: number) : void {
    this.produitService.delete(id);
  }

  edition(id: number) : void {
    this.produitService.editer(id);
  }
  // (ngModelChange)="searchUpdate($event)"
/*
  searchUpdate(newValue: string) : void {
    //console.log("searchUpdate");
    //console.log(newValue);
    this.searchterm = newValue;
    //this.produitService.setSearchTerm(this.searchterm);
  }*/
}

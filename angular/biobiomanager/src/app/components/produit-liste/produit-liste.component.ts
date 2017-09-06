import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs/Observable";
import { Produit } from "../../metier/produit";
import { ProduitService } from "../../services/produit.service";

@Component({
  selector: 'app-produit-liste',
  templateUrl: './produit-liste.component.html',
  styleUrls: ['./produit-liste.component.css']
})
export class ProduitListeComponent implements OnInit {

  public produits:Observable<Produit[]>;


  constructor(private produitService: ProduitService) { }

  ngOnInit() {
    this.produits=this.produitService.listenProduit();
  }

  suppression(id:number):void{
    this.produitService.deleteProduit(id);
  }
  edition(id:number):void{
    this.produitService.editerProduit(id);
  }
  triPrix():void{
    this.produitService.triPrix();
  }

}

import { Component, OnInit } from '@angular/core';
import { Produit } from "../../metier/produit";
import { ProduitService } from "../../services/produit.service";

@Component({
  selector: 'app-produit-edit',
  templateUrl: './produit-edit.component.html',
  styleUrls: ['./produit-edit.component.css']
})
export class ProduitEditComponent implements OnInit {

  private editProduit: Produit;
  private isNewProduit:boolean;

  constructor(private produitService: ProduitService) {
    this.editProduit=new Produit(0,"nom du produit",
                              0.0,0.0,"catégorie du produit");
    this.isNewProduit=true;
   }

  ngOnInit() {
    this.produitService.listenEditProduit()
                        .subscribe(p=>{
                          this.editProduit=p;
                          this.isNewProduit=false;
                        });
  }
  sauverProduit(produit:Produit):void{
    if (this.isNewProduit) {
      this.produitService.addProduit(produit);
      this.editProduit=new Produit(0,"nom du produit",
      0.0,0.0,"catégorie du produit");
      this.isNewProduit=true;
    }
  }

  nouveauProduit():void{
    this.editProduit=new Produit(0,"nom du produit",
    0.0,0.0,"catégorie du produit");
    this.isNewProduit=true;
  }

}

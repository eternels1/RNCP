import { Component, OnInit, OnDestroy } from '@angular/core';
import { Produit } from "../../metier/Produit";
import { ProduitService } from "../../services/produit.service";
import { Subscription } from "rxjs/Subscription";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-produit-edit',
  templateUrl: './produit-edit.component.html',
  styleUrls: ['./produit-edit.component.css']
})
export class ProduitEditComponent implements OnInit, OnDestroy {

  public editProduit : Produit;
  private abonnement : Subscription;

  constructor(private produitService : ProduitService, private activedRoute:ActivatedRoute,
                private router: Router) { }

  ngOnInit() {
    this.editProduit = new Produit(0, "", 19.99, 1.0,  "divers");
   
    /*
      la methode subscribe de observable fait 2 choses:
        1) prend en parametre notre methode a appeler
        quand une nouvelle donnée est disponnible
        2) me renvoie une souscription, que je peut utiliser
        pour me desabonner par la suite ou savoir si l'abonnement
        est toujours actif par exemple
    */
    this.activedRoute.params.subscribe(params =>{
      let id= parseInt(params["id"]);
      console.log("param id recu = "+ params["id"]);
      if (id!=0) {
        this.produitService.findById(parseInt(params['id']))
        .then(p=>{
          this.editProduit=p;
        }); 
      }
    });
    
  }

  ngOnDestroy(): void {    
  }

  sauver() : void {
   
    this.produitService.save(this.editProduit)
                        .then(p=> {console.log(p);
                          this.router.navigateByUrl('/home');
                        });
    
  }
  nouveau() : void {
    this.editProduit = new Produit(0, "", 19.99, 1.0,  "divers");    
  }

}

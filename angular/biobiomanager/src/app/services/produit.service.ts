import { Injectable } from '@angular/core';
import { Produit } from "../metier/produit";
import { Subject } from "rxjs/Subject";
import { Observable } from "rxjs/Observable";

@Injectable()
export class ProduitService {

  private produits:Produit[]=[];
  private produitsubject :Subject<Produit[]>;
  private editProduitSubject:Subject<Produit>;

  constructor() { 
    this.produitsubject= new Subject<Produit[]>();
    this.produitsubject.next(this.produits);
    this.editProduitSubject= new Subject<Produit>();
  }

  listenEditProduit():Observable<Produit>{
    return this.editProduitSubject.asObservable();
  }
  listenProduit():Observable<Produit[]>{
    return this.produitsubject.asObservable();
  }
  getProduits():Produit[]{
    return this.produits;
  }

  addProduit(p:Produit):void{
    this.produits.push(p);
    this.produitsubject.next(this.produits);
  }

  editerProduit(id:number):void{
    let pos:number= this.produits.findIndex(p=>p.id==id);
    if (pos!=-1) {
      let p=this.produits[pos];
      this.editProduitSubject.next(p);
    }
  }

  deleteProduit(id:number):void{
    let pos:number= this.produits.findIndex(p=>p.id==id);
    if (pos!=-1) {
      this.produits.splice(pos,1);
      this.produitsubject.next(this.produits);
    }
  }
  triPrix():void{
    this.produits.sort((p1,p2)=>p1.prix-p2.prix);
    this.produitsubject.next(this.produits);
  }

  filtrerCat():void{
    
  }

}

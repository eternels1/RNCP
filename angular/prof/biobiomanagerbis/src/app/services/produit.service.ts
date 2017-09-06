import { Injectable } from '@angular/core';
import { Produit } from "../metier/Produit";
import { Http } from "@angular/http";

import { Observable } from "rxjs/Observable";
import { BehaviorSubject } from "rxjs/BehaviorSubject";
import { Subject } from "rxjs/Subject";
import "rxjs/add/operator/toPromise";
import "rxjs/add/operator/map";

@Injectable()
export class ProduitService {
  
  private produitsSubject : BehaviorSubject<Produit[]>;
  
  private searchTerm : string;

  constructor(private http: Http) { 
    
    this.produitsSubject = new BehaviorSubject<Produit[]>([]);
    
    this.searchTerm = "";
  }

  public setSearchTerm(searchTerm : string){
    this.searchTerm=searchTerm;
    this.refreshListe();
  }

  // abonnements disponnibles
  getProduitsObservable() : Observable<Produit[]> {
    return this.produitsSubject.asObservable();
  }
 
  public refreshListe() {
    let url:string;
    if (this.searchTerm!=null && this.searchTerm.length>0) {
      url=`http://localhost:8080/produitrest/api/produits/searchtitre/${this.searchTerm}`
    }
    else{
      url="http://localhost:8080/produitrest/api/produits"
    }
      this.http.get(url)
                .map(response => response.json() as Produit[])
                .toPromise()
                .then(data => {
                  this.produitsSubject.next(data);
                })
    
  } 
  // manipulationd de produits
  
  delete(id : number) : Promise<any> {
   return this.http.delete(`http://localhost:8080/produitrest/api/produit/${id}`)
                    .map(response=>response.json() as any)
                    .toPromise();
  }
  save(p: Produit): Promise<Produit>{
      return this.http.post("http://localhost:8080/produitrest/api/produit",p)
                      .map(response=> response.json() as Produit)
                      .toPromise();
  }
findById(id:number):Promise<Produit>{
  return this.http.get(`http://localhost:8080/produitrest/api/produit/${id}`)
                  .map(response=> response.json() as Produit)
                  .toPromise();  
}
  



}

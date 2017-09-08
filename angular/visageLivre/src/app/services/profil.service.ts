import { Injectable } from '@angular/core';
import { Profil } from "../metier/profil";
import { BehaviorSubject } from "rxjs/BehaviorSubject";
import { Http } from "@angular/http";
import "rxjs/add/operator/toPromise";
import "rxjs/add/operator/map";
import { Observable } from "rxjs/Observable";

@Injectable()
export class ProfilService {
  
  private profilsSubject: BehaviorSubject<Profil[]>;

  private searchTerm :string;
  private searchTermVille :string;

  constructor(private http: Http) {
    this.profilsSubject= new BehaviorSubject<Profil[]>([]);
    this.searchTerm="";
    this.searchTermVille="";
   }

   getProfilsObservable():Observable<Profil[]>{
     return this.profilsSubject.asObservable();
   } 
   public setSearchTerm(searchterm:string){
     this.searchTerm=searchterm;
     this.refreshListe();
   }
   public setSearchTermVille(searchtermVille:string){
    this.searchTermVille=searchtermVille;
    this.refreshListe();
  }
   refreshListe(): any {
    let url:string;
    if (this.searchTerm!=null &&this.searchTerm.length>0 && 
      this.searchTermVille!=null &&this.searchTermVille.length>0) {
     url=`http://localhost:8080/visageLivre/api/profils/searchnameandtown/${this.searchTerm}/${this.searchTermVille}`;
   }
    else if (this.searchTerm!=null &&this.searchTerm.length>0) {
      url=`http://localhost:8080/visageLivre/api/profils/searchname/${this.searchTerm}`;
    }
    else if (this.searchTermVille!=null &&this.searchTermVille.length>0) {
      url=`http://localhost:8080/visageLivre/api/profils/searchtown/${this.searchTermVille}`;
    }
    else{
      url="http://localhost:8080/visageLivre/api/profils"
    };
    this.http.get(url)
              .map(response=>response.json() as Profil[])
              .toPromise()
              .then(data=>{
                this.profilsSubject.next(data);
              })
  }
  delete(id: number): Promise<any> {
    return this.http.delete(`http://localhost:8080/visageLivre/api/profil/${id}`)
                    .map(response=>response.json() as any)
                    .toPromise();
  }

  save(p:Profil):Promise<Profil>{
    return this.http.post("http://localhost:8080/visageLivre/api/profil",p)
                    .map(response=>response.json() as Profil)
                    .toPromise();
  }
  findById(id:number):Promise<Profil>{
    return this.http.get(`http://localhost:8080/visageLivre/api/profil/${id}`)
                    .map(response=>response.json() as Profil)
                    .toPromise();
  }
}

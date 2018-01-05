import { Injectable } from '@angular/core';
import { Tache } from '../metier/tache';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders } from '@angular/common/http';


@Injectable()
export class TodolisteRepositoryService {

  private tachesSubject: BehaviorSubject<Tache[]>;
  private searchLibelle: string;
  private prioriteNumber: number;

  constructor(private _http: HttpClient) { 
    this.searchLibelle="";
    this.prioriteNumber=0;
    this.tachesSubject= new BehaviorSubject([]);
  }


  public setPriorite(pioritechoosed : number): void{
    this.prioriteNumber=pioritechoosed;
    this.refreshListe();
  }

  public chnageSearch(searchTerm:string):void{
    this.searchLibelle=searchTerm;
    //rafraichissement
    this.refreshListe();
  }


  public listeTachesObservable(): Observable<Tache[]>{
    return this.tachesSubject.asObservable();
  }

  public refreshListe():void{
    //quand on veut raffrechir la liste
    //on envoi une requette au serveur
    //quand on recoi sa reponse, on republie les donnée dans le sujet 'tachesSubject'
    //ainsi tous ceux qui écoute le sujet tachesSubject recevrons la liste raffréchis

    let url= "http://localhost:8080/todoliste/taches";

    if(this.searchLibelle!=""){
      url+=`/search/${this.searchLibelle}`;
    }

    if(this.prioriteNumber!=0){
      url+=`/searchPriorite/${this.prioriteNumber}`;
    }
    
    this._http.get<Tache[]>(url)
              .toPromise()
              .then(taches=>this.tachesSubject.next(taches))


  }

  public findTache(id:number) : Promise<Tache>{
    let url=`http://localhost:8080/todoliste/taches/${id}`;
    return this._http.get<Tache>(url).toPromise();
  }

  public saveTache(tache:Tache):Promise<Tache>{
    const httpoptions={
      headers: new HttpHeaders({'Content-Type':'application/json'})
    };
    if(tache.id==0){
      return this._http.post<Tache>("http://localhost:8080/todoliste/taches",tache,httpoptions).toPromise();
    }
    else {
      return this._http.put<Tache>("http://localhost:8080/todoliste/taches",tache,httpoptions).toPromise();
    }
  }

  public deleteTache(id:number) : Promise<Tache>{
    let url=`http://localhost:8080/todoliste/taches/${id}`;
    return this._http.delete<Tache>(url).toPromise();
  }

}

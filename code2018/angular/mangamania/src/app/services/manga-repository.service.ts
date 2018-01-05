import { Injectable } from '@angular/core';
import { Manga } from '../metier/manga';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Page } from '../metier/page';

@Injectable()
export class MangaRepositoryService {

  
  private mangaSubject: BehaviorSubject<Page<Manga>>;
  private searchTitre: string; //recherche sur le titre

  private numPage:number;
  public setNumPage(num:number):void {
    this.numPage=num;
    this.refreshListe();
  }

  constructor(private _http: HttpClient) {
    this.searchTitre="";
    this.mangaSubject= new BehaviorSubject(new Page([],0,0,5,0,1,true,false,null));
    this.numPage=0;
   }

   //méthode appelé si un composant veut modifier le filtrage de la liste des mangas en fonction du titre
   public changeSearch(searchTerm : string) : void {
     this.searchTitre=searchTerm;
     //rafraichissement de la liste
     this.refreshListe();
   }


   public listeMangasObservable():Observable<Page<Manga>>{
    return this.mangaSubject.asObservable();
   }

   public refreshListe() : void {
    //quand on veut raffrechir la liste
    //on envoi une requette au serveur
    //quand on recoi sa reponse, on republie les donnée dans le sujet 'mangasSubject'
    //ainsi tous ceux qui écoute le sujet mangasSubject recevrons la liste raffréchis

    let url= "http://localhost:8080/mangamania/pmangas";
    if(this.searchTitre!="") {
      url+=`/search/${this.searchTitre}`;
    }
    url+=`?page=${this.numPage}`;
    this._http.get<Page<Manga>>(url)
              .toPromise()
              .then(mangas=>this.mangaSubject.next(mangas))
              
   }

   public findManga(id: number) : Promise<Manga>{
     let url=`http://localhost:8080/mangamania/mangas/${id}`;
     return this._http.get<Manga>(url).toPromise();
   }
   public saveManga(manga : Manga): Promise<Manga>{
     const httpoptions={
       headers : new HttpHeaders({'Content-Type':'application/json'})
     };
     if(manga.id==0){
       //insertion
       return this._http.post<Manga>("http://localhost:8080/mangamania/mangas",manga,httpoptions).toPromise();
     }
     else{
       //update
       return this._http.put<Manga>("http://localhost:8080/mangamania/mangas",manga,httpoptions).toPromise();
     }
     
   }

   public deleteManga(id: number) : Promise<Manga>{
    let url=`http://localhost:8080/mangamania/mangas/${id}`;
    return this._http.delete<Manga>(url).toPromise();
  }
}

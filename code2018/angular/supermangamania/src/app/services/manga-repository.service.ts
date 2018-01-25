import { Injectable } from '@angular/core';
import { Manga } from '../metier/manga';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Page } from '../metier/page';

@Injectable()
export class MangaRepositoryService {

  
  private mangaSubject: BehaviorSubject<Page<Manga>>;
  private searchTitre: string; //recherche sur le titre
  private filterByRatingMin: number;

  private baseUrl: string="http://localhost:8080/extendedapi/SuperMangaMania/";
  private apiUrl: string="http://localhost:8080/api/";
  private numPage:number;

  public setNumPage(num:number):void {
    this.numPage=num;
    this.refreshListe();
  }
  public setFilterByRatingMin(rating: number) :void{
    this.filterByRatingMin=rating;
    this.refreshListe();
  }


  constructor(private _http: HttpClient) {
    this.searchTitre="";
    this.mangaSubject= new BehaviorSubject(new Page([],0,0,5,0,1,true,false,null));
    this.numPage=0;
    this.filterByRatingMin=0;
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

    let url= `${this.baseUrl}SuperMangas`;
    if(this.searchTitre!="") {
      url+=`/search/${this.searchTitre}`;
    }

    //ATTENTION params est imutable !
    //cet objet permet de déléguer à angular la construction de l'url après le ?
    //autrement dit des query parameters
    let params : HttpParams= new HttpParams();
    params= params.set("page",""+this.numPage);
    if(this.filterByRatingMin>0){
      params=params.set("ratingMinimum",this.filterByRatingMin.toString());
    } 
    
    this._http.get<Page<Manga>>(url,{params: params})
              .toPromise()
              .then(mangas=>this.mangaSubject.next(mangas))
              
   }

   public findManga(id: number) : Promise<Manga>{
     let url=`${this.apiUrl}mangas/${id}`;
     return this._http.get<Manga>(url).toPromise();
   }
   public saveManga(manga : Manga): Promise<Manga>{
     const httpoptions={
       headers : new HttpHeaders({'Content-Type':'application/json'})
     };
     if(manga.id==0){
       //insertion
       return this._http.post<Manga>(`${this.apiUrl}mangas`,manga,httpoptions).toPromise();
     }
     else{
       //update
       return this._http.put<Manga>(`${this.apiUrl}mangas/${manga.id}`,manga,httpoptions).toPromise();
     }
     
   }

   public getUploadurl(idSuperManga:number) : string {
    return  `${this.baseUrl}/upload/${idSuperManga}`;
  }
   public deleteManga(id: number) : Promise<Manga>{
    let url=`${this.baseUrl}delete`;
    let params : HttpParams= new HttpParams();
    params= params.set("mangaId",""+id);
    return this._http.delete<Manga>(url,{params: params}).toPromise();
  }
}

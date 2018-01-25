import { Injectable } from '@angular/core';
import{Image} from "../models/image";
import{Page} from "../models/page";

import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Tag } from '../models/tag';


@Injectable()
export class ImageRepositoryService {

  //la liste des images renvoyée par le seveur sera publié ici
  private imagesSubject : BehaviorSubject<Page<Image>>;
  private noPage : number;
  private taillePage : number;
  //tag selectionnées
  private selectedTags: Tag[];
  private selectedtagSubject : BehaviorSubject<Tag[]>; 

  private baseUrlAPi:string="http://localhost:8080/images";
  private baseUrlExtendedAPi:string="http://localhost:8080/extendedApi/image";

  

  constructor(private _http: HttpClient) { 
    //initialisation page
    this.noPage=0;
    this.taillePage=12;
    this.imagesSubject=new BehaviorSubject(Page.emptyPage<Image>());
    //initialisation tags
    this.selectedTags=[];
    this.selectedtagSubject=new BehaviorSubject(this.selectedTags);
  }

  public addSelectedtag(tag: Tag){
    if (this.selectedTags.findIndex(t=> t.id==tag.id)==-1) {
      //tag non present , on peut donc l'ajouter
      this.selectedTags.push(tag);
      //je previensceux écoutant la liste des tags selectionnés
      this.selectedtagSubject.next(this.selectedTags);
      //et je rafraichi la liste des images
      this.refreshListe();
    }
  }

  public removeSelectedtag(tag: Tag){
    let index=this.selectedTags.findIndex(t=> t.id==tag.id);
      //tag non present , on peut donc l'ajouter
     if (index!=-1) {
       this.selectedTags.splice(index,1);
        //je previensceux écoutant la liste des tags selectionnés
      this.selectedtagSubject.next(this.selectedTags);
      //et je rafraichi la liste des images
      this.refreshListe();
     }
     
  }

  public selectedtagsAsObservable(): Observable<Tag[]>{
    return this.selectedtagSubject.asObservable();
  }

  public setNoPage(no : number) : void {
    this.noPage = no;
    this.refreshListe();
  }

  public listeImageAsObservable(): Observable<Page<Image>>{
    return this.imagesSubject.asObservable();
  }

  public refreshListe():void {

    let urlparams : HttpParams = new HttpParams();
    urlparams = urlparams.set("page", "" + this.noPage);

    if (this.selectedTags.length>0) {
      urlparams=urlparams.set("tagsId",
                        this.selectedTags.map(t=>""+t.id).join(","));
    }

    this._http.get<Page<Image>>(`${this.baseUrlExtendedAPi}/findbytagfull`,{params:urlparams})
              .toPromise()
              .then(p=>this.imagesSubject.next(p))
              .catch(e=>console.log("pas d'image recu"));
  }

  public getImageThumbUrl(id:number): string{
    return `${this.baseUrlExtendedAPi}/downloadThumb/${id}`;
  }

  public getImageUrl(id:number): string{
    return `${this.baseUrlExtendedAPi}/download/${id}`;
  }

  public getUploadurl(): string{
    return `${this.baseUrlExtendedAPi}/upload`;
  }

  public deleteImages(ids : number[]): void{
    let ids_string=ids.join(",");
    let urlparams: HttpParams =new HttpParams();
    urlparams=urlparams.set("imagesId",ids_string);
    this._http.delete<Map<string,number>>(`${this.baseUrlExtendedAPi}/delete`,
                                              {params:urlparams})
              .toPromise()
              .then(result=>{
                console.log(result);
                this.refreshListe();
              });
  }

}

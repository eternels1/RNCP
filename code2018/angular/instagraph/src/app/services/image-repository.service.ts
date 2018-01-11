import { Injectable } from '@angular/core';
import{Image} from "../models/image";
import{Page} from "../models/page";

import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Promise } from 'q';


@Injectable()
export class ImageRepositoryService {

  //la liste des images renvoyée par le seveur sera publié ici
  private imagesSubject : BehaviorSubject<Page<Image>>;
  private noPage : number;
  private taillePage : number;
  private baseUrlAPi:string="http://localhost:8080/images";
  private baseUrlExtendedAPi:string="http://localhost:8080/extendedApi/image";

  

  constructor(private _http: HttpClient) { 
    this.noPage=0;
    this.taillePage=12;
    this.imagesSubject=new BehaviorSubject(Page.emptyPage<Image>());
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

    this._http.get<Page<Image>>(`${this.baseUrlExtendedAPi}/findbytag`,{params:urlparams})
              .toPromise()
              .then(p=>this.imagesSubject.next(p));
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

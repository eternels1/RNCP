import { Injectable } from '@angular/core';
import{Tag} from "../models/tag";
import{Page} from "../models/page";

import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";

@Injectable()
export class TagRepositoryService {



  //la liste des images renvoyée par le seveur sera publié ici
  private tagsSubject : BehaviorSubject<Page<Tag>>;
  private noPage : number;
  private taillePage : number;

  private baseUrlAPi:string="http://localhost:8080/tags";
  private baseUrlExtendedAPi:string="http://localhost:8080/extendedApi/tag";


  constructor(private _http: HttpClient) { 
    this.noPage=0;
    this.taillePage=15;
    this.tagsSubject=new BehaviorSubject(Page.emptyPage<Tag>());
  }

  public refreshListe():void {

    let urlparams : HttpParams = new HttpParams();
    urlparams = urlparams.set("page", "" + this.noPage);

    this._http.get<Page<Tag>>(`${this.baseUrlExtendedAPi}/liste`,{params:urlparams})
              .toPromise()
              .then(p=>this.tagsSubject.next(p))
              .catch(e=> console.log("pas de tag recu"));
  }

  public setNoPage(no : number) : void {
    this.noPage = no;
    this.refreshListe();
  }

  public listeTagAsObservable(): Observable<Page<Tag>>{
    return this.tagsSubject.asObservable();
  }

}

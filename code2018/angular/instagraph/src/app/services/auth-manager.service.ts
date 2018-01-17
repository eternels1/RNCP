import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Utilisateur } from '../models/utilisateur';
import { Subject } from 'rxjs/Subject';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AuthManagerService {

  private baseUrlAPi:string="http://localhost:8080/extendedApi/auth";

  private currentUser: Utilisateur;
  private utilisateurSubject:Subject<Utilisateur>;

  public getCurrentUser():Utilisateur{
    return this.currentUser;
  }

  public isLoggedIn():boolean{
    if (this.currentUser==null) {
      return false;
    }else return true;
  }

  public getCredentials():string{
    //génération de la valeur des crédential pour authentification
    return window.btoa(this.currentUser.username+":"+this.currentUser.password);
  }


  public getUtilisateurAsObservable():Observable<Utilisateur>{
    return this.utilisateurSubject.asObservable();
  }

  public setCurrentUser(utilisateur: Utilisateur):void{
    this.currentUser=utilisateur;
  }
  

  constructor() { 
    this.currentUser=null;
    this.utilisateurSubject=new Subject<Utilisateur>();
  }





}

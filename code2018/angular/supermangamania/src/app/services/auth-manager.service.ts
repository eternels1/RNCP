import { Injectable } from '@angular/core';
import { Utilisateur } from '../metier/utilisateur';
import { Subject } from 'rxjs/Subject';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AuthManagerService {

  private baseUrlAPi:string="http://localhost:8080/extendedApi/auth";

  private currentUser: Utilisateur;
  private utilisateurSubject:Subject<[boolean,Utilisateur]>;

  constructor() {
    this.currentUser=null;
    this.utilisateurSubject=new Subject<[boolean,Utilisateur]>();
   }

  public getCurrentUser():Utilisateur{
    return this.currentUser;
  }

  public isLoggedIn():boolean{
    if (this.currentUser==null) {
      return false;
    }else return true;
  }

  public logOut():void{
    this.currentUser=null;
    //publication du fait qu'il n'y plus de user logger
    this.utilisateurSubject.next([false,this.currentUser]);
  }

  public getCredentials():string{
    //génération de la valeur des crédential pour authentification
    return window.btoa(this.currentUser.username+":"+this.currentUser.password);
  }

  public getUtilisateurAsObservable():Observable<[boolean,Utilisateur]>{
    return this.utilisateurSubject.asObservable();
  }

  public setCurrentUser(utilisateur: Utilisateur):void{
    this.currentUser=utilisateur;
    //publication qu'il y a un user logger
    this.utilisateurSubject.next([true,this.currentUser]);
  }


}

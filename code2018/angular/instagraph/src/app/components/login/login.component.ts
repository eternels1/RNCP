import { Component, OnInit } from '@angular/core';
import { AuthManagerService } from '../../services/auth-manager.service';
import { Subscription } from 'rxjs/Subscription';
import { HttpClient } from '@angular/common/http';
import { Utilisateur } from '../../models/utilisateur';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public userlogin: any;
  private utilisateurSouscripton : Subscription;
  private baseUrlAPi:string="http://localhost:8080/extendedApi/auth";

  constructor(private autManager:AuthManagerService, private _http: HttpClient, private router:Router) {

    
   }

  ngOnInit() {
    this.userlogin={};
    this.utilisateurSouscripton=this.autManager.getUtilisateurAsObservable()
                    .subscribe(u=>{
                      console.log("je suis bien logger avec : "+u.username)
                    })
  }

  ngOnDestroy(){
    this.utilisateurSouscripton.unsubscribe();
  }

  public trylogin():void{
    console.log("tentative de login avec "+ this.userlogin.username);
    let newuser= new Utilisateur(this.userlogin.username,this.userlogin.password,true);
    this.autManager.setCurrentUser(newuser);
    this._http.post<Utilisateur>(`${this.baseUrlAPi}/login`, newuser)     
              .subscribe(u=>{
                 //l'utilisateur nouvellement logger devient le currentuser
                //this.autManager.setCurrentUser(u);
                console.log("je suis bien logger avec  "+ u.username);
                this.router.navigateByUrl("/liste");
              });
  }
}

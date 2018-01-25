import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Utilisateur } from '../../metier/utilisateur';
import { Manga } from '../../metier/manga';

@Component({
  selector: 'app-register-component',
  templateUrl: './register-component.component.html',
  styleUrls: ['./register-component.component.css']
})
export class RegisterComponentComponent implements OnInit {
  
  userRegister: any;
  private baseUrlAPi:string="http://localhost:8080/extendedApi/auth/register";

  constructor(private _http: HttpClient, private router:Router) { }

  ngOnInit() {
    this.userRegister={};
  }

  public createUser():void{
    
    let params : HttpParams= new HttpParams();
    params= params.set("username",""+this.userRegister.username);
    params= params.set("password",""+this.userRegister.password);
    
    this._http.post<Utilisateur>(this.baseUrlAPi,{},{params: params})
              .toPromise()
              .then(u=>{
                  console.log("Utilisateur : "+this.userRegister.username+" créer!");
                  this.router.navigateByUrl("/login");
              });
    
  }

}

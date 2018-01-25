import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { HttpParams } from '@angular/common/http';
import { Utilisateur } from '../../models/utilisateur';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  constructor(private http:HttpClient, private router:Router) { }

  userRegister:any;
  private baseUrlAPi:string="http://localhost:8080/extendedApi/auth";
  
  ngOnInit() {
    this.userRegister={username:"tarik",password:""};
  }

  public tryregister():void{
    console.log("try register with :"+this.userRegister.username);

    let params : HttpParams= new HttpParams().set("username",this.userRegister.username)
                                              .set("password",this.userRegister.password);
      this.http.post<Utilisateur>(`${this.baseUrlAPi}/register`,{},{params:params})
                .subscribe(u=>{
                  console.log("utlisateur cree : "+u.username);
                  this.router.navigateByUrl("login");
                }, e=> {
                  console.log();
                })


  }

}

import { Component, OnInit } from '@angular/core';
import { Profil } from "../../metier/profil";
import { Subscription } from "rxjs/Subscription";
import { ProfilService } from "../../services/profil.service";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-profil-edit',
  templateUrl: './profil-edit.component.html',
  styleUrls: ['./profil-edit.component.css']
})
export class ProfilEditComponent implements OnInit {

  public editProfil : Profil;
  private abonnement: Subscription;

  constructor(private profilService: ProfilService, 
    private activedRoute: ActivatedRoute,private router:Router) { }

  ngOnInit() {
    this.editProfil= new Profil(0,"nom","prenom",0,"adresse@mail.com","ville","sexe");
    this.activedRoute.params.subscribe(params => {
      let id=parseInt(params["id"]);
      console.log("param id recu = "+params["id"]);
      if (id!=0) {
        this.profilService.findById(parseInt(params['id']))
            .then(p=>{
              this.editProfil=p;
            });
      }      
    });
  }

  sauver(){
    this.profilService.save(this.editProfil)
                      .then(p=>{console.log(p);                      
                        this.router.navigateByUrl('/home');
                      });
  }
  nouveau():void{
    this.editProfil=new Profil(0,"nom","prenom",0,"adresse@mail.com","ville","sexe");
  }

}

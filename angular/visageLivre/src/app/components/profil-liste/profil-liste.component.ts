import { Component, OnInit } from '@angular/core';
import { Subject } from "rxjs/Subject";
import { Profil } from "../../metier/profil";
import { Observable } from "rxjs/Observable";
import { ProfilService } from "../../services/profil.service";
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
  selector: 'app-profil-liste',
  templateUrl: './profil-liste.component.html',
  styleUrls: ['./profil-liste.component.css']
})
export class ProfilListeComponent implements OnInit {

  public varTri:string="nom";
  public searchSubject : Subject<string>;
  public searchVilleSubject : Subject<string>;
  public profils: Observable<Profil[]>;
  public searchterm: string="";
  public searchtermVille: string="";

  constructor(private profilService: ProfilService) {
    this.searchSubject= new Subject<string>();
    this.searchVilleSubject= new Subject<string>();
   }

  ngOnInit() {
    this.profils=this.profilService.getProfilsObservable();
    this.profilService.refreshListe();
    this.searchSubject.debounceTime(300)
                      .distinctUntilChanged()
                      .subscribe(st=>{
                        console.log("search: "+st);
                        this.profilService.setSearchTerm(st);                        
                      })
    this.searchVilleSubject.debounceTime(300)
                      .distinctUntilChanged()
                      .subscribe(st=>{
                        console.log("search: "+st);
                        this.profilService.setSearchTermVille(st);                        
                      })
  }

  suppression(id: number):void{
    this.profilService.delete(id)
                      .then(result=>{
                        console.log(result);
                        this.profilService.refreshListe();                        
                      });
  }

  searchChanged(newValue:string){
    this.searchSubject.next(newValue);
  }

  searchVilleChanged(newValue:string){
    this.searchVilleSubject.next(newValue);
  }

  triage(varTri:string){
    this.varTri=varTri;
  }
}

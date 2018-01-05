import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Tache } from '../../metier/tache';
import { TodolisteRepositoryService } from '../../services/todoliste-repository.service';

@Component({
  selector: 'app-liste-tache',
  templateUrl: './liste-tache.component.html',
  styleUrls: ['./liste-tache.component.css']
})
export class ListeTacheComponent implements OnInit {

  public taches: Observable<Tache[]>;

  constructor(private _tacheRepository: TodolisteRepositoryService) { }

  ngOnInit() {

    //j'écoute la liste des mangas
   this.taches=this._tacheRepository.listeTachesObservable();
   //je demande au service de raffrechir la liste à partir du backend rest
   this._tacheRepository.refreshListe();

  }

  deleteTache(id:number):void{
    this._tacheRepository.deleteTache(id).then(t=>{
      console.log("Tache effacé : "+ t.id);
      this._tacheRepository.refreshListe();
    })
  }

}

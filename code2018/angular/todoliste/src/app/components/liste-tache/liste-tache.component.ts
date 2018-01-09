import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Tache } from '../../metier/tache';
import { TodolisteRepositoryService } from '../../services/todoliste-repository.service';
import { Subject } from 'rxjs/Subject';
import { Page } from '../../metier/page';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-liste-tache',
  templateUrl: './liste-tache.component.html',
  styleUrls: ['./liste-tache.component.css']
})
export class ListeTacheComponent implements OnInit {

  public taches: Subject<Tache[]>;
  public tacheSubscription: Subscription;
  public totalItemsPage:number;
  public currentPage:number;

  constructor(private _tacheRepository: TodolisteRepositoryService) { }

  ngOnInit() {

    this.taches=new Subject();
   this.tacheSubscription=this._tacheRepository.listeTachesObservable().subscribe(page=>{
            this.totalItemsPage=page.totalElements;
            this.currentPage=page.number+1;
            this.taches.next(page.content);
          })
   
          //j'écoute la liste des mangas
   //je demande au service de raffrechir la liste à partir du backend rest
   this._tacheRepository.refreshListe();

  }

  deleteTache(id:number):void{
    this._tacheRepository.deleteTache(id).then(t=>{
      console.log("Tache effacé : "+ t.id);
      this._tacheRepository.refreshListe();
    })
  }

  public pageChanged(event:any):void{
    this._tacheRepository.setNumPage(event.page -1);
  }

}

import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { TodolisteRepositoryService } from '../../services/todoliste-repository.service';

import 'rxjs/add/operator/debounceTime';

@Component({
  selector: 'app-search-tache',
  templateUrl: './search-tache.component.html',
  styleUrls: ['./search-tache.component.css']
})
export class SearchTacheComponent implements OnInit {

  public priorityChoices : number[];
  public prioChoised : number;
  public searchTerm : string;
  private searchTermSubject: Subject<string>;

  constructor(private _tacheRepository: TodolisteRepositoryService) { 
    this.searchTermSubject=new Subject();
  }

  ngOnInit() {
    this.searchTerm="";
    this.prioChoised=0;
    this.priorityChoices=[0,1,2,3,4,5,6,7,8,9];

    this.searchTermSubject.asObservable()
                          .debounceTime(1000)
                          .subscribe(newTerm=> this._tacheRepository.chnageSearch(newTerm));
    this._tacheRepository.chnageSearch("");
  }

  changeTerm(evt):void{
    this.searchTermSubject.next(evt);
    this.searchTerm=evt;
  }
  setPriorite(prio : number) : void {

    this._tacheRepository.setPriorite(prio);
    console.log("nouvelle priorite : " + prio);
  }
}

import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs/Subject';

import 'rxjs/add/operator/debounceTime';
import { MangaRepositoryService } from '../../services/manga-repository.service';

@Component({
  selector: 'app-search-manga',
  templateUrl: './search-manga.component.html',
  styleUrls: ['./search-manga.component.css']
})
export class SearchMangaComponent implements OnInit {

  public searchTerm: string;
  
  private searchTermSubject: Subject<string>;


  constructor(private _mangaRepository: MangaRepositoryService) { 
    this.searchTermSubject= new Subject();
  }




  ngOnInit() {
    this.searchTerm="ubunchu";

    this.searchTermSubject.asObservable()
                          .debounceTime(1000)
                          .subscribe(newTerm=> this._mangaRepository.changeSearch(newTerm));
  }

  changeTerm(evt) : void{

    this.searchTermSubject.next(evt);
    //console.log(evt);
    this.searchTerm=evt;
    }
}

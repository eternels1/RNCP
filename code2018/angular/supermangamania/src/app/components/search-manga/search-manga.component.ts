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
  public ratingRange : number[]= Array.from({length:5},(value,key)=>key+1);
  public currentRating: number;

  private searchTermSubject: Subject<string>;


  constructor(private _mangaRepository: MangaRepositoryService) { 
    this.searchTermSubject= new Subject();
  }

  ngOnInit() {
    this.currentRating=0;
    this.searchTerm="ubunchu";

    this.searchTermSubject.asObservable()
                          .debounceTime(1000)
                          .subscribe(newTerm=> this._mangaRepository.changeSearch(newTerm));
  }

  setRatingMin(rating : number): boolean{
    console.log("change rating : " +rating);
    if(rating!=this.currentRating){
      this._mangaRepository.setFilterByRatingMin(rating);
    }
    this.currentRating=rating;
    return false;//on ne suit pas le href dans le html
  }
  changeTerm(evt) : void{

    this.searchTermSubject.next(evt);
    //console.log(evt);
    this.searchTerm=evt;
    }
}

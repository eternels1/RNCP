import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Manga } from '../../metier/manga';
import { MangaRepositoryService } from '../../services/manga-repository.service';
import { Subject } from 'rxjs/Subject';
import { Subscription } from 'rxjs/Subscription';


@Component({
  selector: 'app-liste-manga',
  templateUrl: './liste-manga.component.html',
  styleUrls: ['./liste-manga.component.css']
})
export class ListeMangaComponent implements OnInit {

  public mangas: Subject<Manga[]>;
  public mangaSubscription: Subscription;
  public totalItems:number;
  public currentPage:number;
  
  constructor(private _mangaRepository: MangaRepositoryService) { }

  ngOnInit() {
    this.mangas=new Subject();
    this.mangaSubscription=this._mangaRepository.listeMangasObservable().subscribe(page=>{
      this.totalItems=page.totalElements;
      this.currentPage=page.number+1;
      this.mangas.next(page.content);
    })
    //j'écoute la liste des mangas
   // code -> this.mangas=this._mangaRepository.listeMangasObservable();
   //je demande au service de raffrechir la liste à partir du backend rest
   this._mangaRepository.refreshListe();
  }
  public deleteManga(id:number): void{
    this._mangaRepository.deleteManga(id).then(m=> {
      console.log("manga effacé : "+m.id);
      this._mangaRepository.refreshListe();
    });
  }

  public pageChanged(event:any) :void{
    this._mangaRepository.setNumPage(event.page -1);
  }

}

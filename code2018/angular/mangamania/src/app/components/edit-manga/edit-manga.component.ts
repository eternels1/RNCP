import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { Manga } from '../../metier/manga';
import { MangaRepositoryService } from '../../services/manga-repository.service';

@Component({
  selector: 'app-edit-manga',
  templateUrl: './edit-manga.component.html',
  styleUrls: ['./edit-manga.component.css']
})
export class EditMangaComponent implements OnInit {

  public editedManga : Manga;
  constructor(private activeRoute: ActivatedRoute, 
              private router: Router,
              private mangaRepository : MangaRepositoryService) { 
                ;
              }

  ngOnInit() {
    this.editedManga= new Manga(0,"ubunchu","linus",new Date(),"informatique",5);
    this.activeRoute.params.subscribe(params=>{
      //le parametre :id dans la route est dispo dans le tableau params
      let id= params["id"];
      console.log("param id recu = "+ id);
      if(id!=0){
      this.mangaRepository.findManga(id)
          .then(m=> this.editedManga=m);
    }
    })
  }

  sauverTache():void{

    console.log("sauvegarde TagPlaceholder...");
    console.log(this.editedManga);
    this.mangaRepository.saveManga(this.editedManga)
                        .then(m=>{
                          console.log("sauvegarde OK : "+m.id);
                          this.router.navigateByUrl('liste');
                        })
                        .catch(err=> {
                          console.log("erreur à la sauvegarde " +err);
                        })
  }

}

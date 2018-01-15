import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { Manga } from '../../metier/manga';
import { MangaRepositoryService } from '../../services/manga-repository.service';
import { NgForm } from '@angular/forms';
import { FileUploader } from 'ng2-file-upload';

@Component({
  selector: 'app-edit-manga',
  templateUrl: './edit-manga.component.html',
  styleUrls: ['./edit-manga.component.css']
})
export class EditMangaComponent implements OnInit {

  public hasBaseDropZoneOver : boolean = false;
  public editedManga : Manga;
  public uploader: FileUploader;
  public idSuperMangaSauver: number;
  constructor(private activeRoute: ActivatedRoute, 
              private router: Router,
              private mangaRepository : MangaRepositoryService) {
                this.uploader = new FileUploader({
                  autoUpload : false
                });
              }

  ngOnInit() {
    this.editedManga= new Manga(0,"","lanzaki",new Date(),"action",5,null);
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
  public fileOverDrop(event) : void {
    console.log("fileover: " + event);
    this.hasBaseDropZoneOver = event;
  }
  sauverTache(monform: NgForm):void{

    console.log("sauvegarde TagPlaceholder...");
    console.log("valide? "+monform.valid);
    console.log("modifier? "+monform.dirty);
    console.log(this.editedManga);
    this.mangaRepository.saveManga(this.editedManga)
                        .then(m=>{
                          console.log("sauvegarde OK : "+m.id);
                          this.idSuperMangaSauver=m.id;
                          let url=this.mangaRepository.getUploadurl(this.idSuperMangaSauver);
                           this.uploader.setOptions({url:url});
                           this.uploader.onCompleteAll=() => {
                             console.log("uplaod fait");
                             this.router.navigateByUrl('liste');
                           };  
                           this.uploader.uploadAll();
                                                       
                        
                        
                        })
                        .catch(err=> {
                          console.log("erreur à la sauvegarde " +err);
                        });

    
  }

}

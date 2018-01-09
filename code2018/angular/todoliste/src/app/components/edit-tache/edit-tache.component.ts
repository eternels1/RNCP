import { Component, OnInit } from '@angular/core';
import { Tache } from '../../metier/tache';
import { ActivatedRoute, Router } from '@angular/router';
import { TodolisteRepositoryService } from '../../services/todoliste-repository.service';

@Component({
  selector: 'app-edit-tache',
  templateUrl: './edit-tache.component.html',
  styleUrls: ['./edit-tache.component.css']
})
export class EditTacheComponent implements OnInit {

  public editedTache : Tache;
  
  constructor(private activeRoute: ActivatedRoute,
              private router: Router,
              private tacheRepository: TodolisteRepositoryService) { }

  ngOnInit() {

    this.editedTache=new Tache(0,"","Description",5,new Date(),new Date(),"contexte",false);
    this.activeRoute.params.subscribe(params=>{

      let id=params["id"];
      console.log("param id recu = "+ id);

      if(id!=0){
        this.tacheRepository.findTache(id)
                            .then(t=>this.editedTache=t);
      }
    })
  }

  public sauverTache():void{
    console.log("sauvegarde Tache...");
    console.log(this.editedTache);
    this.tacheRepository.saveTache(this.editedTache)
                        .then(t=>{
                          console.log("sauvegarde OK : "+t.id);
                          this.router.navigateByUrl('liste');
                        })
                        .catch(err=>{
                          console.log("erreur à la sauvegarde "+err);
                        })
  }

}

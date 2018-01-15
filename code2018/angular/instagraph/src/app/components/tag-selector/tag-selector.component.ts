import { Component, OnInit } from '@angular/core';
import { TagRepositoryService } from '../../services/tag-repository.service';
import { Subject } from 'rxjs/Subject';
import { Tag } from '../../models/tag';
import { Subscription } from 'rxjs/Subscription';
import { Observable } from 'rxjs/Observable';
import { ImageRepositoryService } from '../../services/image-repository.service';

@Component({
  selector: 'app-tag-selector',
  templateUrl: './tag-selector.component.html',
  styleUrls: ['./tag-selector.component.css']
})
export class TagSelectorComponent implements OnInit {

  constructor(private tagrepository:TagRepositoryService,
              private imagerepository: ImageRepositoryService) { }

  public tagSubject: Subject<Tag[]>;
  public tagSubscription: Subscription;

  public tagSelected: Observable<Tag[]>;

  ngOnInit() {
    this.tagSubject=new Subject<Tag[]>();
    this.tagSubscription=this.tagrepository.listeTagAsObservable()
                                            .subscribe(p=>{
                                              this.tagSubject.next(p.content);
                                            });
    this.tagrepository.refreshListe();
    this.tagSelected=this.imagerepository.selectedtagsAsObservable();
  }

  public addToSelectedTag(tag: Tag):void{
    this.imagerepository.addSelectedtag(tag);
  }

  public removeFromSelectedTag(tag: Tag):void{
    this.imagerepository.removeSelectedtag(tag);
  }
}

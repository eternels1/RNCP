import { Component, OnInit,TemplateRef } from '@angular/core';
import { ImageRepositoryService } from '../../services/image-repository.service';
import { Subject } from 'rxjs/Subject';
import { Subscription } from 'rxjs/Subscription';
import { Observable } from 'rxjs/Observable';
import { Image } from '../../models/image';
import { OnDestroy } from '@angular/core/src/metadata/lifecycle_hooks';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { Lightbox } from 'angular2-lightbox';
import { TruncatePipe } from 'angular-pipes/src/string/truncate.pipe';
import { BytesPipe } from 'angular-pipes/src/math/bytes.pipe';


@Component({
  selector: 'app-image-list',
  templateUrl: './image-list.component.html',
  styleUrls: ['./image-list.component.css']
})
export class ImageListComponent implements OnInit, OnDestroy {
  

  public idToDelete:number=0;
  public images : Subject<Image[]>;
  public gallerieLiens:any[]=[];
  public souscription : Subscription;
  public totalItems : number=0;
  public currentPage : number=1;
  modalRef: BsModalRef;

  constructor(private imagereporsitory: ImageRepositoryService,
              private modalService: BsModalService,
            private lightBox: Lightbox) { }

  public getImageThumbUrl(id:number): string{
    return this.imagereporsitory.getImageThumbUrl(id);
  }

  public getImageUrl(id:number): string{
    return this.imagereporsitory.getImageUrl(id);
  }

  public getImagePopoverText(image:Image):string{
    if (image.tags==null || image.tags.length==0){
      return "aucun tags";
    }
    else{
      return "tags: " + image.tags.map(t=>t.libelle).join(",");
    }
  }
  ngOnInit() {
    this.images=new Subject();
    this.souscription=this.imagereporsitory.listeImageAsObservable()
                          .subscribe(p=>{
                            //mettre à jour les liens pour lightbox
                            this.gallerieLiens=[];
                            p.content.forEach(img=>{
                              this.gallerieLiens.push({
                                id:img.id,
                                src:this.imagereporsitory.getImageUrl(img.id),
                                caption: img.fileName
                            });
                            });
                            this.images.next(p.content);
                            this.totalItems=p.totalElements;
                            this.currentPage=p.number+1;
                                        });
    this.imagereporsitory.refreshListe();
  }
  
  public pageChanged(event : any) : void {
    this.imagereporsitory.setNoPage(event.page - 1);
  }
  
  ngOnDestroy(): void {
    this.souscription.unsubscribe();
  }

  public deleteImage(id:number,deleteTemplate: TemplateRef<any>):void{
    console.log("effacement image numero : "+id+" demander");
      this.idToDelete=id;
      this.modalRef = this.modalService.show(deleteTemplate);   

    }
  
  public confirmDelete():void{
    this.modalRef.hide();
    console.log("effacement image numero : "+this.idToDelete+" confirmé");
    this.imagereporsitory.deleteImages([this.idToDelete]);
  }

  public openGallerie(image: Image): void{
    /*let album =[];
    album.push({
      src:this.imagereporsitory.getImageUrl(image.id),
      caption: image.fileName
    });*/
    
    //quelle est la position de l'image cliqué dans l'album
    let position=this.gallerieLiens.findIndex(imglien=> imglien.id==image.id);
    //ouvrir l'album positionné sur la bonne image
    this.lightBox.open(this.gallerieLiens,
                            position,
                          {
                            fadeDuration:0.2,
                            resizeDuration:0.2,
                            showImageNumberLabel:true,
                            wrapAround:true
                          });

  }

}

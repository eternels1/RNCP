import { Component, OnInit } from '@angular/core';
import { ImageService } from "../../services/image.service";
import { ImageFile } from "../../metier/ImageFile";
import { Observable } from "rxjs/Observable";

@Component({
  selector: 'app-image-liste',
  templateUrl: './image-liste.component.html',
  styleUrls: ['./image-liste.component.css']
})
export class ImageListeComponent implements OnInit {

  public imageFiles: Observable<ImageFile[]>;
  constructor(private imageService: ImageService) { }

  ngOnInit() {
    this.imageFiles=this.imageService.getImageFilesObservable();
    this.imageService.refreshListe();   
  }



}

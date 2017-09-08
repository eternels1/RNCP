import { Component, OnInit } from '@angular/core';
import { FileUploader } from 'ng2-file-upload';

@Component({
  selector: 'app-image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.css']
})
export class ImageUploadComponent implements OnInit {

  constructor() { }

  public uploader:FileUploader;
  public hasBaseDropZoneOver:boolean = false;
 
  public fileOverBase(e:any):void {
    this.hasBaseDropZoneOver = e;
  }

  ngOnInit() {
    this.uploader= new FileUploader({url: "http://localhost:8080/epategallerie/api/images/upload"});
  }

}

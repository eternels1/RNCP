import { Injectable } from '@angular/core';
import { Http } from "@angular/http";
import { ImageFile } from "../metier/ImageFile";
import { BehaviorSubject } from "rxjs/BehaviorSubject";
import { Observable } from "rxjs/Observable";
import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";

@Injectable()
export class ImageService {

  private baseUrl:string ="http://localhost:8080/epategallerie/api/";
  private imageSubject: BehaviorSubject<ImageFile[]>;
  constructor(private http: Http) { 
    this.imageSubject=new BehaviorSubject<ImageFile[]>([]);

  }

  public getImageFilesObservable(): Observable<ImageFile[]>{
    return this.imageSubject.asObservable();
  }

  public refreshListe(){
    this.http.get(this.baseUrl+"imagesfull")
              .map(response => response.json() as ImageFile[])
              .toPromise()
              .then(imgs=>{
                this.imageSubject.next(imgs);
              });
  }
}

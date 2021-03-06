import { Injectable } from '@angular/core';
import { HttpInterceptor } from '@angular/common/http/src/interceptor';
import { HttpRequest, HttpHandler, HttpSentEvent, HttpHeaderResponse, HttpProgressEvent, HttpResponse, HttpUserEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AuthManagerService } from './auth-manager.service';
import { Router } from '@angular/router';
import "rxjs/add/operator/catch";
import "rxjs/add/observable/throw";

@Injectable()
export class AuthInterceptorService implements HttpInterceptor {
  
  
  intercept(req: HttpRequest<any>,next: HttpHandler): Observable<HttpSentEvent | HttpHeaderResponse | 
    HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any>> {
     //AVANT envoie de la requette au serveur
    //console.log("requette intercepté: "+req.url);
    if (this.authManager.isLoggedIn()) {
      req=req.clone({setHeaders:{
        Authorization: `Basic ${this.authManager.getCredentials()}`
      }});
    }

     //envoie de la requette au serveur à la suite 
     return next.handle(req).catch((error,caught)=>{
      console.log("erreur de la réponse");
      //console.log(error);
      if (error instanceof HttpErrorResponse) {
        let resp : HttpErrorResponse = error;
        if (resp.status==401) {
          //besoin d'autentification -> allé a la page de login
          this.router.navigateByUrl("/login");
        }
      }
      //retransmition de l'erreur 
      return Observable.throw(error);
});
  }

  constructor(private authManager:AuthManagerService, private router : Router) { }

}

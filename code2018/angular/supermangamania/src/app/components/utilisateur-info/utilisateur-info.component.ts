import { Component, OnInit } from '@angular/core';
import { OnDestroy } from '@angular/core/src/metadata/lifecycle_hooks';
import { Utilisateur } from '../../metier/utilisateur';
import { Subscription } from 'rxjs/Subscription';
import { AuthManagerService } from '../../services/auth-manager.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-utilisateur-info',
  templateUrl: './utilisateur-info.component.html',
  styleUrls: ['./utilisateur-info.component.css']
})
export class UtilisateurInfoComponent implements OnInit,OnDestroy {
  ngOnDestroy(): void {
    this.currentUserSubscription.unsubscribe();
  }
  public isLoggedIn: boolean;
  public currentUser:Utilisateur;
  private currentUserSubscription: Subscription;

  
  constructor(private auteManager: AuthManagerService, private router: Router) { }

  ngOnInit() {
    this.isLoggedIn=this.auteManager.isLoggedIn();
    this.currentUser=this.auteManager.getCurrentUser();
    this.currentUserSubscription=
    this.auteManager.getUtilisateurAsObservable()
                    .subscribe(u=>{
                      this.currentUser=u[1]
                      this.isLoggedIn=u[0]
    
      })
  }
  logMeOut():boolean{
    this.auteManager.logOut();
    this.router.navigateByUrl("/login");
    return false;
  }

}

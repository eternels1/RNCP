import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";

import { AppComponent } from './app.component';
// import { ProfilComponent } from './components/profil/profil.component';
import { ProfilListeComponent } from './components/profil-liste/profil-liste.component';
import { ProfilEditComponent } from './components/profil-edit/profil-edit.component';
import { AboutComponent } from './components/about/about.component';
import { HttpModule } from "@angular/http";
import { RouterModule } from "@angular/router";
import { ProfilService } from "./services/profil.service";
import { TriNomPipe } from './pipes/tri-nom.pipe';

@NgModule({
  declarations: [
    AppComponent,
    // ProfilComponent,
    ProfilListeComponent,
    ProfilEditComponent,
    AboutComponent,
    TriNomPipe
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    BrowserModule,
    RouterModule.forRoot([
      {path:'home',component: ProfilListeComponent},
      {path:'about',component: AboutComponent},
      {path:'edit/:id',component: ProfilEditComponent},
      {path:'',redirectTo:'/home',pathMatch:'full'}
    ])
  ],
  providers: [ProfilService],
  bootstrap: [AppComponent]
})
export class AppModule { }

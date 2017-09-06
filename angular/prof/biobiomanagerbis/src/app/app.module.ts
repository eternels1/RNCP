import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { HttpModule } from "@angular/http";

import { AppComponent } from './app.component';
import { ProduitListeComponent } from './components/produit-liste/produit-liste.component';
import { ProduitEditComponent } from './components/produit-edit/produit-edit.component';
import { ProduitService } from "./services/produit.service";
import { MajusculePipe } from './pipes/majuscule.pipe';
import { WhereStartsWithPipe } from './pipes/where-starts-with.pipe';
import { AboutComponent } from './components/about/about.component';

@NgModule({
  declarations: [
    AppComponent,
    ProduitListeComponent,
    ProduitEditComponent,
    MajusculePipe,
    WhereStartsWithPipe,
    AboutComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    RouterModule.forRoot([
      {path:'home',component: ProduitListeComponent},
      {path:'about',component: AboutComponent},
      {path: 'edit/:id', component : ProduitEditComponent},
      {path:'',redirectTo:'/home',pathMatch:'full'}
    ])
  ],
  providers: [ProduitService],
  bootstrap: [AppComponent]
})
export class AppModule { }

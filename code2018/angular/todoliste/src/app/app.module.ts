import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from "@angular/router";
import{FormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { ListeTacheComponent } from './components/liste-tache/liste-tache.component';
import { SearchTacheComponent } from './components/search-tache/search-tache.component';
import { TodolisteRepositoryService } from './services/todoliste-repository.service';
import { HttpClientModule } from '@angular/common/http';
import { EditTacheComponent } from './components/edit-tache/edit-tache.component';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';

@NgModule({
  declarations: [
    AppComponent,
    ListeTacheComponent,
    SearchTacheComponent,
    EditTacheComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    BsDropdownModule.forRoot(),
    RouterModule.forRoot([
      {path:'liste', component:ListeTacheComponent},
      {path:'edit/:id', component:EditTacheComponent},
      {path:'',redirectTo:'/liste',pathMatch:'full'}
    ])
  ],
  providers: [TodolisteRepositoryService],
  bootstrap: [AppComponent]
})
export class AppModule { }

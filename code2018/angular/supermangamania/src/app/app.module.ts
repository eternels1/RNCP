import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import{HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import{FormsModule} from '@angular/forms';
import { RouterModule } from "@angular/router";
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { FileUploadModule } from "ng2-file-upload";
import { ProgressbarModule } from 'ngx-bootstrap/progressbar';
import { ModalModule } from 'ngx-bootstrap/modal';

import { AuthInterceptorService } from "./services/auth-interceptor.service";
import { AuthManagerService } from './services/auth-manager.service';


import { AppComponent } from './app.component';
import { MangaRepositoryService } from './services/manga-repository.service';
import { HttpClient } from '@angular/common/http/src/client';
import { SearchMangaComponent } from './components/search-manga/search-manga.component';
import { ListeMangaComponent } from './components/liste-manga/liste-manga.component';
import { EditMangaComponent } from './components/edit-manga/edit-manga.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { LoginComponent } from './components/login/login.component';
import { UtilisateurInfoComponent } from './components/utilisateur-info/utilisateur-info.component';
import { RegisterComponentComponent } from './components/register-component/register-component.component';


@NgModule({
  declarations: [
    AppComponent,
    SearchMangaComponent,
    ListeMangaComponent,
    EditMangaComponent,
    NavBarComponent,
    LoginComponent,
    UtilisateurInfoComponent,
    RegisterComponentComponent
  ],
  imports: [
    BrowserModule,
    PaginationModule.forRoot(),
    HttpClientModule,
    FormsModule,
    FileUploadModule,
    ProgressbarModule.forRoot(),
    BsDropdownModule.forRoot(),
    ModalModule.forRoot(),
    RouterModule.forRoot([
      {path:'liste', component:ListeMangaComponent},
      {path:'edit/:id', component:EditMangaComponent},
      {path:'login',component:LoginComponent},
      {path:'register',component:RegisterComponentComponent},
      {path:'', redirectTo:'/liste', pathMatch:'full'}
    ])
  ],
  providers: [MangaRepositoryService,AuthManagerService,
    {
      provide:HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

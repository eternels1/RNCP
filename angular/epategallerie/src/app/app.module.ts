import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { HttpModule } from "@angular/http";
import { ImageService } from "./services/image.service";
import { FileUploadModule } from "ng2-file-upload";
import { TruncatePipe } from 'angular-pipes/src/string/truncate.pipe';
import { BytesPipe } from 'angular-pipes/src/math/bytes.pipe';

import { AppComponent } from './app.component';
import { ImageListeComponent } from './components/image-liste/image-liste.component';
import { ImageEditComponent } from './components/image-edit/image-edit.component';
import { ImageUploadComponent } from './components/image-upload/image-upload.component';

@NgModule({
  declarations: [
    AppComponent,
    ImageListeComponent,
    ImageEditComponent,
    ImageUploadComponent,
    TruncatePipe,
    BytesPipe
  ],
  imports: [
    BrowserModule,
    FileUploadModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {path:'home',component: ImageListeComponent},
      {path:'edit/:id',component: ImageEditComponent},
      {path:'upload',component: ImageUploadComponent},
      {path:'',redirectTo: "/home",pathMatch:'full'}

    ])
  ],
  providers: [ImageService],
  bootstrap: [AppComponent]
})
export class AppModule { }

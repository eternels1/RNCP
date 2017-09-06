import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";

import { AppComponent } from './app.component';
import { ClienteditComponent } from './components/clientedit/clientedit.component';
import { ClientlisteComponent } from './components/clientliste/clientliste.component';
import { ClientService } from "./services/client.service";

@NgModule({
  declarations: [
    AppComponent,
    ClienteditComponent,
    ClientlisteComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [ClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }

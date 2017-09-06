import { Component,OnInit } from '@angular/core';
import { ClientService } from "./services/client.service";
import { Client } from "./metier/client";
import { Observable } from "rxjs/Observable";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers:[ClientService]
})
export class AppComponent implements OnInit{
  
  title = 'app';
  compteur : number =0;
  clients : Observable<Client[]>;
  editClient : Client;

  constructor(private clientService : ClientService){}

  incrementeCompteur():void {
    this.compteur++;
  }
  ngOnInit(): void {
    this.editClient=new Client(0,"anonyme","noemail@none.com");
    this.clients= this.clientService.listenClients();
  }

  ajouteClient(client: Client){
    console.log(client);
    this.clientService.addClient(client);
    //this.clients=this.clientService.getClients();
  }

}

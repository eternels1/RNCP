import { Component, OnInit } from '@angular/core';
import { ClientService } from "../../services/client.service";
import { Observable } from "rxjs/Observable";
import { Client } from "../../metier/client";

@Component({
  selector: 'app-clientliste',
  templateUrl: './clientliste.component.html',
  styleUrls: ['./clientliste.component.css']
})
export class ClientlisteComponent implements OnInit {


  public clients : Observable<Client[]>;

  constructor(private clientService: ClientService) { }

  ngOnInit() {
    this.clients=this.clientService.listenClients();    
  }

  suppression(id:number):void{
    this.clientService.deleteClient(id);    
  }
  edition(id:number):void{
    this.clientService.editerClient(id);    
  }

}

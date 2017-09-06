import { Component, OnInit } from '@angular/core';
import { ClientService } from "../../services/client.service";
import { Client } from "../../metier/client";
import { Observable } from "rxjs/Observable";

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {

  nbclients: number=0;
  clients : Observable<Client[]>;
  constructor(private clientServices : ClientService) { }

  ngOnInit() {
    this.clients=this.clientServices.listenClients();
    this.clients.subscribe(tc=>{
      this.nbclients=tc.length;
    })
  }

}

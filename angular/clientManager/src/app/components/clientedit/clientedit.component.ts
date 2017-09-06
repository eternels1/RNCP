import { Component, OnInit } from '@angular/core';
import { ClientService } from "../../services/client.service";
import { Client } from "../../metier/client";

@Component({
  selector: 'app-clientedit',
  templateUrl: './clientedit.component.html',
  styleUrls: ['./clientedit.component.css']
})
export class ClienteditComponent implements OnInit {

  private editClient : Client;
  private isnewClient:boolean;

  constructor(private clientService: ClientService) {
    this.editClient= new Client(0,"anonyme",100.0,"email@gmail.com");
    this.isnewClient=true;
  }

  ngOnInit() {
    this.clientService.listenEditClient()
                      .subscribe(c=>{
                            this.editClient=c;
                            this.isnewClient=false;
                          });
  }
sauverClient(client:Client):void{
  if(this.isnewClient){
    this.clientService.addClient(client);
    this.isnewClient=false;
  }
}
nouveauClient():void{
  this.editClient= new Client(0,"anonyme",100.0,"email@gmail.com");
  this.isnewClient=true;
}
}

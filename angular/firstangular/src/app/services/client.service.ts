import { Injectable } from '@angular/core';
import { Client } from "../metier/client";
import { Observable } from "rxjs/Observable";
import { Subject } from "rxjs/Subject";

@Injectable()
export class ClientService {

  private clients: Client[]=[];
  private clientsubject: Subject<Client[]>;
  

  constructor() { 
    this.clientsubject= new Subject<Client[]>();
    this.clientsubject.next(this.clients);
  }

  listenClients():Observable<Client[]>{
    return this.clientsubject.asObservable();
  }

  getClients(): Client[] {

    return this.clients.slice();
  }

  addClient(c: Client): void{
    this.clients.push(c);
    this.clientsubject.next(this.clients);
  }

}

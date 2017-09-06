import { Injectable } from '@angular/core';
import { Client } from "../metier/client";
import { Observable } from "rxjs/Observable";
import { Subject } from "rxjs/Subject";

@Injectable()
export class ClientService {

  private clients: Client[]=[];
  private clientsubject: Subject<Client[]>;
  private editClientSubject : Subject<Client>;
  

  constructor() { 
    this.clientsubject= new Subject<Client[]>();
    this.clientsubject.next(this.clients);
    this.editClientSubject= new Subject<Client>();
  }

  listenEditClient():Observable<Client>{
    return this.editClientSubject.asObservable();
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

editerClient(id:number):void{
  let pos:number = this.clients.findIndex(c=>c.id==id);
  if(pos!=-1){
    let c = this.clients[pos];
    this.editClientSubject.next(c);
  }
}

  deleteClient(id:number) : void{
    let pos: number =this.clients.findIndex(c=> c.id==id);
    if(pos!=-1){
      this.clients.splice(pos,1);
      this.clientsubject.next(this.clients);
    }    
  }

}

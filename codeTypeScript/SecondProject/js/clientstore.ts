import { Client } from "./client";

export class Clientstore{
    private clients :Array<Client>=[];
    constructor(){

    } 

    addClient(client:Client):void{
        this.clients.push(client);
    }

    clearClient():void{
        this.clients=[];
    }
    removeClient(id:number):void{
        let pos: number=
        this.clients.findIndex(c =>c.id==id);
        if (pos!=-1){
            this.clients.splice(pos,1);
        }
    }

    getClients():Array<Client>{
        return this.clients;
    }
}
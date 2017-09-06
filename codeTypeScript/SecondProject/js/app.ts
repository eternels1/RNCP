import { Client } from "./client";
import { Clientstore } from "./clientstore";

export class App{
    private clientstore: Clientstore;
    private div_client: HTMLElement;
    private add_button: HTMLElement;
    private champ_id:HTMLInputElement;
    private champ_nom:HTMLInputElement;
    private champ_prenom:HTMLInputElement;
    private champ_email:HTMLInputElement;

    constructor(){
        this.clientstore= new Clientstore();
    }
 initPage() : void{
    this.div_client=document.getElementById("listeClient");
    this.add_button=document.getElementById("ajoutClient");
    this.add_button.addEventListener("click",e => this.ajouterClient(e));
    this.champ_id= <HTMLInputElement>document.getElementById("idClient");
    this.champ_nom= <HTMLInputElement>document.getElementById("nomClient");
    this.champ_prenom= <HTMLInputElement>document.getElementById("prenomClient");
    this.champ_email= <HTMLInputElement>document.getElementById("emailClient");

    this.clientstore.addClient(new Client(1,"smith","sangoku","supersayan@kemeha.com"));
    this.clientstore.addClient(new Client(2,"krilin","krilin","supersayan@kemeha.com"));
    this.refreshListe();    
}

ajouterClient(event:Event): void{
console.log("ajout demandé...");
let client = new Client(parseInt(this.champ_id.value),
                        this.champ_nom.value,
                        this.champ_prenom.value,
                        this.champ_email.value);
this.clientstore.addClient(client);
this.refreshListe();

}


refreshListe():void{
let clients=this.clientstore.getClients();
let html ="";
clients.forEach((client,index)=>{
    html+= 
`<div class="div_client" data-cid="${client.id}">
    <h3>${client.nom} - ${client.prenom}</h3>
    <p>contact : ${client.email} id: ${client.id}</p>
    </div>`;
});
html +="</ul>";
this.div_client.innerHTML=html;
let divs= document.getElementsByClassName("div_client");
let self=this;
for(let index=0; index<divs.length;index++){
    divs.item(index).addEventListener("click", function(e)
    {
self.removeClient(this);
    });
}

}

removeClient(div:HTMLElement){
    let cid= parseInt(div.getAttribute("data-cid"));
    console.log("remove..."+cid);
    this.clientstore.removeClient(cid);
    this.refreshListe();
    

}

}

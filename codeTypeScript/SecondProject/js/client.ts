export class Client{
    constructor(public id: number,
        public nom:string, 
        public prenom:string,
         public email:string){

    }
    toString():string{
        return `client : ${this.nom}, ${this.prenom} : email ${this.email}`;
    }
}
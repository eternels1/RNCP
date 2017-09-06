class Humain{
    protected prenom:string;
    private nom:string;
    constructor(nom:string,prenom:string){
        this.nom=nom;
        this.prenom=prenom;
    }
    public getPrenom(): string { return this.prenom}
    public getNom(): string {return this.nom}

    saluer(): string {
        return `Bonjour, ${this.nom} ${this.prenom}`;
    }
}

let h1: Humain= new Humain("bob","eponge");
console.log(h1.saluer());

class Client extends Humain{
    public email : string;
    constructor (nom:string,prenom:string,email:string){
        super(nom,prenom);
        this.email=email;
    }

saluer(): string{
    return super.saluer()+` cher client ${this.email}`;
}
}

let cl1 : Client = new Client("etoile","patrick","patrick@bikinibotom.com");

console.log(cl1.saluer());

abstract class Figure{
    x:number;
    y:number;
    constructor(x:number,y:number){
        this.x=x;
        this.y=y;
    }
    abstract afficher():string;
}

let fig1 : Figure;

class Triangle extends Figure{
    taille: number;
    constructor(x:number,y:number, taille: number){
        super(x,y);
        this.taille=taille;
    }
    afficher():string{
        return `triangle de taille ${this.taille} 
        placé à ${this.x},${this.y}`;
    }

}
fig1=new Triangle(4,5,15);
console.log(fig1.afficher());

class Livre{
    constructor(private titre:string, 
                private auteur:string, 
                public nbpage:number){
    }
    afficher():string{
        return `livre : ${this.titre} écris par 
        ${this.auteur}, ${this.nbpage} pages`;
    }
}

let lv1:Livre = new Livre("le seigneur des anneaux", "tolkien",1500);
console.log(lv1.afficher());


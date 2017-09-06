console.log("bonjour TypeScript");
//les variable en typscript sont typées
//on les ceclare avec le mot clé let

let isFinished : boolean =false;
console.log("isFinished = "+isFinished);

let monNom = "khalid";
// impossible car typé en string -> monNom=42;

let dinamique : any =15;
dinamique="hoho";
console.log(dinamique);

let jours : string[] = ["lundi", "mardi","mercredi"];
console.log(jours);
function addition(x:number,y:number) : number{
    return x+y;
} 

console.log(addition(4,5));

class Personne{
    private nom : string;
    private prenom: string;

    constructor(nom: string, prenom: string){
        this.nom=nom;
        this.prenom=prenom;
    }
    saluer() : string {
        return "bonjour, "+ this.prenom;
    }

    public getNom() : string {return this.nom;}    
    public get getPrenom() : string {return this.prenom;}    
}

let p1 : Personne = new Personne("bob","joe");
console.log(p1.saluer());
console.log("toto");

let chaine1: string ="bonjour world";
let chaine2: string ='bonjour world';
let nameino : string = "khalid";
let chaine3: string =
`bonjour world,
${nameino}
comment ca va?`;

console.log(chaine3);

let tauxTVA: Array<number> =[5.5,10.0,20.0];
console.log(tauxTVA);

enum Color {rouge, vert, bleu, jaune};
let c: Color= Color.jaune;
console.log(`${c} : ${Color[c]}`);

let vile_cp : [string,number]=["paris",75000];
let destinations : Array<[string,number]>=
[
    ["paris", 75000],
    ["toulouse",31000],
    ["La Rochelle",17000]
];
console.log(destinations);

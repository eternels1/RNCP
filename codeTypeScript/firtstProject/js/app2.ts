function addition(x:number, y:number) : number{
    return x+y;
};

let a=10;
let b=15;
let result=addition(a,b);
console.log(result);

interface Denomination {
    nom: string;
    prenom : string;
}

function salutation (identite : Denomination) : void{
    console.log(`Bonjour, 
    ${identite.nom} ${identite.prenom}
    Quoi de neuf? ca va?`);
    
}

salutation({nom:"courtalon",prenom:"vincent"});

interface Societe {
    nom:string;
    siret:string;
    objet ? :string;
}

function afficher(infos: Societe) : void{
    console.log(`${infos.nom}, ${infos.siret}
    but : ${(infos.objet?infos.objet:"inconnu")}`);


    
}

afficher(
    {nom:"edugroup",siret:"124645", objet:"formation"});

    afficher(
        {nom:"edugroup",siret:"124645"}
    );


interface Produit {
    readonly id: number;
    nom : string;
    prix :number;
}

let prod1: Produit={id:1, nom:"steak de lama", prix:29.99};
prod1.prix=39.99;
//prod1.id=2;
console.log(prod1);

interface OperationMathematique {
    (valeur:number) : number;
}

let carre : OperationMathematique ;

carre= function(valeur:number): number{
    return valeur*valeur;
}

console.log(carre(6));

interface MapLectuerSeul{
    readonly [index:string]:string;
}

let capitales : MapLectuerSeul={
    "france":"paris",
    "espagne":"madrid",
    "italie":"rome"
};
console.log(capitales["espagne"]);
//capitales["angleterre"]="londres";

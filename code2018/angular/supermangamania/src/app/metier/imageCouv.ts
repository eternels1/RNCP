import { Manga } from "./manga";

export class ImageCouv{
    constructor(public id: number,
                public fileName: string,
                public fileSize:number,
                public storageId: string,
                public contentType: string,
                public superMangas: Manga[]){}
}
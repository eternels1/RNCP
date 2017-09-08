import { Tag } from "./Tag";

export class ImageFile {
    
    constructor(public id: number,public name:string, public filename: string,
                    public contentType:string, public fileSize:number,
                    public hashMD5?:string, public tags?:Tag[]){}
}

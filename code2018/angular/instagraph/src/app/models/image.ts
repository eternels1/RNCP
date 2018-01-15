import { Tag } from "./tag";

export class Image{
    constructor(public id:number,
                public name:string,
                public descrciption:string,
                public dateAdded:Date,
                public fileName:string,
                public contentType:string,
                public width:number,
                public height:number,
                public tags?:Tag[]){}

    
}
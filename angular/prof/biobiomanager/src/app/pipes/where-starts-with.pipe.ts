import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'whereStartsWith',
  pure:false
})
export class WhereStartsWithPipe implements PipeTransform {

  transform(value: any[], propname:string,propvalue : string): any[] 
  {console.log("filtre "+propvalue);
  
    if (Array.isArray(value) && value !=null) {
      let filtered= value.filter((data,pos)=>{
         if (typeof(data[propname])=='string'
              && data [propname]!= null
              && (<string>data[propname]).startsWith(propvalue)) {
           return true;
         }
         else 
          return false;
      });
      console.log( filtered);
      
      return filtered;
    }
    else
      return value;
  }

}

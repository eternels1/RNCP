import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'majuscule'
})
export class MajusculePipe implements PipeTransform {

  transform(value: string, args?: any): string {
    if (typeof(value)!='undefined' && value != null) {
         return value.toUpperCase();; 
    }
       return value;
  }

}

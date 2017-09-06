import { Pipe, PipeTransform } from '@angular/core';
import { Profil } from "../metier/profil";

@Pipe({
  name: 'triNom'
})
export class TriNomPipe implements PipeTransform {

  transform(value: Profil[], varTri: string): Profil[] {
    if (Array.isArray(value)) {
      return value.sort((a,b)=>{
        if (a[varTri]>b[varTri]) {
          return 1;
        }else if(a[varTri]<b[varTri]){
          return -1;
        }
        else return 0;
      });
    }
    return value;
  }

}

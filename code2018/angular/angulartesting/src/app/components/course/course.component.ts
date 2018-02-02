import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {


 
  coursesListe: [string,number][]=[];
  constructor() {
    this.coursesListe.push(["acheter des pdt",2],["acheter un velo",2],["acheter à boire",2]);
   }

  ngOnInit() {
  }

  addCourse(){
    let random = Math.random() * 2;
    this.coursesListe.push(["courseAjouter",10*random]);
  }

  clearListeCourse(){
    this.coursesListe=[];
  }
}

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseComponent } from './course.component';

describe('CourseComponent', () => {
  let component: CourseComponent;
  let fixture: ComponentFixture<CourseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  it('test Ajout Course', async(() => {
    const fixture = TestBed.createComponent(CourseComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    const app = fixture.debugElement.componentInstance;
    compiled.querySelector('#testAdd').click();
    expect(app.coursesListe[app.coursesListe.length-1][0]).toEqual("courseAjouter");
  }));

  it('test Ajout Course', async(() => {
    const fixture = TestBed.createComponent(CourseComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    const app = fixture.debugElement.componentInstance;
    compiled.querySelector('#testAdd').click();
    expect(compiled.querySelectorAll("tr.courseelements ").length).toEqual(3);
  }));

});

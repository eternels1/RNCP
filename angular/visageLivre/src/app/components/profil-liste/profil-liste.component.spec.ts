import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilListeComponent } from './profil-liste.component';

describe('ProfilListeComponent', () => {
  let component: ProfilListeComponent;
  let fixture: ComponentFixture<ProfilListeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfilListeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfilListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

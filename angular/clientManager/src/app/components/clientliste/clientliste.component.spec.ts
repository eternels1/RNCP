import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientlisteComponent } from './clientliste.component';

describe('ClientlisteComponent', () => {
  let component: ClientlisteComponent;
  let fixture: ComponentFixture<ClientlisteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientlisteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientlisteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

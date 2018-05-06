import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAreasComponent } from './create-areas.component';

describe('CreateAreasComponent', () => {
  let component: CreateAreasComponent;
  let fixture: ComponentFixture<CreateAreasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateAreasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateAreasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

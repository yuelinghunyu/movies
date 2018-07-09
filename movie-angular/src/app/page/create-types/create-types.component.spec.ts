import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTypesComponent } from './create-types.component';

describe('CreateTypesComponent', () => {
  let component: CreateTypesComponent;
  let fixture: ComponentFixture<CreateTypesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateTypesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateTypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

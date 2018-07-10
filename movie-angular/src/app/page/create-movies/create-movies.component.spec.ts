import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMoviesComponent } from './create-movies.component';

describe('CreateMoviesComponent', () => {
  let component: CreateMoviesComponent;
  let fixture: ComponentFixture<CreateMoviesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateMoviesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateMoviesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

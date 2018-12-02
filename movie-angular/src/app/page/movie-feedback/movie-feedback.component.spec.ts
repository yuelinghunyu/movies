import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieFeedbackComponent } from './movie-feedback.component';

describe('MovieFeedbackComponent', () => {
  let component: MovieFeedbackComponent;
  let fixture: ComponentFixture<MovieFeedbackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieFeedbackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

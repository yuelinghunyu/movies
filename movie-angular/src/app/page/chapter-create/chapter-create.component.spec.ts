import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChapterCreateComponent } from './chapter-create.component';

describe('ChapterCreateComponent', () => {
  let component: ChapterCreateComponent;
  let fixture: ComponentFixture<ChapterCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChapterCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChapterCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

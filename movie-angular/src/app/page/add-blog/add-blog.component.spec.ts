import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBlogComponent } from './add-blog.component';

describe('AddBlogComponent', () => {
  let component: AddBlogComponent;
  let fixture: ComponentFixture<AddBlogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddBlogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBlogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

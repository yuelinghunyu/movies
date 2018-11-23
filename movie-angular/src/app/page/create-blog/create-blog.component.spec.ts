import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateBlogComponent } from './create-blog.component';

describe('CreateBlogComponent', () => {
  let component: CreateBlogComponent;
  let fixture: ComponentFixture<CreateBlogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateBlogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateBlogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BannerAddOrUpdateComponent } from './banner-add-or-update.component';

describe('BannerAddOrUpdateComponent', () => {
  let component: BannerAddOrUpdateComponent;
  let fixture: ComponentFixture<BannerAddOrUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BannerAddOrUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BannerAddOrUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

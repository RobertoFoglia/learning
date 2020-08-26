import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerdatamanagementComponent } from './customerdatamanagement.component';

describe('CustomerdatamanagementComponent', () => {
  let component: CustomerdatamanagementComponent;
  let fixture: ComponentFixture<CustomerdatamanagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerdatamanagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerdatamanagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

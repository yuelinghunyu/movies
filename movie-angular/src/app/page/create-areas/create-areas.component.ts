import { Component, OnInit } from '@angular/core';
import { trigger,state,style,animate,transition} from '@angular/animations';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-create-areas',
  templateUrl: './create-areas.component.html',
  styleUrls: ['./create-areas.component.css'],
  animations:[
    trigger('panel-left',[
      state('inactive', style({
        left:'-400px'
      })),
      state('active',   style({
        left:'0'
      })),
      transition('inactive => active', animate('200ms ease-in')),
      transition('active => inactive', animate('200ms ease-out'))
    ]),
  ]
})
export class CreateAreasComponent implements OnInit {
  private state:string;
  areasForm: FormGroup;
  constructor(
    private fb: FormBuilder
  ) {
    this.createAreasForm();
   }
  
  createAreasForm(){
    this.areasForm = this.fb.group({
      
    })
  }
  ngOnInit() {
    this.state = 'inactive';
  }
  addPanel(){
    this.state = this.state === 'active' ? 'inactive' : 'active';
  }
}

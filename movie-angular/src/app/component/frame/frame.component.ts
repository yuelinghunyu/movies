import { Component, OnInit } from '@angular/core';
import { trigger,state,style,animate,transition} from '@angular/animations';

@Component({
  selector: 'app-frame',
  templateUrl: './frame.component.html',
  styleUrls: ['./frame.component.css'],
  animations:[
    trigger('expend-left',[
      state('inactive', style({
        left:'-250px'
      })),
      state('active',   style({
        left:'0px'
      })),
      transition('inactive => active', animate('200ms ease-in')),
      transition('active => inactive', animate('200ms ease-out'))
    ]),
    trigger('expend-right',[
      state('inactive', style({
        paddingLeft:'0px'
      })),
      state('active',   style({
        paddingLeft:'250px'
      })),
      transition('inactive => active', animate('200ms ease-in')),
      transition('active => inactive', animate('200ms ease-out'))
    ])
  ]
})
export class FrameComponent implements OnInit {
  private state:string;
  constructor() { }

  ngOnInit() {
    this.state = 'active'
  }
  toggleState(){
    this.state = this.state === 'active' ? 'inactive' : 'active';
  }
}

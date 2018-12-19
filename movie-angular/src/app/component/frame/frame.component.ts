import { Component, OnInit } from '@angular/core';
import { trigger,state,style,animate,transition} from '@angular/animations';
import { Router,ActivatedRoute } from '@angular/router';
import { ServiceService } from '../../service/service.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/forkJoin';
import { Person } from './person';

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
  public state:string;
  public listNone:boolean = false;
  loginId:number;
  person:Person = Person.personDefault;
  constructor(
    public router:Router,
    public activatedRoute:ActivatedRoute,
    public service:ServiceService
  ) { }

  ngOnInit() {
    this.state = 'active';
    // this.activatedRoute.queryParams.subscribe(queryParams =>{
    //   this.loginId = queryParams.personId;
    // })
    // const loginParam = {
    //   id:this.loginId
    // }
    // let personItem = this.service.getPersonItem(loginParam);
    // Observable.forkJoin([personItem]).subscribe(res=>{
    //   const personItem = JSON.parse(res[0]["_body"]).data;
    //   this.person.id = personItem["id"];
    //   this.person.img = personItem["img"];
    //   this.person.userName = personItem["userName"];
    //   this.person.passWord = personItem["passWord"];
    //   this.person.createDate = personItem["createDate"];
    // })
    const personItem = JSON.parse(localStorage.getItem("person"));
    if(personItem === null){
      return this.router.navigateByUrl("/login")
    }
    this.person.id = personItem["id"];
    this.person.img = personItem["img"];
    this.person.userName = personItem["userName"];
    this.person.passWord = personItem["passWord"];
    this.person.createDate = personItem["createDate"];
  }
  toggleState(){
    this.state = this.state === 'active' ? 'inactive' : 'active';
  }
  listToggle(){
    this.listNone = !this.listNone;
  }
  listRedirct(flag){
    if(flag == "personel"){
      this.router.navigateByUrl('/frame/personel');
    }else{
      if(localStorage.getItem("accessToken") !== null || localStorage.getItem("person")!== null){
        localStorage.removeItem("accessToken");
        localStorage.removeItem("person");
      }
      if(localStorage.getItem("accessToken") === null && localStorage.getItem("person") === null){
        this.router.navigateByUrl('/login');
      }
    }
  }
}

import { Component, Injectable, OnInit } from '@angular/core';
import { trigger,state,style,animate,transition} from '@angular/animations';
import { FormArray, FormBuilder, FormGroup,Validators} from '@angular/forms';
import { ForbiddenNameValidor } from '../../shared/forbidden-name.directive';
import { ServiceService } from '../../service/service.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  animations: [
    trigger('scaleX', [
      state('focus', style({
        width: '100%'
      })),
      state('blur', style({
        width: '0px'
      })),
      transition('blur => focus', animate('100ms ease-in')),
      transition('focus => blur', animate('100ms ease-out'))
    ]),
    trigger('logining', [
      state('initial', style({
        width: '*',
      })),
      state('activing', style({
        width: '39px',
        background: 'url("../../../assets/imgs/loading2.gif") no-repeat center',
        backgroundSize: 'cover',
        backgroundColor: '#658cc8'
      })),
      transition('initial => activing', animate('100ms ease-in'))
    ]),
    trigger('judgeU', [
      state('noKey', style({
        background: 'url("../../../assets/imgs/rigOrWro.png") no-repeat center 32px'
      })),
      state(('keying'), style({
        background: 'url("../../../assets/imgs/rigOrWro.png") no-repeat center -0px'
      })),
      state(('keyed'), style({
        background: 'url("../../../assets/imgs/rigOrWro.png") no-repeat center -36px'
      })),
      transition('noKey => keying', animate('100ms ease-in')),
      transition('noKey => keyed', animate('100ms ease-in'))
    ]),
    trigger('judgeP', [
      state('noKey', style({
        background: 'url("../../../assets/imgs/rigOrWro.png") no-repeat center 32px'
      })),
      state(('keying'), style({
        background: 'url("../../../assets/imgs/rigOrWro.png") no-repeat center -0px'
      })),
      state(('keyed'), style({
        background: 'url("../../../assets/imgs/rigOrWro.png") no-repeat center -36px'
      })),
      transition('noKey => keying', animate('100ms ease-in')),
      transition('noKey => keyed', animate('100ms ease-in'))
    ])
  ]
})
export class LoginComponent implements OnInit {
  userNameState: String;
  passWordState: String;
  userNameJudge: String;
  passWordJudge: String;
  loginingState: String;
  loginForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private router: Router,
    public service:ServiceService
  ) {
    this.createForm();
   }

  // 创建响应式表单初始化;
  createForm() {
    this.loginForm = this.fb.group({
      // userName: ['', [Validators.required, ForbiddenNameValidor(/^[a-zA-Z0-9_-]{4,16}$/i)]], // iFat3
      // password: ['', [Validators.required, ForbiddenNameValidor(/^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/)]] // iFat3#
      userName: ['', Validators.required], // iFat3
      password: ['', Validators.required] // iFat3#
    });
  }
  ngOnInit() {
    this.userNameState = 'blur';
    this.passWordState = 'blur';
    this.loginingState = 'initial';
    this.userNameJudge = 'noKey';
    this.passWordJudge = 'noKey';
  }

   // 失焦、聚焦、键入事件;
   inputFocus(ev) {
    const id = ev.target.id;
    if (id === 'username') {
      this.userNameState = 'focus';
    }
    if (id === 'password') {
      this.passWordState = 'focus';
    }
  }
  inputBlur(ev) {
    const id = ev.target.id;
    if ( id === 'username') {
      this.userNameState = 'blur';
    }
    if (id === 'password') {
      this.passWordState = 'blur';
    }
  }
  inputKeyU(ev) {
    if (this.loginForm.get('userName').valid) {
      this.userNameJudge = 'keying';
    }else {
      this.userNameJudge = 'keyed';
    }
  }
  inputKeyP(ev) {
    if (this.loginForm.get('password').valid) {
      this.passWordJudge = 'keying';
    }else {
      this.passWordJudge = 'keyed';
    }
  }
  login(ev) {
    if (this.loginForm.valid) {
      ev.target.innerHTML = '';
      this.loginingState = 'activing';
      this.service.loginMovies(this.loginForm.value).subscribe((res)=>{
        let data = JSON.parse(res["_body"]);
        if(data.code == 0){
          localStorage.setItem("accessToken",data.data.accessToken.access_token);
          localStorage.setItem("person",JSON.stringify(data.data.person));
          if(localStorage.getItem("accessToken") !== null && localStorage.getItem("person")!= null){
             this.router.navigateByUrl("/home");
          }
        }else{
          this.loginingState = 'initial';
          ev.target.innerHTML = '登 录';
        }
      })
    }
  }
}

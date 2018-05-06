import { Component, OnInit } from '@angular/core';
import { ServiceService } from '../../service/service.service';

@Component({
  selector: 'app-personal',
  templateUrl: './personal.component.html',
  styleUrls: ['./personal.component.css']
})
export class PersonalComponent implements OnInit {
  private personList = [];
  private person = {};
  constructor(public service:ServiceService) {}

  ngOnInit() {
    this._getPersonList();
  }
  //获取个人列表;
  _getPersonList(){
    this.service.getPersonList().subscribe(res=>{
      let data = JSON.parse(res["_body"]);
      if(data.code == 1){
        let list = data.data;
        this.personList = list;
        this.person = this.personList[0];//第一个
      }
    })
  }
  changePerson(i){
    this.person = this.personList[i];
  }
}

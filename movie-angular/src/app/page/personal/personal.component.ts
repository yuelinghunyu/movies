import { Component, OnInit } from '@angular/core';
import { ServiceService } from '../../service/service.service';
import { Person } from '../../component/frame/person';

@Component({
  selector: 'app-personal',
  templateUrl: './personal.component.html',
  styleUrls: ['./personal.component.css']
})
export class PersonalComponent implements OnInit {
  private personList = [];
  persons:Person = Person.personDefault;
  constructor(public service:ServiceService) {}

  ngOnInit() {
    this._getPersonList();
  }
  //获取个人列表;
  _getPersonList(){
    this.service.getPersonList().subscribe(res=>{
      let data = JSON.parse(res["_body"]);
      if(data.code == 0){
        let list = data.data;
        this.persons = list[0];//第一个
        this.personList = list;
      }
    })
  }
  changePerson(i){
    this.persons = this.personList[i];
  }
}

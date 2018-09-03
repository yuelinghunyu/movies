import { Component, OnInit } from '@angular/core';
import { ServiceService } from '../../service/service.service';
import { Person } from '../../component/frame/person';
import { Config} from "../../config/config";

@Component({
  selector: 'app-personal',
  templateUrl: './personal.component.html',
  styleUrls: ['./personal.component.css']
})
export class PersonalComponent implements OnInit {
  private personList = [];
  persons:Person = Person.personDefault;
  Config:Config = new Config();
  constructor(public service:ServiceService) {}

  ngOnInit() {
    this._getPersonList();
  }
  //获取个人列表;
  _getPersonList(){
    this.service.getPersonList().subscribe(res=>{
      if(res["code"] === this.Config.ERROR_OK){
        let list = res["data"];
        this.persons = list[0];//第一个
        this.personList = list;
      }
    })
  }
  changePerson(i){
    this.persons = this.personList[i];
  }
}

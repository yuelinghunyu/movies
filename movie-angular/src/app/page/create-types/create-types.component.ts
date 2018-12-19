import { Component, OnInit, Output } from '@angular/core';
import { trigger,state,style,animate,transition} from '@angular/animations';
import { FormArray, FormBuilder, FormGroup,Validators} from '@angular/forms';
import { ForbiddenNameValidor } from '../../shared/forbidden-name.directive';
import { ServiceService } from '../../service/service.service';
import { Pagination } from "../../common/pagination/pagination";
import { Modal } from '../../common/modal/modal';
import { Type } from './types';
import { Config} from "../../config/config";

@Component({
  selector: 'app-create-types',
  templateUrl: './create-types.component.html',
  styleUrls: ['./create-types.component.css'],
  animations:[
    trigger('panel-left',[
      state('inactive', style({
        left:'-400px',
        display:'none'
      })),
      state('active',   style({
        left:'0'
      })),
      transition('inactive => active', animate('200ms ease-in')),
      transition('active => inactive', animate('200ms ease-out'))
    ]),
  ]
})
export class CreateTypesComponent implements OnInit {
  @Output()
  public pagination:Pagination = Pagination.defaultPagination;
  public modal:Modal = Modal.modal;
  public type:Type = Type.defaultType;
  public state:string;
  public typeList:Array<any>;
  public total:number;
  typesForm: FormGroup;
  Config:Config = new Config();
  constructor(
    public fb: FormBuilder,
    public service:ServiceService
  ) { 
    this.createTypesForm();
  }

  ngOnInit() {
    this.state = 'inactive';
    this.initList();
    this.pagination.changePage = (()=>{
      this.initList();
    });
  }
  //弹框出现;
  public alertModalItem(id:string){
    this.modal.tips = "是否删除该项？";
    this.modal.id = id;
    this.modal.changeEvent=((id:string)=>{
      this.deleteTypeItem(id);
    })
  }
  //删除一条记录;
  public deleteTypeItem(id:string){
    this.modal.flag = true;
    this.modal.tips = "正在删除，请稍后...";
    const param = {
      "id":id
    }
    this.service.deleteTypeOne(param).subscribe(res=>{
      if(res["code"] === this.Config.ERROR_OK){
        this.modal.tips = "删除成功！";
        $("#tipModal").modal('hide');
        this.modal.flag = false;
        this.initList();
      }
    })
  }
  //获取数据列表;
  public initList():void{
    const param = {
      "page":this.pagination.currentPage,
      "limit":this.pagination.pageItems
    }
    this.service.getTypesList(param).subscribe(res=>{
      if(res["code"] === this.Config.ERROR_OK){
        const data = res["data"];
        this.typeList = data.list;
        this.total = data.total;
        this.pagination.totalItems = this.total;
      }
    });
  }
 
  //表单验证；
  createTypesForm(){
    this.typesForm = this.fb.group({
      typeType:['',[Validators.required,ForbiddenNameValidor(/^[0-9]*$/)]],
      typeTitle:['',[Validators.required]]
    })
  }
  //新增类型
  createType(id:string){
    console.log(id);
    const type = this.typesForm.get('typeType').value;
    const title = this.typesForm.get('typeTitle').value;
    const typeS = {
      'id':id,
      'type':type,
      'title':title
    }
    this.service.createTypeItem(typeS).subscribe(res=>{
      if(res["code"] === this.Config.ERROR_OK){
        this.closePanel();
        this.typesForm.reset();
        this.pagination.currentPage = Math.ceil(res["data"]/this.pagination.pageItems);
        this.initList();
      }
    })
  }
  //获取id对应的类型
  getTypeItem(id:string){
    const param = {
      "id":id,
    }
    return this.service.getTypeOne(param);
  }
  addPanel(){
    this.type.id = "";
    this.state = this.state === 'active' ? 'inactive' : 'active';
  }
  updatePanel(id:string){
    this.getTypeItem(id).subscribe(res=>{
      if(res["code"] === this.Config.ERROR_OK){
        const Type = res["data"];
        this.type.id = Type.id;
        this.type.tp = Type.type;
        this.type.title = Type.title;
        this.state = this.state === 'active' ? 'inactive' : 'active';
      }
    });
  }
  closePanel(){
    this.state = this.state === 'active' ? 'inactive' : 'active';
  }
}

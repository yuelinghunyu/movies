import { Component, OnInit, Output } from '@angular/core';
import { trigger,state,style,animate,transition} from '@angular/animations';
import { FormArray, FormBuilder, FormGroup,Validators} from '@angular/forms';
import { ForbiddenNameValidor } from '../../shared/forbidden-name.directive';
import { ServiceService } from '../../service/service.service';
import { Pagination } from "../../common/pagination/pagination";
import { Modal } from '../../common/modal/modal';
import { Type } from './types';

@Component({
  selector: 'app-create-types',
  templateUrl: './create-types.component.html',
  styleUrls: ['./create-types.component.css'],
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
export class CreateTypesComponent implements OnInit {
  @Output()
  public pagination:Pagination = Pagination.defaultPagination;
  public modal:Modal = Modal.modal;
  public type:Type = Type.defaultType;
  private state:string;
  private typeList:Array<any>;
  private total:number;
  typesForm: FormGroup;
  constructor(
    private fb: FormBuilder,
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
  private alertModalItem(id:string){
    this.modal.tips = "是否删除该项？";
    this.modal.id = id;
    this.modal.changeEvent=((id:string)=>{
      this.deleteTypeItem(id);
    })
  }
  //删除一条记录;
  private deleteTypeItem(id:string){
    this.modal.flag = true;
    this.modal.tips = "正在删除，请稍后...";
    const param = {
      "id":id
    }
    this.service.deleteTypeOne(param).subscribe(res=>{
      let data = JSON.parse(res["_body"]);
      if(data.code == 0){
        this.modal.tips = "删除成功！";
        $("#tipModal").modal('hide');
        this.modal.flag = false;
        this.initList();
      }
    })
  }
  //获取数据列表;
  private initList():void{
    const param = {
      "page":this.pagination.currentPage,
      "limit":this.pagination.pageItems
    }
    this.service.getTypesList(param).subscribe(res=>{
      let data = JSON.parse(res["_body"]);
      if(data.code == 0){
        this.typeList = data.data.list;
        this.total = data.data.total;
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
      const body = JSON.parse(res["_body"]);
      if(body["code"] == 0){
        this.closePanel();
        this.typesForm.reset();
        this.pagination.currentPage = Math.round(body["data"]/this.pagination.pageItems);
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
      let data = JSON.parse(res["_body"]);
      if(data.code == 0){
        const Type = data.data;
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

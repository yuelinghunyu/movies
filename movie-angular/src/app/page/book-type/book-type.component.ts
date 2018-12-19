import { Component, OnInit, Output } from '@angular/core';
import { trigger,state,style,animate,transition} from '@angular/animations';
import { FormBuilder, FormGroup,Validators} from '@angular/forms';
import { ForbiddenNameValidor } from '../../shared/forbidden-name.directive';
import { ServiceService } from '../../service/service.service';
import { Pagination } from "../../common/pagination/pagination";
import { Modal } from '../../common/modal/modal';
import { Config} from "../../config/config";
import { BookType } from './bookType';

@Component({
  selector: 'app-book-type',
  templateUrl: './book-type.component.html',
  styleUrls: ['./book-type.component.css'],
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
export class BookTypeComponent implements OnInit {
  @Output()
  public pagination:Pagination = Pagination.defaultPagination;
  public modal:Modal = Modal.modal;
  public bookType:BookType = BookType.defaultBookType;
  public bookTypeList:Array<any>
  public bookTypeForm:FormGroup
  public config:Config = new Config()
  public state:string;
  public total:number;
  constructor(
    public fb: FormBuilder,
    public service:ServiceService
  ) {
    this.createBookTypeForm();
   }

  ngOnInit() {
    this.state = 'inactive';
    this.initList();
    this.pagination.changePage = (()=>{
      this.initList();
    });
  }

   //获取数据列表;
   public initList():void{
    const param = {
      "page":this.pagination.currentPage,
      "limit":this.pagination.pageItems
    }
    this.service.getBookTypeList(param).subscribe(res=>{
      if(res["code"] === this.config.ERROR_OK){
        const data = res["data"];
        this.bookTypeList = data.list;
        this.total = data.total;
        this.pagination.totalItems = this.total;
      }
    });
  }
  //初始化表单
  createBookTypeForm(){
    this.bookTypeForm = this.fb.group({
      bookType:['',[Validators.required,ForbiddenNameValidor(/^[0-9]*$/)]],
      bookTypeTitle:['',[Validators.required]]
    })
  }
   //新增小册类型;
  createBookType () {
    const typeId = this.bookTypeForm.get('bookType').value
    const typeTitle = this.bookTypeForm.get('bookTypeTitle').value

    const body = {
      id:"",
      typeId:typeId,
      typeTitle:typeTitle
    }

    this.service.createBookType(body).subscribe(res=>{
      if(res["code"] === this.config.ERROR_OK){
        this.closePanel();
        this.bookTypeForm.reset();
        this.pagination.currentPage = Math.ceil(res["data"]/this.pagination.pageItems);
        this.initList();
      }
    })
  }
  addPanel(){
    this.state = this.state === 'active' ? 'inactive' : 'active';
  }
  closePanel() {
    this.state = this.state === 'active' ? 'inactive' : 'active';
  }
  //弹框出现;
  public alertModalItem(id:string){
    this.modal.tips = "是否删除该项？";
    this.modal.id = id;
    this.modal.changeEvent=((id:string)=>{
      this.deleteItem(id);
    })
  }
  deleteItem(id:string){
    this.modal.flag = true;
    this.modal.tips = "正在删除，请稍后...";
    const body = {
      id:id
    }
    this.service.deleteBookType(body).subscribe(res=>{
      if(res['code'] === this.config.ERROR_OK){
        this.modal.tips = "删除成功！";
        $("#tipModal").modal('hide');
        this.modal.flag = false;
        this.initList();
      }
    })
  }
}

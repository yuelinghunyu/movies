import { Component, OnInit,Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup,Validators} from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/forkJoin';
import { Router } from '@angular/router';
import { ForbiddenNameValidor } from '../../shared/forbidden-name.directive';
import { ServiceService } from '../../service/service.service';
import { FileUploader } from 'ng2-file-upload';
import { Movie } from './movie';
import { Modal } from '../../common/modal/modal';
import { Config} from "../../config/config";

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent implements OnInit {
  @Output()
  addMovieForm:FormGroup;
  public isFree:boolean = true;
  movieModel:Movie= Movie.movieDefault;
  modal:Modal = Modal.modal;
  areaList:Array<any>;
  typeList:Array<any>;
  authorization:string;
  Config:Config = new Config();

  uploader:FileUploader = new FileUploader({
    url:"/mso/file/toOssServer",
    method:"post",
    headers:[{name:'Authorization',value:this.authorization = "mso " + localStorage.getItem("accessToken")}],
    itemAlias:"uploader",
    autoUpload:false
  });
  //类型字段；
  movieAreaTitle:string="区域";
  movieAreaArea:number;

  movieTypeTitle:string="类型";
  movieTypeType:number;
  //图片判断字段：
  jpgValid:boolean=false;
  jpgWarn:boolean=false;
  picDisabled:boolean=false;
  picUploaded:boolean=false;

  //最热、最新、经典字段：
  movieTypeVal:number=1;

  //是否免费;
  movieIsFreeVal:number = 1;
  constructor(
    private fb:FormBuilder,
    private service:ServiceService,
    private router:Router
  ) { 
    this.createAddMovieForm();
  };
  //表单验证
  createAddMovieForm(){
    this.addMovieForm = this.fb.group({
      movieArea:['',[Validators.required,ForbiddenNameValidor(/^[0-9]*$/)]],
      movieType:['',[Validators.required,ForbiddenNameValidor(/^[0-9]*$/)]],
      moviePic:['',Validators.required],
      movieTps:['',[Validators.required,ForbiddenNameValidor(/^[0-9]*$/)]],
      movieUrl:['',Validators.required],
      movieTitle:['',Validators.required],
      movieDescription:['',Validators.required],
      movieIsFree:['',Validators.required],
      moviePrice:['']
    })
  }
  ngOnInit() {
   //并发请求地域、类型的total值;
   let areasCount = 0,typesCount=0;
  //  let areaTotal = this.service.getAreaTotal();
  //  let typeTotal = this.service.getTypeTotal();
  //  Observable.forkJoin([areaTotal,typeTotal]).subscribe(res=>{
  //    console.log(res);
  //     areasCount = res[0]["data"];
  //     typesCount = res[1]["data"];

  //     let areaParam = {
  //       "page":1,
  //       "limit":areasCount
  //     };
  //     let typeParam = {
  //       "page":1,
  //       "limit":typesCount
  //     }
  //     let areaList = this.service.getAreasList(areaParam);
  //     let typeList = this.service.getTypesList(typeParam);

  //     Observable.forkJoin([areaList,typeList]).subscribe(res=>{
  //       this.areaList = res[0]["data"].list;
  //       this.typeList = res[1]["data"].list;
  //     })
  //  });
  //以上注释代码与拦截器冲突;
   //以上注释代码在添加拦截器后无效;
   $.when(this.service.getAreaTotal(),this.service.getTypeTotal()).done((res1,res2)=>{
      res1.subscribe(areas=>{
        areasCount = areas["data"];
        let areaParam = {
          "page":1,
          "limit":areasCount
        };
        let areaList = this.service.getAreasList(areaParam);
        areaList.subscribe(list=>{
          this.areaList = list["data"].list;
        });
      });
      res2.subscribe(types=>{
        typesCount = types["data"];
        let typeParam = {
          "page":1,
          "limit":typesCount
        }
        let typeList = this.service.getTypesList(typeParam);
        typeList.subscribe(list=>{
          this.typeList = list["data"].list;
        });
      });
    })
  }
  //创建电影模板;
  addMovie(){
    const formData = this.addMovieForm.value;
    if(formData["movieArea"] === undefined){
      this.modal.tips = "请选择影片区域！";
      $("#tipModal").modal('show');
      return false;
    }
    if(formData["movieType"] === undefined){
      this.modal.tips = "请选择影片类型！";
      $("#tipModal").modal('show');
      return false;
    }
    if(formData["moviePic"] === ""){
      this.modal.tips = "请选择影片图片！";
      $("#tipModal").modal('show');
      return false;
    }
    if(formData["movieUrl"] === ""){
      this.modal.tips = "请填写影片云地址！";
      $("#tipModal").modal('show');
      return false;
    }
    if(formData["movieTitle"] === ""){
      this.modal.tips = "请填写影片名称！";
      $("#tipModal").modal('show');
      return false;
    }
    if(formData["movieDescription"] === ""){
      this.modal.tips = "请填写影片简述！";
      $("#tipModal").modal('show');
      return false;
    }

    const movieParam = {
      "id":"",
      "area":formData["movieArea"],
      "picUrl":formData["moviePic"],
      "content":formData["movieUrl"],
      "description":formData["movieDescription"],
      "title":formData["movieTitle"],
      "type":formData["movieType"],
      "price":formData["moviePrice"],
      "movieType":formData["movieTps"],
      "isFree":formData["movieIsFree"]
    }

    console.log(movieParam);
    this.service.createMovieItem(movieParam).subscribe(res=>{
      if(res["code"] === this.Config.ERROR_OK){
        this.modal.tips = "继续提交!";
        $("#tipModal").modal('show');
        this.modal.changeEvent=((id:string)=>{
          this.modal.tips = "";
          this.picDisabled = false;
          this.picUploaded = false;
          $("#tipModal").modal('hide');
        })
        this.modal.closeEvent=(()=>{
          this.addMovieForm.reset();
          this.modal.tips = "";
          this.modal.close = true;
          $("#tipModal").modal('hide');
            this.router.navigateByUrl("/frame/create-movie");
        })
      
      }else{
        this.modal.tips = "提交失败哦!";
        $("#tipModal").modal('show');
      }
    })
  }
  //是否免费事件;
  freeChange(ev:any){
    const val = ev.target.value;
    this.isFree = val==1?true:false;
    this.movieIsFreeVal = parseInt(val);
    if(this.isFree){
      document.getElementById("movie-price")["value"]="";//免费将价格清空
    }
  }
  //图片文件事件；
  fileChange(ev:any){
    this.picDisabled = true;
    if(ev.target.files.length===0){
      this.picDisabled = false;
    }
    const isJpg = ev.target.files[0].name.split(".")[1];
    const exg = "(png|jpg|gif|jpeg|PNG|JPG|GIF|JPEG)";
    this.jpgValid = new RegExp(exg).test(isJpg);
    if(this.jpgValid){
      this.uploader.queue[0].upload();//开始上传;
      this.uploader.queue[0].onSuccess = (res,status,headers)=>{
        if(status == 200){
          this.picDisabled = false;
          this.picUploaded = true;
          let data = JSON.parse(res);
          this.movieModel.picUrl = data.data.fileUrl;
          this.uploader.queue = [];//清空
        }else{
          this.picDisabled = false;
          this.picUploaded = false;
        }
      }
      this.uploader.queue[0].onError =(res,status,headers)=>{
          this.picDisabled = false;
          this.picUploaded = false;
      }
    }else{
      this.jpgWarn = true;
      this.picDisabled = false;
      this.picUploaded = false;
    }
  }
  //删除图片；
  delMoviePic(){
    document.getElementById("movie-pics")["value"] = "";
    this.movieModel.picUrl = "";
    
    this.picUploaded = false;
    this.picDisabled = false;
  }

  //选择地区；
  areaEvent(title:string,area:number){
    this.movieAreaArea = area;
    this.movieAreaTitle = title;
  }
  //选择类型
  typeEvent(title:string,type:number){
    this.movieTypeType = type;
    this.movieTypeTitle = title;
  }

  //选择电影类别（最热、最新、经典）
  radioChange(ev:any){
    this.movieTypeVal = parseInt(ev.target.value);
  }
}

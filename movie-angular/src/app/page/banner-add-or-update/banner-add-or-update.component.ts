import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators} from '@angular/forms';
import { Banner } from './banner';
import { FileUploader } from 'ng2-file-upload';
import { ServiceService } from '../../service/service.service';
import { Router } from '@angular/router';
import { Config} from "../../config/config";

@Component({
  selector: 'app-banner-add-or-update',
  templateUrl: './banner-add-or-update.component.html',
  styleUrls: ['./banner-add-or-update.component.css']
})
export class BannerAddOrUpdateComponent implements OnInit {

  private bannerForm:FormGroup;
  private titleFlag:Boolean = false;
  private picHrefFlag:Boolean = false;
  private bannerTypeTxt:String="电影";
  private bannerModel:Banner = Banner.bannerDefault;
  private jpgValid:boolean=false;
  private authorization:string;
  private hrefLink:boolean = false;
  private Config:Config = new Config();

  uploader:FileUploader = new FileUploader({
    url:"/mso/file/toOssServer",
    method:"post",
    headers:[{name:'Authorization',value:this.authorization = "mso " + localStorage.getItem("accessToken")}],
    itemAlias:"uploader",
    autoUpload:false
  });

  constructor(
    private fb: FormBuilder,
    private service:ServiceService,
    private router:Router
  ) {
    this.createBannerForm()
   }

  ngOnInit() {
  }

  createBannerForm(){
    this.bannerForm = this.fb.group({
      bannerTitle:["",[Validators.required]],
      bannerType:["1",[Validators.required]],
      bannerHref:["",[Validators.required]],
      bannerRedirect:[""]
    })
  }

  createBanner(){
    const formData = this.bannerForm.value;

    console.log(formData)

    if(formData.bannerTitle === ""){
      this.titleFlag = true;
      return false;
    }
    if(formData.bannerHref === ""){
      this.picHrefFlag = true;
      return false;
    }

    const body = {
      id:"",
      title:formData.bannerTitle,
      type:formData.bannerType,
      href:formData.bannerHref,
      redirect:formData.bannerRedirect
    }
    this.service.setBanner(body).subscribe(res=>{
      if(res["code"] === this.Config.ERROR_OK){
        this.bannerForm.reset();
        this.router.navigateByUrl("/frame/banner-list");
      }
    })
  }
  selectType(ev){
    if(ev.target.getAttribute("type") === "1"){
      this.bannerTypeTxt = "电影"
      this.bannerModel.type = 1;
    }
    if(ev.target.getAttribute("type") === "2"){
      this.bannerTypeTxt = "小册";
      this.bannerModel.type = 2;
    }
  }

  fileChange(ev){
    const isJpg = ev.target.files[0].name.split(".")[1];
    const exg = "(png|jpg|gif|jpeg|PNG|JPG|GIF|JPEG)";
    this.jpgValid = new RegExp(exg).test(isJpg);
    if(this.jpgValid){
      this.uploader.queue[0].upload();//开始上传;
      this.uploader.queue[0].onSuccess = (res,status,headers)=>{
        if(status == 200){
           const resData = JSON.parse(res);
           this.bannerModel.href = resData["data"].fileUrl;
           this.hrefLink = true;
          
        }else{
          
        }
      }
      this.uploader.queue[0].onError =(res,status,headers)=>{
         
      }
    }
  }
}

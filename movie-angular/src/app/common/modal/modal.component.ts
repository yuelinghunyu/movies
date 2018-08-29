import { Component, OnInit, Input } from '@angular/core';
import { Modal } from "./modal";

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {

  @Input()
  public modal:Modal;

  ngOnInit() {
   
  }
  //添加事件
  confirmEvent(id:string){
    this.modal.changeEvent(id);
  }
  refuseEvent(){
    this.modal.closeEvent();
  }
}

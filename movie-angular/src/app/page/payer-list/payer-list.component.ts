import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Pagination } from "../../common/pagination/pagination";
import { Modal } from '../../common/modal/modal';
import { Config } from '../../config/config';
import { ServiceService } from '../../service/service.service';

@Component({
  selector: 'app-payer-list',
  templateUrl: './payer-list.component.html',
  styleUrls: ['./payer-list.component.css']
})
export class PayerListComponent implements OnInit {

  @Output()
  public pagination:Pagination = Pagination.defaultPagination;
  public modal:Modal = Modal.modal;
  public config:Config = new Config();
  constructor(
    public service:ServiceService,
    public router:Router,
  ) { }

  ngOnInit() {
  }

}

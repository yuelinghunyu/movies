import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Pagination } from "../../common/pagination/pagination";
import { Modal } from '../../common/modal/modal';
import { Config } from '../../config/config';
import { ServiceService } from '../../service/service.service';

@Component({
  selector: 'app-movie-feedback',
  templateUrl: './movie-feedback.component.html',
  styleUrls: ['./movie-feedback.component.css']
})
export class MovieFeedbackComponent implements OnInit {
  @Output()
  public pagination:Pagination = Pagination.defaultPagination;
  public modal:Modal = Modal.modal;
  public config:Config = new Config();
  constructor(
    public service:ServiceService,
    private router:Router,
  ) { }

  ngOnInit() {
  }

}

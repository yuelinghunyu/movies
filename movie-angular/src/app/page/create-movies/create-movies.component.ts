import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-movies',
  templateUrl: './create-movies.component.html',
  styleUrls: ['./create-movies.component.css']
})
export class CreateMoviesComponent implements OnInit {

  constructor(
    private router:Router
  ) { }

  ngOnInit() {
  }
  //跳转到新增影片面板;
  addMovieUrl(){
    this.router.navigateByUrl("/add-movie");
  }
}

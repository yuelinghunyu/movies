import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup,Validators} from '@angular/forms';
@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.css']
})
export class AddMovieComponent implements OnInit {

  addMovieForm:FormGroup;
  constructor() { }

  ngOnInit() {
  }
  //创建电影模板;
  addMovie(){
    
  }
}

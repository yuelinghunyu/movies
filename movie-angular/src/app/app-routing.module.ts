import { NgModule } from '@angular/core';
import { RouterModule,Routes} from '@angular/router';
import { FrameComponent } from './component/frame/frame.component';
import { LoginComponent } from './page/login/login.component';
import { HomeComponent } from './page/home/home.component';
import { PersonalComponent } from './page/personal/personal.component';
//电影
import { CreateAreasComponent } from './page/create-areas/create-areas.component';
import { CreateTypesComponent } from './page/create-types/create-types.component';
import { CreateMoviesComponent } from './page/create-movies/create-movies.component';
import { AddMovieComponent } from './page/add-movie/add-movie.component';
//小册
import { BookTypeComponent } from './page/book-type/book-type.component';

//轮播图
import { BannerListComponent } from './page/banner-list/banner-list.component';
import { BannerAddOrUpdateComponent } from './page/banner-add-or-update/banner-add-or-update.component';


const routes:Routes = [
  {
    path:'frame',
    component:FrameComponent,
    children:[
      {
        path:'home',
        component:HomeComponent
      },
      {
        path:'personel',
        component:PersonalComponent
      },
      {
        path:'create-area',
        component:CreateAreasComponent
      },
      {
        path:'create-type',
        component:CreateTypesComponent
      },
      {
        path:'create-movie',
        component:CreateMoviesComponent
      },
      {
        path:'add-movie',
        component:AddMovieComponent
      },
      {
        path:'book-type',
        component:BookTypeComponent
      },
      {
        path:'banner-list',
        component:BannerListComponent
      },
      {
        path:'banner-add-update',
        component:BannerAddOrUpdateComponent
      }
    ]
  },
  {path:'login',component:LoginComponent},
]

@NgModule({
  imports:[ RouterModule.forRoot(routes) ],
  exports:[ RouterModule ]
})
export class AppRoutingModule { }

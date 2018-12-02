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
import { CreateBlogComponent} from './page/create-blog/create-blog.component';
import { AddBlogComponent} from './page/add-blog/add-blog.component';
import { BooksComponent } from './page/books/books.component';
import { ChaptersComponent } from './page/chapters/chapters.component';
import { BookCreateComponent } from './page/book-create/book-create.component';
import { ChapterCreateComponent } from './page/chapter-create/chapter-create.component';
import { PayerListComponent } from './page/payer-list/payer-list.component';
//轮播图
import { BannerListComponent } from './page/banner-list/banner-list.component';
import { BannerAddOrUpdateComponent } from './page/banner-add-or-update/banner-add-or-update.component';
//反馈
import { MovieFeedbackComponent } from './page/movie-feedback/movie-feedback.component';

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
      },
      {
        path:'create-blog',
        component:CreateBlogComponent
      },
      {
        path:'add-blog',
        component:AddBlogComponent
      },
      {
        path:'book-list',
        component:BooksComponent
      },
      {
        path:'chapter-list',
        component:ChaptersComponent
      },
      {
        path:'book-create',
        component:BookCreateComponent
      },
      {
        path:'chapter-create',
        component:ChapterCreateComponent
      },
      {
        path:'feed-back',
        component:MovieFeedbackComponent
      },
      {
        path:'payer-list',
        component:PayerListComponent
      }
    ]
  },
  {path:'login',component:LoginComponent},
  {path: '', redirectTo: '/login', pathMatch: 'full'},
]

@NgModule({
  imports:[ RouterModule.forRoot(routes) ],
  exports:[ RouterModule ]
})
export class AppRoutingModule { }

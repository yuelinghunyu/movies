import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms'; 
import { ServiceService } from "./service/service.service";
import {InterceptorService} from "./InterceptorService";
import {HTTP_INTERCEPTORS,HttpClientModule} from "@angular/common/http";
import { CommonModule,HashLocationStrategy , LocationStrategy } from "@angular/common";
import { FileUploadModule } from "ng2-file-upload";
import { LazyLoadImageModule } from 'ng-lazyload-image';
import { ChartModule } from 'angular-highcharts/angular-highcharts';

import { AppComponent } from './app.component';
import { LoginComponent } from './page/login/login.component';
import { HomeComponent } from './page/home/home.component';
import { AppRoutingModule } from './/app-routing.module';
import { FrameComponent } from './component/frame/frame.component';
import { PersonalComponent } from './page/personal/personal.component';
import { CreateAreasComponent } from './page/create-areas/create-areas.component';
import { ForbiddenNameDirective } from './shared/forbidden-name.directive';
import { PaginationComponent } from './common/pagination/pagination.component';
import { ModalComponent } from './common/modal/modal.component';
import { CreateTypesComponent } from './page/create-types/create-types.component';
import { CreateMoviesComponent } from './page/create-movies/create-movies.component';
import { AddMovieComponent } from './page/add-movie/add-movie.component';
import { BookTypeComponent } from './page/book-type/book-type.component';
import { BannerListComponent } from './page/banner-list/banner-list.component';
import { BannerAddOrUpdateComponent } from './page/banner-add-or-update/banner-add-or-update.component';
import { CreateBlogComponent } from './page/create-blog/create-blog.component';
import { AddBlogComponent } from './page/add-blog/add-blog.component';
import { BooksComponent } from './page/books/books.component';
import { ChaptersComponent } from './page/chapters/chapters.component';
import { MovieFeedbackComponent } from './page/movie-feedback/movie-feedback.component';
import { BookCreateComponent } from './page/book-create/book-create.component';
import { ChapterCreateComponent } from './page/chapter-create/chapter-create.component';
import { PayerListComponent } from './page/payer-list/payer-list.component';

export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true },
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    FrameComponent,
    PersonalComponent,
    CreateAreasComponent,
    ForbiddenNameDirective,
    PaginationComponent,
    ModalComponent,
    CreateTypesComponent,
    CreateMoviesComponent,
    AddMovieComponent,
    BookTypeComponent,
    BannerListComponent,
    BannerAddOrUpdateComponent,
    CreateBlogComponent,
    AddBlogComponent,
    BooksComponent,
    ChaptersComponent,
    MovieFeedbackComponent,
    BookCreateComponent,
    ChapterCreateComponent,
    PayerListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    CommonModule,
    FileUploadModule,
    LazyLoadImageModule,
    ChartModule
  ],
  providers: [ServiceService,httpInterceptorProviders,{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }

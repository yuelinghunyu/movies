import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms'; 
import { HttpModule, Jsonp} from "@angular/http";
import { ServiceService } from "./service/service.service";

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
    AddMovieComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpModule,
    ReactiveFormsModule,
  ],
  providers: [ServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }

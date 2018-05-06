import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import {HttpModule, Jsonp} from "@angular/http";
import { ServiceService } from "./service/service.service";

import { AppComponent } from './app.component';
import { LoginComponent } from './page/login/login.component';
import { HomeComponent } from './page/home/home.component';
import { AppRoutingModule } from './/app-routing.module';
import { FrameComponent } from './component/frame/frame.component';
import { PersonalComponent } from './page/personal/personal.component';
import { CreateAreasComponent } from './page/create-areas/create-areas.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    FrameComponent,
    PersonalComponent,
    CreateAreasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpModule,
  ],
  providers: [ServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }

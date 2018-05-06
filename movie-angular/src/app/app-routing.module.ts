import { NgModule } from '@angular/core';
import { RouterModule,Routes} from '@angular/router';
import { FrameComponent } from './component/frame/frame.component';
import { LoginComponent } from './page/login/login.component';
import { HomeComponent } from './page/home/home.component';
import { PersonalComponent } from './page/personal/personal.component';
import { CreateAreasComponent } from './page/create-areas/create-areas.component';

const routes:Routes = [
  {
    path:'',
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

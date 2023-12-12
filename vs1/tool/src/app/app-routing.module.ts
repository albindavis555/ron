import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DetailsComponent } from './details/details.component';
import { AppComponent } from './app.component';
import { CreateComponent } from './create/create.component';
import { UpdateComponent } from './update/update.component';


const routes: Routes = [
  { path: '', component:AppComponent, children: [
    { path: 'create', component: CreateComponent },
    { path: 'details', component: DetailsComponent },
    { path: 'update/:id1/:id2', component: UpdateComponent },
    // { path: 'test', component: TestComponent },
    // { path: 'demo', component: DemoComponent },

    { path: '', redirectTo: 'details', pathMatch: 'full' }
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

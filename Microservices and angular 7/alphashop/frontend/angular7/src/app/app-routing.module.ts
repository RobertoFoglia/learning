import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ErrorUrlComponent } from './error-url/error-url.component';
import { ArticoliComponent } from './articoli/articoli.component';


const routes: Routes = [
  { path: '', component: LoginComponent},
  { path: 'login', component: LoginComponent },
  // the userid is mandatory
  { path: 'welcome/:userid', component: WelcomeComponent }, // @@@   routing parameters
  { path: 'articoli', component: ArticoliComponent},
  { path: '**', component: ErrorUrlComponent} // @@@ pages not found
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { CustomerdatamanagementComponent } from './customerdatamanagement/customerdatamanagement.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerdatamanagementComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

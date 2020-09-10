import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EntryEditorComponent} from "./entry-editor/entry-editor.component";
import {LoginComponent} from "./login/login.component";
import {AuthGuardService} from "./interceptor/AuthenticationGuardService";

const routes: Routes = [
  { path: '', component: EntryEditorComponent, canActivate: [AuthGuardService] },
  { path: 'login', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { BrowseBooksComponent } from './browse-books/browse-books.component';
import { AuthGuard } from './services/auth.guard';
import { UserPanelComponent } from './user-panel/user-panel.component';
import { MyBooksComponent } from './my-books/my-books.component';
import { MessagesComponent } from './messages/messages.component';
import { UserPanelProfileComponent } from './user-panel/user-panel-profile/user-panel-profile.component';
import { UserPanelPasswordComponent } from './user-panel/user-panel-password/user-panel-password.component';
import { UserPanelEmailComponent } from './user-panel/user-panel-email/user-panel-email.component';

const routes: Routes = [
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'register', component: RegisterComponent
  },
  {
    path: 'browsebooks', component: BrowseBooksComponent
  },
  {
    path: 'userpanel', component: UserPanelComponent, canActivate: [AuthGuard],
    children: [
      { path: 'profile', component: UserPanelProfileComponent, canActivate: [AuthGuard] },
      { path: 'password', component: UserPanelPasswordComponent, canActivate: [AuthGuard] },
      { path: 'email', component: UserPanelEmailComponent, canActivate: [AuthGuard] }
  ]
  },
  {
    path: 'mybooks', component: MyBooksComponent, canActivate: [AuthGuard]
  },
  {
    path: 'messages', component: MessagesComponent, canActivate: [AuthGuard]
  }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

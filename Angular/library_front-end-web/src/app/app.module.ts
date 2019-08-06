import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthService } from './services/auth.service';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowseBooksComponent } from './browse-books/browse-books.component';
import { BookFilterPipe } from './shared/pipes/book-filter.pipe';
import { TokenInterceptor } from './services/token.interceptor';
import { AuthGuard } from './services/auth.guard';
import { UserService } from './services/user.service';
import { AlertComponent } from './alert/alert.component';
import { AlertService } from './services/alert.service';
import { MyBooksComponent } from './my-books/my-books.component';
import { MessagesComponent } from './messages/messages.component';
import { UserPanelComponent } from './user-panel/user-panel.component';
import { BookService } from './services/book.service';
import { BrowseBooksSearchComponent } from './browse-books/browse-books-search/browse-books-search.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import {PlatformModule} from '@angular/cdk/platform';
import 'hammerjs';
import { MatCheckboxModule, MatSelectModule, MatFormFieldModule, MatInputModule } from '@angular/material';
import { UserPanelProfileComponent } from './user-panel/user-panel-profile/user-panel-profile.component';
import { UserPanelPasswordComponent } from './user-panel/user-panel-password/user-panel-password.component';
import { UserPanelEmailComponent } from './user-panel/user-panel-email/user-panel-email.component';
import { BrowseBooksConfirmResComponent } from './browse-books/browse-books-confirm-res/browse-books-confirm-res.component';
import { NotificationsComponent } from './notifications/notifications.component';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { MyBooksPaymentComponent } from './my-books/my-books-payment/my-books-payment.component';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    BrowseBooksComponent,
    BookFilterPipe,
    AlertComponent,
    MyBooksComponent,
    MessagesComponent,
    UserPanelComponent,
    BrowseBooksSearchComponent,
    UserPanelProfileComponent,
    UserPanelPasswordComponent,
    UserPanelEmailComponent,
    BrowseBooksConfirmResComponent,
    NotificationsComponent,
    MyBooksPaymentComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatPaginatorModule,
    PlatformModule,
    MatCheckboxModule,
    MatSelectModule,
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatAutocompleteModule
  ],
  providers: [
    AlertService,
    AuthService,
    AuthGuard,
    UserService,
    BookService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }

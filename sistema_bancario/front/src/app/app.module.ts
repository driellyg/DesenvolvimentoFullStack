import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CadastroClienteComponent } from './cadastro-cliente/cadastro-cliente.component';
import { LoginComponent } from './login/login.component';
import { ContaComponent } from './conta/conta.component';
import { ContaService } from './services/conta.service';

@NgModule({
  declarations: [
    AppComponent,
    CadastroClienteComponent,
    LoginComponent,
    ContaComponent,
  ],
  imports: [FormsModule, BrowserModule, AppRoutingModule, HttpClientModule],
  providers: [ContaService],
  bootstrap: [AppComponent],
})
export class AppModule {}

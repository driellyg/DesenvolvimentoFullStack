import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { CadastroClienteComponent } from './cadastro-cliente/cadastro-cliente.component';
import { ContaComponent } from './conta/conta.component';
const routes: Routes = [    
    { path: '', component: LoginComponent },
    { path: 'cadastro', component: CadastroClienteComponent },
    { path: 'conta', component: ContaComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

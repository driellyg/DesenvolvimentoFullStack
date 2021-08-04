import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ContaService } from '../services/conta.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  cpf: string = '';
  cnpj: string = '';
  numeroConta: string = '';
  tipoPessoa: string = 'pf';

  constructor(private router: Router, private contaService: ContaService) {}

  handleChangePessoa(element: any) {
    this.tipoPessoa = element.value;
  }

  handleLogin(): void {
    if (
      !this.numeroConta ||
      (this.tipoPessoa === 'pf' && !this.cpf) ||
      (this.tipoPessoa === 'pj' && !this.cnpj)
    ) {
      return;
    }

    const _numeroConta = Number(this.numeroConta);
    const cpfCnpj = this.tipoPessoa === 'pf' ? this.cpf : this.cnpj;

    this.contaService.login(_numeroConta, cpfCnpj, this.tipoPessoa).then(() => {
      this.router.navigate(['/conta']);
    });
  }

  handleCadastro(): void {
    this.router.navigate(['/cadastro']);
  }
  ngOnInit(): void {}
}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { ContaService } from '../services/conta.service';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.css'],
})
export class ContaComponent {
  saqueAction: boolean = false;
  depositoAction: boolean = false;
  transferenciaAction: boolean = false;
  relatorioAction: boolean = false;
  saldoTotal: number = 0;

  valorOperacao = 0;
  contaDestino = '';

  conta: any = null;
  contas: any = [];

  relatorioError = false;

  constructor(private router: Router, private contaService: ContaService) {
    this.conta = this.contaService.conta;

    this.contaService
      .gerarRelatorio()
      .then((response) => {
        this.contas = response;
      })
      .catch((e) => {
        this.relatorioError = true;
      });
  }

  handleTotal(): number {
    return this.contas.reduce((acc: number, current: any) => {
      return acc + current.saldo;
    }, 0);
  }

  handleSaqueModal(event: any) {
    if (event.currentTarget === event.target) this.saqueAction = false;
  }

  handleDepositoModal(event: any) {
    if (event.currentTarget === event.target) this.depositoAction = false;
  }

  handleTransferenciaModal(event: any) {
    if (event.currentTarget === event.target) this.transferenciaAction = false;
  }

  handleRelatorioModal(event: any) {
    if (event.currentTarget === event.target) this.relatorioAction = false;
  }

  handleLogOut() {
    this.router.navigate(['/']);
  }

  realizarSaque() {
    this.contaService
      .sacar(Number(this.valorOperacao), this.conta.numeroConta)
      .then((response: any) => {
        this.saqueAction = false;
        this.conta.saldo = response.saldo;
        this.contaService.updateLocalConta(this.conta);
        Swal.fire(
          `Saque realizado. O seu novo saldo é de: R$ ${response.saldo}.`
        );
      })
      .catch((e) => {
        console.error(e);
        Swal.fire('Saldo insuficiente. Realize um depósito.');
      })
      .finally(() => {
        this.valorOperacao = 0;
      });
  }

  realizarDeposito() {
    this.contaService
      .depositar(Number(this.valorOperacao), Number(this.conta.numeroConta))
      .then((response: any) => {
        this.depositoAction = false;
        this.conta.saldo = response.saldo;
        this.contaService.updateLocalConta(this.conta);
        Swal.fire(
          `Depósito realizado. O seu novo saldo é de: R$ ${response.saldo}.`
        );
      })
      .catch((e) => {
        console.error(e);
        Swal.fire('Erro ao realizar depósito. Tente novamente.');
      })
      .finally(() => {
        this.valorOperacao = 0;
      });
  }

  realizarTransferencia() {
    if (Number(this.conta.numeroConta) == Number(this.contaDestino)) {
      Swal.fire('Você não pode transferir para a mesma conta. Mude a conta.');
      return;
    }

    this.contaService
      .transferir(
        Number(this.valorOperacao),
        Number(this.conta.numeroConta),
        Number(this.contaDestino)
      )
      .then((response: any) => {
        this.transferenciaAction = false;
        this.conta.saldo = response.saldo;
        this.contaService.updateLocalConta(this.conta);
        Swal.fire(
          `Transferência realizada. O seu novo saldo é de: R$ ${response.saldo}.`
        );
      })
      .catch((e: any) => {
        console.error(e);
        Swal.fire(
          'Erro ao realizar transferência. Conta inválida ou saldo insuficiente.'
        );
      })
      .finally(() => {
        this.valorOperacao = 0;
      });
  }
}

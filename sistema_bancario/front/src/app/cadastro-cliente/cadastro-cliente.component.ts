import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import * as moment from 'moment';
import { ContaService } from '../services/conta.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastro-cliente',
  templateUrl: './cadastro-cliente.component.html',
  styleUrls: ['./cadastro-cliente.component.css'],
})
export class CadastroClienteComponent implements OnInit {
  genderOptions: string = 'genero';
  error: string = '';
  tipoPessoa: string = 'pf'; // pf = pessoa fisica, pj = pessoa juridica
  tipoConta: string = 'ce'; // ce = conta especial, cp = conta poupança

  limite = 1000;
  saldo = 0;
  taxaCorrecao = 0.85;

  constructor(private contaService: ContaService, private router: Router) {}

  ngOnInit() {}

  validate(form: any): boolean {
    const cpfRegex = /^\d{3}\.?\d{3}\.?\d{3}-?\d{2}$/;
    if (
      !Object.keys(form).reduce((acc: any, current: any): any => {
        if (form[current] === '' || null || undefined) return acc * 0;
        else return acc * 1;
      }, 1)
    ) {
      Swal.fire('Preencha todos os campos!');
      return false;
    }
    if (this.tipoPessoa === 'pf' && !cpfRegex.test(form.cpf)) {
      Swal.fire('Insira um CPF válido!');
      return false;
    } else if (this.tipoPessoa === 'pf' && !moment(form.dataNasc).isValid()) {
      Swal.fire('Insira uma data válida!');
      return false;
    } else if (form.genero === 'genero') {
      Swal.fire('Selecione o gênero!');
      return false;
    }

    return true;
  }

  onSubmit(target: any): void {
    if (!this.validate(target.form.value)) return;

    const pessoa = { ...target.form.value };
    const conta: any = {
      tipoConta: target.form.value.tipoConta,
      saldo: this.saldo,
    };

    if (pessoa.tipoConta === 'ce') {
      conta.limite = this.limite;
    } else {
      conta.taxaCorrecao = this.taxaCorrecao;
    }

    delete pessoa.tipoConta;

    if (pessoa.dataNascimento) {
      pessoa.dataNascimento = moment(pessoa.dataNascimento)
        .format('DD/MM/yyyy')
        .toString();
    }

    this.contaService
      .signUp(target.form.value, conta)
      .then(() => {
        Swal.fire(
          'Conta criada com sucesso! Você será redirecionado...'
        ).finally(() => {
          this.router.navigate(['/conta']);
        });
      })
      .catch((e) => {
        Swal.fire('Não foi possível criar a conta.');
      });
  }

  handleChangePessoa(target: any): void {
    this.tipoPessoa = target.value;
  }

  handleChangeConta(target: any): void {
    this.tipoConta = target.value;
  }

  handleGoBack() {
    this.router.navigate(['/']);
  }
}

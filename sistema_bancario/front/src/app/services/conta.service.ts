import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class ContaService {
  apiUrl = 'http://localhost:8080';

  public conta: any = null;

  constructor(private http: HttpClient, private router: Router) {
    const _conta = localStorage.getItem('conta');

    if (_conta) {
      this.conta = JSON.parse(_conta);
    }
  }

  updateBackendConta() {
    if (!this.conta) return;

    const url = `${this.apiUrl}/conta/${this.conta.numeroConta}/${
      this.conta.cliente.tipo == 'pf' ? 'cpf' : 'cnpj'
    }/${
      this.conta.cliente.tipo == 'pf'
        ? this.conta.cliente.cpf
        : this.conta.cliente.cnpj
    }`;
    return new Promise((resolve, reject) => {
      this.http
        .get(url)
        .toPromise()
        .then((_conta) => {
          this.conta = _conta;
          localStorage.setItem('conta', JSON.stringify(_conta));
          resolve(_conta);
        })
        .catch((e) => {
          reject(e);
        });
    });
  }

  updateLocalConta(conta: any) {
    this.conta = conta;
    this.updateBackendConta();
  }

  signUp(pessoa: any, conta: any) {
    const pessoaSuffix = pessoa.tipoPessoa === 'pf' ? '/pf' : '/pj';
    const contaSuffix = conta.tipoConta === 'ce' ? '/ce' : '/cp';

    return new Promise((resolve, reject) => {
      this.http
        .post(`${this.apiUrl}${pessoaSuffix}`, pessoa)
        .toPromise()
        .then((_pessoa) => {
          conta.cliente = _pessoa;
          this.http
            .post(`${this.apiUrl}${contaSuffix}`, conta)
            .toPromise()
            .then((_conta) => {
              this.conta = _conta;
              localStorage.setItem('conta', JSON.stringify(_conta));
              resolve(_conta);
            })
            .catch((e) => {
              reject(e);
            });
        })
        .catch((e) => {
          reject(e);
        });
    });
  }

  logout() {
    this.conta = null;
    localStorage.removeItem('conta');
    this.router.navigate(['/login']);
  }

  login(numeroConta: number, cpfOrCnpj: string, tipoPessoa: string) {
    const url = `${this.apiUrl}/conta/${numeroConta}/${
      tipoPessoa == 'pf' ? 'cpf' : 'cnpj'
    }/${cpfOrCnpj}`;
    return new Promise((resolve, reject) => {
      this.http
        .get(url)
        .toPromise()
        .then((_conta) => {
          this.conta = _conta;
          localStorage.setItem('conta', JSON.stringify(_conta));
          resolve(_conta);
        })
        .catch((e) => {
          reject(e);
        });
    });
  }

  sacar(valor: number, numeroConta: any) {
    const url = `${this.apiUrl}/operacao`;

    const body = {
      operacao: 'SAQUE',
      payload: {
        contaOrigem: numeroConta,
        valor: valor * 1.0,
      },
    };

    return new Promise((resolve, reject) => {
      this.http
        .post(url, body)
        .toPromise()
        .then((response) => {
          resolve(response);
        })
        .catch((e) => {
          reject(e);
        });
    });
  }

  depositar(valor: number, numeroConta: any) {
    const url = `${this.apiUrl}/operacao`;

    const body = {
      operacao: 'DEPOSITO',
      payload: {
        contaDestino: numeroConta,
        valor: valor * 1.0,
      },
    };

    return new Promise((resolve, reject) => {
      this.http
        .post(url, body)
        .toPromise()
        .then((response) => {
          resolve(response);
        })
        .catch((e) => {
          reject(e);
        });
    });
  }

  transferir(valor: number, contaOrigem: any, contaDestino: any) {
    const url = `${this.apiUrl}/operacao`;

    const body = {
      operacao: 'TRANSFERENCIA',
      payload: {
        contaDestino,
        contaOrigem,
        valor: valor * 1.0,
      },
    };

    return new Promise((resolve, reject) => {
      this.http
        .post(url, body)
        .toPromise()
        .then((response) => {
          resolve(response);
        })
        .catch((e) => {
          reject(e);
        });
    });
  }

  gerarRelatorio() {
    return this.http.get(`${this.apiUrl}/relatorio`).toPromise();
  }
}

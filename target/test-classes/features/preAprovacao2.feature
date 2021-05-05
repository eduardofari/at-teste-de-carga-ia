#language: pt
#coding: utf-8
#Author: João Paulo Fialho Araujo | Thiago de Moraes
#E-mail: joao.araujo@auditeste.com.br | thiago.moraes@auditeste.com.br


@preAprovacao2
Funcionalidade: Consulta de GTO 2

  Esquema do Cenario: Validar se a consulta da GTO é realizada com sucesso no pre aprovacao 2

    Dado que acessei o "<Sistema_Portal>" "<NM_Portal>" "<Cenario>" e realizei o Login 2
    Quando clicado na aba ficha clinica e acessado consolidacao 2
    Então validar fila Auditoria 2
  # E aceitar o alert 2
  # E digitado o numero da gto no campo nr ficha 2
  # E a senha apresentada no campo senha 2
  # E validar o protocolo "<PROTOCOLO>" e clicar em ok 2
  # Quando validar o campo histórico risco 2
  # Então deve ser clicado no botão confirmar 2


    Exemplos:
      | Sistema_Portal |  | NM_Portal    |  | Cenario  |
      | Portal         |  | preaprovacao |  | dentista |
    
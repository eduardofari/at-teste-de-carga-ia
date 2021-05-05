#language: pt
#coding: utf-8
#Author: João Paulo Fialho Araujo | Thiago de Moraes
#E-mail: joao.araujo@auditeste.com.br | thiago.moraes@auditeste.com.br


@preAprovacao3
Funcionalidade: Consulta de GTO 3

  Esquema do Cenario: Validar se a consulta da GTO é realizada com sucesso no pre aprovacao3

    Dado que acessei o "<Sistema_Portal>" "<NM_Portal>" "<Cenario>" e realizei o Login 3
    Quando clicado na aba ficha clinica e acessado consolidacao 3
    Então validar fila Auditoria 3
  # E aceitar o alert 3
  # E digitado o numero da gto no campo nr ficha 3
  # E a senha apresentada no campo senha 3
  # E validar o protocolo "<PROTOCOLO>" e clicar em ok 3
  # Quando validar o campo histórico risco 3
  # Então deve ser clicado no botão confirmar 3


    Exemplos:
      | Sistema_Portal |  | NM_Portal    |  | Cenario  |
      | Portal         |  | preaprovacao |  | dentista |
    
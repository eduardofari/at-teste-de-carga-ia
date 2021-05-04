#language: pt
#coding: utf-8
#Author: João Paulo Fialho Araujo | Thiago de Moraes
#E-mail: joao.araujo@auditeste.com.br | thiago.moraes@auditeste.com.br


@preAprovacao
Funcionalidade: Consulta de GTO

  Esquema do Cenario: Validar se a consulta da GTO é realizada com sucesso no pre aprovacao

    Dado que acessei o "<Sistema_Portal>" "<NM_Portal>" "<Cenario>" e realizei o Login
    Quando clicado na aba ficha clinica e acessado consolidacao
    Então validar fila Auditoria
  #  E aceitar o alert
   # E digitado o numero da gto no campo nr ficha
   # E a senha apresentada no campo senha
   # E validar o protocolo "<PROTOCOLO>" e clicar em ok
   # Quando validar o campo histórico risco
  #  Então deve ser clicado no botão confirmar


    Exemplos:
      | Sistema_Portal |  | NM_Portal    |  | Cenario  |
      | Portal         |  | preaprovacao |  | dentista |
    
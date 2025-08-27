package org.example.entidades

import org.example.enumeradores.Sexo

class Cliente(
    nome : String,
    idade : Int,
    cpf : Long,
    sexo : Sexo,
    val orcamento : Int,
    val residencia : String,
    val contato : String,
    val pedidos : Array<String>,
) : Pessoa(
    nome = nome,
    idade = idade,
    cpf = cpf,
    sexo = Sexo.MASCULINO,
){

}
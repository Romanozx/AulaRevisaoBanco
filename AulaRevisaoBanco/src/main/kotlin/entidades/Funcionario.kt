package org.example.entidades

import org.example.enumeradores.Setor
import org.example.enumeradores.Sexo
import java.math.BigDecimal

class Funcionario(
    nome : String,
    idade : Int,
    cpf : Long,
    val funcao : String,
    sexo : Sexo,
    val salario : BigDecimal,
    val experiencia : Int,
    val agendamento : String,
    val setor : Setor
) : Pessoa(
    nome = nome,
    idade = idade,
    cpf = cpf,
    sexo = sexo
){
    //Comportamentos OU ações do Profissional
    fun instalarCaixaDAgua(clt : Funcionario){
        if(clt.setor.equals(Setor.MONTAGEM)){
            println("Profissional habilitado")
        } else {
            println("Profissional desqualificado")
        }
    }
    override fun receberConta(conta : Conta, aPagar : BigDecimal){
        conta.saldo = conta.saldo.subtract(aPagar)
    }
}

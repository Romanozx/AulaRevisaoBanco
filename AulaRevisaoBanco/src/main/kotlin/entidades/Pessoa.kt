package org.example.entidades

import org.example.enumeradores.Sexo
import java.math.BigDecimal

open class Pessoa(
    val nome : String,
    val idade : Int,
    val cpf : Long,
    sexo : Sexo,
) {
    //Comportamento
    open fun receberConta(conta : Conta, aReceber : BigDecimal){
        conta.saldo = conta.saldo.add(aReceber)
    }
}
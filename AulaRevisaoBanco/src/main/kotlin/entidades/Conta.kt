package org.example.entidades

import java.math.BigDecimal

class Conta(
    val titular : Pessoa,
    var saldo : BigDecimal,
    val numeroConta : Int,
    val senha : Int
)
{
}
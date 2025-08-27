package org.example.entidades

import org.example.enumeradores.Cores
import org.example.enumeradores.Material
import java.math.BigDecimal

class CaixaDAgua (//Os 2 parenteses são Construtor da Classe
    val material: Material,
    val capacidade : Int,
    val cor : Cores,
    val peso : Double,
    val preco : BigDecimal,
    val dimenssao : Array<Double>
)//Aqui vai todos os atributos da classe
{

}
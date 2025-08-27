package org.example.crud

import org.example.entidades.CaixaDAgua
import org.example.enumeradores.Cores
import org.example.enumeradores.Material

fun cadastrarCaixa() {
    /*

    */
    println("Escolha o material do qual a caixa é composta:")
    println("1 - Plástico")
    println("2 - PVC")
    println("3 - Fibra")
    println("4 - Metal")
    val opcao = readln().toInt()
    var material: Material
    when (opcao) {
        1 -> material = Material.PLASTICO
        2 -> material = Material.PVC
        2 -> material = Material.FIBRA
        3 -> material = Material.METAL
        else -> material = Material.PLASTICO
    }

    println("Capacidade da Caixa em Litros:")
    val capacidade = readln().toDouble()

    println("Cor da Caixa em Litros:")
    val opcoes = readln().toInt()
    var cor: Cores
    when (opcoes) {
        1 -> cor = Cores.AZUL
        2 -> cor = Cores.PRETA
        3 -> cor = Cores.BRANCA
        4 -> cor = Cores.BEGE
        5 -> cor = Cores.CINZA
        6 -> cor = Cores.VERDE
        7 -> cor = Cores.METALICA
        else -> cor = Cores.AZUL

    }

    println("Peso da Caixa em Litros:")
    val peso = readln().toDouble()

    println("Preço da Caixa:")
    val preco = readln().toBigDecimal()

    println("Altura da Caixa:")
    val alt = readln().toDouble()//não salve esse!
    println("Largura da Caixa:")
    val lag = readln().toDouble()//não salve esse!
    println("Profundidade da Caixa:")
    val pro = readln().toDouble()//não salve esse!

    val dimenssao = arrayOf(alt, lag, pro)//Salvar apenas esse e não os outros 3

    //Salvar as variáveis agora dentro da classe
    //Conecte o atributo da classe a variável que o usuáruio digitou
    CaixaDAgua(
        material = material,
        capacidade = capacidade,
        cor = cor,
        peso = peso,
        preco = preco,
        dimenssao = dimenssao
    )
    }

    fun editarCaixa() {

    }

    fun listarCaixas() {

    }

    fun excluirCaixa() {

    }
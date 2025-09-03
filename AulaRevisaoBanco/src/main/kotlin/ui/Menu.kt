package org.example.ui

import org.example.crud.cadastrarCaixa

fun menu(){
    do{
        println("1 - Cadastrar Caixa D'Agua")
        println("2 - Editar Caixa D'Agua")
        println("3 - Listar Caixas D'Agua")
        println("4 - Excluir Caixa D'Agua")
        println("0 - Sair")

        val opcao = readln().toInt()
        when(opcao){
            0-> println("Saindo...")
            1-> cadastrarCaixa()
            2-> println("Editando caixa...")
            3-> println("Listando caixa...")
            4-> println("Excluindo caixa...")
            else -> println("Opção inválida!")
        }
    } while (opcao != 0)
}
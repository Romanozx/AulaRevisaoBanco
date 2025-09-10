package org.example.crud

import org.example.entidades.CaixaDAgua
import org.example.enumeradores.Cores
import org.example.enumeradores.Material
import java.sql.ResultSet

val conectar = EntidadeJDBC(
    //Porta 5433 ou 5432
    url = "jdbc:postgresql://localhost:5432/aula",
    usuario = "postgres",
    senha = "postgres"
)

fun criarTabelaCaixa() {

    //Colocar o mesmo nome da Tabela o nome da Entidade!
    val sql = "CREATE TABLE IF NOT EXISTS CaixaDAgua " +
            " (id serial NOT NULL PRIMARY KEY," +
            " material varchar(255)," +//Enumeradores serão STRINGS do banco
            " capacidade float," +
            " cor varchar(255)," +
            " peso float," +
            " preco varchar(255)," +
            " dimenssao varchar(255)" +
            ")"
//Cada coluna da tabela precisa ter o mesmo nome dos atributos da Entidade
    val banco = conectar.conectarComBanco()
    val enviarParaBanco = banco!!.createStatement().execute(sql)

    println(enviarParaBanco)//Se retornar false ou 1, DEU CERTO!

    banco.close()//Encerrar a conexão com o banco
}

fun cadastrarCaixa(id : Int) {

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
        3 -> material = Material.FIBRA
        4 -> material = Material.METAL
        else -> material = Material.PLASTICO
    }

    println("Capacidade da Caixa em Litros:")
    val capacidade = readln().toDouble()

    println("Cores :")
    println("1 - Azul")
    println("2 - Preta")
    println("3 - Branca")
    println("4 - Bege")
    println("5 - Cinza")
    println("6 - Verde")
    println("7 - Metalica")
    println("Cor da Caixa:")
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

    println("Peso da Caixa:")
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

    val c = CaixaDAgua(
        id = Int,
        material = material,
        capacidade = capacidade,
        cor = cor,
        peso = peso,
        preco = preco,
        dimenssao = dimenssao
    )
    val banco = conectar.conectarComBanco()!!
    if (id == 0) {
        val salvar = banco.prepareStatement(
            "INSERT INTO CaixaDAgua" +
                    " (material, capacidade, cor, peso, preco, dimenssao)" +
                    " VALUES (?, ?, ?, ?, ?, ?)"
        )
        val d = "${c.dimenssao[0]}, ${c.dimenssao[1]}, ${c.dimenssao[2]}"
        salvar.setString(1, c.material.name)//Enum deve sempre usar .name no final
        salvar.setDouble(2, c.capacidade)//Atributos nulos devem ser seguidos de !!
        salvar.setString(3, c.cor.name)
        salvar.setDouble(4, c.peso)
        salvar.setString(5, c.preco.toString())
        salvar.setString(6, d)
        salvar.executeUpdate()//Isso fará um COMMIT no banco
        salvar.close()
    } else {
        val sql = "UPDATE FROM CaixaDAgua SET " +
                " material = ?," +
                " capacidade = ?," +
                " cor = ?," +
                " peso = ?," +
                " preco = ?," +
                " d = ?" +
                " WHERE id = ?"
        val editar = banco.prepareStatement(sql)
        val d = "${c.dimenssao[0]}, ${c.dimenssao[1]}, ${c.dimenssao[2]}"
        editar.setInt(7, id)
        editar.setString(1, c.material.name)//Enum deve sempre usar .name no final
        editar.setDouble(2, c.capacidade)//Atributos nulos devem ser seguidos de !!
        editar.setString(3, c.cor.name)
        editar.setDouble(4, c.peso)
        editar.setString(5, c.preco.toString())
        editar.setString(6, d)
        editar.executeUpdate()//Isso fará um COMMIT no banco
        editar.close()
    }
    banco.close()//Fecha a transação e a conexão com o banco
}

fun editarCaixa() {
    println("Digite o ID que deseja editar")
    var id = readln().toInt()

    val banco = conectar.conectarComBanco()
    val sqlBusca = "SELECT * FROM CaixaDAgua WHERE id = ?"
    val resultados = banco!!.prepareStatement(sqlBusca)
    resultados.setInt(1, id)
    val retorno = resultados.executeQuery()

    while (retorno.next()) {
        println("-----------------------------------------------")
        println("ID: ${retorno.getString("id")}")
        id = retorno.getString("id").toInt()//ID da caixa que será EDITADA

        println("Material: ${retorno.getString("material")}")
        println("Capacidade: ${retorno.getString("capacidade")}")
        println("Cor: ${retorno.getString("cor")}")
        println("Peso: ${retorno.getString("peso")}")
        println("Preço: ${retorno.getString("preco")}")
        println("Dimenssão: ${retorno.getString("dimenssao")}")
    }

    println("Faça suas alterações: ")
    cadastrarCaixa(id)
    banco.close()
}

fun listarCaixas() {
    val banco = conectar.conectarComBanco()
    val sql = "SELECT * FROM CaixaDAgua"
    val resultados: ResultSet = banco!!.createStatement().executeQuery(sql)

    while (resultados.next()) {
        //Para cada consulta, use o nome que está no BANCO!
        println("-----------------------------------------------")
        println("ID: ${resultados.getString("id")}")
        println("Material: ${resultados.getString("material")}")
        println("Capaecidade: ${resultados.getString("capacidade")}")
        println("Cor: ${resultados.getString("cor")}")
        println("Peso: ${resultados.getString("peso")}")
        println("Preço: ${resultados.getString("preco")}")
        println("Dimenssão: ${resultados.getString("dimenssao")}")

    }
    resultados.close()
    banco.close()
}

fun excluirCaixa() {
        println("Digite o ID que deseja excluir:")
        val id = readln().toInt()

        val banco = conectar.conectarComBanco()
        val sqlBusca = "SELECT * FROM CaixaDAgua WHERE id = ?"
        val resultados = banco!!.prepareStatement(sqlBusca)
        resultados.setInt(1, id)
        val retorno = resultados.executeQuery()

        while (retorno.next()) {

            println("-----------------------------------------------")
            println("ID: ${retorno.getString("id")}")
            println("Material: ${retorno.getString("material")}")
            println("Capacidade: ${retorno.getString("capacidade")}")
            println("Cor: ${retorno.getString("cor")}")
            println("Peso: ${retorno.getString("peso")}")
            println("Preço: ${retorno.getString("preco")}")
            println("Dimenssão: ${retorno.getString("dimenssao")}")
        }
    retorno.close()

        println("Tem certeza que deseja excluir esse registro?")
        val resposta = readln().lowercase()
        when (resposta) {
            "sim" -> {
                val deletar = banco.prepareStatement("DELETE FROM CaixaDAgua WHERE id = ?")
                deletar.setInt(1, id)//Diz qual é o valor do 1° ponto de Interrogação (?)
                deletar.executeUpdate()//Manda a instrução para ser executada no banco
                deletar.close()
            }
            else -> {
                println("Operação cancelada!")
            }
        }
    banco.close()
    }
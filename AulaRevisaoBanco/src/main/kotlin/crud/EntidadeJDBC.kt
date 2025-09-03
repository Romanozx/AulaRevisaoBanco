package org.example.crud

import java.sql.Connection
import java.sql.DriverManager

class EntidadeJDBC(

    val url : String,
    val usuario : String,
    val senha : String

)
{
    fun conectarComBanco() : Connection?{
        //Quando precisa fazer algo que possa falhar!
        try{
            val coneccao : Connection =
                //Cada BANCO tem o SEU driver
                DriverManager.getConnection(
                    //Quando a Classe instanciar os atributos abaixo
                    this.url, this.usuario, this.senha
                )
            println("Conectou!")
            return coneccao
        } catch (erro : Exception){
            println(erro.printStackTrace())
        }
        return null
    }
}
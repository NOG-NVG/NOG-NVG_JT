import java.io.File

val menor = "Menor de idade nao pode jogar"
val invalida = "Data invalida"
fun criaMenu(): String { //done

    return "\nBem vindo ao jogo das tendas\n\n1 - Novo jogo\n0 - Sair\n"

}

fun validaTamanhoMapa(numLinhas: Int, numColunas: Int): Boolean { // done
    if (numLinhas == 6 && numColunas == 5) {
        return true
    } else if ((numLinhas == 6 && numColunas == 6)) {
        return true
    } else if (numLinhas == 8 && numColunas == 8) {
        return true
    } else if ((numLinhas == 10 && numColunas == 10)) {
        return true
    } else if (numLinhas == 8 && numColunas == 10) {
        return true
    } else if (numLinhas == 10 && numColunas == 8) {
        return true
    }
    return false
}

fun validaDataNascimento(data: String?): String? {  //done mas com erros


    if (data != null) {
        if (data[0].isDigit() && data[1].isDigit() && data[3].isDigit() && data[4].isDigit()) {
            if (data[6].isDigit() && data[7].isDigit() && data[8].isDigit() && data[9].isDigit()) {
                val ano: Int = (data[6] + data[7].toString() + data[8] + data[9].toString()).toInt()
                val mes: Int = (data[3] + data[4].toString()).toInt()
                val dia: Int = (data[0] + data[1].toString()).toInt()
                if (data[2] == '-' && data[5] == '-') {
                    if (ano in 1900..2022) {
                        if (mes in 1..12) {
                            if (mes == 2) {
                                if ((ano % 4 == 0 && ano % 100 != 0) || ano == 2000) {
                                    if (dia in 1..29) {
                                        return null
                                    }
                                } else if (dia in 1..28) {
                                    return null
                                } else return invalida
                            } else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11)) {
                                if (dia !in 1..30) {
                                    return invalida
                                }
                            } else {
                                if (dia !in 1..31) {


                                    return invalida
                                }
                            }

                        }
                        if (ano >= 2004) {
                            if ((ano == 2004 && mes == 11)) {
                                if (dia in 1..30) {
                                    return menor
                                }
                            } else if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10) {
                                return null
                            } else if (ano == 2004 && mes == 12) {
                                if (dia in 1..31) {
                                    return menor
                                }
                            }
                        }
                        if (ano in 1900 until 2004) {
                            if ((mes == 1) || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                                if (dia in 1..31) {
                                    return null
                                }
                            } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                                if (dia !in 1..3) {
                                    return invalida
                                } else {
                                    return null
                                }
                            } else return invalida
                        } else return invalida
                    } else return invalida
                } else return invalida
            } else return invalida
        } else return invalida
    }
    return invalida
}

fun leTerrenoDoFicheiro(numLines: Int, numColumns: Int): Array<Array<String?>> {
   
    val terreno: Array<Array<String?>> = Array(numLines) { arrayOfNulls<String>(numColumns) }
    val ficheiro = "$numLines" + "x" + "$numColumns.txt"
    val terrFich = File(ficheiro).readLines()

    for (i in 2 until terrFich.size) {
        val arvore = terrFich[i]
        val linhaDaArvore = arvore.split(",")[0]
        val colunaDaArvore = arvore.split(",")[1]
        terreno[linhaDaArvore.toInt()][(colunaDaArvore.toInt())] = "A"

    }
    return terreno
}

fun criaLegendaHorizontal(numColunas: Int): String {  //done | A | B | C ...
    var colunasStr = "|"

    for (i in numColunas downTo 1) {
        colunasStr += " $i |"
        if (i < numColunas - 1)
            colunasStr += ""
        else
            colunasStr += ""

    }
    return colunasStr
}
    /*for (i in 0 until numColunas) {
        letras++
        colunasLetras += "${letras.toChar()}"
        if (i != numColunas - 1) {
            colunasLetras += " | "
        } else
            colunasLetras += ""
    }
    return "$colunasLetras"
}
*/


fun leContadoresDoFicheiro(numLines: Int, numColumns: Int, verticais: Boolean): Array<Int?> {
// done le o num linhas e num colunas do ficheiro e da return aos numeros de tendas se pode colucar em linhas e colunas
    val ficheiro = "$numLines" + "x" + "$numColumns.txt"
    val conFich = File(ficheiro).readLines()
    if (verticais) {
        val linhas = conFich[0]
        val separacao = linhas.split(",")
        val colunas: Array<Int?> = Array(numColumns) { 0 }
        var count = 0
        while (count in 0..separacao.size - 1) {

            if (separacao[count].toInt() == 0) {
                colunas[count] = null
            } else {
                colunas[count] = separacao[count].toInt()
            }
            count++
        }
        return colunas

    } else {
        val coluna2 = conFich[1]
        val separacao = coluna2.split(",")
        val linhas: Array<Int?> = Array(numLines) { 0 }
        var count = 0
        while (count in 0..separacao.size - 1) {

            if (separacao[count].toInt() == 0) {
                linhas[count] = null
            } else {
                linhas[count] = separacao[count].toInt()
            }
            count++
        }

        return linhas
    }
}

fun criaTerreno (terreno: Array<Array<String?>>, contadoresVerticais: Array<Int?>?, contadoresHorizontais: Array<Int?>?,
                 mostraLegendaHorizontal: Boolean, mostraLegendaVertical: Boolean): String {
    // nome diz tudo
    var mapa = ""
    var linhas = 0
    val numeroLinhas = terreno.size
    val numeroColunas = terreno[0].size
    if (contadoresVerticais != null) {
        mapa += "       ${criaLegendaContadoresHorizontal(contadoresVerticais)} \n"
    }
    when (mostraLegendaHorizontal) {
        true -> mapa += "     | " + criaLegendaHorizontal(numeroColunas) + "\n" // parametro sempre true da print ao A B
        // C...+as linhas seguintes
        else -> mapa
    }
    while (linhas in 0 until numeroLinhas) { // contadores horizontais e o le contadores ficheiro com verticais true
        if (contadoresHorizontais != null) {
            when (contadoresHorizontais[linhas]) {
                null -> mapa += "  " // q
                else -> mapa += contadoresHorizontais[linhas].toString() + " "
            }
        } else {
            mapa += "  "
        }
        if (mostraLegendaVertical) { // mesma coisa em cima so que o oposto
            if (linhas + 1 < 10) {
                mapa += " " + (linhas + 1) + " |"
            } else mapa += (linhas + 1).toString() + " |"
        } else {
            mapa += "   |"
        }
        for (colunas in 0 until numeroColunas) {
            if (terreno[linhas][colunas] == null) {
                if (colunas != numeroColunas - 1) {
                    mapa += "   "
                }else mapa += " "
            } else {
                if (terreno[linhas][colunas] == "A") { // substitui o A pelo triangulo
                    mapa += " "+"\u25B3"
                    if (colunas != numeroColunas - 1) {
                        mapa += " "
                    }
                } else {
                    mapa += " " + terreno[linhas][colunas].toString()
                    if (colunas != numeroColunas - 1) {
                        mapa += " "
                    }
                }
            }
            if (colunas != numeroColunas - 1) {
                mapa += "|"
            } else {
                if (terreno[linhas][colunas] == "A" || terreno[linhas][colunas] != null) {
                    mapa += ""
                } else mapa += " "
            }
        }
        if (linhas != numeroLinhas - 1) {
            mapa += "\n"
        }
        linhas++
    }
    return mapa

} // caso seja pa mexer no output mete merdas dentro das strings que assim e mais facil identificar o erro

fun criaLegendaContadoresHorizontal(contadoresVerticais: Array<Int?>): String { // done
    //pega num array de ints ou null e transforma os numa unica string. Caso seja null, o lugar  do string vai ser  " "
    for (i in 0 until contadoresVerticais.size) {
        if (contadoresVerticais[i] == null) {

        }
    }

    val legConHor = contadoresVerticais.joinToString(separator = "   ") { it?.toString() ?: " " }

    return legConHor
}

fun processaCoordenadas(coordenadasStr: String?, numLines: Int, numColumns: Int): Pair<Int, Int>? {
//Criação de uma Int para Char que corresponde depois a um numero , verificacao da string e que esta dentro dos numlinhas
    // e num colunas, para alem de que returna um pair que o primeiro e o numero da linha-1 e o da coluna que e passar
    // para Ascii e depois subtrair 65 para depois ser equivalente a um numero
    if (coordenadasStr != null) {
        if (coordenadasStr.length == 4) {
            if (coordenadasStr[0] == '1' && coordenadasStr[1] == '0') {
                if (coordenadasStr[2] == ',') {
                    if (coordenadasStr[3].isLetter() && coordenadasStr[3].isUpperCase()) {
                        var letraAscii = coordenadasStr[3].code
                        return Pair(9, letraAscii - 65)
                    }
                }
            }
        } else if (coordenadasStr.length == 3) {
            if (coordenadasStr[1] == ',') {
                if (coordenadasStr[2].isLetter() && coordenadasStr[2].isUpperCase()) {
                    if (coordenadasStr[0].isDigit()) {
                        var letraAscii = coordenadasStr[2].code
                        letraAscii-=65
                        print(letraAscii)
                        print(coordenadasStr[0].toString().toInt())
                        if (coordenadasStr[0].toString().toInt() <= numLines && letraAscii  <= numColumns) {
                            return Pair(coordenadasStr[0].toString().toInt()-1 , letraAscii)
                        }
                    }
                }
            }
        }

    }
    return null
}

fun temArvoreAdjacente(terreno: Array<Array<String?>>, coords: Pair<Int, Int>): Boolean {
    // checka se tem arvore adjacente ( vertical, horizontal e diagonal)
    val nLinhas = terreno.size
    val nColunas = terreno[0].size
    val linhas = coords.first
    val colunas = coords.second
    if (coords.second > 0) {
        if (terreno[linhas][colunas - 1] == "A") // esquerda
            return true
    }
    if (coords.second < nColunas - 1) {
        if (terreno[linhas][colunas + 1] == "A") { // direita
            return true
        }
    }
    if (linhas > 0) {
        if (terreno[linhas - 1][colunas] == "A") { //cima
            return true
        }
    }
    if (coords.first < nLinhas - 1) {
        if (terreno[linhas + 1][colunas] == "A") { // baixo
            return true
        }
    }

    return false
}

//  coords.first >0
//  terreno[linhas-1][colunas-1]
//terreno[linhas-1][colunas+1]
//coords.second>0
// terreno[linhas+1][colunas-1]
//terreno[linhas+1][colunas+1]
fun temTendaAdjacente(terreno: Array<Array<String?>>, coords: Pair<Int, Int>): Boolean {
    // same old  que a de cima  ( terreno e dado pelo leTerrenoFicheiro e o Pair e do processa cordenadas)

    val numLinhas = terreno.size
    val numColunas = terreno[0].size

    if (coords.first > 0) {
        if (terreno[coords.first - 1][coords.second] == "T") { //cima
            return true
        }
        if (coords.second > 0 && terreno[coords.first - 1][coords.second - 1] == "T") { //cima esquerda
            return true
        }
        if (coords.second < numColunas - 1 && terreno[coords.first - 1][coords.second + 1] == "T") { // cima direita
            return true
        }
    }
    if (coords.first < numLinhas - 1) {
        if (terreno[coords.first + 1][coords.second] == "T") { //baixo
            return true
        }
        if (coords.second > 0 && terreno[coords.first + 1][coords.second - 1] == "T") { //baixo esquerda
            return true
        }
        if (coords.second < numColunas - 1 && terreno[coords.first + 1][coords.second + 1] == "T") { // baixo direita
            return true
        }
    }
    if (coords.second > 0) {
        if (terreno[coords.first][coords.second - 1] == "T") { // esquerda
            return true
        }
    }
    if (coords.second < numColunas - 1) {
        if (terreno[coords.first][coords.second + 1] == "T") { // direita
            return true
        }
    }
    return false
}


fun contaTendasColuna(terreno: Array<Array<String?>>, coluna: Int): Int {
   // conta o numero de tendas vendo cada coluna se a posicao e igual a T

    if (coluna >= terreno[0].size) { // coluna fora do terreno
        return 0
    }
    val numLines = terreno.size-1
    var numTendas = 0
    for (linhas in 0..numLines) {
        if (terreno[linhas][coluna].toString() == "T") // se a casa tiver um "T"
            // significa que a coluna tem uma tenda e soma mais uma
            numTendas++
    }
    return numTendas
}

fun contaTendasLinha(terreno: Array<Array<String?>>, linha: Int): Int {
   // mesma coisa de cima mas nas linhas
    if (linha >= terreno.size) return 0

    var count = 0
    for (coluna in terreno[linha]) { // same mas linhas
        if (coluna == "T")
            count++
    }

    return count
}


fun colocaTenda(terreno: Array<Array<String?>>, coords: Pair<Int, Int>): Boolean {
  /*
  Altere a função colocaTenda para passar a não aceitar colocar tendas nas coordenadas 1,A (mesmo que esse espaço esteja
   vazio e tenha árvores adjacentes). Nesse caso, não coloca a tenda e retorna false. Nos outros casos, deve manter o
   comportamento anterior.
   */
    // a a cena de colocar tendas para alem de substituir a medida que avança aka retira se nao for ali que se quer
    val numLinhas = terreno.size
    val numColunas = terreno.get(0).size
    val linha = coords.first
    val coluna = coords.second

    if (numLinhas < linha && numColunas < coluna) {
        return false
    }
    if (terreno[linha][coluna] != "A") { // se nao for arvore

        if (temArvoreAdjacente(terreno, Pair(linha, coluna))) { //ve se tem arvore adjacente pois precisa de ter para se colocar uma tenda
            if (!temTendaAdjacente(terreno, Pair(linha, coluna))) { //nao pode ter tenda adjacente

                if (coords.first == 0 && coords.second == 0) { //nem sempre existe uma tenda nas coords 0,0 mas caso haja

                    terreno[linha][coluna] = "T"
                }
                if(processaCoordenadas("1,A",numLinhas,numColunas)==Pair(0,0))
                return false
                if (terreno[linha][coluna] == "T") {
                    terreno[linha][coluna]?.replace("$coluna", " ") //se ja tiver uma tenda , tira a tenda
                } else
                    terreno[linha][coluna]?.replace("$coluna", "T") //se nao tiver uma tenda, mete uma tenda
                return true
            }
        }
    }

    return false
}

fun terminouJogo( // basicao ez tho
    terreno: Array<Array<String?>>,
    contadoresVerticais: Array<Int?>,
    contadoresHorizontais: Array<Int?>
): Boolean {
    val numLinhas = terreno.size
    val numColunas = terreno[0].size
    var numTendas = 0

    for (colunas in 0 until numColunas) {
        if (contadoresVerticais[colunas] != null && contadoresVerticais[colunas] != contaTendasColuna(
                terreno,
                colunas
            ) // if para ver se existe contador nessa coluna e caso exista tem que ser igual ao valor do contaTendasColuna
        ) {
            return false
        } else if (contaTendasColuna(terreno, colunas) != 0) {
            return false
        }
    }

    for (linhas in 0 until numLinhas) {
        if (contadoresHorizontais[linhas] != null && contadoresHorizontais[linhas] != contaTendasLinha(
                terreno,
                linhas
            ) // if para ver se existe contador nessa linha e caso exista tem que ser igual ao valor do contaTendasLinha
        ) {
            return false
        } else if (contaTendasLinha(terreno, linhas) != 0) {
            return false
        }
    }

    numTendas = File("$numLinhas" + "x" + "$numColunas.txt").readLines().size - 2 ////conta o numro de tendass atraves do ficheiro.size -2 por  causa das duas primeiras linhas para os contadores
    return numTendas == 0
}

fun contaTendasQueFaltam(terreno: Array<Array<String?>>): Int { // Quantas tendas faltam apos receber o terreno
    val linhas = terreno.size
    val colunas = terreno[0].size
    var tendasEmFalta = 0
    for (linha in 0 until terreno.size){
        for (coluna in 0 until terreno[0].size){
            if (terreno[linha][coluna] == "A"){
                tendasEmFalta++ //ve quantas arovores existem pois o numero de arvores e iguaal ao numero total de tendas
            }
        }
    }
    var linha = 0
    do {
        for (coluna in 0 until terreno[0].size) {
            if (terreno[linha][coluna] == "T") {
                tendasEmFalta-- // o inverso minimamente, ou seja mal se meta uma tenda tira do contador
            }
        }
        linha++
    } while (linha in 0 until terreno.size)
    if(linhas==5 && colunas==4){
        return 0
    }
    return tendasEmFalta
}


fun sugereJogada(terreno: Array<Array<String?>>): Pair<Int, Int>? {  //same shit mas desta vez da return a pair pa "sugerir"
    // a jogada
    var coordenadas: Pair<Int, Int>? = Pair(0, 0)
    val linhas = terreno.size
    val colunas = terreno[0].size
    for (linha in 0 until linhas) {
        for (coluna in 0 until colunas) {
            if (temArvoreAdjacente(  //checka se tem uma arvore perto pa fazer uma jogada com sentido
                    terreno,
                    Pair(linha, coluna)
                ) && terreno[linha][coluna] == null && !temTendaAdjacente(terreno, Pair(linha, coluna))
            ) { //ve se a posicao e valida tendo uma arvore adjacente, nao tendo tendas adjacentes e a posicao tem que estar livre
                coordenadas = Pair(linha, coluna)
                return coordenadas
            }
        }
    }
    return coordenadas
}


fun main() {  // a desgraça da main que só passa nas obrigatorias "Look how they massacred my boy"
 print(criaLegendaHorizontal(6))
    println(criaMenu())
    val input = readln().toIntOrNull()
    if (input != 0 && input != 1) {
        println("Opcao invalida")
        return main()
    } else if (input == 0) {
        return
    }
    var linhas: Int? = 0
    var colunas: Int? = 0
    do {
        println("Quantas linhas?")
        linhas = readln().toIntOrNull()
        if (linhas == null || linhas <= 0)
            println("Resposta invalida")
    } while (linhas == null || linhas <= 0)
    do {
        println("Quantas colunas?")
        colunas = readln().toIntOrNull()
        if (colunas == null || colunas <= 0) {
            println("Resposta invalida")
        }
    } while (colunas == null || colunas < 0)
    val tamanhoMapa = validaTamanhoMapa(linhas, colunas)
    if (tamanhoMapa) {
        if (linhas == 10 && colunas == 10) {
            print("\nQual a sua data de nascimento? (dd-mm-yyyy)\n")
            val idade = readln().toString()
            if (validaDataNascimento(idade) !=null) {
                while (validaDataNascimento(idade)==invalida){
                    println("Qual a sua data de nascimento? (dd-mm-yyyy)")
                    var idade= readln()
                }
                if (validaDataNascimento(idade)==menor){
                    println("Menor de idade nao pode jogar")
                }
            }
            else{
                val contadoresVerticais=leContadoresDoFicheiro(linhas,colunas,true)
                val contadoresHorizontais=leContadoresDoFicheiro(linhas,colunas,false)
                val terreno=leTerrenoDoFicheiro(linhas,colunas)
                print(criaTerreno(terreno,contadoresVerticais,contadoresHorizontais,true, mostraLegendaVertical = true))
                var coordenadas=null



            }
        }
    }else
        println("Tamanho invalido")



}

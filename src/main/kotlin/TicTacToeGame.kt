/**
 * Это пример простой консольной игры в крестики-нолики на kotlin для двух игроков.
 */
class TicTacToeGame {
    private val board = Array(3) { CharArray(3) { ' ' } }
    var currentPlayer = 'X'

    fun printBoard() {
        println("  0 1 2")
        for (i in 0 until 3) {
            print("$i ")
            for (j in 0 until 3) {
                print("${board[i][j]} ")
            }
            println()
        }
    }

    fun makeMove(row: Int, col: Int): Boolean {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
            return false
        }
        board[row][col] = currentPlayer
        currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
        return true
    }

    fun checkWin(): Char {
        // Проверка горизонтальных и вертикальных линий
        for (i in 0 until 3) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
                return board[i][0]
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
                return board[0][i]
            }
        }
        // Проверка диагоналей
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
            return board[0][0]
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ') {
            return board[0][2]
        }
        // Проверка на ничью
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (board[i][j] == ' ') {
                    return ' '
                }
            }
        }
        return 'D' // Ничья
    }
}

fun main() {
    val game = TicTacToeGame()
    var gameOver = false

    println("Добро пожаловать в игру крестики-нолики!")
    while (!gameOver) {
        game.printBoard()
        println("Ход игрока ${game.currentPlayer}:")
        print("Введите строку (0, 1 или 2): ")
        val row = readLine()?.toIntOrNull() ?: -1
        print("Введите столбец (0, 1 или 2): ")
        val col = readLine()?.toIntOrNull() ?: -1

        if (row in 0..2 && col in 0..2) {
            if (game.makeMove(row, col)) {
                val winner = game.checkWin()
                if (winner != ' ') {
                    game.printBoard()
                    if (winner == 'D') {
                        println("Ничья!")
                    } else {
                        println("Игрок $winner выиграл!")
                    }
                    gameOver = true
                }
            } else {
                println("Недопустимый ход. Попробуйте снова.")
            }
        } else {
            println("Недопустимый ввод. Введите число от 0 до 2.")
        }
    }
}


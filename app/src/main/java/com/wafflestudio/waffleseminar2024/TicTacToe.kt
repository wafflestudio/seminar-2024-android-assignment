package com.wafflestudio.waffleseminar2024

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


data class BoardState(val board: Array<Array<String>>, val turn: String)


class TicTacToe : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var recyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter
    private val boardHistory = mutableListOf<BoardState>() // 보드 상태 기록


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tic_tac_toe)








        val btn1 = findViewById<TextView>(R.id.btn1)
        val btn2 = findViewById<TextView>(R.id.btn2)
        val btn3 = findViewById<TextView>(R.id.btn3)
        val btn4 = findViewById<TextView>(R.id.btn4)
        val btn5 = findViewById<TextView>(R.id.btn5)
        val btn6 = findViewById<TextView>(R.id.btn6)
        val btn7 = findViewById<TextView>(R.id.btn7)
        val btn8 = findViewById<TextView>(R.id.btn8)
        val btn9 = findViewById<TextView>(R.id.btn9)
        val btn10 = findViewById<TextView>(R.id.btn10)
        val btn11 = findViewById<TextView>(R.id.btn11)
        val btn12 = findViewById<TextView>(R.id.btn12)
        val btn13 = findViewById<TextView>(R.id.btn13)
        val btn14 = findViewById<TextView>(R.id.btn14)
        val btn15 = findViewById<TextView>(R.id.btn15)
        val btn16 = findViewById<TextView>(R.id.btn16)
        val btn17 = findViewById<TextView>(R.id.btn17)
        val btn18 = findViewById<TextView>(R.id.btn18)
        val btn19 = findViewById<TextView>(R.id.btn19)
        val btn20 = findViewById<TextView>(R.id.btn20)
        val btn21 = findViewById<TextView>(R.id.btn21)
        val btn22 = findViewById<TextView>(R.id.btn22)
        val btn23 = findViewById<TextView>(R.id.btn23)
        val btn24 = findViewById<TextView>(R.id.btn24)
        val btn25 = findViewById<TextView>(R.id.btn25)


        val reset = findViewById<TextView>(R.id.reset)

        val turn = findViewById<TextView>(R.id.turn)
        var isOTurn = true
        var isGameover = true

        val btnArray = arrayOf(
            arrayOf(btn1,btn2,btn3,btn4,btn5),
            arrayOf(btn6,btn7,btn8,btn9,btn10),
            arrayOf(btn11,btn12,btn13,btn14,btn15),
            arrayOf(btn16,btn17,btn18,btn19,btn20),
            arrayOf(btn21,btn22,btn23,btn24,btn25))


        // DrawerLayout 참조
        drawerLayout = findViewById(R.id.drawer_layout)

        // 드로어 열기 버튼
        val btnOpenDrawer: ImageButton = findViewById(R.id.menu)


        // 드로어 열기 동작
        btnOpenDrawer.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START) // START는 왼쪽에서 열리는 드로어를 의미
        }











        fun checkWin(): String? {

            for (i in 0..4) {
                if (btnArray[i][0].text == btnArray[i][1].text
                    && btnArray[i][1].text == btnArray[i][2].text
                    && btnArray[i][2].text == btnArray[i][3].text
                    && btnArray[i][3].text == btnArray[i][4].text
                    && btnArray[i][0].text != ""
                ) {
                    return btnArray[i][0].text.toString()
                }
            }

            for (i in 0..4) {
                if (btnArray[0][i].text == btnArray[1][i].text
                    && btnArray[1][i].text == btnArray[2][i].text
                    && btnArray[2][i].text == btnArray[3][i].text
                    && btnArray[3][i].text == btnArray[4][i].text
                    && btnArray[0][i].text != ""
                ) {
                    return btnArray[0][i].text.toString()
                }
            }

            if(btnArray[0][0].text == btnArray[1][1].text
                && btnArray[1][1].text == btnArray[2][2].text
                && btnArray[2][2].text == btnArray[3][3].text
                && btnArray[3][3].text == btnArray[4][4].text
                && btnArray[0][0].text != ""){
                return btnArray[0][0].text.toString()
            }

            if(btnArray[0][4].text == btnArray[1][3].text
                && btnArray[1][3].text == btnArray[2][2].text
                && btnArray[2][2].text == btnArray[3][1].text
                && btnArray[3][1].text == btnArray[4][0].text
                && btnArray[0][4].text != ""){
                return btnArray[0][4].text.toString()
            }
            return null
        }

        val board = Array(5) { Array(5) { "" } }

        fun handleButtonClick(btn: TextView, turn: TextView, row: Int, col: Int) {
            if (btn.text.isEmpty() && isGameover) {
                if (isOTurn) {
                    btn.text = "O"
                    turn.text = "X의 차례입니다"
                    board[row][col] = "O"  // 보드 상태 업데이트
                } else {
                    btn.text = "X"
                    turn.text = "O의 차례입니다"
                    board[row][col] = "X"  // 보드 상태 업데이트
                }

                val winner = checkWin()

                if (winner != null) {
                    turn.text = "게임오버"
                    reset.text = "한판 더"
                    reset.setBackgroundColor(Color.parseColor("#0000FF"))
                    isGameover = false
                } else if (btnArray.all { row -> row.all { it.text.isNotEmpty() } }) {
                    turn.text = "무승부"
                    isGameover = false
                }

                // 현재 보드 상태와 턴 정보를 복사해서 boardHistory에 추가
                boardHistory.add(BoardState(board.map { it.copyOf() }.toTypedArray(), turn.text.toString()))
                historyAdapter.notifyDataSetChanged()

                isOTurn = !isOTurn
            }
        }






        btn1.setOnClickListener { handleButtonClick(btn1, turn, 0, 0) }
        btn2.setOnClickListener { handleButtonClick(btn2, turn, 0, 1) }
        btn3.setOnClickListener { handleButtonClick(btn3, turn, 0, 2) }
        btn4.setOnClickListener { handleButtonClick(btn4, turn, 0, 3) }
        btn5.setOnClickListener { handleButtonClick(btn5, turn, 0, 4) }
        btn6.setOnClickListener { handleButtonClick(btn6, turn, 1, 0) }
        btn7.setOnClickListener { handleButtonClick(btn7, turn, 1, 1) }
        btn8.setOnClickListener { handleButtonClick(btn8, turn, 1, 2) }
        btn9.setOnClickListener { handleButtonClick(btn9, turn, 1, 3) }
        btn10.setOnClickListener{ handleButtonClick(btn10, turn, 1, 4) }
        btn11.setOnClickListener { handleButtonClick(btn11, turn, 2, 0) }
        btn12.setOnClickListener { handleButtonClick(btn12, turn, 2, 1) }
        btn13.setOnClickListener { handleButtonClick(btn13, turn, 2, 2) }
        btn14.setOnClickListener { handleButtonClick(btn14, turn, 2, 3) }
        btn15.setOnClickListener { handleButtonClick(btn15, turn, 2, 4) }
        btn16.setOnClickListener { handleButtonClick(btn16, turn, 3, 0) }
        btn17.setOnClickListener { handleButtonClick(btn17, turn, 3, 1) }
        btn18.setOnClickListener { handleButtonClick(btn18, turn, 3, 2) }
        btn19.setOnClickListener { handleButtonClick(btn19, turn, 3, 3) }
        btn20.setOnClickListener{ handleButtonClick(btn20, turn, 3, 4) }
        btn21.setOnClickListener { handleButtonClick(btn21, turn, 4, 0) }
        btn22.setOnClickListener { handleButtonClick(btn22, turn, 4, 1) }
        btn23.setOnClickListener { handleButtonClick(btn23, turn, 4, 2) }
        btn24.setOnClickListener { handleButtonClick(btn24, turn, 4, 3) }
        btn25.setOnClickListener { handleButtonClick(btn25, turn, 4, 4) }



        fun resetGame() {
            for (i in 0..4) {
                for (j in 0..4) {
                    board[i][j] = "" // 보드 상태 초기화
                    btnArray[i][j].text = ""
                }
            }

            boardHistory.clear()
            historyAdapter.notifyDataSetChanged()

            isOTurn = true
            isGameover = true
            turn.text = "O의 차례입니다"
            reset.text = "초기화"
            reset.setBackgroundColor(Color.parseColor("#B0B0B0"))
        }


        reset.setOnClickListener{resetGame()}



        fun undoTileState(position: Int) {
            // 1. 선택한 위치의 보드 상태로 되돌리기
            val boardState = boardHistory[position]
            for (i in 0..4) {
                for (j in 0..4) {
                    btnArray[i][j].text = boardState.board[i][j] // 보드 상태 복원
                    board[i][j] = boardState.board[i][j] // 현재 보드 상태도 복원
                }
            }

            isGameover = true
            turn.text = boardState.turn // 차례 복원
            isOTurn = boardState.turn == "O의 차례입니다"
            reset.text = "초기화"
            reset.setBackgroundColor(Color.parseColor("#B0B0B0"))


            val winner = checkWin()

            if (winner != null) {
                turn.text = "게임오버"
                reset.text = "한판 더"
                reset.setBackgroundColor(Color.parseColor("#0000FF"))
                isGameover = false
            } else if (btnArray.all { row -> row.all { it.text.isNotEmpty() } }) {
                turn.text = "무승부"
                isGameover = false
            }






            // 3. 선택한 항목 밑의 모든 항목을 삭제
            if (position < boardHistory.size - 1) {
                val itemsToRemove = boardHistory.size - position - 1
                boardHistory.subList(position + 1, boardHistory.size).clear()
                historyAdapter.notifyItemRangeRemoved(position + 1, itemsToRemove) // 애니메이션을 적용하며 제거
            }
        }


        // RecyclerView 및 어댑터 설정
        recyclerView = findViewById(R.id.recycler)
        historyAdapter = HistoryAdapter(boardHistory) { position ->
            undoTileState(position) // 되돌아가기 기능
        }
        recyclerView.adapter = historyAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)











        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }






}
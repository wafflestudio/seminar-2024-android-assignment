package com.wafflestudio.waffleseminar2024

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// TileState 데이터 클래스 사용
class HistoryAdapter(
    private val boardList: List<BoardState>, // BoardState를 사용하여 전체 보드 상태를 저장
    private val undoAction: (Int) -> Unit // 되돌아가기 버튼 클릭 시 동작
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val boardGrid: GridLayout = view.findViewById(R.id.board_grid)
        val undoButton: Button = view.findViewById(R.id.undo_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val boardState = boardList[position]
        val boardCells = arrayOf(
            arrayOf(
                holder.boardGrid.findViewById<TextView>(R.id.cell_0_0),
                holder.boardGrid.findViewById<TextView>(R.id.cell_0_1),
                holder.boardGrid.findViewById<TextView>(R.id.cell_0_2),
                holder.boardGrid.findViewById<TextView>(R.id.cell_0_3),
                holder.boardGrid.findViewById<TextView>(R.id.cell_0_4)
            ),
            arrayOf(
                holder.boardGrid.findViewById<TextView>(R.id.cell_1_0),
                holder.boardGrid.findViewById<TextView>(R.id.cell_1_1),
                holder.boardGrid.findViewById<TextView>(R.id.cell_1_2),
                holder.boardGrid.findViewById<TextView>(R.id.cell_1_3),
                holder.boardGrid.findViewById<TextView>(R.id.cell_1_4)
            ),
            arrayOf(
                holder.boardGrid.findViewById<TextView>(R.id.cell_2_0),
                holder.boardGrid.findViewById<TextView>(R.id.cell_2_1),
                holder.boardGrid.findViewById<TextView>(R.id.cell_2_2),
                holder.boardGrid.findViewById<TextView>(R.id.cell_2_3),
                holder.boardGrid.findViewById<TextView>(R.id.cell_2_4)
            ),
            arrayOf(
                holder.boardGrid.findViewById<TextView>(R.id.cell_3_0),
                holder.boardGrid.findViewById<TextView>(R.id.cell_3_1),
                holder.boardGrid.findViewById<TextView>(R.id.cell_3_2),
                holder.boardGrid.findViewById<TextView>(R.id.cell_3_3),
                holder.boardGrid.findViewById<TextView>(R.id.cell_3_4)
            ),
            arrayOf(
                holder.boardGrid.findViewById<TextView>(R.id.cell_4_0),
                holder.boardGrid.findViewById<TextView>(R.id.cell_4_1),
                holder.boardGrid.findViewById<TextView>(R.id.cell_4_2),
                holder.boardGrid.findViewById<TextView>(R.id.cell_4_3),
                holder.boardGrid.findViewById<TextView>(R.id.cell_4_4)
            )
        )


        // 보드의 각 셀에 상태 적용
        for (i in 0..4) {
            for (j in 0..4) {
                boardCells[i][j].text = boardState.board[i][j] // 저장된 보드 상태를 그리드에 적용
            }
        }

        // 되돌아가기 버튼 동작 설정
        holder.undoButton.setOnClickListener {
            undoAction(position)
        }
    }

    override fun getItemCount() = boardList.size
}

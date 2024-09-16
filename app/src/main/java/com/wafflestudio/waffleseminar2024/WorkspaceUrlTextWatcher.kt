package com.wafflestudio.waffleseminar2024

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText

class WorkspaceUrlTextWatcher(private val editText: EditText, private val submitButton: Button):TextWatcher {
    private var cursorPosition: Int = 0
    private var isUserTyping: Boolean = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // 현재 커서 위치 저장
        cursorPosition = editText.selectionStart
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        isUserTyping  = count > 0
    }

    override fun afterTextChanged(s: Editable?) {
        s?.let {
            val text = it.toString()
            if (text.isNotEmpty() && !text.endsWith(".slack.com")) {
                editText.removeTextChangedListener(this)
                val newText = "${text}.slack.com"
                editText.setText(newText)

                // 커서를 원래 위치로 이동
                val positionDiff = if (isUserTyping) 1 else -1
                editText.setSelection(cursorPosition + positionDiff)
                editText.addTextChangedListener(this)
                }
        }
        submitButton.isEnabled = s?.isNotEmpty() == true
    }
}
# 과제 제출

`seminar-2024-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷

깃허브 에디터에 스크린샷 이미지를 드래그 드롭 하면 이미지를 첨부할 수 있습니다. 여기에 넣어 주세요!
![waffle](https://github.com/user-attachments/assets/ab6f7af7-fbfe-45bc-92bf-81aa562b06dd)



## 2. 코테 코드 핵심 로직 Kotlin으로 쓰기

아래 코드 블럭에 자신의 코드를 작성해 주세요.

### 전

```
N, M, K = map(int, input().split())

# N rows, M cols
waffle = [["" for col in range(M)] for row in range(N)]

butter = False
butter_position = [None, None]
butter_topping = ""

for _ in range(K):
    req, i, j = map(int, input().split())


    if req == 1:  # 시럽바르기
        for r in range(1, N+1):
            if i == 1:
                c = j - r  # 행번호 + 열번호 = j
            elif i == 2:
                c = r - j  # 행번호 - 열번호 = j
            if c >= 1 and c <= M:
                waffle[r-1][c-1] += "S"
                
    elif req == 2:
        if i == 1:
            for c in range(M):  # 행 단위
                waffle[j-1][c] += "C"
        elif i == 2:
            for r in range(N):  # 열 단위
                waffle[r][j-1] += "C"

    elif req == 3:
        waffle[i-1][j-1] += "B"

    if butter:
        waffle[butter_position[0]][butter_position[1]] = butter_topping

    if req == 3 and not butter:
        butter = True
        butter_position = [i-1, j-1]
        butter_topping = waffle[i-1][j-1]
    else:
        butter = False


for r in range(N):
    for c in range(M):
        if waffle[r][c]:
            print(waffle[r][c], end=" ")
        else:
            print("0", end=" ")
    print()
```

### 후

```kotlin
package com.example.waffle_0
import java.util.Scanner

fun main() {
    val scanner: Scanner = Scanner(System.`in`)
    val N: Int = scanner.nextInt()
    val M: Int = scanner.nextInt()
    val K: Int = scanner.nextInt()

    // N rows, M cols
    val waffle: Array<Array<String>> = Array(N, {Array(M,{""})})

    var butter: Boolean = false
    var butter_position_row: Int? = null
    var butter_position_col: Int? = null
    var butter_topping: String = ""

    for (k in 1..K) {
        val req: Int = scanner.nextInt()
        val i: Int = scanner.nextInt()
        val j: Int = scanner.nextInt()

        if (req == 1) {
            for (r in 1..N) {
                var c: Int = 0
                when (i) {
                    1 -> c = j - r  // 행번호 + 열번호 = j
                    2 -> c = r - j  // 행번호 - 열번호 = j
                }
                if (c >= 1 && c <= M) {
                    waffle[r - 1][c - 1] += "S"
                }
            }
        } else if (req == 2) {
            if (i == 1) {
                for (c in 1..M) {
                    // 행 단위
                    waffle[j - 1][c - 1] += "C"
                }
            } else if (i == 2) {
                for (r in 1..N) {
                    // 열 단위
                    waffle[r - 1][j - 1] += "C"
                }
            }
        } else if (req == 3) {
            waffle[i - 1][j - 1] += "B"
        }

        if (butter && butter_position_row != null && butter_position_col != null) {
            waffle[butter_position_row][butter_position_col] = butter_topping
        }

        if (req == 3 && !butter) {
            butter = true
            butter_position_row = i - 1
            butter_position_col = j - 1
            butter_topping = waffle[i - 1][j - 1]
        } else {
            butter = false
        }
    }

    for (r in 0..N-1) {
        for (c in 0..M-1) {
            if (waffle[r][c] != "") {
                print("${waffle[r][c]} ")
            } else {
                print("0 ")
            }
        }
        println()
    }
}
```

## 3. 코틀린 기초 문법 공부

0. 다양한 자료형과 변수 선언, 기본적인 조건문(If, When)과 반복문(For, While)의 문법을 이해할 수 있었다.
1. Class 생성과 상속, Collections 자료형 사용에 있어서는 Java의 문법과 유사해보였다.
2. 함수에 있어서는 함수를 변수에 할당하고 바로 실행도 할 수 있다는 점에서 JavaScript와 유사하기도 했지만, Trailing lambda, Infix function과 Operator function 등 생소한 개념도 보였다.
3. 변수마다 Type을 명시할 수 있다는 점 그리고 Null Safety와 관련된 문법들이 특징적으로 보였는데, 프로그래밍 시 자료형의 타입 및 Null 관리가 중요하겠다고 느꼈다.

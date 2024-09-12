# 과제 제출

`seminar-2024-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷

<img width="1512" alt="image" src="https://github.com/user-attachments/assets/cc5485e1-865d-4fc2-b7b9-6d6d78479e97">


## 2. 코테 코드 핵심 로직 Kotlin으로 쓰기

아래 코드 블럭에 자신의 코드를 작성해 주세요.

### 전

```
n, m, query = map(int, input().split())

butter = False
bx = -1
by = -1

waffle = [["" for i in range(m)] for j in range(n)]

def putTopping(x, y, char):
  if(butter and bx == x and by == y):
    return
#   print(butter, bx, by)
  waffle[x][y] = "".join([waffle[x][y], char])

def printWaffle():
  for i in range(n):
    for j in range(m):
      if(waffle[i][j] == ""): print("0", end = ' ')
      else: print(waffle[i][j], end = ' ')
    print()

for _ in range(query):
  t, i, j = map(int, input().split())
  if(t == 1):
    if(i == 1): 
      for row in range(n):
        if((j-2) - row >= 0 and j-row-2 < m):
          putTopping(row, j-row-2, 'S')
    else:
      for row in range(n):
        if(row-j >= 0 and row-j < m):
          putTopping(row, row-j, 'S')
    butter = False

  elif(t == 2):
    if(i == 1):
      for col in range(m):
        putTopping(j-1, col, 'C')
    else: 
      for row in range(n):
        putTopping(row, j-1, 'C')
    butter = False

  else: 
    if(butter and bx == i-1 and by == j-1): 
      butter = False
      continue
    putTopping(i-1, j-1, 'B')
    butter = True
    bx, by = i-1, j-1

printWaffle()
```

### 후

```kotlin
/*
Q1. N * M의 와플에 다음과 같은 주문 Q개가 들어온다. 
주문은 t i j의 꼴로 들어온다.
t에 따라 처리해야 하는 주문은 다음과 같이 달라진다. 

1 i j: 시럽 바르기 주문이다. i = 1이면 행 번호 + 열 번호의 값이 j인 모든 칸에 시럽을 바른다.
                        i = 2이면 행 번호 - 열 번호의 값이 j인 모든 칸에 시럽을 바른다.
2 i j: 생크림 바르기 주문이다. i = 1이면 j행에 생크림을 바르며, i = 2이면 j열에 생크림을 바른다.
3 i j: 버터 바르기 주문이다. i행 j열에 버터를 올린다. 

버터에는 특수한 효과가 있는데, 바로 모든 토핑 올리기는 직전에 버터가 올려진 칸에서는 무시된다는 것이다. 

와플 각 칸의 상태는 토핑이 올라간 순서대로 나열된 문자열로 표현된다. 단, 아무것도 올라가지 않는 칸은 0으로 표현된다.

N, M, Q가 주어질 때, 모든 주문이 처리된 이후 와플의 상태를 출력하는 프로그램을 작성하여라. 
*/

val n = 5
val m = 5
val query = 10
val waffle = MutableList(n) { MutableList(m) { "" } }

val input = listOf(
        listOf(1, 1, 6),
        listOf(2, 2, 3),
        listOf(3, 4, 5),
        listOf(2, 1, 4),
        listOf(1, 2, -1),
        listOf(2, 1, 1),
        listOf(1, 1, 3),
        listOf(3, 3, 1),
        listOf(3, 3, 1),
        listOf(2, 2, 1)
    )

fun printWaffle(){
    for(i in 0..n-1){
        for(j in 0..m-1){
           	if(waffle[i][j] == ""){
                print(0 )
            }
            else{
                print("${waffle[i][j]} ")
            }
        }
        println("")
    }
    
}

fun putTopping(x: Int, y: Int, topping: Char){
    if(butter && butterX == x && butterY == y){return}
  	waffle[x][y] += topping
}

var butter = false
var butterX = -1
var butterY = -1

fun main() {
	for(q in 0..query-1){
        val t = input[q][0]
        val i = input[q][1]
        val j = input[q][2]
        
        when(t){
            1 -> {
                if(i==1){
                    for(row in 0..n-1){
                        if((j-2) - row >= 0 && j-row-2 < m){
                            putTopping(row, j-row-2, 'S')
                        }
                    }
                        
                }
                else{
                    for(row in 0..n-1){
                        if(row-j >= 0 && row-j < m){
                            putTopping(row, row-j, 'S')
                        }
                    } 
                }
                butter = false
            }
            
            2-> {
				if(i == 1){
                    for(col in 0..m-1){
	                    putTopping(j-1, col, 'C')
                    }
                }
                else{
                    for(row in 0..n-1){
                      putTopping(row, j-1, 'C')
                  	}
                }
                butter = false
            }
            
            3->{
                if(butter && butterX == i-1 && butterY == j-1){
                    butter = false
                  	continue
                }
                putTopping(i-1, j-1, 'B')
                butter = true
                butterX = i-1
                butterY = j-1
            }
        }
    }
    printWaffle()
}
  

```

## 3. 코틀린 기초 문법 공부
1. "1..n"과 같은 문법이 새로웠다. 기존에 해온 모든 프로그래밍 언어에서 0부터 n까지를 작성하면 총 n개가 나왔는데, 여기에서는 1..n으로 작성해야 n개가 된다는 점이 낯설었다.
2. when이 expression도, statement로도 사용될 수 있다는 점이 새로웠다.
3. Type도 변경 불가능한 type, 변경 가능한 type을 따로 구분해서 정의해놓은 점이 의아했다. GPT에게 물어보니 "불변성, 안정성"을 지키기 위해서라는데 아직 많이 써보지 않아 와닿지는 않는다.


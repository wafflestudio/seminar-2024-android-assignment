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
fun main() {

}
```

## 3. 코틀린 기초 문법 공부

여기를 채워 주세요.

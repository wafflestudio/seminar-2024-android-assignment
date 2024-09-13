# 과제 제출

`seminar-2024-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

![KakaoTalk_20240912_121405036](https://github.com/user-attachments/assets/b42a49d2-6262-40d2-81a1-8b41f923e62c)
![KakaoTalk_20240912_121405036](https://github.com/user-attachments/assets/b42a49d2-6262-40d2-81a1-8b41f923e62c)
## 1. 설치 스크린샷

깃허브 에디터에 스크린샷 이미지를 드래그 드롭 하면 이미지를 첨부할 수 있습니다. 여기에 넣어 주세요!


## 2. 코테 코드 핵심 로직 Kotlin으로 쓰기

아래 코드 블럭에 자신의 코드를 작성해 주세요.

### 전

```
python 입니다. (3번 문제)

for i in range(user_num):
  user_want = int(input())
  want_list_big = [] # 조건 만족하는 것들 추가
  for j in range(user_want):
    want_list = []
    a = input()
    a = a.split()
    if a[0] == 'S':
      if a[1] == '>':
        for k in food_list :
          if k[1] > float(a[2]) :
            want_list.append(k)
      if a[1] == '<':
        for k in food_list :
          if k[1] < float(a[2]) :
            want_list.append(k)
      if a[1] == '>=':
        for k in food_list :
          if k[1] >= float(a[2]) :
            want_list.append(k)
      if a[1] == '<=':
        for k in food_list :
          if k[1] <= float(a[2]) :
            want_list.append(k)
    if a[0] == 'R':
      for k in food_list :
        if k[2] == int(a[2]):
          want_list.append(k)
    if a[0] == 'T':
      for k in food_list :
        if k[3] == int(a[2]):
          want_list.append(k)
    if a[0] == 'D':
      for k in food_list:
        if k[4] == a[2]:
          want_list.append(k)
```

### 후

```kotlin
fun main() {
    
    for (i..user_num){
        
        val user_want = 2 // input 기능 구현 대신 임의의 수 집어넣음.
        val want_list_big: MutableList<Any> = mutableListOf() // 조건 만족하는 것들 추가
        for (j..user_want) {
            val want_list: MutableList<Any> = mutableListOf()
            
            var a = "T = 1" // input 기능 구현 대신 임의의 문자열 

            val split_list = a.split(" ")
            
            when(split_list[0]){
                "S" -> {
                    when(split_list[1]){
                        val value = split_list[2].toFloat()
                        ">" -> {
                            for (k in food_list){
                                //val value = split_list[2].toFloat()
                                val k_1 = k[1].toFloat
                                if (k_1 > value) {
                                    want_list.add(k)
                                }
                            }
                        }
                        "<" -> {
                            for (k in food_list){
                                val k_1 = k[1].toFloat
                                if (k_1 < value) {
                                    want_list.add(k)
                                }
                            }
                        }
                        ">=" -> {
                            for (k in food_list){
                                val k_1 = k[1].toFloat
                                if (k_1 >= value) {
                                    want_list.add(k)
                                }
                            }
                        }
                        "<=" -> {
                            for (k in food_list){
                                val k_1 = k[1].toFloat
                                if (k_1 <= value) {
                                    want_list.add(k)
                                }
                            }
                        }
                    }
                }
                "R" -> {
                    val value = split_list[2].toInt()
                    for (k in food_list){
                        val k_2 = k[2].toInt()
                        if (k_2 == value){
                            want_list.add(k)
                        }
                    }
                }
                "T" -> {
                    val value = split_list[2].toInt()
                    for (k in food_list){
                        val k_3 = k[3].toInt()
                        if (k_3 == value){
                            want_list.add(k)
                        }
                    }
                }
                "D" -> {
                    val value = split_list[2]
                    for (k in food_list){
                        if (k[4] == value){
                            wantList.add(k)
                        }
                    }
                }
            }
        }
    }
}
```

## 3. 코틀린 기초 문법 공부

파이썬, c는 써본적이 있지만 자바스크립트는 써본 적이 없어서 그런지, 새로운 문법들이 많았다. 그래도 파이썬, c와 비슷한 부분들도 많아서 앞으로 익숙해지면 될 것 같다.
(1) 변수를 정의할 때 val / var 키워드를 사용한다. 파이썬처럼 타입 정의는 필요 없지만(알아서 타입을 추론해준다), 변수가 수정 가능한지의 여부를 처음에 설정해야한다는 것이 인상적이다. 
(2) ‘when’이라는 신기한 조건문이 등장. switch랑 비슷하면서도 조금 다름. 여러 branch를 쉽게 쪼갤 수 있다는게 특징. 그리고 이걸 이용한 ‘expression’이라는, 조건문의 리턴값을 변수에 저장할 수 있는 신기한 표현이 등장. + when은 if와 다르게 object없이도 사용이 가능하다.
(3) 함수, class 주의할 점 : 함수에서 파라미터랑 리턴값은 무조건 타입 지정 필수. 특히 리턴값 타입 지정 안하면 무조건 리턴 없는 걸로 얘가 받아들임. 안된다. class의 파라미터들도 똑같.
(4) null handling : error handling이랑 살짝 비슷한 것 같기도. 변수 선언할 때 자료형에 ‘?’붙이면 null type이 가능해진다. 디폴트는 불가능. ‘?.’ operator를 쓰면 만약 원래 변수가 null이면 에러가 아니라 null을 반환해준다. Elvis operator ‘?:’ 을 쓰면 변수가 null이면 반환해주는 값을 지정해줄 수 있음.

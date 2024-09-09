# 과제 제출

`seminar-2024-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷

깃허브 에디터에 스크린샷 이미지를 드래그 드롭 하면 이미지를 첨부할 수 있습니다. 여기에 넣어 주세요!
![과제0스크린샷](https://github.com/user-attachments/assets/86f1bb09-1f46-4e6a-8112-3be2b66d622a)


## 2. 코테 코드 핵심 로직 Kotlin으로 쓰기

아래 코드 블럭에 자신의 코드를 작성해 주세요.

### 전

```python
n = int(input().strip())  

menus = []
for I in range(n):
    menu_data = input().strip().split()
    X = int(menu_data[0])
    S = float(menu_data[1])
    R = int(menu_data[2])
    T = int(menu_data[3])
    D = menu_data[4]
    menus.append((X, S, R, T, D))

q = int(input().strip()) 

results = []

for _ in range(q):
    m = int(input().strip())  
    valid_menus = menus
    
    for _ in range(m):
        condition = input().strip().split()
        
        if condition[0] == 'S':  
            operator = condition[1]
            value = float(condition[2])
            
            if operator == '>=':
                valid_menus = [menu for menu in valid_menus if menu[1] >= value]
            elif operator == '<=':
                valid_menus = [menu for menu in valid_menus if menu[1] <= value]
            elif operator == '>':
                valid_menus = [menu for menu in valid_menus if menu[1] > value]
            elif operator == '<':
                valid_menus = [menu for menu in valid_menus if menu[1] < value]
        
        elif condition[0] == 'R':  
            restaurant_id = int(condition[2])
            valid_menus = [menu for menu in valid_menus if menu[2] == restaurant_id]
        
        elif condition[0] == 'T':  
            meal_time = int(condition[2])
            valid_menus = [menu for menu in valid_menus if menu[3] == meal_time]
        
        elif condition[0] == 'D': 
            day_of_week = condition[2]
            valid_menus = [menu for menu in valid_menus if menu[4] == day_of_week]
    
    # 메뉴 ID 추출 및 정렬
    valid_ids = sorted([menu[0] for menu in valid_menus])
    
    # 결과 저장
    if valid_ids:
        results.append(" ".join(map(str, valid_ids)))
    else:
        results.append("-1")

# 결과 출력
for result in results:
    print(result)

```

### 후

```kotlin
fun main() {
val n = readLine()!!.toInt()

    val menus = mutableListOf<Triple<Int, MenuData, String>>()

    for (i in 0 until n) {
        val menuData = readLine()!!.split(" ")
        val X = menuData[0].toInt()
        val S = menuData[1].toFloat()
        val R = menuData[2].toInt()
        val T = menuData[3].toInt()
        val D = menuData[4]
        menus.add(Triple(X, MenuData(S, R, T), D))
    }

    val q = readLine()!!.toInt()
    val results = mutableListOf<String>()

    for (i in 0 until q) {
        val m = readLine()!!.toInt()
        var validMenus = menus

        for (j in 0 until m) {
            val condition = readLine()!!.split(" ")

            when (condition[0]) {
                "S" -> {
                    val operator = condition[1]
                    val value = condition[2].toFloat()
                    validMenus = when (operator) {
                        ">=" -> validMenus.filter { it.second.S >= value }.toMutableList()
                        "<=" -> validMenus.filter { it.second.S <= value }.toMutableList()
                        ">" -> validMenus.filter { it.second.S > value }.toMutableList()
                        "<" -> validMenus.filter { it.second.S < value }.toMutableList()
                        else -> validMenus
                    }
                }
                "R" -> {
                    val restaurantId = condition[2].toInt()
                    validMenus = validMenus.filter { it.second.R == restaurantId }.toMutableList()
                }
                "T" -> {
                    val mealTime = condition[2].toInt()
                    validMenus = validMenus.filter { it.second.T == mealTime }.toMutableList()
                }
                "D" -> {
                    val dayOfWeek = condition[2]
                    validMenus = validMenus.filter { it.third == dayOfWeek }.toMutableList()
                }
            }
        }

        val validIds = validMenus.map { it.first }.sorted()
        if (validIds.isNotEmpty()) {
            results.add(validIds.joinToString(" "))
        } else {
            results.add("-1")
        }
    }

    results.forEach { println(it) }
}

data class MenuData(val S: Float, val R: Int, val T: Int)

}
```

## 3. 코틀린 기초 문법 공부
우선 when 용법을 처음 배워서 이것을 사용하는 것에 적응을 해야할 듯 하다. 
그리고 higher-order function을 보는데 함수를 하나의 객체로 봐서 인자로 받는 것도 신기하다.
count 함수는 조건을 달아서 셀수 있도록 만든 것이 간편해보였다
let은 it을 사용하고 run은 this를 사용하여 객체를 나타내는데 아직은 잘 이해가 가지 않아 공부를 더해야겠다.

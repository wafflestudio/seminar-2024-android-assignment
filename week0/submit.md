# 과제 제출

`seminar-2024-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷

깃허브 에디터에 스크린샷 이미지를 드래그 드롭 하면 이미지를 첨부할 수 있습니다. 여기에 넣어 주세요!
<img width="1440" alt="hello_world" src="https://github.com/user-attachments/assets/1c138828-4296-4e31-b5b9-b3e0717d4be2">



## 2. 코테 코드 핵심 로직 Kotlin으로 쓰기

아래 코드 블럭에 자신의 코드를 작성해 주세요.

### 전

```cpp
#include <iostream>
#include <string>
#include <cctype> 


int main() {
    std::string input;
    std::getline(std::cin, input); 

    int count[26] = {0}; 

    // 입력 문자열의 모든 문자를 대문자로 변환하고 빈도수 계산
    for (char& ch : input) {
        ch = std::toupper(static_cast<unsigned char>(ch)); // 대문자로 변환
        if (std::isalpha(ch)) { // 알파벳인 경우만 처리
            count[ch - 'A']++; // 빈도수 증가
        }
    }

    // 가장 빈도가 높은 알파벳 찾기
    int maxCount = 0;
    char maxChar = '?'; // 결과 초기값
    bool duplicate = false;

    for (int i = 0; i < 26; ++i) {
        if (count[i] > maxCount) {
            maxCount = count[i];
            maxChar = 'A' + i;
            duplicate = false; // 새로운 최대 빈도 발견 시 중복 없음
        } else if (count[i] == maxCount) {
            duplicate = true; // 동일 최대 빈도 발견 시 중복 존재
        }
    }

    // 결과 출력
    if (duplicate) {
        std::cout << '?' << std::endl; // 중복일 경우 '?'
    } else {
        std::cout << maxChar << std::endl; // 가장 많이 등장한 알파벳 출력
    }

    return 0;
}


```

### 후

```kotlin
fun main() {
    val input = readLine()!!.toUpperCase() // 입력을 대문자로 변환
    val count = IntArray(26) // 알파벳 개수만큼 배열 생성

    // 각 알파벳의 빈도수 계산
    for (char in input) {
        if (char.isLetter()) {
            count[char - 'A']++
        }
    }

    // 가장 많이 등장한 알파벳 찾기
    val maxCount = count.maxOrNull() ?: 0
    val maxCountIndex = count.indexOf(maxCount)
    val mostFrequent = count.count { it == maxCount }

    if (mostFrequent > 1) {
        println('?') // 여러 개일 경우 '?'
    } else {
        println('A' + maxCountIndex) // 가장 많이 등장한 알파벳 출력
    }
}

```

## 3. 코틀린 기초 문법 공부

여기를 채워 주세요.
1. 엄청 다양한 함수를 내장하고 있어서 잘 검색만 하면 코드를 간결하게 짤 수 있을 것 같다. 다만 아직은 구글링 없이 코드를 짜는 게 어렵다.
2. 간결하게 코드를 짤 수 있는 문법들이 많은 것 같다. when, 단일표현식 함수 등등이 생소한 개념이었는데 익숙해지면 좋을 것 같다.
3. C++에서는 수동으로 메모리를 관리하는 것이 꽤 귀찮은 부분인데, Kotlin은 null safety를 제공해 주고, null pointer 예외를 방지해줘서 편리할 것 같다.

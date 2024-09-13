# 과제 제출

`seminar-2024-andoid-assignment` 레포를 clone한 후, 이 파일의 아래 부분을 수정해서 PR로 올려 주세요.

## 1. 설치 스크린샷
![캡처](https://github.com/user-attachments/assets/7682ef72-8606-400c-b8bf-f99ac589846c)


## 2. 코테 코드 핵심 로직 Kotlin으로 쓰기

아래 코드 블럭에 자신의 코드를 작성해 주세요.

### 전

```
#include<stdio.h>
int N,M,K;
int ni[3],mi[3],ki[3];
int dt[110][110][110];

int f(int n,int m,int k){
	if(dt[n][m][k]) return dt[n][m][k];
	
	int mx=0;
	for(int j=0;j<3;j++){
		if(n-ni[j]>=0 && m-mi[j]>=0 && k-ki[j]>=0){
			int x=f(n-ni[j],m-mi[j],k-ki[j])+1;
			if(x>mx)mx=x;
		}
	}
	return dt[n][m][k]=mx;
}

int main(){
	scanf("%d %d %d",&N,&M,&K);
	
	for(int i=0;i<3;i++){
		scanf("%d %d %d",&ni[i],&mi[i],&ki[i]);
	}
	printf("%d",f(N,M,K));
}
```

### 후

```kotlin
var N = 0
var M = 0
var K = 0
val ni = MutableList(3) { 0 }
val mi = MutableList(3) { 0 }
val ki = MutableList(3) { 0 }
val dt = MutableList(110) { MutableList(110) { MutableList(110) { 0 } } }

fun f(n: Int, m: Int, k: Int): Int {
    if (dt[n][m][k] != 0) return dt[n][m][k]
    
    var mx = 0
    for (j in 0..2) {
        if (n - ni[j] >= 0 && m - mi[j] >= 0 && k - ki[j] >= 0) {
            val x = f(n - ni[j], m - mi[j], k - ki[j]) + 1
            if (x > mx) mx = x
        }
    }
    return dt[n][m][k].also { dt[n][m][k] = mx }
}
```

## 3. 코틀린 기초 문법 공부
1. 변수 설정 방법
수정 가능한 변수와 수정 불가능한 변수를 구분하여 각각 val 과 var 로 다르게 정의하는 것이 새로웠다. 파이썬처럼 자료형을 지정하지 않고 사용할 수도 있고, C언어처럼 지정할 수도 있다. 자료형을 지정할 때는 val d: Int 와 같은 형식으로 지정한다. 또한 null 값을 저장할 수 있는 변수는 따로 설정을 해줘야 한다는 점도 흥미롭다.

2. 리스트의 가변성
파이썬에서는 리스트는 mutable한 자료형, 튜플은 immutable한 자료형 등으로 구분되어 있던 것과 달리 코틀린은 listOf 와 mutaleListOf를 이용해 수정 불가능하거나 수정 가능한 리스트를 모두 만들 수 있다. 또한 불변 리스트를 가변 리스트를 통해 정의하기도 하며 casting 이라고 부른다.

3. Lambda 함수의 형식
lambda 함수 자체는 파이썬에서도 본 적이 있지만, 코틀린에서의 형식과 많이 다르다. 파라미터 -> 함수 내용 으로 작성되며, 이때 lambda 함수이름은 fun 을 쓰지 않고 변수 정의처럼 쓰인다. 또한 단순히 길이를 줄이기 위해 많이 사용했던 파이썬의 lambda와 다르게 함수의 기능을 설계하면서 해야 할 일을 여러개의 lambda로 분리하는 등 더 적극적으로 활용된다는 느낌을 받았다.

4. Data class
클래스 중에서도 데이터를 저장하는 데에 특히 유용한 data class가 존재한다. 또한 자체 내장 함수들이 있어 문자열로 출력, 객체 비교, 복사를 통한 생성 등을 손쉽게 할 수 있다. 아직 어떤면에서 data class 가 유용하기 쓰이는지는 잘 와닿지 않았다.


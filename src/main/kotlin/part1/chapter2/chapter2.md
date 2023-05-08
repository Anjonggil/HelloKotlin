# 2. 코틀린 기초

---

## 2.1 기본요소: 함수와 변수
### 2.1.1 Hello, World!
```kotlin
fun main(args: Array<String>){
    println("Hello World")
}
```

- 함수 선언 시 fun을 사용
- 파라미터 이름 뒤에 그 파라미터의 타입을 쓴다.
- 함수를 최상위 수준에서 정의가 가능.
- 배열도 일반적인 클래스와 마찬가지. 배열처리 문법이 따로 존재 하지 않음
- 코틀린 표준 라이브러리는 자바의 라이브러리를 간결하게 사용할 수 있게 가짠 래퍼를 제공한다. Ex) println()
- 세미 콜론이 붙지 않음.

### 2.1.2 함수
```kotlin
fun main(args: Array<String>){
    println(max(1,2))
}

fun max(a:Int,b:Int) :Int{
    return if (a> b) a else b
}
```

- max 라는 이름의 함수
- 파라미터 목록 a,b를 가짐
- 반환 타입 = Int
- 코틀린에서 if는 문장이 아니라 식으로 사용이 된다. (자바의 3항 연산자와 비슷함.)
```kotlin
fun max(a:Int,b:Int) :Int = if (a> b) a else b
```
- 중괄호와 return을 제거한 뒤 = 를 추가하면 더 간결하게 함수를 표현 가능함.
- **식이 본문인 함수**라 함

```kotlin
fun max(a:Int,b:Int) = if (a> b) a else b
```
- 컴파일러가 함수 본문 식을 분석 결과 타입ㅇ르 함수 반환 타입으로 정해준다.
- **타입 추론**이라함

### 2.1.3 변수
```kotlin
val question = "삶, 우주, 그리고 모든 것에 대한 궁극적인 질문"

val answer = 42
val answer: Int = 42 //둘 다 가능
val yearsToComputer = 7.5e6 // 더블 타입이 됨.

val answer: Int // 초기화식을 사용하지 않는 다면 반드시 타입명시를 해야함
```

- immutable(val) : 참조를 저장하는 변수. val로 선온된 변수는 일단 초기화하고 나면 재대입이 불가능 하다. 자바로 말하면 final 변수
- mutable(var) : 변수 값 변경 가능. 자바의 일반 변수에 해당.

```kotlin
val message : String
if (canPerformOperation()){
    message = "Success"
} else {
    message = "Fail"
} // 가능

val languages = arrayListOf("Java") // 불변 참조를 선언
languages.add("Kotlin") // 참조가 가르키는 객체 내부를 변경한다. -> 가능
```
### 2.1.4 더 쉽게 문자열 형식 지정: 문자열 템플릿

```kotlin
fun main(args: Array<String>) {
    val name = if (args.isNotEmpty()) args[0] else "Kotlin"
    println("Hello, $name!")
}
```
- 변수를 문자열 안에 사용할 수 있다.
- 문자열 리터럴이 필요한 곳에 변수를 넣되 $를 추가해야 한다.
- $ 문자를 넣고 싶을 경우 println("\$x")

```kotlin
fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        println("Hello, ${args[0]}!")
    }
}
```
## 2.2 클래스와 프로퍼티

```java
public class Person {
    private final String name;
    
    public Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

```kotlin
class Person(val name: String)
```
```kotlin
class Person(
    val name: String, //읽기 전용 프로퍼티로, 코틀린은 (비공개)필드와 필드를 읽는 단순한 (공개) 게터를 만들어낸다.
    var isMarried: Boolean //쓰기 가능한 프로퍼티로, 코틀린은 (비공개) 필드, (공개) 게터,(공개) 세터를 만들어 낸다.
)
```
```kotlin
val person = Person("Bob", true)
println(person.name)
println(person.isMarried)
```
### 2.2.2 커스텀 접근자

```kotlin
class Rectangle(val height: Int, val width: Int){
    val isSquare: Boolean
        get(){
            return height == width
        }
}

val rectangle  = Rectangle(41,43)
println(rectangle.isSquare)
```

- isSquare 프로퍼티에는 자체 값을 저장하는 필드 필요 없음.
- 자체 구현을 제공하는 게터만 존재.
- 클라이언트가 프로퍼티에 접근할때마다 다시 계산.
- 자바에선 isSquare 따로 구현해야함.

### 2.2.3 코틀린 소스코드 구조 : 디렉터리와 패키지

```kotlin
package part1.chapter2 //패키지 선언

import part1.chapter2.shapes.createRandomRectangle //이름으로 함수 import 하기


fun main(args: Array<String>){
    println(createRandomRectangle());
}
```


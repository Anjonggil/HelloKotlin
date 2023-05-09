[2 코틀린 기초](#2-코틀린-기초)\
[2.1 기본요소: 함수와 변수](#21-기본요소-함수와-변수)\
[2.2 클래스와 프로퍼티](#22-클래스와-프로퍼티)\
[2.3 선택과 표현 처리 : enum,when](#23-선택과-표현-처리--enumwhen)\
[2.4 대상을 이터레이션](#24-대상을-이터레이션-while-for)\
[2.5 코틀린의 예외처리](#25-코틀린의-예외-처리)
# 2. 코틀린 기초

---

## 2.1 기본요소: 함수와 변수

### 2.1.1 Hello, World!

```kotlin
fun main(args: Array<String>) {
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
fun main(args: Array<String>) {
    println(max(1, 2))
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}
```

- max 라는 이름의 함수
- 파라미터 목록 a,b를 가짐
- 반환 타입 = Int
- 코틀린에서 if는 문장이 아니라 식으로 사용이 된다. (자바의 3항 연산자와 비슷함.)

```kotlin
fun max(a: Int, b: Int): Int = if (a > b) a else b
```

- 중괄호와 return을 제거한 뒤 = 를 추가하면 더 간결하게 함수를 표현 가능함.
- **식이 본문인 함수**라 함

```kotlin
fun max(a: Int, b: Int) = if (a > b) a else b
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
val message: String
if (canPerformOperation()) {
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

    public Person(String name) {
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
class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }
}

val rectangle = Rectangle(41, 43)
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


fun main(args: Array<String>) {
    println(createRandomRectangle());
}
```

## 2.3 선택과 표현 처리 : enum,when

### 2.3.1 enum class 정의

```kotlin
enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}
```

- 자바와 달리 class를 붙여줘야함.
- enum 클래스 안에 프로퍼티나 메소드 정의 가능.

```kotlin
enum class Color(
        val r: Int, val g: Int, val b: Int
) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);

    fun rgb() = (r * 256 + g) * 256 + b
}
```

- 각 enum 상수를 정의 할 때는 그 상수에 해당하는 프로퍼티 값을 지정해야한다.
- enum 클래스안에 메소드를 정의하는 경우 반드시 enum상수와 메서드 정의 사이에 세미콜런을 추가해야만 한다.

### 2.3.2 when으로 enum 클래스 다루기

```kotlin
fun getMnemonic(color: Color) =
        when (color) {
            Color.RED -> "Richard"
            Color.ORANGE -> "Of"
            Color.YELLOW -> "York"
            Color.GREEN -> "Gave"
            Color.BLUE -> "Battle"
            Color.INDIGO -> "In"
            Color.VIOLET -> "Vain"
        }
```

- color로 전달된 값과 같은 분기를 찾는다.
- break를 넣지 않아도 된다.
- 한 분기 안에 여러 값을 매치 패턴으로 사용할 수 있고 콤마(,)로 구분한다.

```kotlin
fun getMnemonic(color: Color) =
        when (color) {
            Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
            Color.GREEN -> "neutral"
            Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
        }
```

```kotlin
fun getWarmth(color: Color) = when (color) {
    RED, ORANGE, YELLOW -> "warm"
    GREEN -> "neutral"
    BLUE, INDIGO, VIOLET -> "cold"
}
```

### 2.3.3 when과 임의의 객체를 함께 사용

```kotlin
fun mix(c1: Color, c2: Color) =
        when (setOf(c1, c2)) {
            setOf(RED, YELLOW) -> ORANGE
            setOf(YELLOW, BLUE) -> GREEN
            setOf(BLUE, VIOLET) -> INDIGO
            else -> throw Exception("Dirty Color")
        }
```

- 인자로 전달 받은 여러객체들을 포함하는 집합인 set
- set을 만드는 setOf 함수가 있다.
- 인자 값과 매치하는 조건 값을 찾을 때까지 각 분기를 검색한다.

### 2.3.4 인자 없는 when 사용

```kotlin
fun mixOptimized(c1: Color, c2: Color) =
        when {
            (c1 == RED && c2 == YELLOW) ||
                    (c2 == RED && c1 == YELLOW) ->
                ORANGE
            (c1 == BLUE && c2 == YELLOW) ||
                    (c2 == YELLOW && c1 == BLUE) ->
                GREEN
            (c1 == BLUE && c2 == VIOLET) ||
                    (c2 == VIOLET && c1 == BLUE) ->
                INDIGO
            else -> throw Exception("Dirty Color")
        }
```

### 2.3.5 스마트 캐스트: 타입 검사와 타입 캐스트를 조합
```kotlin
interface Expr
class Num(val value: Int) : Expr
class Sum(val left:Expr, val right:Expr) :Expr 
```
- 클래스가 구현하는 인터페이스를 지정하기 위해서 콜론(:) 뒤에 인터페이스 이름을 사용한다.
- Expr 사이에는 두 가지 구현 클래스가 존재
  - 어떤 식이 수라면 그 값을 반환
  - 언떤 식이 합계라면 좌항과 우항의 값을 계산한 다음에 그 두 값을 반환한다.
```kotlin
interface Expr
class Num(val value: Int) : Expr
class Sum(val left:Expr, val right:Expr) :Expr

fun eval(e: Expr) :Int{
    if(e is Num){
        val n = e as Num
        return n.value
    }
    if (e is Sum){
        return eval(e.right) + eval(e.left)
    }
}
```

- is를 사용해 변수 타입을 검사한다. 
- java의 instanceof와 비슷화지만 코틀에서는 컴파일러가 자동으로 캐스팅한다.
- 이를 **스마트 캐스트**라 한다.


### 2.3.6 리팩토링 : if to when

```kotlin
interface Expr
class Num(val value: Int) : Expr
class Sum(val left:Expr, val right:Expr) :Expr

fun eval(e: Expr): Int =
  when (e) {
    is Num -> e.value
    is Sum ->
      eval(e.right) + eval(e.left)
    else ->
      throw IllegalArgumentException("Unknown expression")
  }
```

### 2.3.7 if와 when의 분기에서 블럭 사용
```kotlin
interface Expr
class Num(val value: Int) : Expr
class Sum(val left:Expr, val right:Expr) :Expr

fun evalWithLogging(e: Expr) :Int{
  when (e) {
    is Num -> {
        println("num: $e.value")
        e.value
    }
    is Sum ->{
      val right = evalWithLogging(e.right)
      val left = evalWithLogging(e.left)
      println("sum: $left + $right")
      left + right
    }
    else ->
      throw IllegalArgumentException("Unknown expression")
  }
}
```

## 2.4 대상을 이터레이션: while, for
### 2.4.1 while 루프
```kotlin
while(조건){
    //조건이 참인 동안 본문을 반복 실행 한다.
}

do{
    //맨 처음에 무조건 본문을 한 번 실행한 다음, 조건이 참인 동안 본문을 반복 실행한다.
}while (조건)
```

### 2.4.2 수에 대한 이터레이션 : 범위와 수열
```kotlin
fun fizzBuzz(i:Int) = when{
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i "
}

fun main(){
  for (i in 1..100){
    println(fizzBuzz(i))
  }
}
```
- 초깃값, 증가 값, 최종 값을 사용한 루프를 대신하기 위해 범위(**Range**)를 사용한다.

```kotlin
for (i in 100 downTo 1 step 2){
    println(fizzBuzz(i))
}
```
- .. 항상 끝값을 표시
- 끝 값을 포함하지 않는 반만 닫힌 범위에 대해 이터레이션할 때 until을 사용
- ex) x in 0 until size

### 2.4.3 맵에 대한 이터레이션
```kotlin

import java.util.TreeMap

val binaryReps = TreeMap<Char, String>() // 키에 대한 정렬하기 위해 TreeMap을 사용한다.
for (c in 'A'..'F') { // A 부터 F까지 문자의 범위를 사용해 이터레이션 한다.
    val binary = Integer.toBinaryString(c.toInt()) // 아스키 코드 2진 표현으로 바꾼다.
    binaryReps[c] = binary // c를 키로 c의 2진 표현을 맵에 넣는다.
}

for ((letter,binary) in binaryReps){ // 맵에 대해 이터레이션한다. 맵의 키와 값을 두 변수에 각각 대입한다.
    println("$letter = $binary")
}
```

```kotlin
val list = arrayListOf("10","11","1001")
for((index, element) in list.withIndex()){//인덱스와 함께 컬렉션을 이터레이션한다.
    println("$index: $element")
}
```

### 2.4.4 in 으로 컬렉션이나 범위의 원소 검사
```kotlin
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'
```
- 코드는 깔끔해 보일 수 있음
- 다만 범위 클래스 안에 감춰져 있음
```kotlin
// c in 'a'..'z'
fun recognize(c: Char) = when(c){
    in '0' .. '9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know"
}
```

- 범위는 문자에만 국한 되지 않음
- 비교 가능한 클래스라면 인스턴스 객체를 사용해 범위를 만들 수 있다.
- Comparable을 사용하는 범위의 경우 그 범위 내의 모든 객체를 항상 이터레이하지는 못한다.
- ex) println("Kotlin" in "Java".."Scala") // true
- ex) println("Kotlin" in setOf("Java","Scala")) //false <- 이 집합에는 Kotlin이 포함되지 않았다.

## 2.5 코틀린의 예외 처리
```kotlin
if (percentage !in 0..100) {
  throw IllegalArgumentException()
}
```
- 다른 클래스와 마찬가지로 new 키워드 생략가능
- throw는 식임으로 다른 식에 표현이 가능

```kotlin
val percentage = 
    if (number in 0..100)
        number
    else 
        throw IllegalArgumentException()
```

2.5.1 try,catch,finally
```kotlin

import java.io.BufferedReader

fun readNumber(reader: BufferedReader): Int?{
    try {
        val line = reader.readLine()
      return Integer.parseInt(line)
    }
    catch (e: NumberFormatException){
        return null
    } finally {
        reader.close()
    }
}
```
- throws 절이 코드에 없다.
- 체크 예외이기 때문이다.
- checked Exception의 경우 명시적으로 처리가 피룡한데 catch를 통해 처리하면됨

### 2.5.2 try를 식으로 사용

```kotlin

import java.io.BufferedReader

fun readNumber(read: BufferedReader) {
    val number = try {
        Integer.parseInt(read.readLine())
    } catch (e: NumberFormatException) {
        return
    } finally {
        reader.close()
    }
}
```
- if, when과 마찬가지로 변수에 대입하여 사용할 수 있다.
- if 달리 중괄호로 둘러싸야 한다.
- 식의 마지막이 리턴값

```kotlin

import java.io.BufferedReader

fun readNumber(reader: BufferedReader) {
  val number = try {
    Integer.parseInt(read.readLine())
  } catch (e: NumberFormatException) {
    null // 예외가 발생하면 null 값을 사용한다.
  } finally {
    reader.close()
  }
  return number
}
```
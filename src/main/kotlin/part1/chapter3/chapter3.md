<!-- TOC -->
* [함수 정의와 호출](#함수-정의와-호출)
    * [3.1 코틀린 컬렉션 만들기](#31-코틀린-컬렉션-만들기)
    * [3.2 함수를 호출하기 쉽게 만들기](#32-함수를-호출하기-쉽게-만들기)
        * [3.2.1 이름 붙인 인자](#321-이름-붙인-인자)
        * [3.2.2 디폴트 파라미터 값](#322-디폴트-파라미터-값)
        * [3.2.3 정적인 유틸리티 클래스 없애기: 최상위 함수와 프로퍼티](#323-정적인-유틸리티-클래스-없애기-최상위-함수와-프로퍼티)
    * [3.3 메소드를 다른 클래스에 추가: 확장 함수와 확장 프로퍼티](#33-메소드를-다른-클래스에-추가-확장-함수와-확장-프로퍼티)
        * [3.3.1 임포트와 확장 함수](#331-임포트와-확장-함수)
        * [3.3.2 확장 함수로 유틸리티 함수 정의](#332-확장-함수로-유틸리티-함수-정의)
        * [3.3.4 확장 함수는 오버라이드 할 수 없다](#334-확장-함수는-오버라이드-할-수-없다)
        * [3.3.5 확장 프로퍼티](#335-확장-프로퍼티)
    * [3.4 코드 다듬기: 로컬 함수와 확장](#34-코드-다듬기-로컬-함수와-확장)
<!-- TOC -->

# 함수 정의와 호출

## 3.1 코틀린 컬렉션 만들기
- 코들린은 자체 적인 컬렉션을 사용하지 않는다.
- 표준 자바 컬렉션을 활용하면 자바코드와 상호작용하기 쉽기 때문에
- 하지만 자바보다 더 다양한 기술을 사용 가능
- Ex) 리스트의 마지막 원소를 가져오거나 수로 이뤄진 컬렉션에서 최댓값을 찾을 수 있다.

```kotlin
val strings = listOf("first", "second", "fourteenth")
println(strings.last())
// fourteenth
val numbers = setOf(1, 14, 2)
println(numbers.max())
// 14
```

## 3.2 함수를 호출하기 쉽게 만들기
```kotlin
val list = listOf(1,2,3)
println(list) // toString() 호출
```

```kotlin
 import java.lang.StringBuilder
 fun <T> joinToString(collection: Collection<T>,
                     separator: String,
                     prefix: String,
                     postfix: String) : String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()){
        if (index > 0) result.append(separator)
        result.append(element)
    }
     result.append(postfix)
     return result.toString()
}
```
- 어떤 타입의 값을 원소로 하는 컬렉션이든 처리할 수 있다.

### 3.2.1 이름 붙인 인자
```kotlin
joinToString(collection, "", "", ".")
joinToString(collection, separator = " ", prefix = "", postfix =".")
```
- 함수를 호출할 때 인자의 일부 또는 전부의 이름을 명시할 수 있다.
- 호출 시 인자 중 어느 하나라도 명시하고 나면 그 뒤에 오는 모든 인자는 이름을 명시해야 한다.

### 3.2.2 디폴트 파라미터 값
```kotlin
 fun <T> joinToString(collection: Collection<T>,
                     separator: String = ", ",
                     prefix: String = "",
                     postfix: String = "") : String
```
- 함수 오버로딩이 많아 지는 것을 방지 하기 위해 디폴드 파라미터를 사용
- 함수를 호출할 때 모든 인자를 쓸 수도 있고, 일부 생략도 가능하다.
- 일반 호출 문법을 사용할 경우 함수를 선언할 때와 같은 순서로 인자를 지정해야 한다.
- 일부 생략하면 뒷부분의 인자들은 생략 된다.

### 3.2.3 정적인 유틸리티 클래스 없애기: 최상위 함수와 프로퍼티
- 함수를 직접 소스파일의 최상위 수준, 모든 클래스 밖에 위치 시키면 된다.
- 그 파일의 맨앞에 정의된 패키지의 멤버 함수 이므로 다른 패키지에서 함수를 사용 하고 싶을 때 사용이 가능하다.

**최상위 프로퍼티**
- 함수와 마찬가지로 프로퍼티도 파일의 최상위 수준에 놓을 수 있다.
- Ex) 어떤 연산의 수행한 횟수를 저장하는 프로퍼티?
- 이런 프로퍼티 값은 정적 필드에 저장이 된다.

## 3.3 메소드를 다른 클래스에 추가: 확장 함수와 확장 프로퍼티
**확장함수**
> 어떤 클래스의 멤버 메소드인 것처럼 호출할 수 있지만 그 클래스의 밖에서 선언된 함수.

```kotlin
fun String.lastChar(): Char = this.get(this.length - 1)
println("Kotlin".lastChar())
```

- 클래스 이름을 수신 객체 타입이라 한다.
- 호출되는 대상이 되는 값을 수신객체라고 한다.
- 함수 호출 구문의 경우 일반 클래스 멤버를 호출하는 구문과 같다.
- 확장 함수가 캡슐화를 깰 순 없다.
- 확장 함수 안에서는 클래스 내부에서만 사용할 수 있는 비공개 멤버나 보호된 멤버를 사용할 수 없다.

### 3.3.1 임포트와 확장 함수
- 확장 함수를 만들었다고 해서 바로 모든 프로젝트에서 사용할 순 없다.
- 확장 함수를 사용하기 위해선 다른 클래스, 함수와 같이 import를 해야만 한다.
- 확장 함수가 충돌하는 경우가 있을 수 있기 때문에 동일한 구문을 사용해 개별 함수를 import 할 수 있다.

```kotlin
import strings.lastChar as last
val c = "Kotlin".last()
```

### 3.3.2 확장 함수로 유틸리티 함수 정의
```kotlin
fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String ="",
    postfix: String = ""
) : String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()){
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}
```
### 3.3.4 확장 함수는 오버라이드 할 수 없다
```kotlin
open class View{
    open fun click() = println("View clicked")
}

class Button:View(){
    override fun click() = println("Button clicked")
}
```
- 확장 함수는 클래스의 일부가 아니다.
- 확장 함수는 클래스 밖에 선언된다.

```kotlin
fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button!")
```

### 3.3.5 확장 프로퍼티
```kotlin
val String.lastChar: Char
    get() = get(length - 1)
```

```kotlin
import java.lang.StringBuilder

var StringBuilder.lastChar: Char
    get() = get(length -1) // 프로퍼티 게터
    set(value: Char){
        this.setCharAt(length -1, value) // 프로퍼티 세터
    }
```
## 3.4 코드 다듬기: 로컬 함수와 확장
```kotlin
class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User){
    if (user.name.isEmpty()){
        throw IllegalArgumentException()
    }
    
    if (user.address.isEmpty()){
        throw IllegalArgumentException()
    }
    
    userRepository.save(user)
}
```
- 클래스가 사용자 필드 검증이 중복되고 있다.

```kotlin
class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User){
    fun validate(user: User,
                 value: String,
                 fieldName: String){
        if (value.isEmpty()){
            throw IllegalArgumentException()
        }
    }
    
    validate(user, user.name, "Name")
    validate(user, user.address, "Address")
    
    userRepository.save(user)
}
```
- 로컬 함수를 호출해서 각필드를 검증한다.
- User 객체를 로컬하수에 하나씩 전달해야한다는 점이 아쉽다.

```kotlin
class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User){
    fun validate(value: String, fieldName: String){
        if (value.isEmpty()){
            throw IllegalArgumentException()
        }
    }

    validate(user.name, "Name")
    validate(user.address, "Address")
}
```

```kotlin
class User(val id: Int, val name: String, val address: String)

fun User.validateBeforeSave(){
    fun validate(value: String, fieldName: String){
        if (value.isEmpty()){
            throw IllegalArgumentException()
        }
    }

    validate(user.name, "Name")
    validate(user.address, "Address")
}

fun saveUser(user: User){
    user.validateBeforeSave()
}
```
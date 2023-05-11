# 4.클래스, 객체, 인터페이스

## 4.1 클래스 계층 정의

### 4.1.1 코틀린 인터페이스

```kotlin
interface Clickable {
    fun click()
}

class Button : Clickable {
    override fun click() {
        println(" I was Clicked")
    }
}
```

- 클래스 이름 뒤에 콜론을 붙이고 인터페이스와 클래스 이름을 적는 것으로 클래스 확장과 인터페이스 구현을 모두 처리한다.
- 자바와 동일하게 인터페이스는 다중 상속 가능, 클래스는 하나만 상속이 가능하다.
- @Override 어노테이션과 비슷한 ovverride 변경자는 상위 클래스나 상위 인터페이스에 있는 프로퍼티나 메소드를 오버라이드 한 표시
- 실수로 상위 클래스의 메소드나 프로퍼티를 오버라이드 하는 것을 방지한다.
- 자바와 마찬가지 디폴트 구현이 가능하다.
- 코틀린의 경우 override 키워드를 생략할 수 없다.

```kotlin
interface Clickable {
    fun click()
    fun showOff() = println("I'm Clickable")
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" elsa "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}

class Button : Clickable, Focusable {
    override fun click() {
        println("I was clicked")
    }

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}
```

- 같은 메소드를 가진 인터페이스를 함께 구현하면 어느쪽도 선택 되지 않는다.
- 두 메소드를 아우르는 하위 클래스에 직접 구현하게 강제 한다.

### 4.1.2 open, final, abstract 변경자: 기본적으로 final

> **취약한 기발 클래스**
> 하위 클래스가 기반 클래스에 대해 가졌던 가정이 기반 클래스를 변경함으로써 깨져버린 경우에 생긴다. 
> 어떤 클래스가 자신을 상속하는 방법에 대해 정확한 규칙을 제공하지 않는다면 그 클래스의 크라이언트는 기반 클래스를 작성한 사람의 의도와 다른 방식으로 메소드를 오버라이드 할 위험이 있다.

- 코틀린은 클래스와 메소드는 기본적으로 final 키워드를 통해 상속을 불가능하게 한다.
- 어떤 클래스의 상속을 허용하기 위해선 클래스 앞에 open 변경을 붙여야 한다.
- 오버라이드 하고 싶은 메소드와 프로퍼티 앞에도 open 키워드를 붙여야 한다.

```kotlin
interface Clickable {
    fun click()
    fun showOff() = println("I'm Clickable")
}

open class RichButton : Clickable {
    fun disable() {}
    open fun animate() {}
    override fun click() {}
}
```

```kotlin
interface Clickable {
    fun click()
    fun showOff() = println("I'm Clickable")
}
//오버라이드 금지
open class RichButton : Clickable{
    final override fun click()
}
```
```kotlin
abstract class Animated{ //이 클래스는 추상클래스다. 이 클래스의 인스턴스를 만ㄷ들 수 없다.
    abstract fun animate() // 이 함수는 추상함수다. 이 함수에는 구현이 없다. 하위 클래스에서는 이 함수를 반드시 오버라이드해야 한다..
    
    open fun stopAnimating(){ 
    //추상 클래스에 속했더라도 비추상 함수는 기본적으로 파이널이지만 원한다면 open으로 오버라이드를 허용할 수 있다.
    }
    
    fun  animateTwice(){
        
    }
}
```
- 인터페이스의 멤버의 경우 항상 열려있으며 final로 변경할 수 없다.
- 안터페이스 멤버에게 본문이 없으면 자동으로 추상 멤버가 되지만, 따로 멤버 선언 앞에 abstract 키워드를 덧붙일 필요가 없다.

### 4.1.3 가시성 변경자: 기본적으로 공개



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
package part1.chapter3

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
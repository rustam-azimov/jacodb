package org.utbot.java.compilation.database.impl

import org.junit.jupiter.api.Nested

@Nested
class Foo {

    var foo: Int = 0
    private var bar: String = ""

    fun smthPublic(foo: Int) = foo

    private fun smthPrivate(): Int = foo
}
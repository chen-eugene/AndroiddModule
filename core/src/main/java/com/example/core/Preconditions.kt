package com.example.core

object Preconditions {

    fun checkArgument(expression: Boolean) {
        if (!expression) {
            throw IllegalArgumentException()
        }
    }

    fun checkArgument(expression: Boolean, errorMessage: Any?) {
        if (!expression) {
            throw IllegalArgumentException(errorMessage.toString())
        }
    }

    fun checkArgument(expression: Boolean, errorMessageTemplate: String?, vararg errorMessageArgs: Any) {
        if (!expression) {
            throw IllegalArgumentException(format(errorMessageTemplate!!, errorMessageArgs))
        }
    }

    fun checkState(expression: Boolean) {
        if (!expression) {
            throw IllegalStateException()
        }
    }

    fun checkState(expression: Boolean, errorMessage: Any?) {
        if (!expression) {
            throw IllegalStateException(errorMessage.toString())
        }
    }

    fun checkState(expression: Boolean, errorMessageTemplate: String?, vararg errorMessageArgs: Any) {
        if (!expression) {
            throw IllegalStateException(format(errorMessageTemplate!!, errorMessageArgs))
        }
    }

    fun <T> checkNotNull(reference: T): T {
        return reference ?: throw NullPointerException()
    }

    fun <T> checkNotNull(reference: T?, errorMessage: Any?): T {
        return reference ?: throw NullPointerException(errorMessage.toString())
    }

    fun <T> checkNotNull(reference: T?, errorMessageTemplate: String?, vararg errorMessageArgs: Any): T {
        return reference
                ?: throw NullPointerException(format(errorMessageTemplate!!, errorMessageArgs))
    }

    fun checkElementIndex(index: Int, size: Int): Int {
        return checkElementIndex(index, size, "index")
    }

    fun checkElementIndex(index: Int, size: Int, desc: String): Int {
        return if (index in 0..(size - 1)) {
            index
        } else {
            throw IndexOutOfBoundsException(badElementIndex(index, size, desc))
        }
    }

    private fun badElementIndex(index: Int, size: Int, desc: String): String {
        return when {
            index < 0 -> format("%s (%s) must not be negative", arrayOf<Any>(desc, Integer.valueOf(index)))
            size < 0 -> throw IllegalArgumentException(StringBuilder(26).append("negative size: ").append(size).toString())
            else -> format("%s (%s) must be less than size (%s)", arrayOf<Any>(desc, Integer.valueOf(index), Integer.valueOf(size)))
        }
    }

    fun checkPositionIndex(index: Int, size: Int): Int {
        return checkPositionIndex(index, size, "index")
    }

    fun checkPositionIndex(index: Int, size: Int, desc: String): Int {
        return if (index in 0..size) {
            index
        } else {
            throw IndexOutOfBoundsException(badPositionIndex(index, size, desc))
        }
    }

    private fun badPositionIndex(index: Int, size: Int, desc: String): String {
        return when {
            index < 0 -> format("%s (%s) must not be negative", arrayOf<Any>(desc, Integer.valueOf(index)))
            size < 0 -> throw IllegalArgumentException(StringBuilder(26).append("negative size: ").append(size).toString())
            else -> format("%s (%s) must not be greater than size (%s)", arrayOf<Any>(desc, Integer.valueOf(index), Integer.valueOf(size)))
        }
    }

    fun checkPositionIndexes(start: Int, end: Int, size: Int) {
        if (start < 0 || end < start || end > size) {
            throw IndexOutOfBoundsException(badPositionIndexes(start, end, size))
        }
    }

    private fun badPositionIndexes(start: Int, end: Int, size: Int): String {
        return if (start in 0..size) {
            if (end in 0..size) {
                format("end index (%s) must not be less than start index (%s)",
                        arrayOf<Any>(Integer.valueOf(end), Integer.valueOf(start)))
            } else {
                badPositionIndex(end, size, "end index")
            }
        } else {
            badPositionIndex(start, size, "start index")
        }
    }

    private fun format(template: String, vararg args: Any?): String {
        val sb = StringBuilder(template.length + 16 * args.size)
        var templateStart = 0
        var i = 0
        var placeholderStart: Int

        args.forEachIndexed { index, _ ->
            i = index + 1
            placeholderStart = template.indexOf("%s", templateStart)
            if (placeholderStart == -1) {
                return@forEachIndexed
            }
            sb.append(template.substring(templateStart, placeholderStart))
            sb.append(args[i])
            templateStart = placeholderStart + 2
        }

        sb.append(template.substring(templateStart))

        if (i < args.size) {
            sb.append(" [")
            sb.append(args[i++])

            while (i < args.size) {
                sb.append(", ")
                sb.append(args[i++])
            }
            sb.append(']')
        }

        return sb.toString()
    }

}
package utils

import java.util.concurrent.ConcurrentLinkedDeque

class EvictingQueue<E>(private val  maxSize: Int) : ConcurrentLinkedDeque<E>() {
    override fun add(element: E): Boolean {
        val result =  super.add(element)
        if(result && size > this.maxSize)
        {
            remove()
        }
        return result;
    }

    override fun addAll(elements: Collection<E>): Boolean {
        val result = super.addAll(elements)
        while ( size > this.maxSize)
        {
            remove()
        }
        return result
    }

    override fun addFirst(e: E) {
        super.addFirst(e)
        if (size > this.maxSize) {
            remove()
        }
    }

    override fun addLast(e: E) {
        super.addLast(e)
        if (size > this.maxSize) {
            remove()
        }
    }
}
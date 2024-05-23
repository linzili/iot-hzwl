package com.hzwl.iot.common.utils

import java.util.concurrent.ConcurrentHashMap

class BidirectionalMap<A, B> {
    private val mapAtoB = ConcurrentHashMap<A, B>()
    private val mapBtoA = ConcurrentHashMap<B, A>()

    @Synchronized
    fun put(keyA: A, keyB: B) {
        mapAtoB[keyA] = keyB
        mapBtoA[keyB] = keyA
    }

    @Synchronized
    fun removeByA(keyA: A) {
        mapAtoB[keyA]?.let { mapBtoA.remove(it) }
        mapAtoB.remove(keyA)
    }

    @Synchronized
    fun removeByB(keyB: B) {
        mapBtoA[keyB]?.let { mapAtoB.remove(it) }
        mapBtoA.remove(keyB)
    }

    fun getByA(keyA: A): B? = mapAtoB[keyA]

    fun getByB(keyB: B): A? = mapBtoA[keyB]

    fun containsA(keyA: A): Boolean = mapAtoB.containsKey(keyA)

    fun containsB(keyB: B): Boolean = mapBtoA.containsKey(keyB)

    @Synchronized
    fun clear() {
        mapAtoB.clear()
        mapBtoA.clear()
    }
}

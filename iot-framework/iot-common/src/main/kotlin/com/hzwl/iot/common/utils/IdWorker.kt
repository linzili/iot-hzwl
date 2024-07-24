package com.hzwl.iot.common.utils

import com.hzwl.iot.common.extensions.log
import java.lang.management.ManagementFactory
import java.net.InetAddress
import java.net.NetworkInterface

/**
 * 分布式自增长ID实现，底层基于Twitter的Snowflake
 * 64位ID (42(时间戳)+5(机房ID)+5(机器ID)+12(序列号-同毫秒内重复累加))
 * @author 林子立
 */
class IdWorker {
    //同毫秒并发控制
    private var sequence = 0L

    //机器ID
    private val workerId: Long

    //机房ID
    private val datacenterId: Long

    constructor() {
        this.datacenterId = getDatacenterId(maxDatacenterId)
        this.workerId = getMaxWorkerId(datacenterId, maxWorkerId)
    }

    /**
     * @param workerId
     * 工作机器ID
     * @param datacenterId
     * 序列号
     */
    constructor(workerId: Long, datacenterId: Long) {
        require(!(workerId > maxWorkerId || workerId < 0)) {
            String.format(
                "worker Id can't be greater than %d or less than 0",
                maxWorkerId
            )
        }
        require(!(datacenterId > maxDatacenterId || datacenterId < 0)) {
            String.format(
                "datacenter Id can't be greater than %d or less than 0",
                maxDatacenterId
            )
        }
        this.workerId = workerId
        this.datacenterId = datacenterId
    }

    /**
     * 获取下一个ID
     *
     * @return
     */
    @Synchronized
    fun nextId(): Long {
        var timestamp = timeGen()
        if (timestamp < lastTimestamp) {
            throw RuntimeException(
                String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp
                )
            )
        }

        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则+1
            sequence = (sequence + 1) and sequenceMask
            if (sequence == 0L) {
                // 当前毫秒内计数满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp)
            }
        } else {
            sequence = 0L
        }
        lastTimestamp = timestamp
        // ID偏移组合生成最终的ID，并返回ID
        val nextId = (((timestamp - twepoch) shl timestampLeftShift.toInt())
                or (datacenterId shl datacenterIdShift.toInt())
                or (workerId shl workerIdShift.toInt()) or sequence)

        return nextId
    }

    private fun tilNextMillis(lastTimestamp: Long): Long {
        var timestamp = this.timeGen()
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen()
        }
        return timestamp
    }

    private fun timeGen(): Long {
        return System.currentTimeMillis()
    }

    companion object {
        // 时间起始标记点，作为基准，一般取系统的最近时间（一旦确定不能变动）
        private const val twepoch = 1288834974657L

        // 机器标识位数
        private const val workerIdBits = 5L

        // 数据中心标识位数
        private const val datacenterIdBits = 5L

        // 机器ID最大值
        private const val maxWorkerId = -1L xor (-1L shl workerIdBits.toInt())

        // 数据中心ID最大值
        private const val maxDatacenterId = -1L xor (-1L shl datacenterIdBits.toInt())

        // 毫秒内自增位
        private const val sequenceBits = 12L

        // 机器ID偏左移12位
        private const val workerIdShift = sequenceBits

        // 数据中心ID左移17位
        private const val datacenterIdShift = sequenceBits + workerIdBits

        // 时间毫秒左移22位
        private const val timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits

        private const val sequenceMask = -1L xor (-1L shl sequenceBits.toInt())

        /* 上次生产id时间戳 */
        private var lastTimestamp = -1L

        /**
         *
         *
         * 获取 maxWorkerId
         *
         */
        protected fun getMaxWorkerId(datacenterId: Long, maxWorkerId: Long): Long {
            val mpid = StringBuffer()
            mpid.append(datacenterId)
            val name = ManagementFactory.getRuntimeMXBean().name
            if (!name.isEmpty()) {
                /*
             * GET jvmPid
             */
                mpid.append(name.split("@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0])
            }
            /*
         * MAC + PID 的 hashcode 获取16个低位
         */
            return (mpid.toString().hashCode() and 0xffff) % (maxWorkerId + 1)
        }

        /**
         *
         *
         * 数据标识id部分
         *
         */
        protected fun getDatacenterId(maxDatacenterId: Long): Long {
            var id = 0L
            try {
                val ip = InetAddress.getLocalHost()
                val network = NetworkInterface.getByInetAddress(ip)
                if (network == null) {
                    id = 1L
                } else {
                    val mac = network.hardwareAddress
                    id = ((0x000000FFL and mac[mac.size - 1].toLong())
                            or (0x0000FF00L and ((mac[mac.size - 2].toLong()) shl 8))) shr 6
                    (id % (maxDatacenterId + 1)).also { id = it }
                }
            } catch (e: Exception) {
               log.error(" getDatacenterId: " + e.message)
            }
            return id
        }
    }
}

package com.hzwl

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan
class ServerApplication

fun main(args: Array<String>) {
   runApplication<ServerApplication>(*args)
}

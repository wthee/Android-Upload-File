package cn.wthee.face

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class FaceApplication: SpringBootServletInitializer(){

}

fun main(args: Array<String>) {
    runApplication<FaceApplication>(*args)
}

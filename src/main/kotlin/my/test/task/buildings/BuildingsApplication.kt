package my.test.task.buildings

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BuildingsApplication

fun main(args: Array<String>) {
	runApplication<BuildingsApplication>(*args)
}

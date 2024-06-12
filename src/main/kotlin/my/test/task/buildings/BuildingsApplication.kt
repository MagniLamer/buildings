package my.test.task.buildings

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class BuildingsApplication

fun main(args: Array<String>) {
	runApplication<BuildingsApplication>(*args)
}

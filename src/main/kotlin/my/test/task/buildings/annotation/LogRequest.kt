package my.test.task.buildings.annotation

/**
 * Used like a marker for adding a logging
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class LogRequest()
package my.test.task.buildings.configuration.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

internal const val USER_ROLE= "USER"
internal const val ADMIN_ROLE= "ADMIN"

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    @Value("\${security.user.name}")
    private val userName: String ,
    @Value("\${security.user.password}")
    private val pass: String,
    @Value("\${security.admin.name}")
    private val adminName: String ,
    @Value("\${security.admin.password}")
    private val adminPass: String
) {
    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers("/login", "/css/**", "/js/**").permitAll()
                    .requestMatchers("/public/api/buildings/**").authenticated()
                    .anyRequest().authenticated()
            }
            .formLogin {
                it
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/public/api/buildings/", true)
                    .failureUrl("/login?error=true")
                    .permitAll()
            }
            .logout {
                it
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true")
                    .permitAll()
            }
        return http.build()
    }

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager? {
        val auth = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        auth.userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder())
        return auth.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(): UserDetailsService? {
        val manager = InMemoryUserDetailsManager()
        manager.createUser(
            User.withUsername(userName)
                .password(passwordEncoder()?.encode(pass))
                .roles(USER_ROLE)
                .build()
        )
        manager.createUser(
            User.withUsername(adminName)
                .password(passwordEncoder()?.encode(adminPass))
                .roles(USER_ROLE, ADMIN_ROLE)
                .build()
        )
        return manager
    }
}
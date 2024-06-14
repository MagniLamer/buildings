package my.test.task.buildings.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

//@Service
class CustomUserDetailsService(
//    private val passwordEncoder: PasswordEncoder? = null,
)  {

//    @Throws(UsernameNotFoundException::class)
//    override fun loadUserByUsername(username: String): UserDetails? {
//        if (this.userName != username) {
//            throw UsernameNotFoundException("User not found")
//        }
//        return User.builder()
//            .username(username)
//            .password(passwordEncoder?.encode(pass))
//            .roles("USER")
//            .build()
//    }

}
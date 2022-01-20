package cinema.config;

import cinema.model.enums.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ROLE_USER = RoleName.USER.name();
    private static final String ROLE_ADMIN = RoleName.ADMIN.name();
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers(HttpMethod.GET, "/users/by-email").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST,
                        "/cinema-halls",
                        "/movies",
                        "/movie-sessions").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE,
                        "/movie-sessions/**").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT,
                        "/movie-sessions/**").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET,
                        "/cinema-halls",
                        "/movies",
                        "/movie-sessions/**").hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers(HttpMethod.GET,
                        "/shopping-carts/by-user",
                        "/orders").hasRole(ROLE_USER)
                .antMatchers(HttpMethod.PUT,
                        "/shopping-carts/movie-sessions").hasRole(ROLE_USER)
                .antMatchers(HttpMethod.POST,
                        "/orders/complete").hasRole(ROLE_USER)
                .and()
                .formLogin().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}

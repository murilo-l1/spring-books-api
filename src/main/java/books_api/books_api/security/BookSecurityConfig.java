package books_api.books_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;


@Configuration
public class BookSecurityConfig {

    // Setting hierarchy for Admin > User
    @Bean
    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String systemHierarchy = "ROLE_ADMIN > ROLE_USER";
        roleHierarchy.setHierarchy(systemHierarchy);
        return roleHierarchy;
    }

    // passing dataSource from .properties file and then creating a query to retrieve users and roles
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager(dataSource);


        detailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"); // when passing the logg form, '?' is replaced by the value being passed


        detailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?");

        return detailsManager;
    }

    // filter chain to restrict endpoints based on business logic
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // GET -> USER / POST || PUT || DELETE -> ADMIN
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "books/").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "books/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "books/author/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "books/category/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "books/status/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "books").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "books/**").hasRole("ADMIN")
                        // swagger enabled for all users
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable()); // csrf disabled as it's a REST api, and a simple one

        return http.build();
    }

}
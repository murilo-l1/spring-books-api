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

    // configurando que a hierarquia do sistema é baseada em ADMIN > User
    @Bean
    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String systemHierarchy = "ROLE_ADMIN > ROLE_USER";
        roleHierarchy.setHierarchy(systemHierarchy);
        return roleHierarchy;
    }

    // criando um bean que configura o userDetailsManager para encontrar os dados de cada membro app (user ou adm) no banco - dataSource
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager(dataSource);

        // pegando os dados de users
        detailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?");

        // assimilando esses users a seus respectivos roles
        detailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?");

        return detailsManager;
    }

    // filtro de segurança que permite apenas o admin para atualizar ou deletar uma informação dentro do banco
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // GET -> USER / PUT || DELETE -> ADMIN
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "books/").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "books/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "books/author/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "books/category/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "books/status/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "books/").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "books/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "books/**").hasRole("ADMIN")
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}
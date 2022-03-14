package com.noroff.mefit.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.HashSet;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Enable CORS -- this is further configured on the controllers
                .cors().and()

                // Sessions will not be used
                .sessionManagement().disable()

                // Disable CSRF -- not necessary when there are sessions
                .csrf().disable()

                // Enable security for http requests
                .authorizeRequests(authorize -> authorize
                        // Specify paths where public access is allowed
                        .antMatchers("/v3/api-docs", "/v3/api-docs/*", "/v3/api-docs/**").permitAll()
                        .antMatchers("/swagger-ui", "/swagger-ui/*", "/swagger-ui/**").permitAll()

                        /*
                         * Add urls here
                         */
                        .antMatchers("/user/**").hasAnyRole("MeFit_User")
                        .antMatchers("/security/admin").hasAnyRole("MeFit_Admin")

                        // All remaining paths require authentication
                        .anyRequest().authenticated())

                // Configure OAuth2 Resource Server (JWT authentication)
                .oauth2ResourceServer(oauth2 -> {
                    // Convert Jwt to AbstractAuthenticationToken
                    JwtAuthenticationConverter authnConverter = new JwtAuthenticationConverter();

                    // Convert Jwt scopes claim to GrantedAuthorities
                    JwtGrantedAuthoritiesConverter scopeConverter = new JwtGrantedAuthoritiesConverter();

                    // Convert Jwt groups claim to GrantedAuthorities
                    JwtGrantedAuthoritiesConverter groupConverter = new JwtGrantedAuthoritiesConverter();
                    groupConverter.setAuthorityPrefix("GROUP_");
                    groupConverter.setAuthoritiesClaimName("groups");

                    // Convert Jwt roles claim to GrantedAuthorities
                    JwtGrantedAuthoritiesConverter roleConverter = new JwtGrantedAuthoritiesConverter();
                    roleConverter.setAuthorityPrefix("ROLE_");
                    roleConverter.setAuthoritiesClaimName("roles");

                    // Jwt -> GrantedAuthorities -> AbstractAuthenticationToken
                    authnConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
                        // This will read the 'scope' claim inside the payload
                        Collection<GrantedAuthority> scopes = scopeConverter.convert(jwt);

                        // This will read the 'roles' claim you configured above
                        // jwt["roles"] -> new GrantedAuthority("ROLE_roleName")
                        Collection<GrantedAuthority> roles = roleConverter.convert(jwt);

                        // This will read the 'groups' claim you configured above
                        // jwt["groups"] -> new GrantedAuthority("GROUP_groupName")
                        Collection<GrantedAuthority> groups = groupConverter.convert(jwt);

                        // Merge the above sets
                        HashSet<GrantedAuthority> union = new HashSet<>();
                        union.addAll(scopes);
                        union.addAll(roles);
                        union.addAll(groups);

//                        for (var a : union) {
//                            logger.warn("JWT Authority: {}", a.getAuthority());
//                        }

                        return union;
                    });

                    // Enable JWT authentication and access control from JWT claims
                    oauth2.jwt().jwtAuthenticationConverter(authnConverter);
                });
    }
}

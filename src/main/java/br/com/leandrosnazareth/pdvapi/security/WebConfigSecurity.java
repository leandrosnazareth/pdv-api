package br.com.leandrosnazareth.pdvapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.leandrosnazareth.pdvapi.service.ImplementacaoUserDetailsSercice;

/*Mapeaia URL, enderecos, autoriza ou bloqueia acessoa a URL*/
@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementacaoUserDetailsSercice implementacaoUserDetailsSercice;

	// Configura as solicitações de acesso por Http
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf()
				// Ativando a proteção contra usuário que não estão validados por TOKEN
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

				// Permitir todos acessarem a pagina pincipal index ou /
				.disable().authorizeRequests()
				.antMatchers("/api/login/").permitAll()
				.antMatchers("/index").permitAll()
				.antMatchers(AUTH_WHITELIST).permitAll()//liberar acesso ao endereço da doc swagger

				// Logout - Redireciona após o user deslogar do sistema
				.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")

				// Maperia URL de Logout e insvalida o usuário
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

				// Filtra requisições de login para autenticação
				.and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)

				// Filtra demais requisições paa verificar a presenção do TOKEN JWT no HEADER
				// HTTP
				.addFilterBefore(new JwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Service que irá consultar o usuário no banco de dados
		auth.userDetailsService(implementacaoUserDetailsSercice)
				// Padrão de codigição de senha
				.passwordEncoder(new BCryptPasswordEncoder());
	}

	private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/v3/api-docs",  
            "/swagger-resources/**", 
            "/swagger-ui/**",
             };

}
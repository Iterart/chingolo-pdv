package net.iterart.chingolo.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
*
* @author ander
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// Inyectar la conexión a la BD para consultar a los usuarios registrados:
	@Autowired
	DataSource dataSource;

	@Bean
	BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/img/**").permitAll().anyRequest().authenticated()
				.and().formLogin().loginPage("/login").permitAll().and().logout().permitAll()
				.and().exceptionHandling().accessDeniedPage("/error_403")
				.and().csrf().disable().cors();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {

		// Auth con JDBC
		build.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select email, password, activo from usuarios where email=?")
				.authoritiesByUsernameQuery("select u.email, r.nombre from roles r inner join usuarios u "
						+ "on u.rol_id = r.id where u.email =?");
	}
}

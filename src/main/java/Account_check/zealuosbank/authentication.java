package Account_check.zealuosbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class authentication 
{
  @Autowired
  accountservice service;

  @Bean
  public PasswordEncoder encoder()
  {
    return new BCryptPasswordEncoder();
  }
    
  @Bean
  public WebSecurityCustomizer customurl()
  {
    return url-> url.ignoring().requestMatchers("/zealousbank/accountcreate");
  }

  @Bean
  public InMemoryUserDetailsManager userdetails()
  {
    UserDetails user1=User.withUsername("tamil").password(encoder().encode("tamil123")).roles("manager").build();
    UserDetails user2=User.withUsername("kesavan").password(encoder().encode("kesavan123")).roles("owner").build();
  
    return new InMemoryUserDetailsManager(user1,user2);
  }

  
@Bean //collection object
@Deprecated(forRemoval = true)
  public SecurityFilterChain httpfitler(HttpSecurity hp) throws Exception
  {
    //hp.authorizeHttpRequests().anyRequest().authenticated(); //anyrequest can access
    //hp.authorizeHttpRequests().anyRequest().authenticated();// it permit all

    hp.authorizeHttpRequests().requestMatchers("/zealousbank/**")
    .authenticated()
    .and()
    .csrf().disable()
    .cors() 
    .and()
    .httpBasic()
    .and()
    .formLogin();

    AuthenticationManagerBuilder builder=hp.getSharedObject(AuthenticationManagerBuilder.class);
    builder.userDetailsService(service).passwordEncoder((encoder()));
    hp.authenticationManager(builder.build());

    return hp.build();
  }
}

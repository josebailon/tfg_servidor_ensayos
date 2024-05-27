/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
//definir como clase de configuracion
@Configuration

//definir como clase de configuracion de seguridad
@EnableWebSecurity

@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserPrincipalService userPrincipalService;
    private final UnauthorizedHandler unauthorizedHandler;
    
    /**
     * Configuracion de la seguridad
     * @param http Peticion http
     * @return Devuelve una cadena de seguridad
     * @throws Exception Si se produce un problema durante la creacion de la cadena
     */
    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception{
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);//agregar filtro de token antes de la autenticacion
        return http.cors(cors -> cors.disable())//deshabilitar cors
            .csrf(csrf -> csrf.disable())//deshabilitar csrf
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .formLogin((fl) -> fl.disable())// desactivar formulario de login
            .exceptionHandling((t) -> t.authenticationEntryPoint(unauthorizedHandler))//handler de no autorizado
            .securityMatcher("/**")//asignar la configuracion a todas las rutas
            .authorizeHttpRequests(
                    (registro) -> registro.requestMatchers("/").permitAll()//permitir la raiz para todos
                    .requestMatchers("/auth/login").permitAll()//permitir para todos la ruta de autorizacion
                    .requestMatchers("/auth/registrar").permitAll()
                    .anyRequest().authenticated()//el resto de rutas solo permitirlas a los autenticados
            ).build();
    }
    
    
    /**
     * Define el codificador  para passwords
     * @return El codificador
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
 
    @Bean
    public AuthenticationManager authenticationmanager(HttpSecurity http) throws Exception{
        //Crear el builder del autentication manager
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        
        //establecer servicio a usar para extraer user principals del sistema
        builder.userDetailsService(userPrincipalService)
                .passwordEncoder(passwordEncoder());
        //crear y devolver
        return builder.build();
                
    }
    
}//end WebSecurityConfig

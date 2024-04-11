/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
Lista de paquetes:
 */

package josebailon.ensayos.servidor.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Jose Javier Bailon Ortiz
 */
//definir como clase de configuracion
@Configuration

//definir como clase de configuracion de servicio web
@EnableWebSecurity
public class WebSecurityConfig {
    
    //configurar el SecurityFilterChain para ser inyectado
    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception{
        http.cors(cors -> cors.disable())//deshabilitar cors
            .csrf(csrf -> csrf.disable())//deshabilitar csrf
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//establecer stateless para evitar sesiones
        http.formLogin((fl) -> fl.disable())// desactivar formulario de login
            .securityMatcher("/**")//asignar la configuracion a todas las rutas
            .authorizeHttpRequests(
                    (registro) -> registro.requestMatchers("/").permitAll()//permitir la raiz para todos
                    .requestMatchers("/auth/login").permitAll()//permitir para todos la ruta de autorizacion
                    .anyRequest().authenticated()//el resto de rutas solo permitirlas a los autenticados
            );
        return http.build();
                 
    }
    
 
    
}//end WebSecurityConfig

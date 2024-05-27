package josebailon.ensayos.servidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * Punto de entrada de la aplicación.
 * 
 *<p>La aplicación es un servicio web cuya seguridad la maneja Spring Security segun las clases especificadas en el paquete security.
 * Spring Security filtra las peticiones web hacia los endpoints especificados en el paquete controller. Este recoge las peticiones 
 * y las ejecuta a través de llamadas a clases del paquete service que finalmente utilizan las clases del paquete repository para efectuar
 * las operaciones. Los repositorios especificados en ese paquete usan Spring Data segun lo definido en las entidades del paquete model.
 * Además una tarea cron realiza limpieza de archivos a intervalos regulares. Esta tarea cron está definida en el paquete cron</p>
 * @author Jose Javier Bailon Ortiz
 */
@SpringBootApplication
@EnableScheduling
public class ServidorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServidorApplication.class, args);
	}

}

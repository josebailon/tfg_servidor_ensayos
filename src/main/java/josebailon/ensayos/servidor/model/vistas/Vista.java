/*
LICENCIA JOSE JAVIER BO
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
Lista de paquetes:
 */
package josebailon.ensayos.servidor.model.vistas;

/**
 * Definicion de ls modos de transformacion de entidades a JSON por parte de 
 * la libreria Jackson
 * @author Jose Javier Bailon Ortiz
 */
public interface Vista {
    /**
     * Vista para los valores esenciales.
     * Ignora claves de usario y entidades relacionadas (exceptuando usuario de un grupo)
     */
    public class Esencial{}
    
    /**
     * Vista para los valores extendidos.
     * Ignora claves de usario pero incluye entidades hijas relacionadas. 
     * Usado para la extraccion del arbol de datos de grupos de un usuario
     */    
    public class Completa extends Esencial{}
}

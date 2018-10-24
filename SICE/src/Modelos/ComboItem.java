/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/*
  @author Grupo #30 Ingeniería 2018-2019 
 *@author David Rodríguez Zamora
 *@author Katherine Jiménez Soto
 *@author Melany Monge Montero
 *@author Stefanny Villalobos Uva
 * Proyecto de Ingeniería - Universidad Nacional de Costa Rica
 * Sistema Interno de Control de Estudiantes, SICE
 * Profesor: Rafael Alvarado Arley
 * Dueño del producto: Yensy Soto, Centro Cultural Corporación Costa Rica
 * Versión 1.2, 21/10/2018
 * Since 1.0
 */

//Clase necesaria para cargar los items en los combobox con el valur que es el ID, este se ocupa para los metodos de la base
public class ComboItem{
        private String key;
        private String value;
        
        public ComboItem(String key, String value){
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString(){return key;}
    
        public String getKey(){ return key;}

        public String getValue(){return value;}
}  
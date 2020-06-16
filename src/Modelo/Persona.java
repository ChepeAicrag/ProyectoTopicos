
package Modelo;

/**
 * 
 * @author Garcia Garcia Jose Angel
 */
public class Persona{
  private String nombre;
  private char sexo;
  private int edad;
  
    public Persona(String nombre, char sexo, int edad){
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setSexo(char sexo){
        this.sexo = sexo;
    }
    
    public void setEdad(int edad){
        this.edad = edad;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public char getSexo(){
        return sexo;
    }
    
    public int getEdad(){
        return edad;
    }
}

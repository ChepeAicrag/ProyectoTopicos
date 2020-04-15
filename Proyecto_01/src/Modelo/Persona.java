
package Modelo;

/**
 * 
 * @author Garcia Garcia Jose Angel
 */
public class Persona{
    
  private String curp;
  private String nombre;
  private String sexo;
  private int edad;
  
    public Persona(String curp, String nombre, String sexo, int edad){
        this.curp = curp;
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setSexo(String sexo){
        this.sexo = sexo;
    }
    
    public void setEdad(int edad){
        this.edad = edad;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getSexo(){
        return sexo;
    }
    
    public int getEdad(){
        return edad;
    }
    
    public String getCurp(){
        return curp;
    }
}

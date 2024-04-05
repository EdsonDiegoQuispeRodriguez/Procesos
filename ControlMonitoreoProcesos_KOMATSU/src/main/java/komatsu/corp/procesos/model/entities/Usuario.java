
package komatsu.corp.procesos.model.entities;

import java.util.ArrayList;


public class Usuario {
//atributos de usuario
  private int id;
  private String username;
  private String contrasenia;
  /*private int id_persona;
  private int id_rol;*/
  //constructor
  public Usuario() {

  }
  //METODOS
  public boolean isPasswordCorrect(String inputPassword) {
      
      if(this.contrasenia.equalsIgnoreCase(inputPassword)){
          return true;
      }
      
      return false;
}

  //getter y setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

  

    

    

}

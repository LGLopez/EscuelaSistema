/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuela;

/**
 *
 * @author roman
 */
public class Maestros {
    int id;
    String nombre;
    String gradoA;
    String grupoA;
    String direccion;
    String telefono;
    String materia;

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int addAndIncrease(){
        id++;
        return id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGradoA() {
        return gradoA;
    }

    public void setGradoA(String gradoA) {
        this.gradoA = gradoA;
    }

    public String getGrupoA() {
        return grupoA;
    }

    public void setGrupoA(String grupoA) {
        this.grupoA = grupoA;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}

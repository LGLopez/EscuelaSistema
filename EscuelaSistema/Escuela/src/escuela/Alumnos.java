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
public class Alumnos {
    int id;
    String nombre;
    String aPaterno;
    String aMaterno;
    String direccion;
    String telefono;
    String email;
    String fNacimientoDia;
    String fNacimientoMes;
    String fNacimientoAnio;
    String fechaDia;
    String fechaMes;
    String fechaAnio;
    String ciudad;
    String carrera;
    String semestre;
    String grupo;
    String periodo;

    public String getFnacimientodia() {
        return fNacimientoDia;
    }

    public void setFnacimientodia(String fnacimientodia) {
        this.fNacimientoDia = fnacimientodia;
    }

    public String getFnacimientomes() {
        return fNacimientoMes;
    }

    public void setFnacimientomes(String fnacimientomes) {
        this.fNacimientoMes = fNacimientoMes;
    }

    public String getFnacimientoanio() {
        return fNacimientoAnio;
    }

    public void setFnacimientoanio(String fnacimientoanio) {
        this.fNacimientoAnio = fNacimientoAnio;
    }

    public String getFechadia() {
        return fechaDia;
    }

    public void setFechadia(String fechaDia) {
        this.fechaDia = fechaDia;
    }

    public String getFechames() {
        return fechaMes;
    }

    public void setFechames(String fechames) {
        this.fechaMes = fechames;
    }

    public String getFechaanio() {
        return fechaAnio;
    }

    public void setFechaanio(String fechaanio) {
        this.fechaAnio = fechaanio;
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

    public String getAp() {
        return aPaterno;
    }

    public void setAp(String ap) {
        this.aPaterno = ap;
    }

    public String getAm() {
        return aMaterno;
    }

    public void setAm(String am) {
        this.aMaterno = am;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
}

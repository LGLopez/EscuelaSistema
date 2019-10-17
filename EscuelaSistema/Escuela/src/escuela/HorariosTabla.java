/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuela;

/**
 *
 * @author destr
 */
public class HorariosTabla {
    private String materia;
    private String maestro;
    private int horarioDia;
    private boolean usado;

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getMaestro() {
        return maestro;
    }

    public void setMaestro(String maestro) {
        this.maestro = maestro;
    }

    public int getHorarioDia() {
        return horarioDia;
    }

    public void setHorarioDia(int horarioDia) {
        this.horarioDia = horarioDia;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }
    
}

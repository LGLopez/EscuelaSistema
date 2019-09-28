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
public class Horarios {
    int nHoras;
    int hInicio;
    int hFinal;
    int idGrupo;

    public int getnHoras() {
        return nHoras;
    }

    public void setnHoras(int nHoras) {
        this.nHoras = nHoras;
    }

    public int gethInicio() {
        return hInicio;
    }

    public void sethInicio(int hInicio) {
        this.hInicio = hInicio;
    }

    public int gethFinal() {
        return hFinal;
    }

    public void sethFinal(int hFinal) {
        this.hFinal = hFinal;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
}

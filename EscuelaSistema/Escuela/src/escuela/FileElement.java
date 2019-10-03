/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuela;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

//
///**
// *
// * @author roman
// */
//public class FileElement extends javax.swing.JFrame {
//    int id =0;
//    Materia materia;
//    ArrayList<Materia> materias = new ArrayList<Materia>();
//    File fileMateria = new File("Materias.txt");
//    
//    Alumnos alumno;
//    ArrayList<Alumnos> alumnos = new ArrayList<Alumnos>();
//    File fileAlumno = new File("Alumnos.txt"); 
//    
//    Carrera carrera;
//    ArrayList<Carrera> carreras = new ArrayList<Carrera>();
//    File fileCarrera = new File("Carreras.txt"); 
//    
//    /**
//     * Creates new form Registrar
//     */
//    public FileElement() throws FileNotFoundException, IOException {
//        DataInputStream archivoRead;
//        
//        archivoRead = new DataInputStream(new FileInputStream(fileMateria));
//        
//        
//        while(archivoRead.available() > 0){
//            int readID = archivoRead.readInt();
//            String readNombreM = archivoRead.readUTF();
//            String readAcademia = archivoRead.readUTF();
//            String readIdCarrera = archivoRead.readUTF();
//            int readCreditos = archivoRead.readInt();
//            String readSeparador = archivoRead.readUTF();
//            
//            Materia temp = new Materia();
//            
//            temp.setID(readID);
//            temp.setNombreM(readNombreM);
//            temp.setAcademia(readAcademia);
//            temp.setIdcarrera(readIdCarrera);
//            temp.setCreditos(readCreditos);
//            
//            materias.add(temp);
//        }
//        
//        /////////////////////////////////////////////////////////////////////////////
//        archivoRead = new DataInputStream(new FileInputStream(fileAlumno));
//        
//        
//        while(archivoRead.available() > 0){
//            int readId = archivoRead.readInt();
//            String readNombre = archivoRead.readUTF();
//            String readAp = archivoRead.readUTF();
//            String readAm = archivoRead.readUTF();
//            String readDireccion = archivoRead.readUTF();
//            String readTelefono = archivoRead.readUTF();
//            String readEmail = archivoRead.readUTF();
//            String readFnacimientodia = archivoRead.readUTF();
//            String readFnacimientomes = archivoRead.readUTF();
//            String readFnacimientoanio = archivoRead.readUTF();
//            String readFechadia = archivoRead.readUTF();
//            String readFechames = archivoRead.readUTF();
//            String readFechaanio = archivoRead.readUTF();
//            String readCiudad = archivoRead.readUTF();
//            String readCarrera = archivoRead.readUTF();
//            String readSemestre = archivoRead.readUTF();
//            String readGrupo = archivoRead.readUTF();
//            String readPeriodo = archivoRead.readUTF();
//            String readSeparador = archivoRead.readUTF();
//            
//            Alumnos temp = new Alumnos();
//            
//            temp.setId(readId);
//            temp.setNombre(readNombre);
//            temp.setAp(readAp);
//            temp.setAm(readAm);
//            temp.setDireccion(readDireccion);
//            temp.setTelefono(readTelefono);
//            temp.setEmail(readEmail);
//            temp.setFnacimientodia(readFnacimientodia);
//            temp.setFnacimientodia(readFnacimientomes);
//            temp.setFnacimientodia(readFnacimientoanio);
//            temp.setFechadia(readFechadia);
//            temp.setFechames(readFechames);
//            temp.setFechaanio(readFechaanio);
//            temp.setCiudad(readCiudad);
//            temp.setCarrera(readCiudad);
//            temp.setSemestre(readCiudad);
//            temp.setGrupo(readCiudad);
//            temp.setPeriodo(readCiudad);
//            
//            alumnos.add(temp);
//        }
//        ///////////////////////////////////////////////////////////////////
//        archivoRead = new DataInputStream(new FileInputStream(fileCarrera));
//        
//        
//        while(archivoRead.available() > 0){
//            String readId = archivoRead.readUTF();
//            String readNombreC = archivoRead.readUTF();
//            String readAreaC = archivoRead.readUTF();
//            String readSemestre = archivoRead.readUTF();
//            String readFechaD = archivoRead.readUTF();
//            String readFechaM = archivoRead.readUTF();
//            String readFechaA = archivoRead.readUTF();
//            String readSeparador = archivoRead.readUTF();
//            
//            Carrera temp = new Carrera();
//            
//            temp.setId(readId);
//            temp.setNombre(readNombreC);
//            temp.setArea(readAreaC);
//            temp.setSemestre(readSemestre);
//            temp.setFechaD(readFechaD);
//            temp.setFechaM(readFechaM);
//            temp.setFechaA(readFechaA);
//            
//            
//            carreras.add(temp);
//        }
//        
//        
//    }
//    
//    
//    public FileElement(Materia materia) throws FileNotFoundException, IOException{
//        DataInputStream archivoRead;
//        
//        archivoRead = new DataInputStream(new FileInputStream(fileMateria));
//        
//        
//        while(archivoRead.available() > 0){
//            int readID = archivoRead.readInt();
//            String readNombreM = archivoRead.readUTF();
//            String readAcademia = archivoRead.readUTF();
//            String readIdCarrera = archivoRead.readUTF();
//            int readCreditos = archivoRead.readInt();
//            String readSeparador = archivoRead.readUTF();
//            
//            Materia temp = new Materia();
//            
//            temp.setID(readID);
//            temp.setNombreM(readNombreM);
//            temp.setAcademia(readAcademia);
//            temp.setIdcarrera(readIdCarrera);
//            temp.setCreditos(readCreditos);
//            
//            materias.add(temp);
//        }
//        
//        materia.setID(id);
//        id+=1;
//        materias.add(materia);
//                
//        fileMateria.delete();
//        
//        DataOutputStream archivoWrite;
//        
//        archivoWrite = new DataOutputStream(new FileOutputStream(fileMateria));
//        
//        for(int i=0; i< materias.size(); i++){
//            archivoWrite.writeInt(materias.get(i).getID());
//            archivoWrite.writeUTF(materias.get(i).getNombreM());
//            archivoWrite.writeUTF(materias.get(i).getAcademia());
//            archivoWrite.writeUTF(materias.get(i).getIdcarrera());
//            archivoWrite.writeInt(materias.get(i).getCreditos());
//            archivoWrite.writeUTF("#");
//            
//        }
//        
//        archivoWrite.close();
//
//    }
//    
//    public FileElement(Alumnos alumno) throws FileNotFoundException, IOException{
//        
//        DataInputStream archivoRead;
//        
//        archivoRead = new DataInputStream(new FileInputStream(fileAlumno));
//        
//        
//       while(archivoRead.available() > 0){
//            int readId = archivoRead.readInt();
//            String readNombre = archivoRead.readUTF();
//            String readAp = archivoRead.readUTF();
//            String readAm = archivoRead.readUTF();
//            String readDireccion = archivoRead.readUTF();
//            String readTelefono = archivoRead.readUTF();
//            String readEmail = archivoRead.readUTF();
//            String readFnacimientodia = archivoRead.readUTF();
//            String readFnacimientomes = archivoRead.readUTF();
//            String readFnacimientoanio = archivoRead.readUTF();
//            String readFechadia = archivoRead.readUTF();
//            String readFechames = archivoRead.readUTF();
//            String readFechaanio = archivoRead.readUTF();
//            String readCiudad = archivoRead.readUTF();
//            String readCarrera = archivoRead.readUTF();
//            String readSemestre = archivoRead.readUTF();
//            String readGrupo = archivoRead.readUTF();
//            String readPeriodo = archivoRead.readUTF();
//            String readSeparador = archivoRead.readUTF();
//            
//            Alumnos temp = new Alumnos();
//            
//            temp.setId(readId);
//            temp.setNombre(readNombre);
//            temp.setAp(readAp);
//            temp.setAm(readAm);
//            temp.setDireccion(readDireccion);
//            temp.setTelefono(readTelefono);
//            temp.setEmail(readEmail);
//            temp.setFnacimientodia(readFnacimientodia);
//            temp.setFnacimientodia(readFnacimientomes);
//            temp.setFnacimientodia(readFnacimientoanio);
//            temp.setFechadia(readFechadia);
//            temp.setFechames(readFechames);
//            temp.setFechaanio(readFechaanio);
//            temp.setCiudad(readCiudad);
//            temp.setCarrera(readCiudad);
//            temp.setSemestre(readCiudad);
//            temp.setGrupo(readCiudad);
//            temp.setPeriodo(readCiudad);
//            
//            alumnos.add(temp);
//        }
//        
//        alumno.setId(id);
//        id+=1;
//        alumnos.add(alumno);
//                
//        fileAlumno.delete();
//        
//        DataOutputStream archivoWrite;
//        
//        archivoWrite = new DataOutputStream(new FileOutputStream(fileAlumno));
//        
//        for(int i=0; i< alumnos.size(); i++){
//            archivoWrite.writeInt(alumnos.get(i).getId());
//            archivoWrite.writeUTF(alumnos.get(i).getNombre());
//            archivoWrite.writeUTF(alumnos.get(i).getAp());
//            archivoWrite.writeUTF(alumnos.get(i).getAm());
//            archivoWrite.writeUTF(alumnos.get(i).getDireccion());
//            archivoWrite.writeUTF(alumnos.get(i).getTelefono());
//            archivoWrite.writeUTF(alumnos.get(i).getEmail());
//            archivoWrite.writeUTF(alumnos.get(i).getFnacimientodia());
//            archivoWrite.writeUTF(alumnos.get(i).getFnacimientomes());
//            archivoWrite.writeUTF(alumnos.get(i).getFnacimientoanio());
//            archivoWrite.writeUTF(alumnos.get(i).getFechadia());
//            archivoWrite.writeUTF(alumnos.get(i).getFechames());
//            archivoWrite.writeUTF(alumnos.get(i).getFechaanio());
//            archivoWrite.writeUTF(alumnos.get(i).getCiudad());
//            archivoWrite.writeUTF(alumnos.get(i).getCarrera());
//            archivoWrite.writeUTF(alumnos.get(i).getSemestre());
//            archivoWrite.writeUTF(alumnos.get(i).getGrupo());
//            archivoWrite.writeUTF(alumnos.get(i).getPeriodo());
//            archivoWrite.writeUTF("#");
//            
//        }
//        
//        archivoWrite.close();
//
//    }
//    
//    public FileElement(Carrera carrera) throws FileNotFoundException, IOException{
//
//        DataInputStream archivoRead;
//        
//        archivoRead = new DataInputStream(new FileInputStream(fileCarrera));
//        
//        
//        while(archivoRead.available() > 0){
//            String readId = archivoRead.readUTF();
//            String readNombreC = archivoRead.readUTF();
//            String readAreaC = archivoRead.readUTF();
//            String readSemestre = archivoRead.readUTF();
//            String readFechaD = archivoRead.readUTF();
//            String readFechaM = archivoRead.readUTF();
//            String readFechaA = archivoRead.readUTF();
//            String readSeparador = archivoRead.readUTF();
//            
//            Carrera temp = new Carrera();
//            
//            temp.setId(readId);
//            temp.setNombre(readNombreC);
//            temp.setArea(readAreaC);
//            temp.setSemestre(readSemestre);
//            temp.setFechaD(readFechaD);
//            temp.setFechaM(readFechaM);
//            temp.setFechaA(readFechaA);
//            
//            
//            carreras.add(temp);
//        }
//        
//        carrera.setCont(id);
//        id+=1;
//        carreras.add(carrera);
//                
//        fileCarrera.delete();
//        
//        DataOutputStream archivoWrite;
//        
//        archivoWrite = new DataOutputStream(new FileOutputStream(fileCarrera));
//        
//        for(int i=0; i< carreras.size(); i++){
//            archivoWrite.writeUTF(carreras.get(i).getId());
//            archivoWrite.writeUTF(carreras.get(i).getNombre());
//            archivoWrite.writeUTF(carreras.get(i).getArea());
//            archivoWrite.writeUTF(carreras.get(i).getSemestre());
//            archivoWrite.writeUTF(carreras.get(i).getFechaD());
//            archivoWrite.writeUTF(carreras.get(i).getFechaM());
//            archivoWrite.writeUTF(carreras.get(i).getFechaA());
//            archivoWrite.writeUTF("#");
//            
//        }
//        
//        archivoWrite.close();
//
//    }
//    
//}

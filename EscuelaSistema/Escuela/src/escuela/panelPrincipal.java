/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuela;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class panelPrincipal extends javax.swing.JFrame {
    File file = new File("Usuarios.txt");
    ArrayList<Usuario> users = new ArrayList<Usuario>();
    
    File fileMateria = new File("Materias.txt");
    ArrayList<Materia> materias = new ArrayList<Materia>();
    
    File fileAlumno = new File("Alumno.txt");
    ArrayList<Alumnos> alumnos = new ArrayList<Alumnos>();
    
    File fileCarrera = new File("Carrera.txt");
    ArrayList<Carrera> carreras = new ArrayList<Carrera>();
    
    File fileMaestro = new File("Maestros.txt");
    ArrayList<Maestros> maestros = new ArrayList<Maestros>();
    
    File fileSemestre = new File("Semestre.txt");
    ArrayList<Semestre> semestres = new ArrayList<Semestre>();
    
    File fileGrupo = new File("Grupos.txt");
    ArrayList<Grupos> grupos = new ArrayList<Grupos>();
    
    int toEdit = -1;
    /**
     * Creates new form panelPrincipal
     */
    public panelPrincipal() {
        initComponents();
    }

    public panelPrincipal(String perfil){
        try {
            initComponents();
            
            switch (perfil) {
                case "Registrar":
                    panelTabs.setEnabledAt(0, true);
                    panelTabs.setEnabledAt(1, false);
                    panelTabs.setEnabledAt(2, false);
                    panelTabs.setEnabledAt(3, false);
                    panelTabs.setEnabledAt(4, false);
                    panelTabs.setEnabledAt(5, false);
                    panelTabs.setEnabledAt(6, false);
                    panelTabs.setEnabledAt(7, false);
                    
                    btnEliminarUser.setVisible(false);
                    btnEditar.setVisible(false);
                    btnGuardarCambios.setVisible(false);
                    btnCancelUsEdit.setVisible(false);
                    break;
                case "Administrador":
                    panelTabs.setEnabledAt(0, true);
                    panelTabs.setEnabledAt(1, true);
                    panelTabs.setEnabledAt(2, true);
                    panelTabs.setEnabledAt(3, true);
                    panelTabs.setEnabledAt(4, true);
                    panelTabs.setEnabledAt(5, true);
                    panelTabs.setEnabledAt(6, true);
                    panelTabs.setEnabledAt(7, true);
                    
                    //Botones de semestre
                    btnCambiosSem.setVisible(false);
                    btnCancelarEdicion.setVisible(false);
                    
                    //Botones de usario
                    btnGuardarCambios.setVisible(false);
                    btnCancelUsEdit.setVisible(false);
                    
                    if(fileSemestre.length() != 0){
                        DataInputStream archivoRead;

                        archivoRead = new DataInputStream(new FileInputStream(fileSemestre));

                        while(archivoRead.available() > 0){
                            int readID = archivoRead.readInt();
                            String readPeriodo = archivoRead.readUTF();
                            String readInicioSem = archivoRead.readUTF();
                            String readFinSem = archivoRead.readUTF();
                            String readSeparador = archivoRead.readUTF();

                            Semestre temp = new Semestre();

                            temp.setId(readID);
                            temp.setPeriodo(readPeriodo);
                            temp.setFechaI(readInicioSem);
                            temp.setFechaF(readFinSem);

                            semestres.add(temp);
                        }
                        comboSemestreC.removeAll();

                        
                        for(int i=0; i<semestres.size(); i++){
                            comboSemestreC.addItem(semestres.get(i).getPeriodo());
                        }
                        
                    }
                    else{
                        comboSemestreC.removeAll();
                        comboSemestreC.addItem("No hay semestres agregados.");
                    }
                    
                    if(fileCarrera.length() !=0){
                        DataInputStream archivoRead;

                        archivoRead = new DataInputStream(new FileInputStream(fileCarrera));

                        while(archivoRead.available() > 0){
                            int readID = archivoRead.readInt();
                            String readNombre = archivoRead.readUTF();
                            String readArea = archivoRead.readUTF();
                            String readSemestre = archivoRead.readUTF();
                            String readFechaC = archivoRead.readUTF();
                            String readSeparador = archivoRead.readUTF();

                            Carrera temp = new Carrera();

                            temp.setId(readID);
                            temp.setNombre(readNombre);
                            temp.setArea(readArea);
                            temp.setSemestre(readSemestre);
                            temp.setFechaC(readFechaC);


                            carreras.add(temp);
                        }
                        comboIDCarrera.removeAll();
                        
                        for(int i=0; i<carreras.size(); i++){
                            comboIDCarrera.addItem(carreras.get(i).getNombre());
                        }
                        
                    }
                    else{
                        comboIDCarrera.removeAll();
                        comboIDCarrera.addItem("No hay carreras disponibles");
                    }
                    
                    if(fileMateria.length() != 0){
                        DataInputStream archivoRead;

                        archivoRead = new DataInputStream(new FileInputStream(fileMateria));

                        while(archivoRead.available() > 0){
                            int readID = archivoRead.readInt();
                            String readNombreM = archivoRead.readUTF();
                            int readCredito = archivoRead.readInt();
                            String readIdCarrera = archivoRead.readUTF();
                            String readAcademia = archivoRead.readUTF();
                            String readSeparador = archivoRead.readUTF();

                            Materia temp = new Materia();

                            temp.setID(readID);
                            temp.setNombreM(readNombreM);
                            temp.setCreditos(readCredito);
                            temp.setIdcarrera(readIdCarrera);
                            temp.setAcademia(readAcademia);

                            materias.add(temp);
                        }
                        
                        comboIDMateria.removeAll();
                        
                        for(int i=0; i<materias.size(); i++){
                            comboIDMateria.addItem(materias.get(i).getNombreM());
                        }
                        
                    }
                    else{
                        comboIDMateria.removeAll();
                        comboIDMateria.addItem("No hay materias");
                    }
                    
                    if(fileMaestro.length() != 0){
                        DataInputStream archivoRead;

                        archivoRead = new DataInputStream(new FileInputStream(fileMaestro));

                        while(archivoRead.available() > 0){
                            int readID = archivoRead.readInt();
                            String readNombre = archivoRead.readUTF();
                            String readGradoA = archivoRead.readUTF();
                            String readGrupoA = archivoRead.readUTF();
                            String readDireccion = archivoRead.readUTF();
                            String readTelefono = archivoRead.readUTF();
                            String readSeparador = archivoRead.readUTF();

                            Maestros temp = new Maestros();

                            temp.setId(readID);
                            temp.setNombre(readNombre);
                            temp.setGradoA(readGradoA);
                            temp.setGrupoA(readGrupoA);
                            temp.setDireccion(readDireccion);
                            temp.setTelefono(readTelefono);


                            maestros.add(temp);
                        }
                    
                    comboIDMaestro.removeAll();
                    
                    for(int i=0; i<maestros.size(); i++){
                        comboIDMaestro.addItem(maestros.get(i).getNombre());
                    }
                    
                    }
                    else{
                        comboIDMaestro.removeAllItems();
                        comboIDMaestro.addItem("No hay maestros");
                    }
                    
                    break;
                case "Coordinador":
                    panelTabs.setSelectedIndex(1);
                    
                    panelTabs.setEnabledAt(0, false);
                    panelTabs.setEnabledAt(1, true);
                    panelTabs.setEnabledAt(2, true);
                    panelTabs.setEnabledAt(3, false);
                    panelTabs.setEnabledAt(4, false);
                    panelTabs.setEnabledAt(5, true);
                    panelTabs.setEnabledAt(6, true);
                    panelTabs.setEnabledAt(7, true);
                    
                    //Botones de semestre
                    btnCambiosSem.setVisible(false);
                    btnCancelarEdicion.setVisible(false);
                    
                    //Botones de usuario
                    btnGuardarCambios.setVisible(false);
                    btnCancelUsEdit.setVisible(false);
                    break;
                default:
                    break;
            }
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        txtAreaC = new javax.swing.JTextField();
        txtSemestreC = new javax.swing.JTextField();
        jToggleButton2 = new javax.swing.JToggleButton();
        txtFechaI = new javax.swing.JTextField();
        txtFechaF = new javax.swing.JTextField();
        txtPeriodoS = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        panelTabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lblUsuarioID = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtAPaterno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAMaterno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        txtPasswordConfirm = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        comboPerfil = new javax.swing.JComboBox<>();
        btnGuardarUsuario = new javax.swing.JButton();
        btnEliminarUser = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardarCambios = new javax.swing.JButton();
        btnCancelUsEdit = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnGuardarMateria = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtCreditos = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        comboIDCarrera = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        comboAcademia = new javax.swing.JComboBox<>();
        EditarM = new javax.swing.JButton();
        EliminarM = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        txtNombreMa = new javax.swing.JTextField();
        lblMateriaID = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnGuardarS = new javax.swing.JButton();
        btnEditarS = new javax.swing.JButton();
        btnEliminarS = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtBuscarS = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        comboPeriodoS = new javax.swing.JComboBox<>();
        chooserInicioSem = new com.toedter.calendar.JDateChooser();
        chooserFinSem = new com.toedter.calendar.JDateChooser();
        lblSemID = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnCambiosSem = new javax.swing.JButton();
        btnCancelarEdicion = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        comboGrupoAcad = new javax.swing.JComboBox<>();
        txtNombreMaestro = new javax.swing.JTextField();
        comboGradoAcad = new javax.swing.JComboBox<>();
        txtDireccionMa = new javax.swing.JTextField();
        txtTelefonoM = new javax.swing.JTextField();
        btnGuardarMa = new javax.swing.JButton();
        btnEditarMa = new javax.swing.JButton();
        btnEliminarMa = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lblIDMateria = new javax.swing.JLabel();
        txtNombreGrupo = new javax.swing.JTextField();
        comboIDMaestro = new javax.swing.JComboBox<>();
        comboIDMateria = new javax.swing.JComboBox<>();
        txtBuscarG = new javax.swing.JTextField();
        btnBuscarG = new javax.swing.JButton();
        btnGuardarG = new javax.swing.JButton();
        btnEditarG = new javax.swing.JButton();
        btnEliminarG = new javax.swing.JButton();
        lblIDGrupo = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtNumeroH = new javax.swing.JTextField();
        txtBuscarH = new javax.swing.JTextField();
        btnBuscarH = new javax.swing.JButton();
        comboHoraI = new javax.swing.JComboBox<>();
        comboHoraF = new javax.swing.JComboBox<>();
        comboIDGrupoH = new javax.swing.JComboBox<>();
        btnGuardarH = new javax.swing.JButton();
        btnEliminarH = new javax.swing.JButton();
        btnEditarH = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtNombreC = new javax.swing.JTextField();
        lblIDCarrera = new javax.swing.JLabel();
        lblNombreC = new javax.swing.JLabel();
        lblAreaC = new javax.swing.JLabel();
        lblSemestreC = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtBuscarC = new javax.swing.JTextField();
        btnBuscarC = new javax.swing.JButton();
        btnGuardarC = new javax.swing.JButton();
        btnEditarC = new javax.swing.JButton();
        btnEliminarC = new javax.swing.JButton();
        comboAreaC = new javax.swing.JComboBox<>();
        comboSemestreC = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        chooserFechaC = new com.toedter.calendar.JDateChooser();
        jLabel42 = new javax.swing.JLabel();
        panelAlumnos = new javax.swing.JPanel();
        txtNombreA = new javax.swing.JTextField();
        txtAP = new javax.swing.JTextField();
        txtAM = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCiudadA = new javax.swing.JTextField();
        lblIDA = new javax.swing.JLabel();
        lblNombreA = new javax.swing.JLabel();
        lblAP = new javax.swing.JLabel();
        lblAM = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        comboMes1 = new javax.swing.JComboBox<>();
        comboDia2 = new javax.swing.JComboBox<>();
        comboCarreraA = new javax.swing.JComboBox<>();
        comboSemestre = new javax.swing.JComboBox<>();
        comboGrupo = new javax.swing.JComboBox<>();
        comboPeriodo = new javax.swing.JComboBox<>();
        txtBuscarA = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblFNacimiento = new javax.swing.JLabel();
        lblFechaA = new javax.swing.JLabel();
        lblCiudadA = new javax.swing.JLabel();
        lblCarreraA = new javax.swing.JLabel();
        comboSemestreA = new javax.swing.JLabel();
        blbGrupoA = new javax.swing.JLabel();
        lblPeriodoA = new javax.swing.JLabel();
        comboDia1 = new javax.swing.JComboBox<>();
        comboAño1 = new javax.swing.JComboBox<>();
        lblDia1 = new javax.swing.JLabel();
        lblMes1 = new javax.swing.JLabel();
        lblAño1 = new javax.swing.JLabel();
        lblDia2 = new javax.swing.JLabel();
        lblMes2 = new javax.swing.JLabel();
        lblAño2 = new javax.swing.JLabel();
        comboMes2 = new javax.swing.JComboBox<>();
        comboAño2 = new javax.swing.JComboBox<>();
        btnGuardarA = new javax.swing.JButton();
        btnEliminarA = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        btnEditarA = new javax.swing.JButton();

        jTextField8.setText("jTextField8");

        jTextField9.setText("jTextField9");

        jTextField11.setText("jTextField11");

        jTextField12.setText("jTextField12");

        jTextField13.setText("jTextField13");

        jLabel19.setText("jLabel19");

        jToggleButton1.setText("jToggleButton1");

        jToggleButton2.setText("jToggleButton2");

        jTextField4.setText("jTextField4");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblUsuarioID.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        lblUsuarioID.setText("ID:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabel1.setText("Nombre:");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel2.setText("Registrar Usuario");

        jLabel3.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabel3.setText("A. Paterno:");

        jLabel4.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabel4.setText("A. Materno");

        jLabel5.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabel5.setText("Nombre de usuario:");

        jLabel6.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabel6.setText("Contraseña:");

        jLabel7.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabel7.setText("Confirmar Contraseña:");

        jLabel8.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jLabel8.setText("Perfil:");

        comboPerfil.setFont(new java.awt.Font("Yu Gothic Medium", 0, 11)); // NOI18N
        comboPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Coordinador" }));

        btnGuardarUsuario.setText("Guardar");
        btnGuardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUsuarioActionPerformed(evt);
            }
        });

        btnEliminarUser.setText("Eliminar");
        btnEliminarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUserActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnGuardarCambios.setText("Guardar Cambios");
        btnGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCambiosActionPerformed(evt);
            }
        });

        btnCancelUsEdit.setText("Cancelar Edicion");
        btnCancelUsEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelUsEditActionPerformed(evt);
            }
        });

        lblID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblID.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                .addComponent(txtNombreUsuario, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel6)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(btnGuardarUsuario)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(comboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarUser)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditar)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardarCambios)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                                .addComponent(btnCancelUsEdit))))
                    .addComponent(jLabel7)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(lblUsuarioID)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre)
                            .addComponent(txtAPaterno)
                            .addComponent(txtAMaterno, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(lblID)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsuarioID)
                            .addComponent(lblID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtAPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtAMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardarUsuario)
                    .addComponent(btnEliminarUser)
                    .addComponent(btnEditar)
                    .addComponent(btnGuardarCambios)
                    .addComponent(btnCancelUsEdit))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 149, Short.MAX_VALUE))
        );

        panelTabs.addTab("Usuario", jPanel1);

        btnGuardarMateria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGuardarMateria.setText("Guardar");
        btnGuardarMateria.setActionCommand("Guardad");
        btnGuardarMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarMateriaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("ID");

        txtCreditos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreditosActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Nombre de Materia:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Creditos:");

        comboIDCarrera.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboIDCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboIDCarreraActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("ID Carrera:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Academia:");

        comboAcademia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboAcademia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ciencias Básicas", "Ingenierías", "Electrónica y Computación" }));
        comboAcademia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAcademiaActionPerformed(evt);
            }
        });

        EditarM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EditarM.setText("Editar");
        EditarM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarMActionPerformed(evt);
            }
        });

        EliminarM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EliminarM.setText("Eliminar");
        EliminarM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarMActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel38.setText("Materias");

        txtNombreMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreMaActionPerformed(evt);
            }
        });

        lblMateriaID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMateriaID.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNombreMa, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnGuardarMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(EditarM, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(EliminarM, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(jLabel38))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMateriaID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(140, 140, 140)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboAcademia, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboIDCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(549, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel38)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(lblMateriaID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboAcademia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCreditos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(comboIDCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditarM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EliminarM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(303, 303, 303))
        );

        panelTabs.addTab("Materias", jPanel3);

        jLabel14.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel14.setText("Semestre");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("ID:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Periodo");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Fecha Inicio");

        btnGuardarS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGuardarS.setText("Guardar");
        btnGuardarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarSActionPerformed(evt);
            }
        });

        btnEditarS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditarS.setText("Editar");

        btnEliminarS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminarS.setText("Eliminar");
        btnEliminarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Fecha Fin");

        comboPeriodoS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020A", "2019B", "2019A", "2018B", "2018A", "2017B", "2017A", "2016B", "2016A" }));

        lblSemID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSemID.setText("0");

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnCambiosSem.setText("Guardar Cambios");
        btnCambiosSem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiosSemActionPerformed(evt);
            }
        });

        btnCancelarEdicion.setText("Cancelar Edicion");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(lblSemID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                        .addComponent(txtBuscarS, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addGap(296, 296, 296))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardarS)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnEditarS)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminarS)
                                .addGap(18, 18, 18)
                                .addComponent(btnCambiosSem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancelarEdicion)
                                .addGap(123, 123, 123))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboPeriodoS, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(chooserFinSem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                        .addComponent(chooserInicioSem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(jLabel14)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jButton4)
                    .addComponent(txtBuscarS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSemID))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(comboPeriodoS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17))
                            .addComponent(chooserInicioSem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20))
                    .addComponent(chooserFinSem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarS)
                    .addComponent(btnEditarS)
                    .addComponent(btnEliminarS)
                    .addComponent(btnCambiosSem)
                    .addComponent(btnCancelarEdicion))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(326, Short.MAX_VALUE))
        );

        panelTabs.addTab("Semestre", jPanel5);

        jLabel21.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel21.setText("Maestros");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("ID:");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Nombre Maestro");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("Grado Academico");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Grupo Academico");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("Dirección");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("Telefono");

        comboGrupoAcad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboGrupoAcad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ciencias Básicas", "Ingenierías", "Electrónica y Computación" }));

        txtNombreMaestro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreMaestroActionPerformed(evt);
            }
        });

        comboGradoAcad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboGradoAcad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Diplomado", "Técnico", "Bachillerato", "Licenciatura", "Maestria", "Doctorado" }));

        btnGuardarMa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGuardarMa.setText("Guardar");
        btnGuardarMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarMaActionPerformed(evt);
            }
        });

        btnEditarMa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditarMa.setText("Editar");

        btnEliminarMa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminarMa.setText("Eliminar");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jLabel21))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel29))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombreMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(comboGradoAcad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(140, 140, 140)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboGrupoAcad, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel32)
                                .addComponent(jLabel31)
                                .addComponent(txtDireccionMa, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                .addComponent(jLabel33)
                                .addComponent(txtTelefonoM))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGuardarMa)
                        .addGap(33, 33, 33)
                        .addComponent(btnEditarMa)
                        .addGap(34, 34, 34)
                        .addComponent(btnEliminarMa)))
                .addContainerGap(345, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel31)
                    .addComponent(jLabel41))
                .addGap(1, 1, 1)
                .addComponent(comboGrupoAcad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel29)
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtNombreMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireccionMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel30)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(comboGradoAcad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefonoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarMa)
                    .addComponent(btnEditarMa)
                    .addComponent(btnEliminarMa))
                .addContainerGap(343, Short.MAX_VALUE))
        );

        panelTabs.addTab("Maestros", jPanel6);

        jLabel34.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel34.setText("Grupos");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("ID");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("Nombre Grupo");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setText("ID Maestro");

        lblIDMateria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblIDMateria.setText("ID Materia");

        txtNombreGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreGrupoActionPerformed(evt);
            }
        });

        btnBuscarG.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBuscarG.setText("Buscar");

        btnGuardarG.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGuardarG.setText("Guardar");
        btnGuardarG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarGActionPerformed(evt);
            }
        });

        btnEditarG.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditarG.setText("Editar");

        btnEliminarG.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminarG.setText("Eliminar");

        lblIDGrupo.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        lblIDGrupo.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboIDMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(172, 172, 172)
                            .addComponent(jLabel34))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel35)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblIDGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel36))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(69, 69, 69)
                            .addComponent(btnGuardarG)
                            .addGap(18, 18, 18)
                            .addComponent(btnEditarG)
                            .addGap(18, 18, 18)
                            .addComponent(btnEliminarG))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel37))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblIDMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboIDMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(txtNombreGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(txtBuscarG, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnBuscarG)
                .addGap(261, 261, 261))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtBuscarG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarG)
                    .addComponent(lblIDGrupo))
                .addGap(24, 24, 24)
                .addComponent(jLabel36)
                .addGap(2, 2, 2)
                .addComponent(txtNombreGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37)
                .addGap(5, 5, 5)
                .addComponent(comboIDMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblIDMateria)
                .addGap(11, 11, 11)
                .addComponent(comboIDMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarG)
                    .addComponent(btnEditarG)
                    .addComponent(btnEliminarG))
                .addContainerGap(377, Short.MAX_VALUE))
        );

        panelTabs.addTab("Grupos", jPanel7);

        jLabel22.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel22.setText("Horario");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("ID");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Número de horas");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Hora Inicio");
        jLabel25.setToolTipText("");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Hora Final");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("ID Grupo");

        btnBuscarH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBuscarH.setText("Buscar");

        comboHoraI.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboHoraI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", " " }));

        comboHoraF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboHoraF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "07:55", "08:55", "09:55", "10:55", "11:55", "12:55", "13:55", "14:55", "15:55", "16:55", "17:55", "18:55", "19:55", "20:55", " " }));
        comboHoraF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboHoraFActionPerformed(evt);
            }
        });

        comboIDGrupoH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnGuardarH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGuardarH.setText("Guardar");

        btnEliminarH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminarH.setText("Eliminar");

        btnEditarH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditarH.setText("Editar");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(comboIDGrupoH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboHoraF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(18, 18, 18)
                                .addComponent(comboHoraI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNumeroH, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(138, 138, 138))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(btnGuardarH)
                                .addGap(32, 32, 32)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(btnEditarH))
                                .addGap(22, 22, 22)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txtBuscarH, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarH))
                            .addComponent(btnEliminarH))))
                .addContainerGap(340, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(37, 37, 37)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(comboIDGrupoH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtBuscarH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarH))
                        .addGap(23, 23, 23)
                        .addComponent(jLabel24)
                        .addGap(12, 12, 12)
                        .addComponent(txtNumeroH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(comboHoraI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(comboHoraF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)))
                .addGap(48, 48, 48)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarH)
                    .addComponent(btnEliminarH)
                    .addComponent(btnEditarH))
                .addContainerGap(259, Short.MAX_VALUE))
        );

        panelTabs.addTab("Horarios", jPanel8);

        lblIDCarrera.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblIDCarrera.setText("ID");

        lblNombreC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNombreC.setText("Nombre de la Carrera");

        lblAreaC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblAreaC.setText("Área");

        lblSemestreC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSemestreC.setText("Semestre");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Fecha");

        btnBuscarC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBuscarC.setText("Buscar");

        btnGuardarC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGuardarC.setText("Guardar");
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });

        btnEditarC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditarC.setText("Editar");
        btnEditarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCActionPerformed(evt);
            }
        });

        btnEliminarC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminarC.setText("Eliminar");

        comboAreaC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ciencias Básicas", "Ingenierías", "Electrónica y Computación" }));

        jLabel39.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel39.setText("Carrera");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel42.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreC)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addComponent(jLabel39))
                            .addComponent(txtNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblIDCarrera)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtBuscarC, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarC))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chooserFechaC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(btnGuardarC)
                        .addGap(30, 30, 30)
                        .addComponent(btnEditarC)
                        .addGap(32, 32, 32)
                        .addComponent(btnEliminarC))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAreaC)
                            .addComponent(lblSemestreC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboAreaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(comboSemestreC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(70, 70, 70)))))
                .addContainerGap(224, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblIDCarrera)
                                .addComponent(jLabel42)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombreC))
                    .addComponent(chooserFechaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarC))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAreaC)
                    .addComponent(comboAreaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSemestreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSemestreC))
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarC)
                    .addComponent(btnEditarC)
                    .addComponent(btnEliminarC))
                .addContainerGap(340, Short.MAX_VALUE))
        );

        panelTabs.addTab("Carrera", jPanel4);

        txtCiudadA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCiudadAActionPerformed(evt);
            }
        });

        lblIDA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblIDA.setText("ID");

        lblNombreA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNombreA.setText("Nombre");

        lblAP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblAP.setText("Apellido P.");

        lblAM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblAM.setText("Apelliodo M.");

        lblDireccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDireccion.setText("Dirección");

        lblTelefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTelefono.setText("Teléfono");

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEmail.setText("e-mail");

        comboMes1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero ", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        comboDia2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        comboDia2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDia2ActionPerformed(evt);
            }
        });

        comboCarreraA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingenieria Informática", "Ingenieria en Computación", "Ingenieria en Electrónica", " " }));
        comboCarreraA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCarreraAActionPerformed(evt);
            }
        });

        comboSemestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primero", "Segundo", "Tercero", "Cuarto", "Quinto", "Sexto", "Septimo", "Octavo" }));

        comboGrupo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" }));

        comboPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020A", "2019B", "2019A", "2018B", "2018A", "2017B", "2017A", "2016B", "2016A" }));
        comboPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPeriodoActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBuscar.setText("Buscar");

        lblFNacimiento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblFNacimiento.setText("F.Nacimiento");

        lblFechaA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblFechaA.setText("Fecha");

        lblCiudadA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCiudadA.setText("Ciudad");

        lblCarreraA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCarreraA.setText("Carrera");

        comboSemestreA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboSemestreA.setText("Semestre");

        blbGrupoA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        blbGrupoA.setText("Grupo");

        lblPeriodoA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPeriodoA.setText("Periodo");

        comboDia1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        comboAño1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960" }));

        lblDia1.setText("Día");

        lblMes1.setText("Mes");

        lblAño1.setText("Año");

        lblDia2.setText("Día");

        lblMes2.setText("Mes");

        lblAño2.setText("Año");

        comboMes2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero ", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        comboAño2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960" }));

        btnGuardarA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGuardarA.setText("Guardar");
        btnGuardarA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarAActionPerformed(evt);
            }
        });

        btnEliminarA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminarA.setText("Eliminar");
        btnEliminarA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        jLabel40.setText("Alumnos");

        btnEditarA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEditarA.setText("Editar");

        javax.swing.GroupLayout panelAlumnosLayout = new javax.swing.GroupLayout(panelAlumnos);
        panelAlumnos.setLayout(panelAlumnosLayout);
        panelAlumnosLayout.setHorizontalGroup(
            panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAlumnosLayout.createSequentialGroup()
                .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAlumnosLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAP)
                            .addComponent(lblNombreA)
                            .addComponent(lblIDA)))
                    .addGroup(panelAlumnosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAM))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAlumnosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDireccion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTelefono, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAlumnosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreA, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMes2)
                        .addGap(54, 54, 54)
                        .addComponent(lblAño2)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAlumnosLayout.createSequentialGroup()
                        .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAlumnosLayout.createSequentialGroup()
                                .addGap(261, 261, 261)
                                .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblCarreraA)
                                    .addComponent(lblCiudadA)
                                    .addComponent(comboSemestreA)
                                    .addComponent(blbGrupoA)
                                    .addComponent(lblPeriodoA))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAlumnosLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAlumnosLayout.createSequentialGroup()
                                        .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblFNacimiento)
                                            .addComponent(lblFechaA))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAlumnosLayout.createSequentialGroup()
                                        .addComponent(jLabel40)
                                        .addGap(67, 67, 67)))))
                        .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAlumnosLayout.createSequentialGroup()
                                .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelAlumnosLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(lblDia1)
                                        .addGap(48, 48, 48)
                                        .addComponent(lblMes1))
                                    .addGroup(panelAlumnosLayout.createSequentialGroup()
                                        .addComponent(comboDia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboMes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelAlumnosLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(comboAño1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelAlumnosLayout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(lblAño1))))
                            .addComponent(txtCiudadA, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboCarreraA, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelAlumnosLayout.createSequentialGroup()
                                .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblDia2)
                                    .addComponent(comboDia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(comboMes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboAño2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelAlumnosLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnGuardarA)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarA)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarA)))
                .addGap(151, 151, 151))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAlumnosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscarA, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addGap(161, 161, 161))
        );
        panelAlumnosLayout.setVerticalGroup(
            panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlumnosLayout.createSequentialGroup()
                .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelAlumnosLayout.createSequentialGroup()
                            .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelAlumnosLayout.createSequentialGroup()
                                    .addGap(69, 69, 69)
                                    .addComponent(lblIDA)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtNombreA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNombreA))
                                    .addGap(7, 7, 7)
                                    .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblAP)))
                                .addGroup(panelAlumnosLayout.createSequentialGroup()
                                    .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelAlumnosLayout.createSequentialGroup()
                                            .addGap(41, 41, 41)
                                            .addComponent(lblMes1))
                                        .addGroup(panelAlumnosLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel40)))
                                    .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblFNacimiento)
                                        .addComponent(comboDia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboMes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboAño1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblDia2)
                                        .addComponent(lblMes2)
                                        .addComponent(lblAño2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(comboDia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboMes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboAño2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblFechaA))
                                    .addGap(18, 18, 18)
                                    .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCiudadA)
                                        .addComponent(txtCiudadA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblAM))))
                            .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelAlumnosLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblDireccion))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTelefono)))
                                .addGroup(panelAlumnosLayout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCarreraA)
                                        .addComponent(comboCarreraA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboSemestreA)
                                    .addComponent(comboSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(lblEmail))
                    .addGroup(panelAlumnosLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDia1)
                            .addComponent(lblAño1))))
                .addGap(18, 18, 18)
                .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blbGrupoA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPeriodoA)
                    .addComponent(comboPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addGroup(panelAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarA)
                    .addComponent(btnEliminarA)
                    .addComponent(btnEditarA))
                .addContainerGap(226, Short.MAX_VALUE))
        );

        panelTabs.addTab("Alumnos", panelAlumnos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTabs)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAActionPerformed
        try {
            DataInputStream archivoRead;

            archivoRead = new DataInputStream(new FileInputStream(fileAlumno));

            while(archivoRead.available() > 0){
                int readId = archivoRead.readInt();
                String readNombre = archivoRead.readUTF();
                String readAp = archivoRead.readUTF();
                String readAm = archivoRead.readUTF();
                String readDireccion = archivoRead.readUTF();
                String readTelefono = archivoRead.readUTF();
                String readEmail = archivoRead.readUTF();
                String readFnacimientodia = archivoRead.readUTF();
                String readFnacimientomes = archivoRead.readUTF();
                String readFnacimientoanio = archivoRead.readUTF();
                String readFechadia = archivoRead.readUTF();
                String readFechames = archivoRead.readUTF();
                String readFechaanio = archivoRead.readUTF();
                String readCiudad = archivoRead.readUTF();
                String readCarrera = archivoRead.readUTF();
                String readSemestre = archivoRead.readUTF();
                String readGrupo = archivoRead.readUTF();
                String readPeriodo = archivoRead.readUTF();
                String readSeparador = archivoRead.readUTF();

                Alumnos temp = new Alumnos();

                temp.setId(readId);
                temp.setNombre(readNombre);
                temp.setAp(readAp);
                temp.setAm(readAm);
                temp.setDireccion(readDireccion);
                temp.setTelefono(readTelefono);
                temp.setEmail(readEmail);
                temp.setFnacimientodia(readFnacimientodia);
                temp.setFnacimientodia(readFnacimientomes);
                temp.setFnacimientodia(readFnacimientoanio);
                temp.setFechadia(readFechadia);
                temp.setFechames(readFechames);
                temp.setFechaanio(readFechaanio);
                temp.setCiudad(readCiudad);
                temp.setCarrera(readCiudad);
                temp.setSemestre(readCiudad);
                temp.setGrupo(readCiudad);
                temp.setPeriodo(readCiudad);

                alumnos.add(temp);
            }

            String userToDelete = JOptionPane.showInputDialog(this, "Ingrese el nombre de la materia que desea eliminar: ");

            boolean found = false;
            for(int i=0; i<alumnos.size() && !found; i++){
                if(alumnos.get(i).getNombre().equals(userToDelete)){
                    found = true;
                    alumnos.remove(i);
                    fileAlumno.delete();

                    DataOutputStream archivoWrite;

                    archivoWrite = new DataOutputStream(new FileOutputStream(fileAlumno));

                    for(int j=0; j<alumnos.size(); j++){
                        archivoWrite.writeInt(alumnos.get(i).getId());
                        archivoWrite.writeUTF(alumnos.get(i).getNombre());
                        archivoWrite.writeUTF(alumnos.get(i).getAp());
                        archivoWrite.writeUTF(alumnos.get(i).getAm());
                        archivoWrite.writeUTF(alumnos.get(i).getDireccion());
                        archivoWrite.writeUTF(alumnos.get(i).getTelefono());
                        archivoWrite.writeUTF(alumnos.get(i).getEmail());
                        archivoWrite.writeUTF(alumnos.get(i).getFnacimientodia());
                        archivoWrite.writeUTF(alumnos.get(i).getFnacimientomes());
                        archivoWrite.writeUTF(alumnos.get(i).getFnacimientoanio());
                        archivoWrite.writeUTF(alumnos.get(i).getFechadia());
                        archivoWrite.writeUTF(alumnos.get(i).getFechames());
                        archivoWrite.writeUTF(alumnos.get(i).getFechaanio());
                        archivoWrite.writeUTF(alumnos.get(i).getCiudad());
                        archivoWrite.writeUTF(alumnos.get(i).getCarrera());
                        archivoWrite.writeUTF(alumnos.get(i).getSemestre());
                        archivoWrite.writeUTF(alumnos.get(i).getGrupo());
                        archivoWrite.writeUTF(alumnos.get(i).getPeriodo());
                        archivoWrite.writeUTF("#");

                    }
                    archivoWrite.close();
                    JOptionPane.showMessageDialog(this, "El Alumno se ha eliminado con exito.");
                    alumnos.clear();
                }
            }

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarAActionPerformed

    private void btnGuardarAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarAActionPerformed
        try{
        if(txtNombreA.getText().isEmpty() || txtAP.getText().isEmpty() || txtAM.getText().isEmpty()|| txtDireccion.getText().isEmpty()|| txtTelefono.getText().isEmpty()|| txtEmail.getText().isEmpty()|| txtCiudadA.getText().isEmpty() ){
            JOptionPane.showMessageDialog(this, "Faltan campos por llenar");
            return;
        }

        Alumnos alumno = new Alumnos();

        alumno.setNombre(txtNombreA.getText());
        alumno.setAp(txtAP.getText());
        alumno.setAm(txtAM.getText());
        alumno.setDireccion(txtDireccion.getText());
        alumno.setTelefono(txtTelefono.getText());
        alumno.setEmail(txtEmail.getText());
        alumno.setFnacimientodia(comboDia1.getSelectedItem().toString());
        alumno.setFnacimientomes(comboMes1.getSelectedItem().toString());
        alumno.setFnacimientoanio(comboAño1.getSelectedItem().toString());
        alumno.setFechadia(comboDia2.getSelectedItem().toString());
        alumno.setFechames(comboMes2.getSelectedItem().toString());
        alumno.setFechaanio(comboAño2.getSelectedItem().toString());
        alumno.setCiudad(txtCiudadA.getText());
        alumno.setCarrera(comboCarreraA.getSelectedItem().toString());
        alumno.setSemestre(comboSemestre.getSelectedItem().toString());
        alumno.setGrupo(comboGrupo.getSelectedItem().toString());
        alumno.setPeriodo(comboPeriodo.getSelectedItem().toString());
        
        DataInputStream archivoRead;
        
        archivoRead = new DataInputStream(new FileInputStream(fileAlumno));
        
        
        while(archivoRead.available() > 0){
            int readId = archivoRead.readInt();
            String readNombre = archivoRead.readUTF();
            String readAp = archivoRead.readUTF();
            String readAm = archivoRead.readUTF();
            String readDireccion = archivoRead.readUTF();
            String readTelefono = archivoRead.readUTF();
            String readEmail = archivoRead.readUTF();
            String readFnacimientodia = archivoRead.readUTF();
            String readFnacimientomes = archivoRead.readUTF();
            String readFnacimientoanio = archivoRead.readUTF();
            String readFechadia = archivoRead.readUTF();
            String readFechames = archivoRead.readUTF();
            String readFechaanio = archivoRead.readUTF();
            String readCiudad = archivoRead.readUTF();
            String readCarrera = archivoRead.readUTF();
            String readSemestre = archivoRead.readUTF();
            String readGrupo = archivoRead.readUTF();
            String readPeriodo = archivoRead.readUTF();
            String readSeparador = archivoRead.readUTF();
            
            Alumnos temp = new Alumnos();
            
            temp.setId(readId);
            temp.setNombre(readNombre);
            temp.setAp(readAp);
            temp.setAm(readAm);
            temp.setDireccion(readDireccion);
            temp.setTelefono(readTelefono);
            temp.setEmail(readEmail);
            temp.setFnacimientodia(readFnacimientodia);
            temp.setFnacimientodia(readFnacimientomes);
            temp.setFnacimientodia(readFnacimientoanio);
            temp.setFechadia(readFechadia);
            temp.setFechames(readFechames);
            temp.setFechaanio(readFechaanio);
            temp.setCiudad(readCiudad);
            temp.setCarrera(readCiudad);
            temp.setSemestre(readCiudad);
            temp.setGrupo(readCiudad);
            temp.setPeriodo(readCiudad);
            
            alumnos.add(temp);
        }
        
        alumnos.add(alumno);
                
        fileAlumno.delete();
        
        DataOutputStream archivoWrite;
        
        archivoWrite = new DataOutputStream(new FileOutputStream(fileAlumno));
        
        for(int i=0; i< alumnos.size(); i++){
            archivoWrite.writeInt(alumnos.get(i).getId());
            archivoWrite.writeUTF(alumnos.get(i).getNombre());
            archivoWrite.writeUTF(alumnos.get(i).getAp());
            archivoWrite.writeUTF(alumnos.get(i).getAm());
            archivoWrite.writeUTF(alumnos.get(i).getDireccion());
            archivoWrite.writeUTF(alumnos.get(i).getTelefono());
            archivoWrite.writeUTF(alumnos.get(i).getEmail());
            archivoWrite.writeUTF(alumnos.get(i).getFnacimientodia());
            archivoWrite.writeUTF(alumnos.get(i).getFnacimientomes());
            archivoWrite.writeUTF(alumnos.get(i).getFnacimientoanio());
            archivoWrite.writeUTF(alumnos.get(i).getFechadia());
            archivoWrite.writeUTF(alumnos.get(i).getFechames());
            archivoWrite.writeUTF(alumnos.get(i).getFechaanio());
            archivoWrite.writeUTF(alumnos.get(i).getCiudad());
            archivoWrite.writeUTF(alumnos.get(i).getCarrera());
            archivoWrite.writeUTF(alumnos.get(i).getSemestre());
            archivoWrite.writeUTF(alumnos.get(i).getGrupo());
            archivoWrite.writeUTF(alumnos.get(i).getPeriodo());
            
            archivoWrite.writeUTF("#");
            
        }
        
        archivoWrite.close();
        alumnos.clear();    
        JOptionPane.showMessageDialog(this,"Alumno Registrado.","El Alumno fue registrado exitosamente.",JOptionPane.PLAIN_MESSAGE);
        
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnGuardarAActionPerformed

    private void comboPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPeriodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboPeriodoActionPerformed

    private void comboCarreraAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCarreraAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCarreraAActionPerformed

    private void comboDia2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDia2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDia2ActionPerformed

    private void txtCiudadAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCiudadAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCiudadAActionPerformed

    private void btnEditarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarCActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        String patronCarrera = "^[a-zA-Z\\s]{5,30}$";
        Pattern patToCheckCarrera = Pattern.compile(patronCarrera);
        
        Matcher regexMatcherCarrera = patToCheckCarrera.matcher(txtNombreC.getText());
        
        Date toCheckCarrera = chooserFechaC.getDate();
        
        try {
            
            if(toCheckCarrera == null){
                JOptionPane.showMessageDialog(this, "No se selecciono la fecha.");
                return;
            }
            
            if(txtNombreC.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar");
                return;
            }
            
            
            if(regexMatcherCarrera.matches()){
                SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
                String fechaCarrera = fechaFormato.format(chooserFechaC.getDate());
                
                Carrera carrera = new Carrera();
                
                carrera.setNombre(txtNombreC.getText());
                carrera.setArea(comboAreaC.getSelectedItem().toString());
                carrera.setSemestre(comboSemestreC.getSelectedItem().toString());
                carrera.setFechaC(fechaCarrera);

                if(fileCarrera.length() !=0){
                    DataInputStream archivoRead;

                    archivoRead = new DataInputStream(new FileInputStream(fileCarrera));

                    while(archivoRead.available() > 0){
                        int readID = archivoRead.readInt();
                        String readNombre = archivoRead.readUTF();
                        String readArea = archivoRead.readUTF();
                        String readSemestre = archivoRead.readUTF();
                        String readFechaC = archivoRead.readUTF();
                        String readSeparador = archivoRead.readUTF();

                        Carrera temp = new Carrera();

                        temp.setId(readID);
                        temp.setNombre(readNombre);
                        temp.setArea(readArea);
                        temp.setSemestre(readSemestre);
                        temp.setFechaC(readFechaC);


                        carreras.add(temp);
                    }
                    int temp = carreras.size() - 1;
                    carrera.setId(carreras.get(temp).getId() + 1);
                    
                }
                else{
                    carrera.setId(1);
                }   

                carreras.add(carrera);

                fileCarrera.delete();

                DataOutputStream archivoWrite;

                archivoWrite = new DataOutputStream(new FileOutputStream(fileCarrera));

                for(int i=0; i<carreras.size() ; i++){
                    archivoWrite.writeInt(carreras.get(i).getId());
                    archivoWrite.writeUTF(carreras.get(i).getNombre());
                    archivoWrite.writeUTF(carreras.get(i).getArea());
                    archivoWrite.writeUTF(carreras.get(i).getSemestre());
                    archivoWrite.writeUTF(carreras.get(i).getFechaC());
                    
                    archivoWrite.writeUTF("#");
                }

                archivoWrite.close();
                carreras.clear();
                JOptionPane.showMessageDialog(this, "La carrera fue registrada exitosamente.");
                
                comboIDCarrera.addItem(carrera.getNombre());
            }
            else{
                JOptionPane.showMessageDialog(this, "El nombre de la carrera no cuenta con el formato correcto.");
            }
                

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void EliminarMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarMActionPerformed

        try {
            DataInputStream archivoRead;

            archivoRead = new DataInputStream(new FileInputStream(fileMateria));

            while(archivoRead.available() > 0){
                int readID = archivoRead.readInt();
                String readNombreM = archivoRead.readUTF();
                int readCreditos = archivoRead.readInt();
                String readIdCarrera = archivoRead.readUTF();
                String readAcademia = archivoRead.readUTF();

                String readSeparador = archivoRead.readUTF();

                Materia temp = new Materia();

                temp.setID(readID);
                temp.setNombreM(readNombreM);
                temp.setAcademia(readAcademia);
                temp.setIdcarrera(readIdCarrera);
                temp.setCreditos(readCreditos);

                materias.add(temp);
            }

            String userToDelete = JOptionPane.showInputDialog(this, "Ingrese el nombre de la materia que desea eliminar: ");

            boolean found = false;
            for(int i=0; i<materias.size() && !found; i++){
                if(materias.get(i).getNombreM().equals(userToDelete)){
                    found = true;
                    materias.remove(i);
                    fileMateria.delete();

                    DataOutputStream archivoWrite;

                    archivoWrite = new DataOutputStream(new FileOutputStream(fileMateria));

                    for(int j=0; j<materias.size(); j++){
                        archivoWrite.writeInt(materias.get(i).getID());
                        archivoWrite.writeUTF(materias.get(i).getNombreM());
                        archivoWrite.writeInt(materias.get(i).getCreditos());
                        archivoWrite.writeUTF(materias.get(i).getIdcarrera());
                        archivoWrite.writeUTF(materias.get(i).getAcademia());

                        archivoWrite.writeUTF("#");
                    }
                    archivoWrite.close();
                    JOptionPane.showMessageDialog(this, "La Materia se ha eliminado con exito.");
                    materias.clear();
                }
            }

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_EliminarMActionPerformed

    private void EditarMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarMActionPerformed
        String[] academiaO = {"Ciencias Básicas", "Ingenierías", "Electrónica y Computación"};
        materias.clear();
        try {
            DataInputStream archivoRead;

            archivoRead = new DataInputStream(new FileInputStream(fileMateria));

            while(archivoRead.available() > 0){
                int readID = archivoRead.readInt();
                String readNombreM = archivoRead.readUTF();
                int readCreditos = archivoRead.readInt();
                String readIdCarrera = archivoRead.readUTF();
                String readAcademia = archivoRead.readUTF();

                String readSeparador = archivoRead.readUTF();

                Materia temp = new Materia();

                temp.setID(readID);
                temp.setNombreM(readNombreM);
                temp.setAcademia(readAcademia);
                temp.setIdcarrera(readIdCarrera);
                temp.setCreditos(readCreditos);

                materias.add(temp);

            }

            String materiaToDelete = JOptionPane.showInputDialog(this, "Ingrese el nombre de la materia que desea editar: ");

            boolean found = false;
            int toChange =-1;
            for(int i=0; i<materias.size() && !found; i++){
                if(materias.get(i).getNombreM().equals(materiaToDelete)){
                    txtNombreMa.setText(materias.get(i).getNombreM());
                    txtCreditos.setText(Integer.toString(materias.get(i).getCreditos()));
                    comboAcademia.setSelectedIndex(1);
                    comboIDCarrera.setSelectedIndex(0);

                    found = true;
                    toChange=i;
                }
            }

            int editID = 0;
            String editNombreMa = "";
            int editCreditos = 0;
            int editIdCarrera =0;
            int editAcademia =0;

            if(found){
                editID = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el nuevo ID: "));
                editNombreMa = JOptionPane.showInputDialog(this, "Ingrese el nuevo Nombre de la materia:");
                editCreditos = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la nueva cantidad de creditos:"));
                editAcademia = JOptionPane.showOptionDialog(this, "Seleccione la nueva academia: ", "Seleccione una opcion.", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, academiaO, academiaO[0]);

                Materia aux = new Materia();

                aux.setID(editID);
                aux.setNombreM(editNombreMa);
                aux.setCreditos(editCreditos);
                switch (editAcademia) {
                    case 0:
                    aux.setAcademia("Ciencias Básicas");
                    break;
                    case 1:
                    aux.setAcademia("Ingenierías");
                    break;
                    case 2:
                    aux.setAcademia("Electrónica y Computación");
                    break;
                    default:
                    aux.setAcademia("Electrónica y Computación");
                    break;
                }
                aux.setIdcarrera("INNI");

                materias.remove(toChange);
                materias.add(toChange, aux);

                fileMateria.delete();

                fileMateria.createNewFile();

                DataOutputStream archivoWrite = new DataOutputStream(new FileOutputStream(fileMateria));

                for(int i=0; i<materias.size(); i++){
                    archivoWrite.writeInt(materias.get(i).getID());
                    archivoWrite.writeUTF(materias.get(i).getNombreM());
                    archivoWrite.writeInt(materias.get(i).getCreditos());
                    archivoWrite.writeUTF(materias.get(i).getIdcarrera());
                    archivoWrite.writeUTF(materias.get(i).getAcademia());

                    archivoWrite.writeUTF("#");
                }
                archivoWrite.close();
                JOptionPane.showMessageDialog(this, "La materia fue actualizada.");
                materias.clear();
            }
            else{
                materias.clear();
                JOptionPane.showMessageDialog(this, "La materia no fue encontrada.");
            }

        } catch (IOException ex) {
            //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_EditarMActionPerformed

    private void comboAcademiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAcademiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboAcademiaActionPerformed

    private void comboIDCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboIDCarreraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboIDCarreraActionPerformed

    private void txtCreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreditosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCreditosActionPerformed

    private void btnGuardarMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarMateriaActionPerformed
        String patronCredits = "^[0-9]{1,2}$";
        Pattern patToCheckCredits = Pattern.compile(patronCredits);
        
        String patronMaterias = "^[a-zA-Z\\s]{1,20}$";
        Pattern patToCheckMaterias = Pattern.compile(patronMaterias);
        
        Matcher regexMatcherCredits = patToCheckCredits.matcher(txtCreditos.getText());
        Matcher regexMatcherMaterias = patToCheckMaterias.matcher(txtNombreMa.getText());
        
        if(regexMatcherCredits.matches() && regexMatcherMaterias.matches()){
            try {
                if(txtNombreMa.getText().isEmpty() || txtCreditos.getText().isEmpty()  ){
                    JOptionPane.showMessageDialog(this, "Faltan campos por llenar");
                    return;
                }

                Materia materia = new Materia();

                materia.setNombreM(txtNombreMa.getText());
                materia.setCreditos(Integer.parseInt(txtCreditos.getText()));
                materia.setIdcarrera(comboIDCarrera.getSelectedItem().toString());
                materia.setAcademia(comboAcademia.getSelectedItem().toString());

                if(fileMateria.length() != 0){
                    DataInputStream archivoRead;

                    archivoRead = new DataInputStream(new FileInputStream(fileMateria));

                    while(archivoRead.available() > 0){
                        int readID = archivoRead.readInt();
                        String readNombreM = archivoRead.readUTF();
                        int readCredito = archivoRead.readInt();
                        String readIdCarrera = archivoRead.readUTF();
                        String readAcademia = archivoRead.readUTF();
                        String readSeparador = archivoRead.readUTF();

                        Materia temp = new Materia();

                        temp.setID(readID);
                        temp.setNombreM(readNombreM);
                        temp.setCreditos(readCredito);
                        temp.setIdcarrera(readIdCarrera);
                        temp.setAcademia(readAcademia);

                        materias.add(temp);
                    }
                    int temp = materias.size() - 1;
                    materia.setID(materias.get(temp).getID()+1);
                }
                else{
                    materia.setID(1);
                }
                materias.add(materia);

                fileMateria.delete();

                DataOutputStream archivoWrite;

                archivoWrite = new DataOutputStream(new FileOutputStream(fileMateria));

                for(int i=0; i<materias.size() ; i++){
                    archivoWrite.writeInt(materias.get(i).getID());
                    archivoWrite.writeUTF(materias.get(i).getNombreM());
                    archivoWrite.writeInt(materias.get(i).getCreditos());
                    archivoWrite.writeUTF(materias.get(i).getIdcarrera());
                    archivoWrite.writeUTF(materias.get(i).getAcademia());
                    archivoWrite.writeUTF("#");

                }

                archivoWrite.close();
                
                lblMateriaID.setText(String.valueOf(materia.getID()));
                txtNombreMa.setText("");
                txtCreditos.setText("");
                materias.clear();
                JOptionPane.showMessageDialog(this, "La materia fue registrada exitosamente.");
                comboIDMateria.addItem(materia.getNombreM());

            } catch (FileNotFoundException ex) {
                //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Los campos no cumplen con el formato requerido");
        }
    }//GEN-LAST:event_btnGuardarMateriaActionPerformed

    private void txtNombreMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMaActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        
        if(txtNombre.getText().equals("") && txtNombreUsuario.getText().equals("")){
            JOptionPane.showMessageDialog(this,"No ha ingresado un nombre o un nombre de usuario para editar: ");
        }
        else{
            DataInputStream archivoRead;

            try {
                archivoRead = new DataInputStream(new FileInputStream(file));
            

                while(archivoRead.available() > 0){
                    int readID = archivoRead.readInt();
                    String readNombre = archivoRead.readUTF();
                    String readPaterno = archivoRead.readUTF();
                    String readMaterno = archivoRead.readUTF();
                    String readNombreUsuario = archivoRead.readUTF();
                    String readPassword = archivoRead.readUTF();
                    String readPerfil = archivoRead.readUTF();
                    String readSeparador = archivoRead.readUTF();

                    Usuario temp = new Usuario();

                    temp.setId(readID);
                    temp.setNombre(readNombre);
                    temp.setaPaterno(readPaterno);
                    temp.setaMaterno(readMaterno);
                    temp.setNombreUsuario(readNombreUsuario);
                    temp.setPassword(readPassword);
                    temp.setPerfil(readPerfil);

                    users.add(temp);

                }
                boolean found = false;

                for(int i=0; i<users.size() && !found; i++){
                    if(txtNombre.getText().equals(users.get(i).getNombre()) || txtNombreUsuario.getText().equals(users.get(i).getNombreUsuario())){
                        found = true;
                        toEdit = i;
                        btnGuardarCambios.setVisible(true);
                        btnEditar.setVisible(false);
                        btnEliminarUser.setVisible(false);
                        btnGuardarUsuario.setVisible(false);
                        btnCancelUsEdit.setVisible(true);
                        btnCancelar.setVisible(false);
                        
                        txtNombre.setText(users.get(i).getNombre());
                        txtAPaterno.setText(users.get(i).getaPaterno());
                        txtAMaterno.setText(users.get(i).getaMaterno());
                        txtNombreUsuario.setText(users.get(i).getNombreUsuario());
                        txtPassword.setText(users.get(i).getPassword());
                        txtPasswordConfirm.setText(users.get(i).getPassword());
                        lblID.setText(Integer.toString(users.get(i).getId()));
                    }
                }
                if(!found){
                    JOptionPane.showMessageDialog(this, "El usuario no fue encontrado");
                    users.clear();
                }
                
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
        
    }//GEN-LAST:event_btnEditarActionPerformed

        
    private void btnEliminarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUserActionPerformed
        try {
            DataInputStream archivoRead;

            archivoRead = new DataInputStream(new FileInputStream(file));

            while(archivoRead.available() > 0){
                int readID = archivoRead.readInt();
                String readNombre = archivoRead.readUTF();
                String readPaterno = archivoRead.readUTF();
                String readMaterno = archivoRead.readUTF();
                String readNombreUsuario = archivoRead.readUTF();
                String readPassword = archivoRead.readUTF();
                String readPerfil = archivoRead.readUTF();
                String readSeparador = archivoRead.readUTF();

                Usuario temp = new Usuario();

                temp.setId(readID);
                temp.setNombre(readNombre);
                temp.setaPaterno(readPaterno);
                temp.setaMaterno(readMaterno);
                temp.setNombreUsuario(readNombreUsuario);
                temp.setPassword(readPassword);
                temp.setPerfil(readPerfil);

                users.add(temp);

            }

            String userToDelete = JOptionPane.showInputDialog(this, "Ingrese el nombre del ususario que desea eliminar: ");

            boolean found = false;
            for(int i=0; i<users.size() && !found; i++){
                if(users.get(i).getNombreUsuario().equals(userToDelete)){
                    found = true;
                    users.remove(i);
                    file.delete();

                    DataOutputStream archivoWrite;

                    archivoWrite = new DataOutputStream(new FileOutputStream(file));

                    for(int j=0; j<users.size(); j++){
                        archivoWrite.writeInt(users.get(j).getId());
                        archivoWrite.writeUTF(users.get(j).getNombre());
                        archivoWrite.writeUTF(users.get(j).getaPaterno());
                        archivoWrite.writeUTF(users.get(j).getaMaterno());
                        archivoWrite.writeUTF(users.get(j).getNombreUsuario());
                        archivoWrite.writeUTF(users.get(j).getPassword());
                        archivoWrite.writeUTF(users.get(j).getPerfil());
                        archivoWrite.writeUTF("#");

                    }
                    archivoWrite.close();
                    JOptionPane.showMessageDialog(this, "El usuario se ha eliminado con exito.");
                    users.clear();
                }
            }
            if(!found){
                users.clear();
                JOptionPane.showMessageDialog(this, "El usuario no fue encontrado.");
            }
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnEliminarUserActionPerformed

    private void btnGuardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioActionPerformed
        String patronNombres = "^[a-zA-Z]{2,50}$";
        Pattern patToCheckNombre = Pattern.compile(patronNombres);
        
        String patronUsername = "^[a-zA-Z0-9]{3,20}$";
        Pattern patToCheckUs = Pattern.compile(patronUsername);
        
        if(txtNombre.getText().isEmpty() || txtAPaterno.getText().isEmpty() || txtAMaterno.getText().isEmpty() || txtNombreUsuario.getText().isEmpty() || txtPassword.getText().isEmpty() || txtPasswordConfirm.getText().isEmpty() ){
            JOptionPane.showMessageDialog(this, "Faltan campos por llenar");
            return;
        }

        if(!txtPassword.getText().equals(txtPasswordConfirm.getText())){
            JOptionPane.showMessageDialog(this, "La contraseña no coincide.");
            return;
        }
        
        Matcher regexMatcherNombre = patToCheckNombre.matcher(txtNombre.getText());
        Matcher regexMatcherAPaterno = patToCheckNombre.matcher(txtAPaterno.getText());
        Matcher regexMatcherAMaterno = patToCheckNombre.matcher(txtAMaterno.getText());
        Matcher regexMatcherPassword = patToCheckUs.matcher(txtNombreUsuario.getText());
        
        
        if(regexMatcherNombre.matches() && regexMatcherAPaterno.matches() && regexMatcherAMaterno.matches() && regexMatcherPassword.matches()){
            Usuario us = new Usuario();
        
            us.setId(us.addAndIncrease());
            us.setNombre(txtNombre.getText());
            us.setNombreUsuario(txtNombreUsuario.getText());
            us.setPassword(txtPassword.getText());
            us.setPerfil(comboPerfil.getSelectedItem().toString());
            us.setaMaterno(txtAMaterno.getText());
            us.setaPaterno(txtAPaterno.getText());

            Entrar n;
            try {
                n = new Entrar(us);
                n.setVisible(true);
                dispose();
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                //Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Los campos no cumplen con el formato adecuado.");
        }
        
    }//GEN-LAST:event_btnGuardarUsuarioActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Entrar n;
        try {
            n = new Entrar();
            n.setVisible(true);
            dispose();

        } catch (IOException ex) {
            //Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNombreMaestroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreMaestroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMaestroActionPerformed

    private void comboHoraFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboHoraFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboHoraFActionPerformed

    private void btnGuardarMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarMaActionPerformed
        try {
            
            if(txtNombreMaestro.getText().isEmpty() || txtDireccionMa.getText().isEmpty() ||txtTelefonoM.getText().isEmpty() ){
                JOptionPane.showMessageDialog(this, "Faltan campos por llenar");
                return;
            }
            
            String patronMaestro = "^[a-zA-Z\\s]{3,40}$";
            Pattern patToCheckMaestro = Pattern.compile(patronMaestro);
            
            String patronDireccion = "^[a-zA-Z0-9\\s]{3,100}$";
            Pattern patToCheckDireccion = Pattern.compile(patronDireccion);
            
            String patronTelefono = "^[0-9]{8,10}$";
            Pattern patToCheckTelefono = Pattern.compile(patronTelefono);
            
            
            Matcher regexMatcherMaestro = patToCheckMaestro.matcher(txtNombreMaestro.getText());
            Matcher regexMatcherDireccion = patToCheckDireccion.matcher(txtDireccionMa.getText());
            Matcher regexMatcherTelefono = patToCheckTelefono.matcher(txtTelefonoM.getText());
            
            
            
            if(regexMatcherMaestro.matches() && regexMatcherDireccion.matches() && regexMatcherTelefono.matches()){
                Maestros maestro = new Maestros();

                maestro.setNombre(txtNombreMaestro.getText());
                maestro.setGradoA(comboGradoAcad.getSelectedItem().toString());
                maestro.setGrupoA(comboGrupoAcad.getSelectedItem().toString());
                maestro.setDireccion(txtDireccionMa.getText());
                maestro.setTelefono(txtTelefonoM.getText());
                
                
                if(fileMaestro.length() != 0){
                    DataInputStream archivoRead;

                    archivoRead = new DataInputStream(new FileInputStream(fileMaestro));

                    while(archivoRead.available() > 0){
                        int readID = archivoRead.readInt();
                        String readNombre = archivoRead.readUTF();
                        String readGradoA = archivoRead.readUTF();
                        String readGrupoA = archivoRead.readUTF();
                        String readDireccion = archivoRead.readUTF();
                        String readTelefono = archivoRead.readUTF();
                        String readSeparador = archivoRead.readUTF();

                        Maestros temp = new Maestros();

                        temp.setId(readID);
                        temp.setNombre(readNombre);
                        temp.setGradoA(readGradoA);
                        temp.setGrupoA(readGrupoA);
                        temp.setDireccion(readDireccion);
                        temp.setTelefono(readTelefono);


                        maestros.add(temp);
                    }
                    int temp = maestros.size() - 1;
                    maestro.setId(maestros.get(temp).getId()+1);

                }
                else{
                    maestro.setId(1);
                }
                
                maestros.add(maestro);

                fileMaestro.delete();

                DataOutputStream archivoWrite;

                archivoWrite = new DataOutputStream(new FileOutputStream(fileMaestro));

                for(int i=0; i<maestros.size() ; i++){
                    archivoWrite.writeInt(maestros.get(i).getId());
                    archivoWrite.writeUTF(maestros.get(i).getNombre());
                    archivoWrite.writeUTF(maestros.get(i).getGradoA());
                    archivoWrite.writeUTF(maestros.get(i).getGrupoA());
                    archivoWrite.writeUTF(maestros.get(i).getDireccion());
                    archivoWrite.writeUTF(maestros.get(i).getTelefono());
                    archivoWrite.writeUTF("#");

                }

                archivoWrite.close();
                maestros.clear();
                JOptionPane.showMessageDialog(this, "El Maestro fue registrado exitosamente.");
                
                comboIDMaestro.addItem(maestro.getNombre());
                
                txtNombreMaestro.setText("");
                txtDireccionMa.setText("");
                txtTelefonoM.setText("");
            }
            else{
                JOptionPane.showMessageDialog(this, "Los datos no cuentan con el formato correccto.");
            }
                

            } catch (FileNotFoundException ex) {
                //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }//GEN-LAST:event_btnGuardarMaActionPerformed

    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        String patronNombres = "^[a-zA-Z]{2,50}$";
        Pattern patToCheckNombre = Pattern.compile(patronNombres);
        
        String patronUsername = "^[a-zA-Z0-9]{3,20}$";
        Pattern patToCheckUs = Pattern.compile(patronUsername);
        
        Matcher regexMatcherNombre = patToCheckNombre.matcher(txtNombre.getText());
        Matcher regexMatcherAPaterno = patToCheckNombre.matcher(txtAPaterno.getText());
        Matcher regexMatcherAMaterno = patToCheckNombre.matcher(txtAMaterno.getText());
        Matcher regexMatcherPassword = patToCheckUs.matcher(txtNombreUsuario.getText());
        
        if(regexMatcherNombre.matches() && regexMatcherAPaterno.matches() && regexMatcherAMaterno.matches() && regexMatcherPassword.matches()){
        
            try {

                Usuario temp = new Usuario();

                temp.setId(users.size() + 1);
                temp.setNombre(txtNombre.getText());
                temp.setNombreUsuario(txtNombreUsuario.getText());
                temp.setPassword(txtPassword.getText());
                temp.setPerfil(comboPerfil.getSelectedItem().toString());
                temp.setaMaterno(txtAMaterno.getText());
                temp.setaPaterno(txtAPaterno.getText());


                if(toEdit == -1){
                    JOptionPane.showMessageDialog(this, "No se pudo editar el usuario.");
                }
                else{
                    users.remove(toEdit);
                    users.add(toEdit, temp);

                    file.delete();

                    file.createNewFile();

                    DataOutputStream archivoWrite = new DataOutputStream(new FileOutputStream(file));

                    for(int i=0; i<users.size(); i++){
                        archivoWrite.writeInt(users.get(i).getId());
                        archivoWrite.writeUTF(users.get(i).getNombre());
                        archivoWrite.writeUTF(users.get(i).getaPaterno());
                        archivoWrite.writeUTF(users.get(i).getaMaterno());
                        archivoWrite.writeUTF(users.get(i).getNombreUsuario());
                        archivoWrite.writeUTF(users.get(i).getPassword());
                        archivoWrite.writeUTF(users.get(i).getPerfil());
                        archivoWrite.writeUTF("#");
                    }
                    archivoWrite.close();
                    JOptionPane.showMessageDialog(this, "El usuario fue actualizado.");
                    toEdit = -1;
                }
                } catch (IOException ex) {
                    
                }
            }
        else{
            JOptionPane.showMessageDialog(this, "Los campos no cuentan con el formate indicado, saliendo de editar.");
        }
            
            txtNombre.setText("");
            txtAPaterno.setText("");
            txtAMaterno.setText("");
            txtNombreUsuario.setText("");
            txtPassword.setText("");
            txtPasswordConfirm.setText("");

            btnGuardarCambios.setVisible(false);
            btnEditar.setVisible(true);
            btnEliminarUser.setVisible(true);
            btnGuardarUsuario.setVisible(true);
            btnCancelUsEdit.setVisible(false);
            btnCancelar.setVisible(true);
            
            
            users.clear();
       
        
    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void btnCancelUsEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelUsEditActionPerformed
        txtNombre.setText("");
        txtAPaterno.setText("");
        txtAMaterno.setText("");
        txtNombreUsuario.setText("");
        txtPassword.setText("");
        txtPasswordConfirm.setText("");
        
        btnGuardarCambios.setVisible(false);
        btnEditar.setVisible(true);
        btnEliminarUser.setVisible(true);
        btnGuardarUsuario.setVisible(true);
        btnCancelUsEdit.setVisible(false);
        btnCancelar.setVisible(true);
    }//GEN-LAST:event_btnCancelUsEditActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnGuardarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarSActionPerformed
        semestres.clear();
        try {
            Date toCheckInicio = chooserInicioSem.getDate();
            Date toCheckFinal = chooserFinSem.getDate();
            
            if(toCheckInicio == null || toCheckFinal == null){
                JOptionPane.showMessageDialog(this, "No se selecciono la fecha.");
                return;
            }
            
            
            SimpleDateFormat semFormato = new SimpleDateFormat("dd/MM/yyyy");
            String semInicio = semFormato.format(chooserInicioSem.getDate());
            
            String semFinal = semFormato.format(chooserFinSem.getDate());
            
            Semestre semestre = new Semestre();
            
            semestre.setPeriodo(comboPeriodoS.getSelectedItem().toString());
            semestre.setFechaI(semInicio);
            semestre.setFechaF(semFinal);
            
            if(fileSemestre.length() != 0){
                try {
                    DataInputStream archivoRead;
                    
                    archivoRead = new DataInputStream(new FileInputStream(fileSemestre));
                    
                    while(archivoRead.available() > 0){
                        int readID = archivoRead.readInt();
                        String readPeriodo = archivoRead.readUTF();
                        String readInicioSem = archivoRead.readUTF();
                        String readFinSem = archivoRead.readUTF();
                        String readSeparador = archivoRead.readUTF();
                        
                        Semestre temp = new Semestre();
                        
                        temp.setId(readID);
                        temp.setPeriodo(readPeriodo);
                        temp.setFechaI(readInicioSem);
                        temp.setFechaF(readFinSem);
                        
                        semestres.add(temp);
                    }
                    int temp = semestres.size() - 1;
                    semestre.setId(semestres.get(temp).getId() + 1);
                } catch (FileNotFoundException ex) {
                    //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                semestre.setId(1);
            }
            
            semestres.add(semestre);
            
            fileSemestre.delete();
            
            DataOutputStream archivoWrite;
            
            archivoWrite = new DataOutputStream(new FileOutputStream(fileSemestre));
            
            for(int i=0; i<semestres.size() ; i++){
                archivoWrite.writeInt(semestres.get(i).getId());
                archivoWrite.writeUTF(semestres.get(i).getPeriodo());
                archivoWrite.writeUTF(semestres.get(i).getFechaI());
                archivoWrite.writeUTF(semestres.get(i).getFechaF());
                
                archivoWrite.writeUTF("#");
                
            }
            
            archivoWrite.close();
            
            lblSemID.setText(String.valueOf(semestre.getId()));
            
            semestres.clear();
            
            comboSemestreC.addItem(semestre.getPeriodo());
            
            JOptionPane.showMessageDialog(this, "El semestre fue registrado exitosamente.");
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnGuardarSActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEliminarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSActionPerformed
        if(!semestres.isEmpty())
            semestres.clear();
        
        if(fileSemestre.length() != 0){
                try {
                    DataInputStream archivoRead;
                    
                    archivoRead = new DataInputStream(new FileInputStream(fileSemestre));
                    
                    while(archivoRead.available() > 0){
                        int readID = archivoRead.readInt();
                        String readPeriodo = archivoRead.readUTF();
                        String readInicioSem = archivoRead.readUTF();
                        String readFinSem = archivoRead.readUTF();
                        String readSeparador = archivoRead.readUTF();
                        
                        Semestre temp = new Semestre();
                        
                        temp.setId(readID);
                        temp.setPeriodo(readPeriodo);
                        temp.setFechaI(readInicioSem);
                        temp.setFechaF(readFinSem);
                        
                        semestres.add(temp);
                    }
                    
                } catch (FileNotFoundException ex) {
                    //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "No hay semestres guardados.");
            }
        
        String semToDelete = JOptionPane.showInputDialog(this, "Ingrese el periodo que desea eliminar:");
        
        String patronPeriodo = "^[1|2]{1}[0|9]{1}[0-9]{2}[A|B]{1}$";
        Pattern patToCheckPeriodo = Pattern.compile(patronPeriodo);
        
        Matcher regexMatcherPeriodo = patToCheckPeriodo.matcher(semToDelete);
        boolean found = false;
        
        if(regexMatcherPeriodo.matches()){
            for(int i=0; i<semestres.size() && !found; i++){
                if(semestres.get(i).getPeriodo().equals(semToDelete)){
                    try {
                        semestres.remove(i);
                        
                        
                        fileSemestre.delete();
                        
                        DataOutputStream archivoWrite;
                        
                        archivoWrite = new DataOutputStream(new FileOutputStream(fileSemestre));
                        
                        for(int j=0; j<semestres.size(); j++){
                            archivoWrite.writeInt(semestres.get(i).getId());
                            archivoWrite.writeUTF(semestres.get(i).getPeriodo());
                            archivoWrite.writeUTF(semestres.get(i).getFechaI());
                            archivoWrite.writeUTF(semestres.get(i).getFechaF());
                            archivoWrite.writeUTF("#");
                        }
                        archivoWrite.close();
                        
                        semestres.clear();
                        JOptionPane.showMessageDialog(this, "El semestre fue eliminado.");
                        found = true;
                        
                    } catch (FileNotFoundException ex) {
                        //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
            }
            if(!found){
                JOptionPane.showMessageDialog(this, "El semestre no fue encontrado.");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "El periodo no cumple con el formato establecido.");
        }
        
    }//GEN-LAST:event_btnEliminarSActionPerformed

    private void btnCambiosSemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiosSemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCambiosSemActionPerformed

    private void btnGuardarGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarGActionPerformed
        grupos.clear();
        
        String patronNombreG = "^[1-9]{1}-[A-Z]{1}$";
        Pattern patToCheckNombreG = Pattern.compile(patronNombreG);
        Matcher regexMatcherNombreG = patToCheckNombreG.matcher(txtNombreGrupo.getText());
        
        if(regexMatcherNombreG.matches()){
            
            Grupos grupo = new Grupos();
            
            grupo.setNombre(txtNombreGrupo.getText());
            grupo.setIdMaestro(comboIDMaestro.getSelectedItem().toString());
            grupo.setIdMateria(comboIDMateria.getSelectedItem().toString());
            
            
            if(fileGrupo.length() != 0){
                try {
                    DataInputStream archivoRead;
                    
                    archivoRead = new DataInputStream(new FileInputStream(fileGrupo));
                    
                    while(archivoRead.available() > 0){
                        int readID = archivoRead.readInt();
                        String readNombre = archivoRead.readUTF();
                        String readMaestro = archivoRead.readUTF();
                        String readMateria = archivoRead.readUTF();
                        String readSeparador = archivoRead.readUTF();
                        
                        Grupos temp = new Grupos();
                        
                        temp.setId(readID);
                        temp.setNombre(readNombre);
                        temp.setIdMaestro(readMaestro);
                        temp.setIdMateria(readMateria);
                        
                        grupos.add(temp);
                    }
                    int temp = grupos.size() - 1;
                    grupo.setId(grupos.get(temp).getId() + 1);
                } catch (FileNotFoundException ex) {
                    //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                grupo.setId(1);
            }
            
            grupos.add(grupo);
            
            fileGrupo.delete();
            
            DataOutputStream archivoWrite;
            
            try {
                archivoWrite = new DataOutputStream(new FileOutputStream(fileGrupo));
            
                for(int i=0; i<grupos.size() ; i++){
                    archivoWrite.writeInt(grupos.get(i).getId());
                    archivoWrite.writeUTF(grupos.get(i).getNombre());
                    archivoWrite.writeUTF(grupos.get(i).getIdMaestro());
                    archivoWrite.writeUTF(grupos.get(i).getIdMateria());

                    archivoWrite.writeUTF("#");

                }

                archivoWrite.close();

                lblIDGrupo.setText(String.valueOf(grupo.getId()));

                grupos.clear();

                JOptionPane.showMessageDialog(this, "El grupo fue agregado exitosamente.");


            } catch (FileNotFoundException ex) {
                //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                //Logger.getLogger(panelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        else{
            JOptionPane.showMessageDialog(this, "El nombre no cumple con el formato requerido.");
        }
        
    }//GEN-LAST:event_btnGuardarGActionPerformed

    private void txtNombreGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreGrupoActionPerformed
    }//GEN-LAST:event_txtNombreGrupoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EditarM;
    private javax.swing.JButton EliminarM;
    private javax.swing.JLabel blbGrupoA;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarC;
    private javax.swing.JButton btnBuscarG;
    private javax.swing.JButton btnBuscarH;
    private javax.swing.JButton btnCambiosSem;
    private javax.swing.JButton btnCancelUsEdit;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarEdicion;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditarA;
    private javax.swing.JButton btnEditarC;
    private javax.swing.JButton btnEditarG;
    private javax.swing.JButton btnEditarH;
    private javax.swing.JButton btnEditarMa;
    private javax.swing.JButton btnEditarS;
    private javax.swing.JButton btnEliminarA;
    private javax.swing.JButton btnEliminarC;
    private javax.swing.JButton btnEliminarG;
    private javax.swing.JButton btnEliminarH;
    private javax.swing.JButton btnEliminarMa;
    private javax.swing.JButton btnEliminarS;
    private javax.swing.JButton btnEliminarUser;
    private javax.swing.JButton btnGuardarA;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.JButton btnGuardarG;
    private javax.swing.JButton btnGuardarH;
    private javax.swing.JButton btnGuardarMa;
    private javax.swing.JButton btnGuardarMateria;
    private javax.swing.JButton btnGuardarS;
    private javax.swing.JButton btnGuardarUsuario;
    private com.toedter.calendar.JDateChooser chooserFechaC;
    private com.toedter.calendar.JDateChooser chooserFinSem;
    private com.toedter.calendar.JDateChooser chooserInicioSem;
    private javax.swing.JComboBox<String> comboAcademia;
    private javax.swing.JComboBox<String> comboAreaC;
    private javax.swing.JComboBox<String> comboAño1;
    private javax.swing.JComboBox<String> comboAño2;
    private javax.swing.JComboBox<String> comboCarreraA;
    private javax.swing.JComboBox<String> comboDia1;
    private javax.swing.JComboBox<String> comboDia2;
    private javax.swing.JComboBox<String> comboGradoAcad;
    private javax.swing.JComboBox<String> comboGrupo;
    private javax.swing.JComboBox<String> comboGrupoAcad;
    private javax.swing.JComboBox<String> comboHoraF;
    private javax.swing.JComboBox<String> comboHoraI;
    private javax.swing.JComboBox<String> comboIDCarrera;
    private javax.swing.JComboBox<String> comboIDGrupoH;
    private javax.swing.JComboBox<String> comboIDMaestro;
    private javax.swing.JComboBox<String> comboIDMateria;
    private javax.swing.JComboBox<String> comboMes1;
    private javax.swing.JComboBox<String> comboMes2;
    private javax.swing.JComboBox<String> comboPerfil;
    private javax.swing.JComboBox<String> comboPeriodo;
    private javax.swing.JComboBox<String> comboPeriodoS;
    private javax.swing.JComboBox<String> comboSemestre;
    private javax.swing.JLabel comboSemestreA;
    private javax.swing.JComboBox<String> comboSemestreC;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JLabel lblAM;
    private javax.swing.JLabel lblAP;
    private javax.swing.JLabel lblAreaC;
    private javax.swing.JLabel lblAño1;
    private javax.swing.JLabel lblAño2;
    private javax.swing.JLabel lblCarreraA;
    private javax.swing.JLabel lblCiudadA;
    private javax.swing.JLabel lblDia1;
    private javax.swing.JLabel lblDia2;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFNacimiento;
    private javax.swing.JLabel lblFechaA;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblIDA;
    private javax.swing.JLabel lblIDCarrera;
    private javax.swing.JLabel lblIDGrupo;
    private javax.swing.JLabel lblIDMateria;
    private javax.swing.JLabel lblMateriaID;
    private javax.swing.JLabel lblMes1;
    private javax.swing.JLabel lblMes2;
    private javax.swing.JLabel lblNombreA;
    private javax.swing.JLabel lblNombreC;
    private javax.swing.JLabel lblPeriodoA;
    private javax.swing.JLabel lblSemID;
    private javax.swing.JLabel lblSemestreC;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblUsuarioID;
    private javax.swing.JPanel panelAlumnos;
    private javax.swing.JTabbedPane panelTabs;
    private javax.swing.JTextField txtAM;
    private javax.swing.JTextField txtAMaterno;
    private javax.swing.JTextField txtAP;
    private javax.swing.JTextField txtAPaterno;
    private javax.swing.JTextField txtAreaC;
    private javax.swing.JTextField txtBuscarA;
    private javax.swing.JTextField txtBuscarC;
    private javax.swing.JTextField txtBuscarG;
    private javax.swing.JTextField txtBuscarH;
    private javax.swing.JTextField txtBuscarS;
    private javax.swing.JTextField txtCiudadA;
    private javax.swing.JTextField txtCreditos;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccionMa;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFechaF;
    private javax.swing.JTextField txtFechaI;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreA;
    private javax.swing.JTextField txtNombreC;
    private javax.swing.JTextField txtNombreGrupo;
    private javax.swing.JTextField txtNombreMa;
    private javax.swing.JTextField txtNombreMaestro;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtNumeroH;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPasswordConfirm;
    private javax.swing.JTextField txtPeriodoS;
    private javax.swing.JTextField txtSemestreC;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoM;
    // End of variables declaration//GEN-END:variables
}

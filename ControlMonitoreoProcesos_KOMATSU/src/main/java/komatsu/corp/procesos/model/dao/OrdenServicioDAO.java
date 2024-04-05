/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komatsu.corp.procesos.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import komatsu.corp.procesos.config.Conexion;
import komatsu.corp.procesos.model.dto.OrdenServicioDTO;
import komatsu.corp.procesos.model.entities.OrdenServicio;

/**
 *
 * @author edson
 */
public class OrdenServicioDAO {
    
    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    
    
    public List listarToDo(){
        ArrayList<OrdenServicio> list=new ArrayList();
        String sql="SELECT `id`, `nombreOS`, `fecha`, `procesoActual`, `estado`, `idUsuario` FROM `ordenservicio` WHERE `procesoActual` = 'TO DO'";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                OrdenServicio os=new OrdenServicio();
                os.setId(rs.getInt("id"));
                os.setNombreOS(rs.getString("nombreOS"));
                os.setFecha(rs.getString("fecha"));
                os.setProcesoActual(rs.getString("procesoActual"));
                os.setEstado(rs.getString("estado"));
                os.setIdUsuario(rs.getInt("idUsuario"));
                list.add(os);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List listarInProcess(){
        ArrayList<OrdenServicio> list=new ArrayList();
        String sql="SELECT `id`, `nombreOS`, `fecha`, `procesoActual`, `estado`, `idUsuario` FROM `ordenservicio` WHERE `procesoActual` = 'IN PROCESS'";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                OrdenServicio os=new OrdenServicio();
                os.setId(rs.getInt("id"));
                os.setNombreOS(rs.getString("nombreOS"));
                os.setFecha(rs.getString("fecha"));
                os.setProcesoActual(rs.getString("procesoActual"));
                os.setEstado(rs.getString("estado"));
                os.setIdUsuario(rs.getInt("idUsuario"));
                list.add(os);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List listarDone(){
        ArrayList<OrdenServicio> list=new ArrayList();
        String sql="SELECT `id`, `nombreOS`, `fecha`, `procesoActual`, `estado`, `idUsuario` FROM `ordenservicio` WHERE `procesoActual` = 'DONE'";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                OrdenServicio os=new OrdenServicio();
                os.setId(rs.getInt("id"));
                os.setNombreOS(rs.getString("nombreOS"));
                os.setFecha(rs.getString("fecha"));
                os.setProcesoActual(rs.getString("procesoActual"));
                os.setEstado(rs.getString("estado"));
                os.setIdUsuario(rs.getInt("idUsuario"));
                list.add(os);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    //--MODIFICAR OrdenServicio
    public boolean editarOrdenServicioEmpezar(int id){
        String sql = "UPDATE `ordenservicio` SET `procesoActual`='IN PROCESS' WHERE `id`="+id;
        
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    public boolean editarOrdenServicioFinalizar(int id){
        String sql = "UPDATE `ordenservicio` SET `procesoActual`='DONE' WHERE `id`="+id;
        
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    /*public boolean registrarOrdenServicio(OrdenServicioDTO u) {
        int usuarioID = -1; // Valor predeterminado en caso de error

        try {
            // Realiza la inserción en la tabla de usuarios
            String insertUsuarioSQL = "INSERT INTO `persona`(`nombre`, `apellidoPaterno`, `apellidoMaterno`, `fechaNacimiento`, `dni`, `correo`) VALUES (?, ?, ?, ? ,?, ?)";
            
                conn = conexion.getConexion();
                ps = conn.prepareStatement(insertUsuarioSQL, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, u.getNombre());
                ps.setString(2, u.getApellido_paterno());
                ps.setString(3, u.getApellido_materno());
                ps.setString(4, u.getFechaNacimiento());
                ps.setInt(5, u.getDni());
                ps.setString(6, u.getCorreo());
                int affectedRows = ps.executeUpdate();

                if (affectedRows == 0) {
                    // La inserción falló
                    return false;
                }
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    usuarioID = rs.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID generado.");
                }
                // Recupera el ID autoincrementable generado
                

            // Después de obtener el usuarioID, puedes insertar usuario
            String insertDetallesSQL = "INSERT INTO `usuario`(`nombreUsuario`, `contrasenia`, `idPersona`, `id_rol`, `estado`) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertDetallesSQL)) {
                stmt.setString(1, u.getUsername());
                stmt.setString(2, u.getContrasenia());
                stmt.setInt(3, usuarioID);
                stmt.setInt(4, u.getId_rol());
                stmt.setInt(5, 1);
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        return true;
    }*/
}

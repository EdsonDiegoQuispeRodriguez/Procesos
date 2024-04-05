/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komatsu.corp.procesos.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import komatsu.corp.procesos.config.Conexion;
import komatsu.corp.procesos.model.dto.ProcesoDTO;

/**
 *
 * @author edson
 */
public class ProcesoDAO {
    
    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public boolean registrarProceso(ProcesoDTO p) {
        

        try {
            conn = conexion.getConexion();
            String insertDetallesSQL = "INSERT INTO `proceso`(`nombreProceso`, `tipo`, `motivo`, `fecha`, `estado`, `idOS`) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertDetallesSQL)) {
                stmt.setString(1, p.getNombreProceso());
                stmt.setString(2, p.getTipo());
                stmt.setString(3, p.getMotivo());
                stmt.setString(4, p.getFecha());
                stmt.setString(5, p.getEstado());
                stmt.setInt(6, p.getIdOS());
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        return true;
    }
    public boolean registrarPareNoPlaneado(ProcesoDTO p) {
        

        try {
            conn = conexion.getConexion();
            String insertDetallesSQL = "INSERT INTO `proceso`(`nombreProceso`, `tipo`, `motivo`, `fecha`, `estado`, `idOS`) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertDetallesSQL)) {
                stmt.setString(1, p.getNombreProceso());
                stmt.setString(2, p.getTipo());
                stmt.setString(3, p.getMotivo());
                stmt.setString(4, p.getFecha());
                stmt.setString(5, p.getEstado());
                stmt.setInt(6, p.getIdOS());
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        return true;
    }
    public boolean registrarReprogramacion(ProcesoDTO p) {
        

        try {
            conn = conexion.getConexion();
            String insertDetallesSQL = "INSERT INTO `proceso`(`nombreProceso`, `tipo`, `motivo`, `fecha`, `estado`, `idOS`) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertDetallesSQL)) {
                stmt.setString(1, p.getNombreProceso());
                stmt.setString(2, p.getTipo());
                stmt.setString(3, p.getMotivo());
                stmt.setString(4, p.getFecha());
                stmt.setString(5, p.getEstado());
                stmt.setInt(6, p.getIdOS());
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        return true;
    }
    
    
}

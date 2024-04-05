/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komatsu.corp.procesos.model.dto;

/**
 *
 * @author edson
 */
public class OrdenServicioDTO {
    private String nombreOS;
    private String fecha;
    private String procesoActual;
    private String estado;
    private int idUsuario;

    public OrdenServicioDTO() {
    }

    
    
    public String getNombreOS() {
        return nombreOS;
    }

    public void setNombreOS(String nombreOS) {
        this.nombreOS = nombreOS;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getProcesoActual() {
        return procesoActual;
    }

    public void setProcesoActual(String procesoActual) {
        this.procesoActual = procesoActual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}

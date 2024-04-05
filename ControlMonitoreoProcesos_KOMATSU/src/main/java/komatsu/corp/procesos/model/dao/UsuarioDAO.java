    
package komatsu.corp.procesos.model.dao;

import komatsu.corp.procesos.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import komatsu.corp.procesos.model.entities.*;
/*
import modelo.Usuario;
import modelo.Tarjeta;
import modeloDTO.HorarioDTO;
import modeloDTO.RutaDTO;
import modeloDTO.UnidadTransporteDTO;
import modeloDTO.UsuarioDTO;
import modeloDTO.UsuarioTransportistaDTO;
import modeloDTO.ViajeDTO;
*/

public class UsuarioDAO {
    
    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    Usuario usu=new Usuario();
    
    
    public List listar(){
        ArrayList<Usuario> list=new ArrayList();
        String sql="SELECT * FROM `usuario`";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Usuario u=new Usuario();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("nombreUsuario"));
                u.setContrasenia(rs.getString("contrasenia"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    /*
    Usuario usu=new Usuario();
    UsuarioDTO usuDTO = new UsuarioDTO();
    UsuarioTransportistaDTO usutransportDTO = new UsuarioTransportistaDTO();
    ViajeDTO viaje = new ViajeDTO();
    
    public Usuario listarUsername(String username){
        
        String sql="SELECT * FROM `usuario` where nombreUsuario = '"+ username + "'";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                
                usu.setId(rs.getInt("id"));
                usu.setUsername(rs.getString("nombreUsuario"));
                usu.setContrasenia(rs.getString("contrasenia"));
                usu.setId_persona(rs.getInt("idPersona"));
                usu.setId_rol(rs.getInt("id_rol"));
                
                
            }
            System.out.println("nombre"+usu.getUsername());
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return usu;
    }
    
    
    public List listar(){
        ArrayList<Usuario> list=new ArrayList();
        String sql="SELECT * FROM `usuario`";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Usuario u=new Usuario();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("nombreUsuario"));
                u.setContrasenia(rs.getString("contrasenia"));
                u.setId_persona(rs.getInt("idPersona"));
                u.setId_rol(rs.getInt("id_rol"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    public List listarCompleto(){
        ArrayList<UsuarioDTO> list=new ArrayList();
        String sql="SELECT * FROM `usuario` U INNER JOIN `persona` P ON P.id = U.idPersona where U.estado = 1 and U.id_rol = 3";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                UsuarioDTO u=new UsuarioDTO();
                //Usuario
                u.setIdUsuario(rs.getInt("U.id"));
                u.setUsername(rs.getString("U.nombreUsuario"));
                u.setContrasenia(rs.getString("U.contrasenia"));
                u.setIdPersona(rs.getInt("U.idPersona"));
                u.setId_rol(rs.getInt("U.id_rol"));
                //Persona
                u.setNombre(rs.getString("P.nombre"));
                u.setApellido_paterno(rs.getString("P.apellidoPaterno"));
                u.setApellido_materno(rs.getString("P.apellidoMaterno"));
                u.setFechaNacimiento(rs.getString("P.fechaNacimiento"));
                u.setDni(rs.getInt("P.dni"));
                u.setCorreo(rs.getString("P.correo"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List listarCompletoViaje(){
        ArrayList<ViajeDTO> list=new ArrayList();
        String sql="SELECT V.id, R.color, R.nombre_ruta, UT.placa, CONCAT(P.nombre,' ',P.apellidoPaterno,' ',P.apellidoMaterno) AS 'conductor', V.tarifa, H.hora_salida, H.hora_retorno, V.pago FROM `unidad_transporte` UT INNER JOIN `viaje` V\n" +
                    "ON UT.id = V.id_transporte\n" +
                    "INNER JOIN `ruta` R \n" +
                    "ON R.id = V.id_ruta\n" +
                    "INNER JOIN `horario_viaje` HV\n" +
                    "ON HV.id_viaje = V.id\n" +
                    "INNER JOIN `horario` H \n" +
                    "ON H.id = HV.id_horario\n" +
                    "INNER JOIN `usuario` U\n" +
                    "ON V.id_transportista = U.id\n" +
                    "INNER JOIN `persona` P\n" +
                    "ON P.id = U.idPersona where V.estado = 1";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                ViajeDTO u=new ViajeDTO();
                //Usuario
                u.setIdViaje(rs.getInt("V.id"));
                u.setColor(rs.getString("R.color"));
                u.setNombre_ruta(rs.getString("R.nombre_ruta"));
                u.setPlaca(rs.getString("UT.placa"));
                u.setNombre_transportista(rs.getString("conductor"));
                u.setTarifa(rs.getDouble("V.tarifa"));
                u.setHora_salida(rs.getString("H.hora_salida"));
                u.setHora_retorno(rs.getString("H.hora_retorno"));
                u.setPago(rs.getString("V.pago"));        
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List listarCompletoReporte1(int idUsu, String fechaIni, String fechaFin){
        ArrayList<ViajeDTO> list=new ArrayList();
        String sql="SELECT V.id, R.color, UT.placa, V.tarifa, VU.fecha_registro, VU.hora_registro, CONCAT(P.nombre,' ',P.apellidoPaterno,' ',P.apellidoMaterno) AS 'Persona',CONCAT(P2.nombre,' ',P2.apellidoPaterno,' ',P2.apellidoMaterno) AS 'Conductor', VU.numero_tarjeta\n" +
                    "FROM `viaje_usuario` VU \n" +
                    "INNER JOIN `viaje` V ON VU.id_viaje = V.id\n" +
                    "INNER JOIN `ruta` R ON R.id  = V.id_ruta\n" +
                    "INNER JOIN `unidad_transporte` UT ON UT.id = V.id_transporte\n" +
                    "INNER JOIN `usuario` U ON VU.id_usuario = U.id \n" +
                    "INNER JOIN `persona` P ON U.idPersona = P.id\n" +
                    "INNER JOIN `usuario` U2 ON V.id_transportista = U2.id\n" +
                    "INNER JOIN `persona` P2 ON U2.idPersona = P2.id\n" +
                    "WHERE U.id = "+idUsu+" and (VU.fecha_registro BETWEEN '"+fechaIni+"' and '"+fechaFin+"' )";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                ViajeDTO u=new ViajeDTO();
                //Usuario
                u.setIdViaje(rs.getInt("V.id"));
                u.setColor(rs.getString("R.color"));
                u.setPlaca(rs.getString("UT.placa"));
                u.setNombre_transportista(rs.getString("conductor"));
                u.setTarifa(rs.getDouble("V.tarifa"));
                u.setFecha_registro(rs.getString("VU.fecha_registro"));
                u.setHora_registro(rs.getString("VU.hora_registro"));
                u.setPersona(rs.getString("Persona"));
                u.setTarjeta(rs.getString("VU.numero_tarjeta"));
                list.add(u);
                System.out.println("Usuario: "+ u.getPersona());
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List listarCompletoReporte2(String fechaIni, String fechaFin){
        ArrayList<ViajeDTO> list=new ArrayList();
        String sql="SELECT R.color, SUM(vu.monto_cobrado) AS 'Total' , COUNT(vu.id_usuario) AS 'Cantidad de usuarios'\n" +
                    "FROM `viaje_usuario` VU \n" +
                    "INNER JOIN `viaje` V ON VU.id_viaje = V.id\n" +
                    "INNER JOIN `ruta` R ON R.id  = V.id_ruta\n" +
                    
                    "WHERE VU.fecha_registro BETWEEN '"+fechaIni+"' and '"+fechaFin+"'"+ "GROUP BY R.color" ;
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                ViajeDTO u=new ViajeDTO();
                //Usuario
                u.setColor(rs.getString("R.color"));
                u.setTarifa(rs.getDouble("Total"));
                u.setCantidad(rs.getInt("Cantidad de usuarios"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List listarCompletoReporte3(int color, String fechaIni, String fechaFin){
        ArrayList<ViajeDTO> list=new ArrayList();
        String sql="SELECT R.color, SUM(vu.monto_cobrado) AS 'Total' , COUNT(vu.id_usuario) AS 'Cantidad de usuarios'\n" +
                    "FROM `viaje_usuario` VU \n" +
                    "INNER JOIN `viaje` V ON VU.id_viaje = V.id\n" +
                    "INNER JOIN `ruta` R ON R.id  = V.id_ruta\n" +
                    
                    "WHERE R.id = "+color+" AND VU.fecha_registro BETWEEN '"+fechaIni+"' and '"+fechaFin+"'"+ "GROUP BY R.color" ;
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                ViajeDTO u=new ViajeDTO();
                //Usuario
                u.setColor(rs.getString("R.color"));
                u.setTarifa(rs.getDouble("Total"));
                u.setCantidad(rs.getInt("Cantidad de usuarios"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List listarCompletoReporte4(int conductor, String fechaIni, String fechaFin){
        ArrayList<ViajeDTO> list=new ArrayList();
        String sql="SELECT R.color, PT.pago, PT.fecha, CONCAT(P.nombre,' ',P.apellidoPaterno,' ',P.apellidoMaterno) AS 'Conductor' FROM `pago_transportistas` PT \n" +
                    "INNER JOIN `viaje` V ON PT.idViaje = V.id\n" +
                    "INNER JOIN `usuario` U ON V.id_transportista = U.id\n" +
                    "INNER JOIN `persona` P ON U.idPersona = P.id\n" +
                    "INNER JOIN `ruta` R ON V.id_ruta = R.id\n" +
                    "WHERE U.id = "+conductor +" AND PT.fecha BETWEEN '"+fechaIni+"' and '"+fechaFin+"'";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                ViajeDTO u=new ViajeDTO();
                //Usuario
                u.setColor(rs.getString("R.color"));
                u.setTarifa(rs.getDouble("PT.pago"));
                u.setFecha_registro(rs.getString("PT.fecha"));
                u.setNombre_transportista(rs.getString("Conductor"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List listarCompletoViajeReactivar(){
        ArrayList<ViajeDTO> list=new ArrayList();
        String sql="SELECT V.id, R.color, R.nombre_ruta, UT.placa, CONCAT(P.nombre,' ',P.apellidoPaterno,' ',P.apellidoMaterno) AS 'conductor', V.tarifa, H.hora_salida, H.hora_retorno FROM `unidad_transporte` UT INNER JOIN `viaje` V\n" +
                    "ON UT.id = V.id_transporte\n" +
                    "INNER JOIN `ruta` R \n" +
                    "ON R.id = V.id_ruta\n" +
                    "INNER JOIN `horario_viaje` HV\n" +
                    "ON HV.id_viaje = V.id\n" +
                    "INNER JOIN `horario` H \n" +
                    "ON H.id = HV.id_horario\n" +
                    "INNER JOIN `usuario` U\n" +
                    "ON V.id_transportista = U.id\n" +
                    "INNER JOIN `persona` P\n" +
                    "ON P.id = U.idPersona where V.estado = 0";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                ViajeDTO u=new ViajeDTO();
                //Usuario
                u.setIdViaje(rs.getInt("V.id"));
                u.setColor(rs.getString("R.color"));
                u.setNombre_ruta(rs.getString("R.nombre_ruta"));
                u.setPlaca(rs.getString("UT.placa"));
                u.setNombre_transportista(rs.getString("conductor"));
                u.setTarifa(rs.getDouble("V.tarifa"));
                u.setHora_salida(rs.getString("H.hora_salida"));
                u.setHora_retorno(rs.getString("H.hora_retorno"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List listarCompletoReactivar(){
        ArrayList<UsuarioDTO> list=new ArrayList();
        String sql="SELECT * FROM `usuario` U INNER JOIN `persona` P ON P.id = U.idPersona where U.estado = 0 and U.id_rol = 3";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                UsuarioDTO u=new UsuarioDTO();
                //Usuario
                u.setIdUsuario(rs.getInt("U.id"));
                u.setUsername(rs.getString("U.nombreUsuario"));
                u.setContrasenia(rs.getString("U.contrasenia"));
                u.setIdPersona(rs.getInt("U.idPersona"));
                u.setId_rol(rs.getInt("U.id_rol"));
                //Persona
                u.setNombre(rs.getString("P.nombre"));
                u.setApellido_paterno(rs.getString("P.apellidoPaterno"));
                u.setApellido_materno(rs.getString("P.apellidoMaterno"));
                u.setFechaNacimiento(rs.getString("P.fechaNacimiento"));
                u.setDni(rs.getInt("P.dni"));
                u.setCorreo(rs.getString("P.correo"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    public List listarCompletoTransportistas(){
        ArrayList<UsuarioDTO> list=new ArrayList();
        String sql="SELECT * FROM `usuario` U INNER JOIN `persona` P ON P.id = U.idPersona where U.estado = 1 and U.id_rol = 2";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                UsuarioDTO u=new UsuarioDTO();
                //Usuario
                u.setIdUsuario(rs.getInt("U.id"));
                u.setUsername(rs.getString("U.nombreUsuario"));
                u.setContrasenia(rs.getString("U.contrasenia"));
                u.setIdPersona(rs.getInt("U.idPersona"));
                u.setId_rol(rs.getInt("U.id_rol"));
                //Persona
                u.setNombre(rs.getString("P.nombre"));
                u.setApellido_paterno(rs.getString("P.apellidoPaterno"));
                u.setApellido_materno(rs.getString("P.apellidoMaterno"));
                u.setFechaNacimiento(rs.getString("P.fechaNacimiento"));
                u.setDni(rs.getInt("P.dni"));
                u.setCorreo(rs.getString("P.correo"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List listarCompletoTransportistasReactivar(){
        ArrayList<UsuarioDTO> list=new ArrayList();
        String sql="SELECT * FROM `usuario` U INNER JOIN `persona` P ON P.id = U.idPersona where U.estado = 0 and U.id_rol = 2";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                UsuarioDTO u=new UsuarioDTO();
                //Usuario
                u.setIdUsuario(rs.getInt("U.id"));
                u.setUsername(rs.getString("U.nombreUsuario"));
                u.setContrasenia(rs.getString("U.contrasenia"));
                u.setIdPersona(rs.getInt("U.idPersona"));
                u.setId_rol(rs.getInt("U.id_rol"));
                //Persona
                u.setNombre(rs.getString("P.nombre"));
                u.setApellido_paterno(rs.getString("P.apellidoPaterno"));
                u.setApellido_materno(rs.getString("P.apellidoMaterno"));
                u.setFechaNacimiento(rs.getString("P.fechaNacimiento"));
                u.setDni(rs.getInt("P.dni"));
                u.setCorreo(rs.getString("P.correo"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    public List listarCompletoUnidadTransportistas(){
        ArrayList<UnidadTransporteDTO> list=new ArrayList();
        String sql="SELECT * FROM `unidad_transporte` WHERE estado = 1";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                UnidadTransporteDTO u=new UnidadTransporteDTO();
                //Usuario
                u.setId(rs.getInt("id"));
                u.setPlaca(rs.getString("placa"));
                u.setMarca(rs.getString("marca"));
                u.setModelo(rs.getString("modelo"));
                u.setEstado(rs.getInt("estado"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List listarCompletoRutas(){
        ArrayList<RutaDTO> list=new ArrayList();
        String sql="SELECT * FROM `ruta` WHERE estado = 1";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                RutaDTO u=new RutaDTO();
                //Ruta
                u.setId(rs.getInt("id"));
                u.setColor(rs.getString("color"));
                u.setNombre_ruta(rs.getString("nombre_ruta"));
                u.setOrigen(rs.getString("origen"));
                u.setDestino(rs.getString("destino"));
                u.setEstado(rs.getInt("estado"));
                u.setId_distrito(rs.getInt("id_distrito"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List listarCompletoHorarios(){
        ArrayList<HorarioDTO> list=new ArrayList();
        String sql="SELECT * FROM `horario` WHERE estado <> 1";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                HorarioDTO u=new HorarioDTO();
                //Ruta
                u.setId(rs.getInt("id"));
                u.setHora_salida(rs.getString("hora_salida"));
                u.setHora_retorno(rs.getString("hora_retorno"));
                u.setEstado(rs.getInt("estado"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    
    public List listarCompletoUnidadTransportistasReactivar(){
        ArrayList<UnidadTransporteDTO> list=new ArrayList();
        String sql="SELECT * FROM `unidad_transporte` WHERE estado = 0";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                UnidadTransporteDTO u=new UnidadTransporteDTO();
                //Usuario
                u.setId(rs.getInt("id"));
                u.setPlaca(rs.getString("placa"));
                u.setMarca(rs.getString("marca"));
                u.setModelo(rs.getString("modelo"));
                u.setEstado(rs.getInt("estado"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    public List listarCompletoRutasReactivar(){
        ArrayList<RutaDTO> list=new ArrayList();
        String sql="SELECT * FROM `ruta` WHERE estado = 0";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                RutaDTO u=new RutaDTO();
                //Ruta
                u.setId(rs.getInt("id"));
                u.setColor(rs.getString("color"));
                u.setNombre_ruta(rs.getString("nombre_ruta"));
                u.setOrigen(rs.getString("origen"));
                u.setDestino(rs.getString("destino"));
                u.setEstado(rs.getInt("estado"));
                u.setId_distrito(rs.getInt("id_distrito"));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    public Usuario listarId(int id){
        
        String sql="SELECT * FROM `usuario` where id ="+id;
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                
                usu.setId(rs.getInt("id"));
                usu.setUsername(rs.getString("nombreUsuario"));
                usu.setContrasenia(rs.getString("contrasenia"));
                usu.setId_persona(rs.getInt("idPersona"));
                usu.setId_rol(rs.getInt("id_rol"));
                
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return usu;
    }
    
    public UsuarioDTO listarId2(int idUsu, int idPer){
        
        String sql="SELECT * FROM `usuario` U INNER JOIN `persona` P ON P.id = U.idPersona WHERE U.id = "+idUsu+" AND P.id = "+idPer;
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                
                usuDTO.setIdUsuario(rs.getInt("U.id"));
                usuDTO.setUsername(rs.getString("U.nombreUsuario"));
                usuDTO.setContrasenia(rs.getString("U.contrasenia"));
                usuDTO.setIdPersona(rs.getInt("P.id"));
                usuDTO.setId_rol(rs.getInt("U.id_rol"));
                usuDTO.setNombre(rs.getString("P.nombre"));
                usuDTO.setApellido_paterno(rs.getString("P.apellidoPaterno"));
                usuDTO.setApellido_materno(rs.getString("P.apellidoMaterno"));
                usuDTO.setFechaNacimiento(rs.getString("P.fechaNacimiento"));
                usuDTO.setDni(rs.getInt("P.dni"));
                usuDTO.setCorreo(rs.getString("P.correo"));
                
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return usuDTO;
    }
    public UsuarioDTO listarId3(int idUsu){
        
        String sql="SELECT * FROM `usuario` U INNER JOIN `persona` P ON P.id = U.idPersona WHERE U.id = "+idUsu;
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                
                usuDTO.setIdUsuario(rs.getInt("U.id"));
                usuDTO.setUsername(rs.getString("U.nombreUsuario"));
                usuDTO.setContrasenia(rs.getString("U.contrasenia"));
                usuDTO.setIdPersona(rs.getInt("P.id"));
                usuDTO.setId_rol(rs.getInt("U.id_rol"));
                usuDTO.setNombre(rs.getString("P.nombre"));
                usuDTO.setApellido_paterno(rs.getString("P.apellidoPaterno"));
                usuDTO.setApellido_materno(rs.getString("P.apellidoMaterno"));
                usuDTO.setFechaNacimiento(rs.getString("P.fechaNacimiento"));
                usuDTO.setDni(rs.getInt("P.dni"));
                usuDTO.setCorreo(rs.getString("P.correo"));
                
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return usuDTO;
    }
    //Listar Viajes para editar
    public ViajeDTO listarIdViaje(int idViaje){
        ViajeDTO v = new ViajeDTO();
        String sql="SELECT * FROM `viaje` V INNER JOIN `horario_viaje` HV ON V.id = HV.id_viaje WHERE V.id = "+idViaje;
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                
                v.setIdViaje(rs.getInt("V.id"));
                v.setTarifa(rs.getDouble("V.tarifa"));
                v.setId_ruta(rs.getInt("V.id_ruta"));
                v.setId_transporte(rs.getInt("V.id_transporte"));
                v.setId_transportista(rs.getInt("V.id_transportista"));
                v.setHorario(rs.getInt("HV.id_horario"));
                v.setIdHorarioViaje(rs.getInt("HV.id"));
                
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return v;
    }
    //Listar usuario transportista
    public UsuarioTransportistaDTO listarIdTranport(int idUsu, int idPer){
        
        String sql="SELECT * FROM `usuario` U INNER JOIN `persona` P ON P.id = U.idPersona WHERE U.id = "+idUsu+" AND P.id = "+idPer;
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                
                usutransportDTO.setIdUsuario(rs.getInt("U.id"));
                usutransportDTO.setUsername(rs.getString("U.nombreUsuario"));
                usutransportDTO.setContrasenia(rs.getString("U.contrasenia"));
                usutransportDTO.setIdPersona(rs.getInt("P.id"));
                usutransportDTO.setId_rol(rs.getInt("U.id_rol"));
                usutransportDTO.setNombre(rs.getString("P.nombre"));
                usutransportDTO.setApellido_paterno(rs.getString("P.apellidoPaterno"));
                usutransportDTO.setApellido_materno(rs.getString("P.apellidoMaterno"));
                usutransportDTO.setFechaNacimiento(rs.getString("P.fechaNacimiento"));
                usutransportDTO.setDni(rs.getInt("P.dni"));
                usutransportDTO.setCorreo(rs.getString("P.correo"));
                
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return usutransportDTO;
    }
    //Listar unidad transporte
    public UnidadTransporteDTO listarIdUnidadTranport(int idUni){
        UnidadTransporteDTO u = new UnidadTransporteDTO();
        String sql="SELECT * FROM `unidad_transporte` WHERE id="+idUni;
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                
                u.setId(rs.getInt("id"));
                u.setPlaca(rs.getString("placa"));
                u.setMarca(rs.getString("marca"));
                u.setModelo(rs.getString("modelo"));
                u.setEstado(rs.getInt("estado"));
                
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return u;
    }
    //Listar unidad transporte
    public RutaDTO listarIdRuta(int idUni){
        RutaDTO u = new RutaDTO();
        String sql="SELECT * FROM `ruta` WHERE id="+idUni;
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                
                u.setId(rs.getInt("id"));
                u.setColor(rs.getString("color"));
                u.setNombre_ruta(rs.getString("nombre_ruta"));
                u.setOrigen(rs.getString("origen"));
                u.setDestino(rs.getString("destino"));
                u.setEstado(rs.getInt("estado"));
                u.setId_distrito(rs.getInt("id_distrito"));
                
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return u;
    }
    //Listar tarjetas
    public List listarTarjetas(int id){
        ArrayList<Tarjeta> list=new ArrayList();
        String sql="SELECT * FROM `usuario` U INNER JOIN `tarjeta` T ON U.id = T.id_usuario where T.id_usuario = "+id;
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Tarjeta t =new Tarjeta();
                //Tarjeta
                t.setId(rs.getInt("T.id"));
                t.setCodigo_tarjeta(rs.getString("T.codigo_tarjeta"));
                t.setNumero_tarjeta(rs.getString("T.numero_tarjeta"));
                t.setFecha_activacion(rs.getString("T.fecha_activacion"));
                t.setFecha_vencimiento(rs.getString("T.fecha_vencimiento"));
                t.setEstado(rs.getInt("T.estado"));
                t.setSaldo(rs.getDouble("T.saldo"));
                list.add(t);
                
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    //Listar Usuarios con tarjeta para cobrar
    public List listarCobros(){
        ArrayList<UsuarioDTO> list=new ArrayList();
        String sql="SELECT * FROM `usuario` U where U.id_rol <> 2 ";
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                UsuarioDTO u =new UsuarioDTO();
                //Usuario
                u.setIdUsuario(rs.getInt("U.id"));
                u.setUsername(rs.getString("U.nombreUsuario"));
                u.setContrasenia(rs.getString("U.contrasenia"));
                u.setIdPersona(rs.getInt("U.idPersona"));
                u.setId_rol(rs.getInt("U.id_rol"));
                u.setListaTarjetas((ArrayList<Tarjeta>) listarTarjetas(u.getIdUsuario()));
                list.add(u);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    //LISTAR VIAJE
    public ViajeDTO listarViajeID(int id){
        
        String sql=" SELECT * FROM `viaje` V INNER JOIN `ruta` R on V.id_ruta = R.id INNER JOIN `unidad_transporte` UT on V.id_transporte = UT.id INNER JOIN `usuario` U  ON V.id_transportista = U.id Where V.id_transportista = " +id;
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                
                viaje.setIdViaje(rs.getInt("V.id"));
                viaje.setId_ruta(rs.getInt("R.id"));
                viaje.setOrigen(rs.getString("R.origen"));
                viaje.setDestino(rs.getString("R.destino"));
                viaje.setId_transporte(rs.getInt("UT.id"));
                viaje.setMarca(rs.getString("UT.marca"));
                viaje.setPlaca(rs.getString("UT.placa"));
                viaje.setTarifa(rs.getDouble("V.tarifa"));
                
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return viaje;
    }
    
    
    //--MODIFICAR USUARIO
    public boolean editar(Usuario usu){
        String sql = "UPDATE `usuario` SET `nombreUsuario`='"+usu.getUsername()+"',`contrasenia`='"+usu.getContrasenia()+"',`idPersona`="+usu.getId_persona()+" ,`id_rol`="+usu.getId_rol()+" WHERE id ="+usu.getId();
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    
    //--MODIFICAR VIAJE
    public boolean editarViaje2(ViajeDTO usu){
        String sql = "UPDATE `viaje` SET `tarifa`='"+usu.getTarifa()+"',`id_ruta`='"+usu.getId_ruta()+"',`id_transporte`="+usu.getId_transporte()+" ,`id_transportista`="+usu.getId_transportista()+" WHERE id ="+usu.getIdViaje();
        String sql2 = "UPDATE `horario_viaje` SET `id_horario`='"+ usu.getHorario()+"' WHERE id ="+usu.getIdHorarioViaje();
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps = conn.prepareStatement(sql2);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    //--PAGAR USUARIO
    public boolean pagarViaje(int id){
        String sql = "UPDATE `viaje` SET `pago`= 'Si' WHERE id =" +id;
        String sql2 = "INSERT INTO `pago_transportistas`( `pago`, `fecha`, `idViaje`, `estado`) VALUES ( ?, ?, ?, ?)" ;
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        
        // Obtener la fecha actual
        LocalDateTime fechaYHoraActual = LocalDateTime.now();

        // Crear un formateador para el formato DD-MM-AAAA
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Formatear la fecha actual según el formato deseado
        String fechaFormateada = fechaYHoraActual.format(formatter);
        try (PreparedStatement stmt = conn.prepareStatement(sql2)) {
                stmt.setInt(1, 80);
                stmt.setString(2, fechaFormateada);
                stmt.setInt(3, id);
                stmt.setInt(4, 1);
                stmt.executeUpdate();
            }

         catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
        
        return false;
    }
    
    //--MODIFICAR USUARIO
    public boolean editar2(UsuarioDTO usu){
        String sql = "UPDATE `usuario` SET `nombreUsuario`='"+usu.getUsername()+"',`contrasenia`='"+usu.getContrasenia()+"',`idPersona`="+usu.getIdPersona()+" ,`id_rol`="+usu.getId_rol()+" WHERE id ="+usu.getIdUsuario();
        String sql2 = "UPDATE `persona` SET `nombre`='"+ usu.getNombre()+"',`apellidoPaterno`='"+ usu.getApellido_paterno()+"',`apellidoMaterno`='"+usu.getApellido_materno()+"',`fechaNacimiento`='"+usu.getFechaNacimiento()+"',`dni`="+ usu.getDni()+",`correo`='"+ usu.getCorreo()+"' WHERE id ="+usu.getIdPersona();
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps = conn.prepareStatement(sql2);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    //--ELIMINAR USUARIO
    public boolean eliminar(int id){
        String sql = "UPDATE `usuario` SET `estado`= 0 WHERE id =" +id;
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    //--ELIMINAR USUARIO
    public boolean eliminarViaje(int id){
        String sql = "UPDATE `viaje` SET `estado`= 0 WHERE id =" +id;
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    //--REACTIVAR USUARIO
    public boolean reactivar(int id){
        String sql = "UPDATE `usuario` SET `estado`= 1 WHERE id =" +id;
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    //--REACTIVAR USUARIO
    public boolean reactivarViaje(int id){
        String sql = "UPDATE `viaje` SET `estado`= 1 WHERE id =" +id;
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    //--ELIMINAR UNIDAD TRANSPORTE
    public boolean eliminarUnidadTransporte(int id){
        String sql = "UPDATE `unidad_transporte` SET `estado`= 0 WHERE id =" +id;
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    
    //--ELIMINAR RUTA
    public boolean eliminarRuta(int id){
        String sql = "UPDATE `ruta` SET `estado`= 0 WHERE id =" +id;
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    //--REACTIVAR UNIDAD TRANSPORTE
    public boolean reactivarUnidadTransporte(int id){
        String sql = "UPDATE `unidad_transporte` SET `estado`= 1 WHERE id =" +id;
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    //--REACTIVAR UNIDAD TRANSPORTE
    public boolean reactivarRuta(int id){
        String sql = "UPDATE `ruta` SET `estado`= 1 WHERE id =" +id;
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    //--MODIFICAR UNIDAD TRANSPORTE
    public boolean editarUnidadTransporte(UnidadTransporteDTO uni){
        String sql = "UPDATE `unidad_transporte` SET `placa`='"+uni.getPlaca()+"',`marca`='"+uni.getMarca()+"',`modelo`='"+uni.getModelo()+"' WHERE id ="+uni.getId();
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    //--MODIFICAR RUTA
    public boolean editarRuta(RutaDTO uni){
        String sql = "UPDATE `ruta` SET `color`='"+uni.getColor()+"',`nombre_ruta`='"+uni.getNombre_ruta()+"',`origen`='"+uni.getOrigen()+"',`destino`='"+uni.getDestino()+"' WHERE id ="+uni.getId();
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    //--AGREGAR USUARIO
    public boolean add(Usuario usu){
  
        String sql = "INSERT INTO `usuario`( `nombre`, `apellidoParterno`, `apellidoMaterno`, `correo`, `contrasenia`,  `id_rol`) VALUES ('',1)";
        
        try{
            conn = conexion.getConexion();
            ps = conn.prepareCall(sql);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return false;
    }
    public boolean registrarUsuario(UsuarioDTO u) {
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
    }
    public boolean registrarUnidadTransporte(UnidadTransporteDTO u) {
        

        try {
            conn = conexion.getConexion();
            String insertDetallesSQL = "INSERT INTO `unidad_transporte`(`placa`, `marca`, `modelo`, `estado`) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertDetallesSQL)) {
                stmt.setString(1, u.getPlaca());
                stmt.setString(2, u.getMarca());
                stmt.setString(3, u.getModelo());
                stmt.setInt(4, 1);
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        return true;
    }
    public boolean registrarViaje(ViajeDTO u) {
        int viajeID = -1; // Valor predeterminado en caso de error

        try {
            // Realiza la inserción en la tabla de usuarios
            String insertUsuarioSQL = "INSERT INTO `viaje`(`fecha`, `hora`, `tarifa`, `reporte_viaje`, `id_ruta`, `id_transporte`, `id_transportista`, `pago`, `estado`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
                conn = conexion.getConexion();
                ps = conn.prepareStatement(insertUsuarioSQL, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, "2023-12-03");
                ps.setString(2, "02:33");
                ps.setDouble(3, u.getTarifa());
                ps.setString(4, "Reporte completo");
                ps.setInt(5, u.getId_ruta());
                ps.setInt(6, u.getId_transporte());
                ps.setInt(7, u.getId_transportista());
                ps.setString(8, "No");
                ps.setInt(9, 1);
                int affectedRows = ps.executeUpdate();

                if (affectedRows == 0) {
                    // La inserción falló
                    return false;
                }
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    viajeID = rs.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID generado.");
                }
                // Recupera el ID autoincrementable generado
                

            // Después de obtener el usuarioID, puedes insertar viaje.horario
            String insertDetallesSQL = "INSERT INTO `horario_viaje`(`id_horario`, `id_viaje`, `hora_salida_registro`, `hora_retorno_registro`, `fecha_registro`) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertDetallesSQL)) {
                stmt.setInt(1, u.getHorario());
                stmt.setInt(2, viajeID);
                stmt.setString(3, "18:10:29");
                stmt.setString(4, "20:10:29");
                stmt.setString(5, "2023-12-03");
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        

        return true;
    }
    public boolean registrarRuta(RutaDTO u) {
        

        try {
            conn = conexion.getConexion();
            String insertDetallesSQL = "INSERT INTO `ruta`(`color`, `nombre_ruta`, `origen`, `destino`, `estado`, `id_distrito`) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertDetallesSQL)) {
                stmt.setString(1, u.getColor());
                stmt.setString(2, u.getNombre_ruta());
                stmt.setString(3, u.getOrigen());
                stmt.setString(4, u.getDestino());
                stmt.setInt(5, 1);
                stmt.setInt(6, u.getId_distrito());
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        return true;
    }
    
    //buscar usuario por numero de tarjeta
    public int buscarUsuTarjeta(String numTarjeta){
        
        String sql="SELECT * FROM `usuario` U INNER JOIN `tarjeta` T ON U.id = T.id_usuario WHERE T.numero_tarjeta = "+numTarjeta;
        int idEncontrado = 0;
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                
                idEncontrado= rs.getInt("U.id");
                
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return idEncontrado;
    }
    
    /*
    public Usuario listarId(int id){
        
        String sql="SELECT * FROM `usuario` where id ="+id;
        try{
            conn=conexion.getConexion();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                
                usu.setId(rs.getInt("id"));
                usu.setNombre(rs.getString("nombre"));
                usu.setApellido_paterno(rs.getString("apellidoPaterno"));
                usu.setApellido_materno(rs.getString("apellidoMaterno"));
                usu.setCorreo(rs.getString("correo"));
                usu.setContrasena(rs.getString("contrasenia"));
                
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return usu;
    }
    
    //--AGREGAR USUARIO
    public boolean add(Usuario usu){
  
        String sql = "INSERT INTO `usuario`( `nombre`, `apellidoParterno`, `apellidoMaterno`, `correo`, `contrasenia`,  `id_rol`) VALUES ('"+usu.getNombre()+"','"+usu.getApellido_paterno()+"','"+usu.getApellido_materno()+"','"+usu.getCorreo()+"','"+usu.getContrasena()+"',1)";
        
        try{
            conn = conexion.getConexion();
            ps = conn.prepareCall(sql);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
        
        return false;
    }
    //--MODIFICAR USUARIO
    public boolean editar(Usuario usu){
        String sql = "UPDATE `usuario` SET `nombre`='"+usu.getNombre()+"',`apellidoPaterno`='"+usu.getApellido_paterno()+"',`apellidoMaterno`='"+usu.getApellido_materno()+"',`correo`='"+usu.getCorreo()+"',`contrasenia`='"+usu.getContrasena()+"' WHERE id ="+usu.getId();
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
    
    //--ELIMINAR USUARIO
    public boolean eliminar(int id){
        String sql = "DELETE FROM `usuario` WHERE id =" +id;
        try{
            conn = conexion.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }
*/
}

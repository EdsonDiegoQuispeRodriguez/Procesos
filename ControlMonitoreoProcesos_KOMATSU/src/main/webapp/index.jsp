<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="komatsu.corp.procesos.model.dao.UsuarioDAO"%>
<%@page import="komatsu.corp.procesos.model.entities.Usuario"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <section id="tabla">
        
        <table class="" id="miTabla" style="width:100%;">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Usuario</th>
                <th scope="col">Contrasenia</th>
              </tr>
            </thead>
        
            <tbody>
              <% 
                UsuarioDAO dao = new UsuarioDAO();
                List<Usuario> lista = dao.listar();
                Iterator<Usuario> iter = lista.iterator();
                Usuario usu = null;
                while(iter.hasNext()){
                usu = iter.next();
              %>
              <tr>
                <td style="background-color:#1e1e20;"><%= usu.getId() %></td>
                <td style="background-color:#1e1e20;"><%= usu.getUsername()%></td>
                <td style="background-color:#1e1e20;"><%= usu.getContrasenia()%></td>

              </tr>
            
            <%}%>
          </tbody>
      </table>
    </section>
    </body>
</html>

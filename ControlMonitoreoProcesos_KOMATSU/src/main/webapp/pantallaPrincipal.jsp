<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="komatsu.corp.procesos.model.dao.*"%>
<%@page import="komatsu.corp.procesos.model.entities.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Proyecto </title>
    <!--Links para CSS-->
    <script src="https://cdn.tailwindcss.com"></script>

  </head>

  <body class="bg-white ">
    <header>
      <nav class="flex justify-around items-center py-5 text-[#253745] gap-6">

        <a class="font-[500] hover:bg-[#f6f4f5] py-1 px-3 rounded-md" href="">PANEL PRINCIPAL</a>
        <img src="" alt="logo">
      </nav>
    </header>
      <button id="refreshButton" onclick="refreshPage()" class="top-5 right-5 bg-transparent">
        <img src="https://cdn-icons-png.flaticon.com/512/126/126502.png" alt="Refresh Icon" class="w-8 h-8">
      </button>
      
    <main class=" text-[#06141B] flex flex-row items-center justify-center pt-4 ">
      <div class=" border rounded-md m-2 w-[200px] h-[80vh] ">
        <h2 class="text-center font-semibold bg-red-500 py-3">TO DO</h2>
        <form id="myForm" action="${pageContext.request.contextPath}/IniciarToDoServlet" method="post">
          <div class="mt-3 px-3 flex flex-col relative h-[75vh]">
              <% 
                    OrdenServicioDAO dao = new OrdenServicioDAO();
                    List<OrdenServicio> lista = dao.listarToDo();
                    Iterator<OrdenServicio> iter = lista.iterator();
                    OrdenServicio os = null;
                    while(iter.hasNext()){
                    os = iter.next();
                  %>
                <div class="font-nomal text-sm px-2 py-2 border rounded-md">
                  <input class="form-check-input" type="checkbox" name="checkboxName1" value="<%= os.getId()%>" id="<%= os.getNombreOS()%>">
                  <label class="form-check-label" for="<%= os.getNombreOS()%>">
                        <%= os.getNombreOS()%>
                  </label>
                </div>
              <%}%>
              <input type="hidden" id="opcionSeleccionada" name="opcionSeleccionada"> <!-- Campo oculto para almacenar la opción seleccionada -->
              
              
            <input value="Iniciar" type ="submit" class="mt-3 py-1 px-4 bg-gray-400 rounded-md absolute bottom-10  left-1/2 transform -translate-x-1/2">
                
          </div>
        </form>

      </div>
      <div class="bg-red border rounded-md m-2  w-[200px] h-[80vh]">
        <h2 class="text-center font-semibold bg-yellow-500 py-3">IN PROCESS</h2>
        <form action="${pageContext.request.contextPath}/FinalizarInProcessServlet" method="post">
          <div class="mt-3 px-3 flex flex-col relative h-[75vh]">
                <% 
                    List<OrdenServicio> lista2 = dao.listarInProcess();
                    Iterator<OrdenServicio> iter2 = lista2.iterator();
                    OrdenServicio os2 = null;
                    while(iter2.hasNext()){
                    os2 = iter2.next();
                  %>
                <div class="font-nomal text-sm px-2 py-2 border rounded-md">
                  <input class="form-check-input" type="checkbox" name="checkboxName2" value="<%= os2.getId()%>" id="<%= os2.getNombreOS()%>">
                  <label class="form-check-label" for="<%= os2.getNombreOS()%>">
                        <%= os2.getNombreOS()%> 
                  </label>
                  <a id="edit" href="inProcess.jsp?id=<%= os2.getId()%>&nombre=<%= os2.getNombreOS()%>" onclick="redirectToServlet()" class="top-5 right-11 bg-transparent block">
                    <img src="https://static.vecteezy.com/system/resources/previews/026/627/528/non_2x/edit-icon-symbol-design-illustration-vector.jpg" alt="Refresh Icon" class="w-6 ml-3">
                  </a>
                </div>
              <%}%>
            <input value="Finalizar" type ="submit"
              class="mt-3 py-1 px-4 bg-gray-400 rounded-md absolute bottom-10  left-1/2 transform -translate-x-1/2">
                
          </div>
        </form>
      </div>
      <div class="bg-red border rounded-md m-2  w-[200px] h-[80vh]">
        <h2 class="text-center font-semibold bg-green-500 py-3">DONE</h2>
        
                
        <form action="">
          <div class="mt-3 px-3 flex flex-col relative h-[75vh]">
              <% 
                    List<OrdenServicio> lista3 = dao.listarDone();
                    Iterator<OrdenServicio> iter3 = lista3.iterator();
                    OrdenServicio os3 = null;
                    while(iter3.hasNext()){
                    os3 = iter3.next();
                  %>
                <div class="font-nomal text-sm px-2 py-2 border rounded-md">
                  <input class="form-check-input" type="checkbox" value="<%= os3.getId()%>" id="flexCheckDefault">
                  <label class="form-check-label" for="flexCheckDefault">
                        <%= os3.getNombreOS()%>
                  </label>
                </div>
              <%}%>
            
          </div>
        </form>
      </div>
    </main>
        
              
      <script>
        function refreshPage() {
          window.location.href = 'pantallaPrincipal.jsp'; // Esta línea recarga la página actual
        }
        document.getElementById("myForm").addEventListener("submit", function(event) {
            event.preventDefault(); // Previene el envío predeterminado del formulario

            // Muestra la alerta personalizada
            var seleccion = prompt("Seleccione una opción:\n1. Armado\n2. Desarmado");

            // Si se selecciona "Armado" o "Desarmado", envía el formulario
            if (seleccion === "1" || seleccion === "2") {
                document.getElementById("opcionSeleccionada").value = seleccion; // Establece la opción seleccionada en el campo oculto
                this.submit(); // Envía el formulario
            } else {
                alert("Por favor, seleccione una opción válida (Armado o Desarmado).");
            }
        });
    </script>
      
      
  </body>

  </html>
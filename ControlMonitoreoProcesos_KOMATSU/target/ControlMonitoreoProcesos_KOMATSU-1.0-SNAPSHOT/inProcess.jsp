<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
        // Recuperar el valor del parámetro 'parametro'
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String estado = request.getParameter("estado");
        // Realizar alguna acción con el valor recuperado
 %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>

<body>
  <header>
    <nav class="flex justify-around items-center py-5 text-[#253745] gap-6">

      <a class="font-[500] hover:bg-[#f6f4f5] py-1 px-3 rounded-md" href="">PANEL PRINCIPAL</a>
      <img src="" alt="logo">
    </nav>
  </header>
  <div class="flex justify-center gap-1">
    <button id="tab-1"
      class="px-4 py-2 bg-gray-200 text-gray-700 font-semibold rounded-t-md hover:bg-gray-300 focus:outline-none">Tab
      1</button>
    <button id="tab-2"
      class="px-4 py-2 bg-gray-200 text-gray-700 font-semibold rounded-t-md hover:bg-gray-300 focus:outline-none">Tab
      2</button>
    <button id="tab-3"
      class="px-4 py-2 bg-gray-200 text-gray-700 font-semibold rounded-t-md hover:bg-gray-300 focus:outline-none">Tab
      3</button>
  </div>
  <h2 class="text-center font-semibold text-xl bg-yellow-500 py-2">IN PROCESS <%= nombre%></h2>

  <!--contenido de los tabs-->
  <main class="flex justify-center">
    <div class="p-4 w-[500px]">
      <div class="tab-content  flex flex-col justify-center items-center" id="tab-content-1">
          <form action="${pageContext.request.contextPath}/PararInProcessUnplannedServlet" method="post" onsubmit="return confirmarEnvio();">
            <section class=" my-5 w-[400px]">
              <div class="mr-5 sm:mr-16">
                <h1 class="font-semibold text-xl">UNPLANNED DOWNTIME</h1>
                <p class="text-gray-600">Select an option</p>
                <select class="bg-white border-b" name="unplanned" id="unplanned">
                  <option value="Por Priorizacion">Por Priorizacion</option>
                  <option value="Falta de base">Falta de base</option>
                  <option value="Maquina no disponible">Maquina no disponible</option>
                </select>
              </div>
              <input type="hidden" id="idOrdenServicio" name="idOrdenServicio" value="<%= id%>">
              <input type="hidden" id="nombre" name="nombre" value="<%= nombre%>">
              <% if (estado != null && estado.equals("parado")) { %>
                    <!-- Si el estado es "activo", mostrar el botón con valor "STOP" y texto "STOP" -->
                    <input type="submit" name="accion" value="REANUDAR" class="mt-3 py-1 px-4 bg-gray-400 rounded-md">
                <% } else { %>
                    <!-- Si el estado no es "activo" (por defecto o "parado"), mostrar el botón con valor "REANUDAR" y texto "REANUDAR" -->
                    <input type="submit" name="accion" value="STOP" class="mt-3 py-1 px-4 bg-gray-400 rounded-md">
                <% } %>
            </section>
            </form>
              
              
            <form action="${pageContext.request.contextPath}/FinalizarInProcessServlet" method="post">
                <section class="my-5 w-[400px]">
                  <div class="mr-5 sm:mr-16">
                    <h1 class="font-semibold text-xl">PLANNED DOWNTIME</h1>
                    <p class="text-gray-600">Select an option</p>
                    <select class="bg-white border-b" name="unplanned" id="unplanned">
                      <option value="opcion1">Inspección ultrasonido</option>
                      <option value="opcion1">Prueba de flexión (MS)</option>
                      <option value="opcion1">Extraccion (730/830)</option>
                    </select>
                  </div>
                  <input type="hidden" id="idOrdenServicio" name="idOrdenServicio" value="<%= id%>">
                  <input value="STOP" type ="submit" class="mt-3 py-1 px-4 bg-gray-400 rounded-md ">
                </section>
            </form>
      </div>
      <div class="tab-content hidden" id="tab-content-2">
        Contenido de la pestaña 2
      </div>
      <div class="tab-content hidden" id="tab-content-3">
        Contenido de la pestaña 3
      </div>
      <form action="${pageContext.request.contextPath}/ReprogramarProcessServlet" method="post" onsubmit="return enviarInformacion();">
        
            <div class="text-center flex flex-col ">
              <input value="FINALIZAR" type="submit" class="mt-3 py-1 px-4 bg-blue-400 rounded-md ">
              <input type="hidden" id="idOrdenServicio" name="idOrdenServicio" value="<%= id%>">
              <input type="hidden" id="motivoInput" name="motivo">
              <input value="REPROGRAMACIÓN" type ="submit" class="mt-3 py-1 px-4 bg-red-400 rounded-md ">

            </div>
      </form>
    </div>
  </main>


    <script>
        function confirmarEnvio() {
            // Mostrar una alerta con el mensaje deseado
            var mensaje = "Ponte en contacto con su supervisor y técnico líder. Se ha enviado una alerta al equipo de planeamiento.";
            alert("Parada No Planificada\n\n" + mensaje);
            
            // Retornar true para permitir el envío del formulario
            return true;
        }
    function enviarInformacion() {
            // Mostrar una alerta para pedir al usuario ingresar un motivo
            var motivo = window.prompt("¿Cuál es el motivo de la reprogramación?");
            
            // Verificar si se ingresó un motivo
            if (motivo != null && motivo.trim() !== "") {
                // Si se ingresó un motivo, agregarlo como parámetro y enviar el formulario
                document.getElementById("motivoInput").value = motivo;
                return true; // Permitir el envío del formulario
            } else {
                // Si no se ingresó un motivo, cancelar el envío del formulario
                alert("Por favor, ingresa un motivo válido.");
                return false; // Cancelar el envío del formulario
            }
        }
    </script>
  <!--
  <script>
    const tabs = document.querySelectorAll('[id^="tab-"]');
    tabs.forEach(tab => {
      tab.addEventListener('click', () => {
        const tabContentId = tab.getAttribute('id').replace('tab-', 'tab-content-');
        const tabContent = document.getElementById(tabContentId);
        document.querySelectorAll('.tab-content').forEach(content => content.classList.add('hidden'));
        tabContent.classList.remove('hidden');
      });
    });
  </script>
-->
</body>

</html>
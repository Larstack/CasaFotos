<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="ar.edu.usal.model.dao.SucursalesDao"%>
<%@page import="ar.edu.usal.model.dto.Sucursales"%>
<%@page import="ar.edu.usal.model.enums.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>CASA FOTOS</title>
		
		<script>
			
			function validarDatos(){
	
				var ok = true;
				
				ok1 = validarPapel();
				ok2 = validarCopias();
				ok3 = validarNombre();
				ok4 = validarMail();
				ok5 = validarDireccion();
				ok6 = validarFile();
				
				if(ok1 && ok2 && ok3 && ok4 && ok5 && ok6){
					
					document.getElementById("fotosForm").submit();	
				}
			}
		
			function validarPapel() {
				
				var tipoPapelList = document.getElementById("fotosForm").elements.namedItem("papel");
				var ok=false;
				for (var i = 0; i < tipoPapelList.length; i++) {
					
					var papelIt = tipoPapelList[i];
					if(papelIt.checked){
						
						ok = true;
						break;
					}
				}
				if(ok == false){
					document.getElementById('papelError').innerHTML = 'Seleccionar tipo de papel';
				}
				else{
					document.getElementById('papelError').innerHTML = '';
				}
				return ok;
			}
			
			function validarCopias(){
				
				var copias = parseInt(document.getElementById('cantidad').value);
				var error = '';
				var ok = true;
				
				if(copias == null || isNaN(copias) || copias == '' || copias <= 0){
					error = 'La cantidad de copias debe ser mayor a cero';
					ok = false;
				}
				
				document.getElementById('cantidadError').innerHTML = error;
				
				return ok;
			}
			
			function validarNombre(){
				
				var nombre = document.getElementById('nombre').value;
				var error = '';
				var ok = true;
				
				if(nombre == null || nombre == ''){
					error = 'Ingrese un nombre';
					ok = false;
				}
				document.getElementById('nombreError').innerHTML = error;
				
				return ok;
			}
			
			function validarMail() {
				
				var mail = document.getElementById('mail').value;
				var error = '';
				var reg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
				var esValido = reg.test(mail);
				var ok = true;
				
				if(esValido == false){
					error = 'El e-mail ingresado es incorrecto';
					ok = false;
				}
				
				document.getElementById('mailError').innerHTML = error;
				return ok;
			}
			
			function validarDireccion(){
				
				var direccion = document.getElementById('domicilio').value;
				var error = '';
				var ok = true;
				
				if(direccion == null || direccion == ''){
					error = 'Ingrese una direccion';
					ok = false;
				}
				document.getElementById('domicilioError').innerHTML = error;
				
				return ok;
			}
			
			function validarFile(){
				
				var foto = document.getElementById('foto').value;
				var error = '';
				var reg = (/\.(jpg|jpeg)$/i)
				var esValido = reg.test(foto);
				var ok = true;
				
				if(esValido == false){
					error = 'Subir foto';
					ok = false;
				}
				
				document.getElementById('fotoError').innerHTML = error;
				return ok;
			}
			
			function sugerirSucursal(){
				
				var domicilio = document.getElementById('domicilio');
				var sucursalOptions = document.getElementById('sucursal');
				var domicilioArray = domicilio.value.split(" ");
				
				var ok = false;
				for (var i = 0; i < domicilioArray.length; i++) {
					
					var domicilioIt = domicilioArray[i].trim().toUpperCase();
					
					for (var j = 0; j < sucursalOptions.length; j++) {

						if(sucursalOptions[j].textContent.trim().toUpperCase().indexOf(domicilioIt) != -1){
							
							sucursalOptions[j].selected = true;
							
							ok = true;
							break;
						}
					}
					
					if(ok) break;
				}
			}
			
		</script>
	</head>
	<body>
		<form action="CargarFotoServlet" method="post" enctype="multipart/form-data" name="fotosForm" id="fotosForm">
			<table>
				<tr>
					<td>
						<label>Cargar foto: </label>
						<input name="foto" id="foto" type="file"/>						
					</td>
					<td>
						<p id="fotoError" style="color:red;font-weight:bold;font-style:italic;"></p>
					</td>
				</tr>
				<tr>
					<td>
						<label>Seleccionar tama&ntildeo: </label>
						<select name="tamanio" id="tamanio">
						<%for (TamanioEnum tam : TamanioEnum.values()) {%>
						
							<option value="<%=tam%>"><%=tam.getValueTamanio()%></option>
						<% } %>
					</td>
				</tr>
				<tr>
					<td>
						<label>Seleccionar papel: </label>
						<%for (PapelEnum papel : PapelEnum.values()) {%>
							<input name="papel" id="papel" type="radio" value="<%=papel%>"><%=papel%>
						<% } %>
					</td>
					<td>
						<p id="papelError" style="color:red;font-weight:bold;font-style:italic;"></p>
					</td>
				</tr>
				<tr>
					<td>
						<label>Ingrese cantidad de copias: </label> 
						<input type="text" name="cantidad" id="cantidad"/>
					</td>
					<td>
						<p id="cantidadError" style="color:red;font-weight:bold;font-style:italic;"></p>
					</td>
				</tr>
				<tr>
					<td>
						<label>Ingrese nombre y apellido: </label> 
						<input type="text" name="nombre" id="nombre"/>
					</td>
					<td>
						<p id="nombreError" style="color:red;font-weight:bold;font-style:italic;"></p>
					</td>
				</tr>
				<tr>
					<td>
						<label>Ingrese mail: </label> 
						<input type="text" name="mail" id="mail"/>
					</td>
					<td>
						<p id="mailError" style="color:red;font-weight:bold;font-style:italic;"></p>
					</td>
				</tr>
				<tr>
					<td>
						<label>Ingrese domicilio: </label> 
						<input type="text" name="domicilio" id="domicilio" onChange="sugerirSucursal();" />
					</td>
					<td>
						<p id="domicilioError" style="color:red;font-weight:bold;font-style:italic;"></p>
					</td>
				</tr>
				<tr>
					<td>
						<label>Seleccione sucursal: </label> 
						<select name="sucursal" id="sucursal">
						<%
						SucursalesDao sucursalesDao = SucursalesDao.getInstance();
						for (Sucursales sucursal : sucursalesDao.getSucursalesList()) {%>
							<option value="<%=sucursal.getNumeroSucursal()%>"><%=sucursal.getLocalidad().getNombre()%></option>
						<% } %>
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" name="confirmar" id="confirmar" value="Confirmar" onClick="validarDatos();">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
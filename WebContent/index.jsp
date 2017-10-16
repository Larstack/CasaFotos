<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="ar.edu.usal.model.dao.LocalidadesDao"%>
<%@page import="ar.edu.usal.model.dto.Localidades"%>
<%@page import="ar.edu.usal.model.enums.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>CASA FOTOS</title>
	</head>
	
	<body>
		<form action="/CargarFotoServlet">
			<table>
				<tr>
					<td>
						<label>Cargar foto: </label>
						<input name="foto" type="file"/>						
					</td>
				</tr>
				<tr>
					<td>
						<label>Seleccionar tama&ntildeo: </label>
						<select name="tamanio">
						<%for (TamanioEnum tam : TamanioEnum.values()) {%>
						
							<option value="<%=tam%>"><%=tam.getValueTamanio()%></option>
						<% } %>
					</td>
				</tr>
				<tr>
					<td>
						<label>Seleccionar papel: </label>
						<%for (PapelEnum papel : PapelEnum.values()) {%>
							<input name="papel" type="radio" value="<%=papel%>"><%=papel%>
						<% } %>
					</td>
				</tr>
				<tr>
					<td>
						<label>Ingrese cantidad de copias: </label> 
						<input type="text" name="cantidad"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>Ingrese nombre y apellido: </label> 
						<input type="text" name="nombre"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>Ingrese mail: </label> 
						<input type="text" name="mail"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>Ingrese domicilio: </label> 
						<input type="text" name="domicilio"/>
					</td>
				</tr>
				<tr>
					<td>
						<label>Seleccione localidad: </label> 
						<select name="localidad">
						<%
						LocalidadesDao localidadesDao = LocalidadesDao.getInstance();
						for (Localidades localidad : localidadesDao.getLocalidadesList()) {%>
							<option value="<%=localidad.getId()%>"><%=localidad.getNombre()%></option>
						<% } %>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
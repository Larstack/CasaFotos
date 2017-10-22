package ar.edu.usal.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import ar.edu.usal.model.dao.PedidosDao;
import ar.edu.usal.model.dao.SucursalesDao;
import ar.edu.usal.model.dto.Clientes;
import ar.edu.usal.model.dto.Pedidos;

/**
 * Servlet implementation class CargarFotoServlet
 */
@MultipartConfig
@WebServlet("/CargarFotoServlet")
public class CargarFotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletFileUpload uploader = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CargarFotoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		this.uploader = new ServletFileUpload(fileFactory);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	private void mostrarFactura(Pedidos pedido, HttpServletResponse response) {

		response.setContentType("text/html"); 
		PrintWriter out;
		try {
			out = response.getWriter();

			out.print("<html>"); 
			out.print("<head><title>Factura</title></head>");
			out.print("<body>");
			out.print("<h1>Factura</h1>");
			out.print("<br><br>");
			out.print("<h2>Cabecera</h2>");
			out.print("<br><br>");
			out.print("Numero: " + pedido.getNumero()); 
			out.print("<br><br>");

			Calendar fecha = Calendar.getInstance();
			int anio = fecha.get(Calendar.YEAR); 
			int mes = fecha.get(Calendar.MONTH) + 1;
			int dia = fecha.get(Calendar.DAY_OF_MONTH);

			String fechaString = dia + "/" + mes + "/" + anio;

			out.print("Fecha: " + fechaString); 
			out.print("<br><br>");
			out.print("Numero Sucursal: " + pedido.getSucursalRetiro().getNumeroSucursal());
			out.print("<br><br>");
			out.print("Localidad Sucursal: " + pedido.getSucursalRetiro().getLocalidad().getNombre());
			out.print("<br><br>");
			out.print("Cliente: " + pedido.getCliente().getNombreApellido());
			out.print("<br><br>");
			out.print("<h2>Detalle</h2>");
			out.print("<br><br>");
			out.print("Cantidad: " + pedido.getCantidad());
			out.print("<br><br>");
			out.print("Tipo papel: " + pedido.getFoto().getPapel());
			out.print("<br><br>");
			out.print("Tipo Tama&ntildeo: " + pedido.getFoto().getTamanio().getValueTamanio());
			out.print("<br><br>");
			out.print("<br><br>");
			out.print("Importe pedido: " + pedido.getImporte());

		} catch (IOException e) {

			e.printStackTrace();
		} 
	}

	private Pedidos instanciarPedido(String foto, String tamanio, String papel, int cantidad, String nombre, String mail,
			String domicilio, int sucursalId) {

		Pedidos pedido = new Pedidos();
		Clientes cliente = new Clientes();

		cliente.setNombreApellido(nombre);
		cliente.setDomicilio(domicilio);
		cliente.setMail(mail);

		pedido.setCliente(cliente);

		SucursalesDao sucursalesDao = SucursalesDao.getInstance();
		pedido.setSucursalRetiro(sucursalesDao.getSucursalByNumeroSucursal(sucursalId));

		pedido.setFoto(tamanio, papel, foto);
		pedido.setCantidad(cantidad);
		
		pedido.calculoCostoPedido();
		
		return pedido;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!ServletFileUpload.isMultipartContent(request)){
			throw new ServletException("No se subió un archivo");
		}

		try {

			Pedidos pedido = null;

			String foto = "";
			String tamanio = "";
			String papel = "";
			int cantidad = 0;
			String nombre = "";
			String mail = "";
			String domicilio = "";
			int sucursalId = 0;

			List<FileItem> fileItemsList = uploader.parseRequest(new ServletRequestContext(request));
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			
			//El path está definido en el web.xml
			File dir = new File(request.getServletContext().getInitParameter("file-upload"));

			dir.mkdirs();

			while(fileItemsIterator.hasNext()){

				FileItem fileItem = fileItemsIterator.next();
				
				if(fileItem.getFieldName().equals("foto")){
					File file = new File(request.getServletContext().getInitParameter("file-upload")
							+File.separator+fileItem.getName());
					
					fileItem.write(file);
					
					foto = fileItem.getName();
					
				}else{
					
					 String name = fileItem.getFieldName();
					 
					 if(name.equals("tamanio")){
						 
						 tamanio = fileItem.getString(); 
					 }else if(name.equals("papel")){
						 
						 papel = fileItem.getString();
					 }else if(name.equals("cantidad")){
						 
						 cantidad = Integer.parseInt(fileItem.getString());
					 }else if(name.equals("nombre")){
						 
						 nombre = fileItem.getString();
					 }else if(name.equals("mail")){
						 
						 mail = fileItem.getString();
					 }else if(name.equals("domicilio")){
						 
						 domicilio = fileItem.getString();
					 }else if(name.equals("sucursal")){
						 
						 sucursalId = Integer.parseInt(fileItem.getString());
					 }					
				}
			}
			
			pedido = this.instanciarPedido(foto, tamanio, papel, cantidad, nombre, mail, domicilio, sucursalId);

			PedidosDao pedidosDao = PedidosDao.getInstance();

			int idPedido = pedidosDao.grabarPedido(pedido);

			pedido.setNumero(idPedido);
			
			this.mostrarFactura(pedido, response);

		}catch (Exception e) {

			e.printStackTrace();
		}
	}
}

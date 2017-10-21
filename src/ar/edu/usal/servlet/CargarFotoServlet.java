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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import ar.edu.usal.model.dao.PedidosDao;
import ar.edu.usal.model.dao.SucursalesDao;
import ar.edu.usal.model.dto.Clientes;
import ar.edu.usal.model.dto.Pedidos;

/**
 * Servlet implementation class CargarFotoServlet
 */
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
		File direccionFile = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(direccionFile);
		this.uploader = new ServletFileUpload(fileFactory);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String foto = request.getParameter("foto");
		String tamanio = request.getParameter("tamanio");
		String papel = request.getParameter("papel");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		String nombre = request.getParameter("nombre");
		String mail = request.getParameter("mail");
		String domicilio = request.getParameter("domicilio");
		int sucursalId = Integer.parseInt(request.getParameter("sucursal"));

		doPost(request, response);

		Pedidos pedido = this.instanciarPedido(foto, tamanio, papel, cantidad, nombre, mail, domicilio, sucursalId);

		PedidosDao pedidosDao = PedidosDao.getInstance();

		int idPedido = 0;
		// DOIT!!!! con SP que devuelve ID
		//		idPedido = pedidosDao.grabarPedido();

		pedido.setNumero(idPedido);
		this.mostrarFactura(pedido, response);
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
			int mes = fecha.get(Calendar.MONTH + 1);
			int dia = fecha.get(Calendar.DAY_OF_MONTH);

			String fechaString = dia + "/" + mes + "/" + anio;

			out.print("Fecha: " + fechaString); 
			out.print("<br><br>");
			out.print("Numero Sucursal: " + pedido.getSucursalRetiro().getNumeroSucursal());
			out.print("<br><br>");
			out.print("Localidad Sucursal: " + pedido.getSucursalRetiro().getLocalidad());
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

			List<FileItem> fileItemsList = uploader.parseRequest((RequestContext) request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();

			while(fileItemsIterator.hasNext()){

				FileItem fileItem = fileItemsIterator.next();
				File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());

				fileItem.write(file);
			}

		}catch (Exception e) {

			e.printStackTrace();
		}
	}
}

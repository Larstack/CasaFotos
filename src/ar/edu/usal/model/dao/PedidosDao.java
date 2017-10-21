package ar.edu.usal.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import ar.edu.usal.model.dto.Clientes;
import ar.edu.usal.model.dto.Pedidos;
import ar.edu.usal.model.dto.Sucursales;
import ar.edu.usal.model.enums.PapelEnum;
import ar.edu.usal.model.enums.TamanioEnum;

public class PedidosDao {

private static PedidosDao pedidosDaoInstance = null;
	
	private ArrayList<Pedidos> pedidosList;
	private Connection conn;
	
	private PedidosDao(){
		
		DbConnection dbConn = DbConnection.getInstance();
		this.conn = dbConn.getConnection();
		this.pedidosList = new ArrayList<Pedidos>();
		this.loadPedidos();
	}

	public static PedidosDao getInstance(){
		
		if(pedidosDaoInstance==null){
			
			pedidosDaoInstance = new PedidosDao();
		}
		
		return pedidosDaoInstance;
	}
	
	private void loadPedidos() {

		try {

			String sql = "SELECT id, cliente_id, sucursal_id, foto_id, cantidad, importe FROM Pedidos";

			Statement stmt = this.conn.createStatement();		
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				int id = rs.getInt("id");
				int clienteId = rs.getInt("cliente_id");
				int sucursalId = rs.getInt("sucursal_id");
				int fotoId = rs.getInt("foto_id");
				int cantidad = rs.getInt("cantidad");
				int importe = rs.getInt("importe");
				
				ClientesDao clientesDao = ClientesDao.getInstance();
				Clientes cliente = clientesDao.getClientesById(clienteId);
				
				SucursalesDao sucursalesDao = SucursalesDao.getInstance();
				Sucursales sucursal = sucursalesDao.getSucursalByNumeroSucursal(sucursalId);
				
				Pedidos pedido = new Pedidos();
				pedido.setNumero(id);
				pedido.setCliente(cliente);
				pedido.setSucursalRetiro(sucursal);
				pedido.setCantidad(cantidad);
				pedido.setImporte(importe);
				
				PreparedStatement prpStmt = this.conn.prepareStatement(
						"select tamano, papel, [path] ruta " +
						"from Fotos f " +
						"	inner join Pedidos p on f.id = p.foto_id " +
						"where p.id = ? ");
				
				prpStmt.setInt(1, id);				
				ResultSet fotosSet = prpStmt.executeQuery();

				while (fotosSet.next()) {
					
					String tamano = fotosSet.getString("tamano");
					String papel = fotosSet.getString("papel");					
					String ruta = fotosSet.getString("ruta");

					pedido.setFoto(tamano, papel, ruta);
				}				
			}
		}catch (Exception e) {

			System.out.println("Se ha verificado un error al cargar las localidades.");
		}
	}

	public ArrayList<Pedidos> getPedidosList() {
		return pedidosList;
	}	
	
	public Pedidos getPedidosById(int id) {
		
		for (Iterator iterator = pedidosList.iterator(); iterator.hasNext();) {
			
			Pedidos pedidos = (Pedidos) iterator.next();
			
			if (pedidos.getNumero() == id) {
				
				return pedidos;
				
			} 
			
		}

		return null;
	}
}

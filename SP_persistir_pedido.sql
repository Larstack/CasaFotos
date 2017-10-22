CREATE PROCEDURE [dbo].[sp_persistir_pedido]
	
	@nombreApellido varchar(max),
	@mail varchar(max),
	@domicilio varchar(max),
	@numeroSucursal int,
	@pathFoto varchar(max),
	@papel varchar(max),
	@tamanio varchar(max),
	@cantidad int,
	@importe decimal,
	@pedidoId int output
AS

	DECLARE @clienteId int
	DECLARE @fotosId int

		--CLIENTE

		insert into Clientes(domicilio, mail, nombre_apellido)
			values(@domicilio, @mail, @nombreApellido)
		
		set @clienteId = @@IDENTITY

		PRINT 'Cliente: ' + cast(@clienteId as varchar)

		--SUCURSALES

		insert into Fotos(papel, [path], tamano)
		values(
			@papel,
			@pathFoto,
			@tamanio
		)

		set @fotosId = @@IDENTITY

		PRINT 'Foto: ' + cast(@fotosId as varchar)

		--PEDIDOS
		
		insert into Pedidos(cantidad,cliente_id,fecha,foto_id,importe,sucursal_id)
		values(
			@cantidad,
			@clienteId,
			getDate(),
			@fotosId,
			@importe,
			@numeroSucursal
			)

		set @pedidoId = @@IDENTITY

		PRINT 'Pedido: ' + cast(@pedidoId as varchar)

		return @pedidoId
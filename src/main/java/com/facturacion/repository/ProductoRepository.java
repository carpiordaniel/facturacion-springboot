package com.facturacion.repository;

import com.facturacion.entity.Cliente;
import com.facturacion.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

    @Query(value = "SELECT codigo as cod_producto FROM facturacion.producto where codigo = :cod_producto", nativeQuery = true)
    public String verificarSiExiteElCodProducto(@Param("cod_producto") String cod_producto);

    @Modifying
    @Query(value = "UPDATE facturacion.producto SET stock = stock - :cantidad WHERE codigo = :id_producto", nativeQuery = true)
    public Integer disminuirStock(@Param("id_producto") Integer id_producto, @Param("cantidad") Integer cantidad);




}

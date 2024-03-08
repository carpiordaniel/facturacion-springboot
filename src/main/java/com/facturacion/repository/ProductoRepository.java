package com.facturacion.repository;

import com.facturacion.entity.Cliente;
import com.facturacion.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

}

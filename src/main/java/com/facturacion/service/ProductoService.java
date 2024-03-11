package com.facturacion.service;

import com.facturacion.dto.DetFacturaDTO;
import com.facturacion.entity.Cliente;
import com.facturacion.entity.Producto;
import com.facturacion.repository.ClienteRepository;
import com.facturacion.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProducto() {
        return (List<Producto>) this.productoRepository.findAll();
    }

    public Optional<Producto> obtenerProductoPorId(Integer id) {
        return this.productoRepository.findById(id);
    }

    public Producto crearProducto(Producto producto) {
        return this.productoRepository.save(producto);
    }

    public void actualizarProducto(Producto producto) {
        this.productoRepository.save(producto);
    }

    public void eliminarProducto(Integer id) {
        this.productoRepository.deleteById(id);
    }

    public String verificarSiExiteElCodProducto(String cod_producto) {
        return this.productoRepository.verificarSiExiteElCodProducto(cod_producto);
    }
    @Transactional
    public void disminuirStock(List<DetFacturaDTO> detFacturaDTOs) {
        for (DetFacturaDTO detFacturaDTO : detFacturaDTOs) {
            System.out.println("detFacturaDTO::::" +detFacturaDTO.getCodigoProducto() + " " + detFacturaDTO.getCantidad());
            this.productoRepository.disminuirStock(  detFacturaDTO.getCodigoProducto(),
                    detFacturaDTO.getCantidad()
            );
        }
    }


}

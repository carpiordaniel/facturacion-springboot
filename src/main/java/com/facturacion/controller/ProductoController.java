package com.facturacion.controller;

import com.facturacion.entity.Cliente;
import com.facturacion.entity.Producto;
import com.facturacion.service.ClienteService;
import com.facturacion.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listarProducto() {
        return this.productoService.listarProducto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Integer id) {
        Optional<Producto> producto = this.productoService.obtenerProductoPorId(id);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/guardar")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        producto.setFechaCreacion(LocalDate.now());
        Producto nuevoProducto = this.productoService.crearProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Void> actualizarProducto(@RequestBody Producto producto) {
//        if (clienteService.obtenerClientePorId(id).isPresent()) {
//            producto.setFechaCreacion(LocalDate.now());
            this.productoService.actualizarProducto(producto);
            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        if (this.productoService.obtenerProductoPorId(id).isPresent()) {
            this.productoService.eliminarProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

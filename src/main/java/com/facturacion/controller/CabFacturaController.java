package com.facturacion.controller;

import com.facturacion.entity.CabFactura;
import com.facturacion.service.CabFacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cab-factura")
public class CabFacturaController {

    private final CabFacturaService cabFacturaService;

    public CabFacturaController(CabFacturaService cabFacturaService) {
        this.cabFacturaService = cabFacturaService;
    }

    // Endpoint para guardar una nueva factura
    @PostMapping
    public ResponseEntity<CabFactura> guardarFactura(@RequestBody CabFactura cabFactura) {
        CabFactura facturaGuardada = cabFacturaService.guardarCabFactura(cabFactura);
        return new ResponseEntity<>(facturaGuardada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CabFactura>> obtenerTodasCabeceras() {
        List<CabFactura> cabeceras = cabFacturaService.obtenerTodas();
        return new ResponseEntity<>(cabeceras, HttpStatus.OK);
    }

    // Endpoint para obtener una factura por su ID
    @GetMapping("/{id}")
    public ResponseEntity<CabFactura> obtenerFacturaPorId(@PathVariable("id") Integer id) {
        return cabFacturaService.obtenerPorId(id)
                .map(factura -> new ResponseEntity<>(factura, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para eliminar una factura por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFacturaPorId(@PathVariable("id") Integer id) {
        cabFacturaService.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

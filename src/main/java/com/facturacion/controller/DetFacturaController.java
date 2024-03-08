package com.facturacion.controller;

import com.facturacion.dto.DetFacturaDTO;
import com.facturacion.entity.DetFactura;
import com.facturacion.service.DetFacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/det-factura")
public class DetFacturaController {

    private final DetFacturaService detFacturaService;

    public DetFacturaController(DetFacturaService detFacturaService) {
        this.detFacturaService = detFacturaService;
    }

//    @PostMapping("/guardar")
//    public ResponseEntity<List<DetFactura>> guardarFactura(@RequestBody List<DetFacturaDTO> detFacturaDto) {
//        List<DetFactura> detFacturaGuardada = this.detFacturaService.guardarDetalleFactura(detFacturaDto);
//        return new ResponseEntity<>(detFacturaGuardada, HttpStatus.CREATED);
//    }
    @PostMapping("/guardar")
    public ResponseEntity<String> guardarDetallesFactura(@RequestBody List<DetFacturaDTO> detallesFacturaDTO) {
        detFacturaService.insertarFacturas(detallesFacturaDTO);
        return new ResponseEntity<>("Detalles de factura guardados exitosamente", HttpStatus.CREATED);
    }

//    @PostMapping("/guardar")
//    public ResponseEntity<List<DetFactura>> guardarFactura(@RequestBody List<DetFactura> detFactura) {
//        List<DetFactura> detFacturaGuardada = this.detFacturaService.guardarDetalleFactura(detFactura);
//        return new ResponseEntity<>(detFacturaGuardada, HttpStatus.CREATED);
//    }

//    @GetMapping("/all")
//    public ResponseEntity<List<DetFactura>> obtenerTodasCabeceras() {
//        List<DetFactura> detFacturas = this.detFacturaService.obtenerTodas();
//        return new ResponseEntity<>(detFacturas, HttpStatus.OK);
//    }

}

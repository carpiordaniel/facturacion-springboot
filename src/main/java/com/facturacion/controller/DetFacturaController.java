package com.facturacion.controller;

import com.facturacion.dto.DetFacturaDTO;
import com.facturacion.entity.DetFactura;
import com.facturacion.service.DetFacturaService;
import com.facturacion.util.ResponseMessage;
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

    @PostMapping("/guardar")
    public ResponseEntity<ResponseMessage> guardarDetallesFactura(@RequestBody List<DetFacturaDTO> detallesFacturaDTO) {
        detFacturaService.insertarFacturas(detallesFacturaDTO);
        return ResponseEntity.ok(new ResponseMessage(200, "Detalles de factura guardados exitosamente"));
    }


}

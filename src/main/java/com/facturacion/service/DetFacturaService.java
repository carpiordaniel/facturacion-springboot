package com.facturacion.service;

import com.facturacion.dto.DetFacturaDTO;
import com.facturacion.entity.CabFactura;
import com.facturacion.entity.DetFactura;
import com.facturacion.repository.CabFacturaRepository;
import com.facturacion.repository.DetFacturaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetFacturaService {

    private static  final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DetFacturaService.class);
    private final DetFacturaRepository detFacturaRepository;

    private final CabFacturaRepository cabFacturaRepository;

    public DetFacturaService(DetFacturaRepository detFacturaRepository, CabFacturaRepository cabFacturaRepository) {
        this.detFacturaRepository = detFacturaRepository;
        this.cabFacturaRepository = cabFacturaRepository;
    }

    @Transactional
    public void insertarFacturas(List<DetFacturaDTO> detFacturaDTOs) {
        for (DetFacturaDTO detFacturaDTO : detFacturaDTOs) {
            this.detFacturaRepository.insertarFactura(  detFacturaDTO.getCodigoProducto(),
                                                        detFacturaDTO.getCantidad(),
                                                        detFacturaDTO.getPkCabFactura()
                                                      );
        }
    }

}

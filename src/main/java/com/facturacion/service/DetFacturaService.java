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

    // Método para guardar un detalle de factura
//    public List<DetFactura> guardarDetalleFactura(List<DetFactura> detalleFactura) {
//        Iterable<DetFactura> detFacturas = this.detFacturaRepository.saveAll(detalleFactura);
//        List<DetFactura> listaDetFacturas = new ArrayList<>();
//        detalleFactura.forEach(listaDetFacturas::add);
//        return listaDetFacturas;
//    }

//    public List<DetFactura> guardarDetalleFactura(List<DetFacturaDTO> detalleFacturaDto) {
//        List<DetFactura> detalleFacturas = new ArrayList<>();
//        for ( DetFacturaDTO detalleDTO : detalleFacturaDto ){
//            CabFactura cabFactura = this.cabFacturaRepository.findById(detalleDTO.getPkCabFactura()).orElse(null);
//            if ( cabFactura == null){
//                continue;
//            }
//            DetFactura detFactura = new DetFactura();
//            detFactura.setId(detalleDTO.getCantidad());
//            detFactura.setCodigoProducto(detalleDTO.getCodigoProducto());
//            detFactura.setPkCabFactura(cabFactura);
//            detalleFacturas.add(detFactura);
//        }
//        LOGGER.info("Se guardaron {} detalles de facturas", detalleFacturas.size());
//        LOGGER.info("Se guardaron {} detalles de facturas", detalleFacturas);
//        return (List<DetFactura>) this.detFacturaRepository.saveAll(detalleFacturas);
//    }

    @Transactional
    public void insertarFacturas(List<DetFacturaDTO> detFacturaDTOs) {
        for (DetFacturaDTO detFacturaDTO : detFacturaDTOs) {
            this.detFacturaRepository.insertarFactura(  detFacturaDTO.getCodigoProducto(),
                                                        detFacturaDTO.getCantidad(),
                                                        detFacturaDTO.getPkCabFactura()
                                                      );
        }
    }


//    public List<DetFactura> obtenerTodas( ) {
//        Iterable<DetFactura> detFacturas = this.detFacturaRepository.findAll();
//        List<DetFactura> listaDetFacturas = new ArrayList<>();
//        detFacturas.forEach(listaDetFacturas::add);
//        return listaDetFacturas;
//    }

}

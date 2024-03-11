package com.facturacion.service;

import com.facturacion.entity.CabFactura;
import com.facturacion.repository.CabFacturaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CabFacturaService {

    private final CabFacturaRepository cabFacturaRepository;

    public CabFacturaService(CabFacturaRepository cabFacturaRepository) {
        this.cabFacturaRepository = cabFacturaRepository;
    }

    public CabFactura guardarCabFactura(CabFactura cabFactura) {
        return this.cabFacturaRepository.save(cabFactura);
    }

    public List<CabFactura> obtenerTodas( ) {
        Iterable<CabFactura> cabFacturas = this.cabFacturaRepository.findAll();
        List<CabFactura> listaCabFacturas = new ArrayList<>();
        cabFacturas.forEach(listaCabFacturas::add);
        return listaCabFacturas;
    }

    public Optional<CabFactura> obtenerPorId(Integer id) {
        return this.cabFacturaRepository.findById(id);
    }

    public void eliminarPorId(Integer id) {
        this.cabFacturaRepository.deleteById(id);
    }



    public Integer generaFactura() {
        return this.cabFacturaRepository.generaFactura();
    }
}

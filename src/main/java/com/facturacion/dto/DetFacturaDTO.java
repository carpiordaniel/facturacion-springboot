package com.facturacion.dto;

import com.facturacion.entity.CabFactura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetFacturaDTO {

    private Integer codigoProducto;
    private Integer cantidad;
    private Integer pkCabFactura;

}

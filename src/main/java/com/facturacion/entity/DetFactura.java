package com.facturacion.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_producto")
    private Integer codigoProducto;

    private Integer cantidad;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pk_cab_factura")
    private CabFactura pkCabFactura;
}

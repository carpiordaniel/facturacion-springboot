package com.facturacion.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cab_factura")
public class CabFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Integer idFcatura;
    @Column(name = "num_factura")
    private Integer numeroFactura;
    @Column(name = "ruc_cliente")
    private String rucCliente;

    @Column(name = "subtotal", columnDefinition = "DECIMAL(10,2)")
    private String subtotal;
    @Column(name = "igv", columnDefinition = "DECIMAL(10,2)")
    private String igv;
    @Column(name = "total", columnDefinition = "DECIMAL(10,2)")
    private String total;


    @JsonManagedReference
    @OneToMany(mappedBy = "pkCabFactura", cascade = CascadeType.ALL)
    private List<DetFactura> detFactura;


}

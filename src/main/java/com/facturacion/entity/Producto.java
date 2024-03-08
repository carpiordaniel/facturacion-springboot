package com.facturacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;
    @Column(length = 15, unique = true)
    private String codigo;
    private String nombre;
    private Double precio;
    private Double stock;
    @Column(columnDefinition = "TINYINT")
    private Byte activo;
    private LocalDate fechaCreacion;


}

package com.facturacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Column(name = "ruc_dni", length = 15)
    private String rucDni;
    private String nombre;
    private String direccion;
    private String correo;

    @Column(columnDefinition = "TINYINT")
    private Byte activo;
    private LocalDate fechaCreacion;
}

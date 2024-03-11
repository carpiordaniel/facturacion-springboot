package com.facturacion.controller;

import com.facturacion.entity.Cliente;
import com.facturacion.service.ClienteService;
import com.facturacion.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes() {
        return this.clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Integer id) {
        Optional<Cliente> cliente = this.clienteService.obtenerClientePorId(id);
        return cliente.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/verificar-cliente/{ruc_dni}")
    public ResponseEntity<ResponseMessage> verificarSiExiteCliente(@PathVariable String ruc_dni) {
        String verificarSiExiteCliente = this.clienteService.verificarSiExiteCliente(ruc_dni);
        return ResponseEntity.ok(new ResponseMessage(200, verificarSiExiteCliente));
    }

    @PostMapping("/guardar")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        cliente.setFechaCreacion(LocalDate.now());
        Cliente nuevoCliente = this.clienteService.crearCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Void> actualizarCliente(@RequestBody Cliente cliente) {
            this.clienteService.actualizarCliente(cliente);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        if (this.clienteService.obtenerClientePorId(id).isPresent()) {
            this.clienteService.eliminarCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

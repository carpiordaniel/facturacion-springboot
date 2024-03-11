package com.facturacion.service;

import com.facturacion.entity.Cliente;
import com.facturacion.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return (List<Cliente>) this.clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(Integer id) {
        return this.clienteRepository.findById(id);
    }

    public Cliente crearCliente(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public void actualizarCliente(Cliente cliente) {

        this.clienteRepository.save(cliente);
    }

    public void eliminarCliente(Integer id) {
        this.clienteRepository.deleteById(id);
    }

    public String verificarSiExiteCliente(String ruc_dni) {
        return this.clienteRepository.verificarSiExiteCliente(ruc_dni);
    }

}

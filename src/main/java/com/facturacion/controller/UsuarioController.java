package com.facturacion.controller;

import com.facturacion.entity.Usuario;
import com.facturacion.service.UsuarioService;
import com.facturacion.util.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/facturacion")
public class UsuarioController {

    private final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestParam String username, @RequestParam String password) {
        if (username != null && password != null) {
            // Verificar si el usuario está bloqueado
            if (this.usuarioService.estaBloqueado(username)) {
                return ResponseEntity.ok(new ResponseMessage(HttpStatus.FORBIDDEN.value(), "Usuario bloqueado"));
            }

            Optional<Object[]> usuarioAutenticado = this.usuarioService.buscarPorUsuarioYContrasenia(username, password);
            if (usuarioAutenticado.isPresent() && usuarioAutenticado.get().length > 0) {
                return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value(), "Usuario autenticado exitosamente"));
            } else {
                LOGGER.info("Credenciales incorrectas para el usuario: {}", username);
                int intentosFallidos = this.usuarioService.obtenerIntentosFallidos(username).orElse(0);
                if (intentosFallidos >= 3) {
                    LOGGER.info("Usuario bloqueado debido a múltiples intentos fallidos: {}", username);
                    this.usuarioService.bloquearUsuario(username);
                    return ResponseEntity.ok(new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), "Usuario bloqueado: superó los intentos permitidos"));
                } else {
                    LOGGER.info("Incrementando intentos fallidos para el usuario: {}", username);
                    this.usuarioService.incrementarIntentosFallidos(username);
                }
                return ResponseEntity.ok(new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), "Credenciales incorrectas"));

            }
        } else {
            // Falta el usuario o la contraseña
            LOGGER.info("Falta el usuario o la contraseña");
            return ResponseEntity.badRequest().body(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "Falta el usuario o la contraseña"));
        }
    }

}


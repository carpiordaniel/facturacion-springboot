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

    /*@GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {

        if (username != null && password != null) {
            LOGGER.info("1 {} ", username);
            // verificar si está bloqueado
            if(this.usuarioService.estaBloqueado(username)) {
                LOGGER.info("2 {} ", username);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario bloqueado");

            }
            LOGGER.info("2.5 {} - {} ", username , password);
            Optional<Object[]> usuarioAutenticado = this.usuarioService.buscarPorUsuarioYContrasenia(username, password);
            LOGGER.info("3 {} ", usuarioAutenticado.get());
            LOGGER.info("Resultado de la consulta: {}", usuarioAutenticado.orElse(null));
            if (usuarioAutenticado.isPresent()) {
                LOGGER.info("3.5 {} ", usuarioAutenticado.get());
                LOGGER.info("4 {} ", usuarioAutenticado.orElse(null));
                return ResponseEntity.ok("Usuario autenticado exitosamente");
            } else {
                LOGGER.info("5 {} ", usuarioAutenticado.orElse(null));
                if(this.usuarioService.obtenerIntentosFallidos(username).orElse(0) >= 3) {
                    LOGGER.info("6 {} ", usuarioAutenticado.orElse(null));
                    this.usuarioService.bloquearUsuario(username);
                    LOGGER.info("7 {} ", usuarioAutenticado.orElse(null));
                }
                LOGGER.info("8 {} ", usuarioAutenticado.orElse(null));
                this.usuarioService.incrementarIntentosFallidos(username);
                LOGGER.info("9 {} ", usuarioAutenticado.orElse(null));
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
            }
        } else {
            LOGGER.info("10 {} ", username);
            return ResponseEntity.badRequest().body("Falta el usuario o la contraseña");
        }



    }*/

//    @GetMapping("/login")
//    public ResponseEntity<Object> login(@RequestParam String username, @RequestParam String password) {
//        if (username != null && password != null) {
//            //LOGGER.info("1 {} ", username);
//            if (this.usuarioService.estaBloqueado(username)) {
//                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario bloqueado");
//            }
//            //LOGGER.info("2.5 {} - {} ", username, password);
//            Optional<Object[]> usuarioAutenticado = this.usuarioService.buscarPorUsuarioYContrasenia(username, password);
//            if (usuarioAutenticado.orElse(null).length > 0) {
//                //LOGGER.info("Usuario autenticado 1{}", usuarioAutenticado);
//                return ResponseEntity.ok().body("Usuario autenticado exitosamente");
//            } else {
//                // Manejar el caso en que las credenciales no son correctas
//                LOGGER.info("Credenciales incorrectas para el usuario: {}", username);
//                int intentosFallidos = this.usuarioService.obtenerIntentosFallidos(username).orElse(0);
//                LOGGER.info("1 {}", intentosFallidos);
//                if (intentosFallidos >= 3) {
//                    LOGGER.info("Usuario bloqueado debido a múltiples intentos fallidos: {}", username);
//                    this.usuarioService.bloquearUsuario(username);
//                } else {
//                    LOGGER.info("Incrementando intentos fallidos para el usuario: {}", username);
//                    this.usuarioService.incrementarIntentosFallidos(username);
//                }
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
//            }
//        } else {
//            LOGGER.info("Falta el usuario o la contraseña");
//            return ResponseEntity.badRequest().body("Falta el usuario o la contraseña");
//        }
//    }



    @GetMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestParam String username, @RequestParam String password) {
        if (username != null && password != null) {
            // Verificar si el usuario está bloqueado
            if (this.usuarioService.estaBloqueado(username)) {
                return ResponseEntity.ok(new ResponseMessage(HttpStatus.FORBIDDEN.value(), "Usuario bloqueado"));
            }

            // Realizar la autenticación del usuario
            Optional<Object[]> usuarioAutenticado = this.usuarioService.buscarPorUsuarioYContrasenia(username, password);
            if (usuarioAutenticado.isPresent() && usuarioAutenticado.get().length > 0) {
                // Usuario autenticado exitosamente
                return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value(), "Usuario autenticado exitosamente"));
            } else {
                // Incrementar intentos fallidos y manejar la autenticación incorrecta
                LOGGER.info("Credenciales incorrectas para el usuario: {}", username);
                int intentosFallidos = this.usuarioService.obtenerIntentosFallidos(username).orElse(0);
                if (intentosFallidos >= 3) {
                    LOGGER.info("Usuario bloqueado debido a múltiples intentos fallidos: {}", username);
                    this.usuarioService.bloquearUsuario(username);
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


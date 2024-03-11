package com.facturacion.repository;

import com.facturacion.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    @Query(value = "SELECT username, password FROM usuario WHERE username = :username AND password = :password", nativeQuery = true)
    Optional<Object[]> findByUsernameAndPasswords(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT intentos_fallidos FROM usuario WHERE username = :username", nativeQuery = true)
    Optional<Integer> obtenerIntentosFallidos(@Param("username") String username);


    @Query(value = "SELECT bloqueado FROM usuario WHERE username = :username", nativeQuery = true)
    Optional<Byte> estaBloqueado(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuario u SET intentos_fallidos = intentos_fallidos + 1 WHERE u.username = :username", nativeQuery = true)
    public void incrementarIntentosFallidos(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuario  SET bloqueado = 1 WHERE username = :username", nativeQuery = true)
    public void bloquearUsuario(@Param("username") String username);

}

package com.gestionpedidos.proyecto1gestionpedidos.repository;

import com.gestionpedidos.proyecto1gestionpedidos.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    public Usuario findByUsername(String username);
    public Usuario findByEmail(String email);
    public Usuario findByUsernameOrEmail(String username, String email);
    public List<Usuario> findByIdIn(List<Long> userIds);
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
}

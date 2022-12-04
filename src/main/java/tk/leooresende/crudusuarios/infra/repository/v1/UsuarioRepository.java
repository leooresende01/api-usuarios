package tk.leooresende.crudusuarios.infra.repository.v1;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.leooresende.crudusuarios.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Boolean existsByUsername(String username);

	Optional<Usuario> findByUsername(String username);
	Optional<Usuario> findByUsernameOrEmail(String usernameEmailOrId, String usernameEmailOrId2);

	Boolean existsByUsernameOrEmail(String username, String email);

	Boolean existsByEmail(String email);

	Optional<Usuario> findByUsernameOrEmailOrId(String username, String email, String id);

	
}

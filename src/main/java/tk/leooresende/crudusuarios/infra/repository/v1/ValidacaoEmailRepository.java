package tk.leooresende.crudusuarios.infra.repository.v1;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.leooresende.crudusuarios.model.ValidacaoEmail;

@Repository
public interface ValidacaoEmailRepository extends JpaRepository<ValidacaoEmail, Integer> {

	Optional<ValidacaoEmail> findByIdDoUsuario(Integer userId);

	void deleteByIdDoUsuario(Integer id);

}

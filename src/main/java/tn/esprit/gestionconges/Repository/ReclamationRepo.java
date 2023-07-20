package tn.esprit.gestionconges.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gestionconges.entites.Reclamation;
@Repository
public interface ReclamationRepo extends JpaRepository<Reclamation, Long > {
	List<Reclamation> findAllByOrderByUpdatedAtDesc();
	Page<Reclamation> findAllByOrderByUpdatedAtDesc(Pageable pageable);
	Page<Reclamation> findByUsername(String username, Pageable pageable);
	long countByUsername(String username);
}

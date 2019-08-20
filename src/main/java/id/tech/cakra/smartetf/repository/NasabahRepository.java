package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.Nasabah;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Nasabah entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NasabahRepository extends JpaRepository<Nasabah, Long> {

}

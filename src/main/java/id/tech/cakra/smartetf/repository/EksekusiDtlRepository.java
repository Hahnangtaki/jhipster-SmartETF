package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.EksekusiDtl;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EksekusiDtl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EksekusiDtlRepository extends JpaRepository<EksekusiDtl, Long> {

}

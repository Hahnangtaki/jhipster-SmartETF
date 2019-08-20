package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.HairCut;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HairCut entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HairCutRepository extends JpaRepository<HairCut, Long> {

}

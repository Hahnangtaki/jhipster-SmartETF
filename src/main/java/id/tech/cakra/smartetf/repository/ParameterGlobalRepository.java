package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.ParameterGlobal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ParameterGlobal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParameterGlobalRepository extends JpaRepository<ParameterGlobal, Long> {

}

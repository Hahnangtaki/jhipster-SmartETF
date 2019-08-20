package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.EksekusiHeader;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EksekusiHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EksekusiHeaderRepository extends JpaRepository<EksekusiHeader, Long> {

}

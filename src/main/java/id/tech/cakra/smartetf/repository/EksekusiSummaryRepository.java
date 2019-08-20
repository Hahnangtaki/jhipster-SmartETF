package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.EksekusiSummary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EksekusiSummary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EksekusiSummaryRepository extends JpaRepository<EksekusiSummary, Long> {

}

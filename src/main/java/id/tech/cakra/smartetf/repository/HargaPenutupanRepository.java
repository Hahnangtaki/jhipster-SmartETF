package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.HargaPenutupan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HargaPenutupan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HargaPenutupanRepository extends JpaRepository<HargaPenutupan, Long> {

}

package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.IdxHoliday;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the IdxHoliday entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IdxHolidayRepository extends JpaRepository<IdxHoliday, Long> {

}

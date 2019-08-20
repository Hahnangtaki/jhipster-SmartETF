package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.PengajuanGadaiEfekHeader;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PengajuanGadaiEfekHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PengajuanGadaiEfekHeaderRepository extends JpaRepository<PengajuanGadaiEfekHeader, Long> {

}

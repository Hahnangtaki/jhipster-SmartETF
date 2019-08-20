package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.PengajuanGadaiEfekDtl;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PengajuanGadaiEfekDtl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PengajuanGadaiEfekDtlRepository extends JpaRepository<PengajuanGadaiEfekDtl, Long> {

}

package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.TransaksiGadaiEfekHeader;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TransaksiGadaiEfekHeader entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransaksiGadaiEfekHeaderRepository extends JpaRepository<TransaksiGadaiEfekHeader, Long> {

}

package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.BankCustodi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BankCustodi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankCustodiRepository extends JpaRepository<BankCustodi, Long> {

}

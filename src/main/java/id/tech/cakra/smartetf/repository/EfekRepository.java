package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.Efek;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Efek entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EfekRepository extends JpaRepository<Efek, Long> {

}

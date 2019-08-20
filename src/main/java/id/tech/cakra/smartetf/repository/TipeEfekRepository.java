package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.TipeEfek;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipeEfek entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipeEfekRepository extends JpaRepository<TipeEfek, Long> {

}

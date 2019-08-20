package id.tech.cakra.smartetf.repository;

import id.tech.cakra.smartetf.domain.Broker;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Broker entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BrokerRepository extends JpaRepository<Broker, Long> {

}

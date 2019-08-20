package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.domain.EksekusiHeader;
import id.tech.cakra.smartetf.repository.EksekusiHeaderRepository;
import id.tech.cakra.smartetf.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link id.tech.cakra.smartetf.domain.EksekusiHeader}.
 */
@RestController
@RequestMapping("/api")
public class EksekusiHeaderResource {

    private final Logger log = LoggerFactory.getLogger(EksekusiHeaderResource.class);

    private static final String ENTITY_NAME = "eksekusiHeader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EksekusiHeaderRepository eksekusiHeaderRepository;

    public EksekusiHeaderResource(EksekusiHeaderRepository eksekusiHeaderRepository) {
        this.eksekusiHeaderRepository = eksekusiHeaderRepository;
    }

    /**
     * {@code POST  /eksekusi-headers} : Create a new eksekusiHeader.
     *
     * @param eksekusiHeader the eksekusiHeader to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eksekusiHeader, or with status {@code 400 (Bad Request)} if the eksekusiHeader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eksekusi-headers")
    public ResponseEntity<EksekusiHeader> createEksekusiHeader(@Valid @RequestBody EksekusiHeader eksekusiHeader) throws URISyntaxException {
        log.debug("REST request to save EksekusiHeader : {}", eksekusiHeader);
        if (eksekusiHeader.getId() != null) {
            throw new BadRequestAlertException("A new eksekusiHeader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EksekusiHeader result = eksekusiHeaderRepository.save(eksekusiHeader);
        return ResponseEntity.created(new URI("/api/eksekusi-headers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /eksekusi-headers} : Updates an existing eksekusiHeader.
     *
     * @param eksekusiHeader the eksekusiHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eksekusiHeader,
     * or with status {@code 400 (Bad Request)} if the eksekusiHeader is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eksekusiHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eksekusi-headers")
    public ResponseEntity<EksekusiHeader> updateEksekusiHeader(@Valid @RequestBody EksekusiHeader eksekusiHeader) throws URISyntaxException {
        log.debug("REST request to update EksekusiHeader : {}", eksekusiHeader);
        if (eksekusiHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EksekusiHeader result = eksekusiHeaderRepository.save(eksekusiHeader);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, eksekusiHeader.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /eksekusi-headers} : get all the eksekusiHeaders.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eksekusiHeaders in body.
     */
    @GetMapping("/eksekusi-headers")
    public List<EksekusiHeader> getAllEksekusiHeaders() {
        log.debug("REST request to get all EksekusiHeaders");
        return eksekusiHeaderRepository.findAll();
    }

    /**
     * {@code GET  /eksekusi-headers/:id} : get the "id" eksekusiHeader.
     *
     * @param id the id of the eksekusiHeader to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eksekusiHeader, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eksekusi-headers/{id}")
    public ResponseEntity<EksekusiHeader> getEksekusiHeader(@PathVariable Long id) {
        log.debug("REST request to get EksekusiHeader : {}", id);
        Optional<EksekusiHeader> eksekusiHeader = eksekusiHeaderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(eksekusiHeader);
    }

    /**
     * {@code DELETE  /eksekusi-headers/:id} : delete the "id" eksekusiHeader.
     *
     * @param id the id of the eksekusiHeader to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eksekusi-headers/{id}")
    public ResponseEntity<Void> deleteEksekusiHeader(@PathVariable Long id) {
        log.debug("REST request to delete EksekusiHeader : {}", id);
        eksekusiHeaderRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

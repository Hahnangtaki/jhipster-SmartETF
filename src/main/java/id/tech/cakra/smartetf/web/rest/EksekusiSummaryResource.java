package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.domain.EksekusiSummary;
import id.tech.cakra.smartetf.repository.EksekusiSummaryRepository;
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
 * REST controller for managing {@link id.tech.cakra.smartetf.domain.EksekusiSummary}.
 */
@RestController
@RequestMapping("/api")
public class EksekusiSummaryResource {

    private final Logger log = LoggerFactory.getLogger(EksekusiSummaryResource.class);

    private static final String ENTITY_NAME = "eksekusiSummary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EksekusiSummaryRepository eksekusiSummaryRepository;

    public EksekusiSummaryResource(EksekusiSummaryRepository eksekusiSummaryRepository) {
        this.eksekusiSummaryRepository = eksekusiSummaryRepository;
    }

    /**
     * {@code POST  /eksekusi-summaries} : Create a new eksekusiSummary.
     *
     * @param eksekusiSummary the eksekusiSummary to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eksekusiSummary, or with status {@code 400 (Bad Request)} if the eksekusiSummary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eksekusi-summaries")
    public ResponseEntity<EksekusiSummary> createEksekusiSummary(@Valid @RequestBody EksekusiSummary eksekusiSummary) throws URISyntaxException {
        log.debug("REST request to save EksekusiSummary : {}", eksekusiSummary);
        if (eksekusiSummary.getId() != null) {
            throw new BadRequestAlertException("A new eksekusiSummary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EksekusiSummary result = eksekusiSummaryRepository.save(eksekusiSummary);
        return ResponseEntity.created(new URI("/api/eksekusi-summaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /eksekusi-summaries} : Updates an existing eksekusiSummary.
     *
     * @param eksekusiSummary the eksekusiSummary to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eksekusiSummary,
     * or with status {@code 400 (Bad Request)} if the eksekusiSummary is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eksekusiSummary couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eksekusi-summaries")
    public ResponseEntity<EksekusiSummary> updateEksekusiSummary(@Valid @RequestBody EksekusiSummary eksekusiSummary) throws URISyntaxException {
        log.debug("REST request to update EksekusiSummary : {}", eksekusiSummary);
        if (eksekusiSummary.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EksekusiSummary result = eksekusiSummaryRepository.save(eksekusiSummary);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, eksekusiSummary.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /eksekusi-summaries} : get all the eksekusiSummaries.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eksekusiSummaries in body.
     */
    @GetMapping("/eksekusi-summaries")
    public List<EksekusiSummary> getAllEksekusiSummaries() {
        log.debug("REST request to get all EksekusiSummaries");
        return eksekusiSummaryRepository.findAll();
    }

    /**
     * {@code GET  /eksekusi-summaries/:id} : get the "id" eksekusiSummary.
     *
     * @param id the id of the eksekusiSummary to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eksekusiSummary, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eksekusi-summaries/{id}")
    public ResponseEntity<EksekusiSummary> getEksekusiSummary(@PathVariable Long id) {
        log.debug("REST request to get EksekusiSummary : {}", id);
        Optional<EksekusiSummary> eksekusiSummary = eksekusiSummaryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(eksekusiSummary);
    }

    /**
     * {@code DELETE  /eksekusi-summaries/:id} : delete the "id" eksekusiSummary.
     *
     * @param id the id of the eksekusiSummary to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eksekusi-summaries/{id}")
    public ResponseEntity<Void> deleteEksekusiSummary(@PathVariable Long id) {
        log.debug("REST request to delete EksekusiSummary : {}", id);
        eksekusiSummaryRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

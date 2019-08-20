package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.domain.EksekusiDtl;
import id.tech.cakra.smartetf.repository.EksekusiDtlRepository;
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
 * REST controller for managing {@link id.tech.cakra.smartetf.domain.EksekusiDtl}.
 */
@RestController
@RequestMapping("/api")
public class EksekusiDtlResource {

    private final Logger log = LoggerFactory.getLogger(EksekusiDtlResource.class);

    private static final String ENTITY_NAME = "eksekusiDtl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EksekusiDtlRepository eksekusiDtlRepository;

    public EksekusiDtlResource(EksekusiDtlRepository eksekusiDtlRepository) {
        this.eksekusiDtlRepository = eksekusiDtlRepository;
    }

    /**
     * {@code POST  /eksekusi-dtls} : Create a new eksekusiDtl.
     *
     * @param eksekusiDtl the eksekusiDtl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new eksekusiDtl, or with status {@code 400 (Bad Request)} if the eksekusiDtl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eksekusi-dtls")
    public ResponseEntity<EksekusiDtl> createEksekusiDtl(@Valid @RequestBody EksekusiDtl eksekusiDtl) throws URISyntaxException {
        log.debug("REST request to save EksekusiDtl : {}", eksekusiDtl);
        if (eksekusiDtl.getId() != null) {
            throw new BadRequestAlertException("A new eksekusiDtl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EksekusiDtl result = eksekusiDtlRepository.save(eksekusiDtl);
        return ResponseEntity.created(new URI("/api/eksekusi-dtls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /eksekusi-dtls} : Updates an existing eksekusiDtl.
     *
     * @param eksekusiDtl the eksekusiDtl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated eksekusiDtl,
     * or with status {@code 400 (Bad Request)} if the eksekusiDtl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the eksekusiDtl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eksekusi-dtls")
    public ResponseEntity<EksekusiDtl> updateEksekusiDtl(@Valid @RequestBody EksekusiDtl eksekusiDtl) throws URISyntaxException {
        log.debug("REST request to update EksekusiDtl : {}", eksekusiDtl);
        if (eksekusiDtl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EksekusiDtl result = eksekusiDtlRepository.save(eksekusiDtl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, eksekusiDtl.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /eksekusi-dtls} : get all the eksekusiDtls.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of eksekusiDtls in body.
     */
    @GetMapping("/eksekusi-dtls")
    public List<EksekusiDtl> getAllEksekusiDtls() {
        log.debug("REST request to get all EksekusiDtls");
        return eksekusiDtlRepository.findAll();
    }

    /**
     * {@code GET  /eksekusi-dtls/:id} : get the "id" eksekusiDtl.
     *
     * @param id the id of the eksekusiDtl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the eksekusiDtl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eksekusi-dtls/{id}")
    public ResponseEntity<EksekusiDtl> getEksekusiDtl(@PathVariable Long id) {
        log.debug("REST request to get EksekusiDtl : {}", id);
        Optional<EksekusiDtl> eksekusiDtl = eksekusiDtlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(eksekusiDtl);
    }

    /**
     * {@code DELETE  /eksekusi-dtls/:id} : delete the "id" eksekusiDtl.
     *
     * @param id the id of the eksekusiDtl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eksekusi-dtls/{id}")
    public ResponseEntity<Void> deleteEksekusiDtl(@PathVariable Long id) {
        log.debug("REST request to delete EksekusiDtl : {}", id);
        eksekusiDtlRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

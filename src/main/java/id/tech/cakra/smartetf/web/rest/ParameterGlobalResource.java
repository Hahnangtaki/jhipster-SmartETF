package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.domain.ParameterGlobal;
import id.tech.cakra.smartetf.repository.ParameterGlobalRepository;
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
 * REST controller for managing {@link id.tech.cakra.smartetf.domain.ParameterGlobal}.
 */
@RestController
@RequestMapping("/api")
public class ParameterGlobalResource {

    private final Logger log = LoggerFactory.getLogger(ParameterGlobalResource.class);

    private static final String ENTITY_NAME = "parameterGlobal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParameterGlobalRepository parameterGlobalRepository;

    public ParameterGlobalResource(ParameterGlobalRepository parameterGlobalRepository) {
        this.parameterGlobalRepository = parameterGlobalRepository;
    }

    /**
     * {@code POST  /parameter-globals} : Create a new parameterGlobal.
     *
     * @param parameterGlobal the parameterGlobal to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parameterGlobal, or with status {@code 400 (Bad Request)} if the parameterGlobal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/parameter-globals")
    public ResponseEntity<ParameterGlobal> createParameterGlobal(@Valid @RequestBody ParameterGlobal parameterGlobal) throws URISyntaxException {
        log.debug("REST request to save ParameterGlobal : {}", parameterGlobal);
        if (parameterGlobal.getId() != null) {
            throw new BadRequestAlertException("A new parameterGlobal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParameterGlobal result = parameterGlobalRepository.save(parameterGlobal);
        return ResponseEntity.created(new URI("/api/parameter-globals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /parameter-globals} : Updates an existing parameterGlobal.
     *
     * @param parameterGlobal the parameterGlobal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parameterGlobal,
     * or with status {@code 400 (Bad Request)} if the parameterGlobal is not valid,
     * or with status {@code 500 (Internal Server Error)} if the parameterGlobal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/parameter-globals")
    public ResponseEntity<ParameterGlobal> updateParameterGlobal(@Valid @RequestBody ParameterGlobal parameterGlobal) throws URISyntaxException {
        log.debug("REST request to update ParameterGlobal : {}", parameterGlobal);
        if (parameterGlobal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParameterGlobal result = parameterGlobalRepository.save(parameterGlobal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, parameterGlobal.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /parameter-globals} : get all the parameterGlobals.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parameterGlobals in body.
     */
    @GetMapping("/parameter-globals")
    public List<ParameterGlobal> getAllParameterGlobals() {
        log.debug("REST request to get all ParameterGlobals");
        return parameterGlobalRepository.findAll();
    }

    /**
     * {@code GET  /parameter-globals/:id} : get the "id" parameterGlobal.
     *
     * @param id the id of the parameterGlobal to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the parameterGlobal, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/parameter-globals/{id}")
    public ResponseEntity<ParameterGlobal> getParameterGlobal(@PathVariable Long id) {
        log.debug("REST request to get ParameterGlobal : {}", id);
        Optional<ParameterGlobal> parameterGlobal = parameterGlobalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(parameterGlobal);
    }

    /**
     * {@code DELETE  /parameter-globals/:id} : delete the "id" parameterGlobal.
     *
     * @param id the id of the parameterGlobal to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/parameter-globals/{id}")
    public ResponseEntity<Void> deleteParameterGlobal(@PathVariable Long id) {
        log.debug("REST request to delete ParameterGlobal : {}", id);
        parameterGlobalRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

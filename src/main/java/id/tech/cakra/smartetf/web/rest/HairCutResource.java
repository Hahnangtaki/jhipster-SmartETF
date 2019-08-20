package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.domain.HairCut;
import id.tech.cakra.smartetf.repository.HairCutRepository;
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
 * REST controller for managing {@link id.tech.cakra.smartetf.domain.HairCut}.
 */
@RestController
@RequestMapping("/api")
public class HairCutResource {

    private final Logger log = LoggerFactory.getLogger(HairCutResource.class);

    private static final String ENTITY_NAME = "hairCut";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HairCutRepository hairCutRepository;

    public HairCutResource(HairCutRepository hairCutRepository) {
        this.hairCutRepository = hairCutRepository;
    }

    /**
     * {@code POST  /hair-cuts} : Create a new hairCut.
     *
     * @param hairCut the hairCut to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hairCut, or with status {@code 400 (Bad Request)} if the hairCut has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/hair-cuts")
    public ResponseEntity<HairCut> createHairCut(@Valid @RequestBody HairCut hairCut) throws URISyntaxException {
        log.debug("REST request to save HairCut : {}", hairCut);
        if (hairCut.getId() != null) {
            throw new BadRequestAlertException("A new hairCut cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HairCut result = hairCutRepository.save(hairCut);
        return ResponseEntity.created(new URI("/api/hair-cuts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /hair-cuts} : Updates an existing hairCut.
     *
     * @param hairCut the hairCut to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hairCut,
     * or with status {@code 400 (Bad Request)} if the hairCut is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hairCut couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/hair-cuts")
    public ResponseEntity<HairCut> updateHairCut(@Valid @RequestBody HairCut hairCut) throws URISyntaxException {
        log.debug("REST request to update HairCut : {}", hairCut);
        if (hairCut.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HairCut result = hairCutRepository.save(hairCut);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hairCut.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /hair-cuts} : get all the hairCuts.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hairCuts in body.
     */
    @GetMapping("/hair-cuts")
    public List<HairCut> getAllHairCuts() {
        log.debug("REST request to get all HairCuts");
        return hairCutRepository.findAll();
    }

    /**
     * {@code GET  /hair-cuts/:id} : get the "id" hairCut.
     *
     * @param id the id of the hairCut to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hairCut, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hair-cuts/{id}")
    public ResponseEntity<HairCut> getHairCut(@PathVariable Long id) {
        log.debug("REST request to get HairCut : {}", id);
        Optional<HairCut> hairCut = hairCutRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hairCut);
    }

    /**
     * {@code DELETE  /hair-cuts/:id} : delete the "id" hairCut.
     *
     * @param id the id of the hairCut to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/hair-cuts/{id}")
    public ResponseEntity<Void> deleteHairCut(@PathVariable Long id) {
        log.debug("REST request to delete HairCut : {}", id);
        hairCutRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

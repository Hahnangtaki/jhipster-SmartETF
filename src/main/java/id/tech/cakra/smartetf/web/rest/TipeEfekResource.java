package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.domain.TipeEfek;
import id.tech.cakra.smartetf.repository.TipeEfekRepository;
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
 * REST controller for managing {@link id.tech.cakra.smartetf.domain.TipeEfek}.
 */
@RestController
@RequestMapping("/api")
public class TipeEfekResource {

    private final Logger log = LoggerFactory.getLogger(TipeEfekResource.class);

    private static final String ENTITY_NAME = "tipeEfek";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipeEfekRepository tipeEfekRepository;

    public TipeEfekResource(TipeEfekRepository tipeEfekRepository) {
        this.tipeEfekRepository = tipeEfekRepository;
    }

    /**
     * {@code POST  /tipe-efeks} : Create a new tipeEfek.
     *
     * @param tipeEfek the tipeEfek to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipeEfek, or with status {@code 400 (Bad Request)} if the tipeEfek has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipe-efeks")
    public ResponseEntity<TipeEfek> createTipeEfek(@Valid @RequestBody TipeEfek tipeEfek) throws URISyntaxException {
        log.debug("REST request to save TipeEfek : {}", tipeEfek);
        if (tipeEfek.getId() != null) {
            throw new BadRequestAlertException("A new tipeEfek cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipeEfek result = tipeEfekRepository.save(tipeEfek);
        return ResponseEntity.created(new URI("/api/tipe-efeks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipe-efeks} : Updates an existing tipeEfek.
     *
     * @param tipeEfek the tipeEfek to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipeEfek,
     * or with status {@code 400 (Bad Request)} if the tipeEfek is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipeEfek couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipe-efeks")
    public ResponseEntity<TipeEfek> updateTipeEfek(@Valid @RequestBody TipeEfek tipeEfek) throws URISyntaxException {
        log.debug("REST request to update TipeEfek : {}", tipeEfek);
        if (tipeEfek.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipeEfek result = tipeEfekRepository.save(tipeEfek);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tipeEfek.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipe-efeks} : get all the tipeEfeks.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipeEfeks in body.
     */
    @GetMapping("/tipe-efeks")
    public List<TipeEfek> getAllTipeEfeks() {
        log.debug("REST request to get all TipeEfeks");
        return tipeEfekRepository.findAll();
    }

    /**
     * {@code GET  /tipe-efeks/:id} : get the "id" tipeEfek.
     *
     * @param id the id of the tipeEfek to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipeEfek, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipe-efeks/{id}")
    public ResponseEntity<TipeEfek> getTipeEfek(@PathVariable Long id) {
        log.debug("REST request to get TipeEfek : {}", id);
        Optional<TipeEfek> tipeEfek = tipeEfekRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tipeEfek);
    }

    /**
     * {@code DELETE  /tipe-efeks/:id} : delete the "id" tipeEfek.
     *
     * @param id the id of the tipeEfek to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipe-efeks/{id}")
    public ResponseEntity<Void> deleteTipeEfek(@PathVariable Long id) {
        log.debug("REST request to delete TipeEfek : {}", id);
        tipeEfekRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

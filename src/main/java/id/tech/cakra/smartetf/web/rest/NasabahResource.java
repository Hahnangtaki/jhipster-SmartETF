package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.domain.Nasabah;
import id.tech.cakra.smartetf.repository.NasabahRepository;
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
 * REST controller for managing {@link id.tech.cakra.smartetf.domain.Nasabah}.
 */
@RestController
@RequestMapping("/api")
public class NasabahResource {

    private final Logger log = LoggerFactory.getLogger(NasabahResource.class);

    private static final String ENTITY_NAME = "nasabah";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NasabahRepository nasabahRepository;

    public NasabahResource(NasabahRepository nasabahRepository) {
        this.nasabahRepository = nasabahRepository;
    }

    /**
     * {@code POST  /nasabahs} : Create a new nasabah.
     *
     * @param nasabah the nasabah to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nasabah, or with status {@code 400 (Bad Request)} if the nasabah has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nasabahs")
    public ResponseEntity<Nasabah> createNasabah(@Valid @RequestBody Nasabah nasabah) throws URISyntaxException {
        log.debug("REST request to save Nasabah : {}", nasabah);
        if (nasabah.getId() != null) {
            throw new BadRequestAlertException("A new nasabah cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Nasabah result = nasabahRepository.save(nasabah);
        return ResponseEntity.created(new URI("/api/nasabahs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nasabahs} : Updates an existing nasabah.
     *
     * @param nasabah the nasabah to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nasabah,
     * or with status {@code 400 (Bad Request)} if the nasabah is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nasabah couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nasabahs")
    public ResponseEntity<Nasabah> updateNasabah(@Valid @RequestBody Nasabah nasabah) throws URISyntaxException {
        log.debug("REST request to update Nasabah : {}", nasabah);
        if (nasabah.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Nasabah result = nasabahRepository.save(nasabah);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, nasabah.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nasabahs} : get all the nasabahs.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nasabahs in body.
     */
    @GetMapping("/nasabahs")
    public List<Nasabah> getAllNasabahs() {
        log.debug("REST request to get all Nasabahs");
        return nasabahRepository.findAll();
    }

    /**
     * {@code GET  /nasabahs/:id} : get the "id" nasabah.
     *
     * @param id the id of the nasabah to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nasabah, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nasabahs/{id}")
    public ResponseEntity<Nasabah> getNasabah(@PathVariable Long id) {
        log.debug("REST request to get Nasabah : {}", id);
        Optional<Nasabah> nasabah = nasabahRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(nasabah);
    }

    /**
     * {@code DELETE  /nasabahs/:id} : delete the "id" nasabah.
     *
     * @param id the id of the nasabah to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nasabahs/{id}")
    public ResponseEntity<Void> deleteNasabah(@PathVariable Long id) {
        log.debug("REST request to delete Nasabah : {}", id);
        nasabahRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

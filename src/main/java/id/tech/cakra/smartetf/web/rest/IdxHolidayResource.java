package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.domain.IdxHoliday;
import id.tech.cakra.smartetf.repository.IdxHolidayRepository;
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
 * REST controller for managing {@link id.tech.cakra.smartetf.domain.IdxHoliday}.
 */
@RestController
@RequestMapping("/api")
public class IdxHolidayResource {

    private final Logger log = LoggerFactory.getLogger(IdxHolidayResource.class);

    private static final String ENTITY_NAME = "idxHoliday";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IdxHolidayRepository idxHolidayRepository;

    public IdxHolidayResource(IdxHolidayRepository idxHolidayRepository) {
        this.idxHolidayRepository = idxHolidayRepository;
    }

    /**
     * {@code POST  /idx-holidays} : Create a new idxHoliday.
     *
     * @param idxHoliday the idxHoliday to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new idxHoliday, or with status {@code 400 (Bad Request)} if the idxHoliday has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/idx-holidays")
    public ResponseEntity<IdxHoliday> createIdxHoliday(@Valid @RequestBody IdxHoliday idxHoliday) throws URISyntaxException {
        log.debug("REST request to save IdxHoliday : {}", idxHoliday);
        if (idxHoliday.getId() != null) {
            throw new BadRequestAlertException("A new idxHoliday cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IdxHoliday result = idxHolidayRepository.save(idxHoliday);
        return ResponseEntity.created(new URI("/api/idx-holidays/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /idx-holidays} : Updates an existing idxHoliday.
     *
     * @param idxHoliday the idxHoliday to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated idxHoliday,
     * or with status {@code 400 (Bad Request)} if the idxHoliday is not valid,
     * or with status {@code 500 (Internal Server Error)} if the idxHoliday couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/idx-holidays")
    public ResponseEntity<IdxHoliday> updateIdxHoliday(@Valid @RequestBody IdxHoliday idxHoliday) throws URISyntaxException {
        log.debug("REST request to update IdxHoliday : {}", idxHoliday);
        if (idxHoliday.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IdxHoliday result = idxHolidayRepository.save(idxHoliday);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, idxHoliday.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /idx-holidays} : get all the idxHolidays.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of idxHolidays in body.
     */
    @GetMapping("/idx-holidays")
    public List<IdxHoliday> getAllIdxHolidays() {
        log.debug("REST request to get all IdxHolidays");
        return idxHolidayRepository.findAll();
    }

    /**
     * {@code GET  /idx-holidays/:id} : get the "id" idxHoliday.
     *
     * @param id the id of the idxHoliday to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the idxHoliday, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/idx-holidays/{id}")
    public ResponseEntity<IdxHoliday> getIdxHoliday(@PathVariable Long id) {
        log.debug("REST request to get IdxHoliday : {}", id);
        Optional<IdxHoliday> idxHoliday = idxHolidayRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(idxHoliday);
    }

    /**
     * {@code DELETE  /idx-holidays/:id} : delete the "id" idxHoliday.
     *
     * @param id the id of the idxHoliday to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/idx-holidays/{id}")
    public ResponseEntity<Void> deleteIdxHoliday(@PathVariable Long id) {
        log.debug("REST request to delete IdxHoliday : {}", id);
        idxHolidayRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.domain.PengajuanGadaiEfekHeader;
import id.tech.cakra.smartetf.repository.PengajuanGadaiEfekHeaderRepository;
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
 * REST controller for managing {@link id.tech.cakra.smartetf.domain.PengajuanGadaiEfekHeader}.
 */
@RestController
@RequestMapping("/api")
public class PengajuanGadaiEfekHeaderResource {

    private final Logger log = LoggerFactory.getLogger(PengajuanGadaiEfekHeaderResource.class);

    private static final String ENTITY_NAME = "pengajuanGadaiEfekHeader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PengajuanGadaiEfekHeaderRepository pengajuanGadaiEfekHeaderRepository;

    public PengajuanGadaiEfekHeaderResource(PengajuanGadaiEfekHeaderRepository pengajuanGadaiEfekHeaderRepository) {
        this.pengajuanGadaiEfekHeaderRepository = pengajuanGadaiEfekHeaderRepository;
    }

    /**
     * {@code POST  /pengajuan-gadai-efek-headers} : Create a new pengajuanGadaiEfekHeader.
     *
     * @param pengajuanGadaiEfekHeader the pengajuanGadaiEfekHeader to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pengajuanGadaiEfekHeader, or with status {@code 400 (Bad Request)} if the pengajuanGadaiEfekHeader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pengajuan-gadai-efek-headers")
    public ResponseEntity<PengajuanGadaiEfekHeader> createPengajuanGadaiEfekHeader(@Valid @RequestBody PengajuanGadaiEfekHeader pengajuanGadaiEfekHeader) throws URISyntaxException {
        log.debug("REST request to save PengajuanGadaiEfekHeader : {}", pengajuanGadaiEfekHeader);
        if (pengajuanGadaiEfekHeader.getId() != null) {
            throw new BadRequestAlertException("A new pengajuanGadaiEfekHeader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PengajuanGadaiEfekHeader result = pengajuanGadaiEfekHeaderRepository.save(pengajuanGadaiEfekHeader);
        return ResponseEntity.created(new URI("/api/pengajuan-gadai-efek-headers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pengajuan-gadai-efek-headers} : Updates an existing pengajuanGadaiEfekHeader.
     *
     * @param pengajuanGadaiEfekHeader the pengajuanGadaiEfekHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pengajuanGadaiEfekHeader,
     * or with status {@code 400 (Bad Request)} if the pengajuanGadaiEfekHeader is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pengajuanGadaiEfekHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pengajuan-gadai-efek-headers")
    public ResponseEntity<PengajuanGadaiEfekHeader> updatePengajuanGadaiEfekHeader(@Valid @RequestBody PengajuanGadaiEfekHeader pengajuanGadaiEfekHeader) throws URISyntaxException {
        log.debug("REST request to update PengajuanGadaiEfekHeader : {}", pengajuanGadaiEfekHeader);
        if (pengajuanGadaiEfekHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PengajuanGadaiEfekHeader result = pengajuanGadaiEfekHeaderRepository.save(pengajuanGadaiEfekHeader);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pengajuanGadaiEfekHeader.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pengajuan-gadai-efek-headers} : get all the pengajuanGadaiEfekHeaders.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pengajuanGadaiEfekHeaders in body.
     */
    @GetMapping("/pengajuan-gadai-efek-headers")
    public List<PengajuanGadaiEfekHeader> getAllPengajuanGadaiEfekHeaders() {
        log.debug("REST request to get all PengajuanGadaiEfekHeaders");
        return pengajuanGadaiEfekHeaderRepository.findAll();
    }

    /**
     * {@code GET  /pengajuan-gadai-efek-headers/:id} : get the "id" pengajuanGadaiEfekHeader.
     *
     * @param id the id of the pengajuanGadaiEfekHeader to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pengajuanGadaiEfekHeader, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pengajuan-gadai-efek-headers/{id}")
    public ResponseEntity<PengajuanGadaiEfekHeader> getPengajuanGadaiEfekHeader(@PathVariable Long id) {
        log.debug("REST request to get PengajuanGadaiEfekHeader : {}", id);
        Optional<PengajuanGadaiEfekHeader> pengajuanGadaiEfekHeader = pengajuanGadaiEfekHeaderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pengajuanGadaiEfekHeader);
    }

    /**
     * {@code DELETE  /pengajuan-gadai-efek-headers/:id} : delete the "id" pengajuanGadaiEfekHeader.
     *
     * @param id the id of the pengajuanGadaiEfekHeader to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pengajuan-gadai-efek-headers/{id}")
    public ResponseEntity<Void> deletePengajuanGadaiEfekHeader(@PathVariable Long id) {
        log.debug("REST request to delete PengajuanGadaiEfekHeader : {}", id);
        pengajuanGadaiEfekHeaderRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

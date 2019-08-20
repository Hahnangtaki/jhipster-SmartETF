package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.domain.PengajuanGadaiEfekDtl;
import id.tech.cakra.smartetf.repository.PengajuanGadaiEfekDtlRepository;
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
 * REST controller for managing {@link id.tech.cakra.smartetf.domain.PengajuanGadaiEfekDtl}.
 */
@RestController
@RequestMapping("/api")
public class PengajuanGadaiEfekDtlResource {

    private final Logger log = LoggerFactory.getLogger(PengajuanGadaiEfekDtlResource.class);

    private static final String ENTITY_NAME = "pengajuanGadaiEfekDtl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PengajuanGadaiEfekDtlRepository pengajuanGadaiEfekDtlRepository;

    public PengajuanGadaiEfekDtlResource(PengajuanGadaiEfekDtlRepository pengajuanGadaiEfekDtlRepository) {
        this.pengajuanGadaiEfekDtlRepository = pengajuanGadaiEfekDtlRepository;
    }

    /**
     * {@code POST  /pengajuan-gadai-efek-dtls} : Create a new pengajuanGadaiEfekDtl.
     *
     * @param pengajuanGadaiEfekDtl the pengajuanGadaiEfekDtl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pengajuanGadaiEfekDtl, or with status {@code 400 (Bad Request)} if the pengajuanGadaiEfekDtl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pengajuan-gadai-efek-dtls")
    public ResponseEntity<PengajuanGadaiEfekDtl> createPengajuanGadaiEfekDtl(@Valid @RequestBody PengajuanGadaiEfekDtl pengajuanGadaiEfekDtl) throws URISyntaxException {
        log.debug("REST request to save PengajuanGadaiEfekDtl : {}", pengajuanGadaiEfekDtl);
        if (pengajuanGadaiEfekDtl.getId() != null) {
            throw new BadRequestAlertException("A new pengajuanGadaiEfekDtl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PengajuanGadaiEfekDtl result = pengajuanGadaiEfekDtlRepository.save(pengajuanGadaiEfekDtl);
        return ResponseEntity.created(new URI("/api/pengajuan-gadai-efek-dtls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pengajuan-gadai-efek-dtls} : Updates an existing pengajuanGadaiEfekDtl.
     *
     * @param pengajuanGadaiEfekDtl the pengajuanGadaiEfekDtl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pengajuanGadaiEfekDtl,
     * or with status {@code 400 (Bad Request)} if the pengajuanGadaiEfekDtl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pengajuanGadaiEfekDtl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pengajuan-gadai-efek-dtls")
    public ResponseEntity<PengajuanGadaiEfekDtl> updatePengajuanGadaiEfekDtl(@Valid @RequestBody PengajuanGadaiEfekDtl pengajuanGadaiEfekDtl) throws URISyntaxException {
        log.debug("REST request to update PengajuanGadaiEfekDtl : {}", pengajuanGadaiEfekDtl);
        if (pengajuanGadaiEfekDtl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PengajuanGadaiEfekDtl result = pengajuanGadaiEfekDtlRepository.save(pengajuanGadaiEfekDtl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pengajuanGadaiEfekDtl.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pengajuan-gadai-efek-dtls} : get all the pengajuanGadaiEfekDtls.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pengajuanGadaiEfekDtls in body.
     */
    @GetMapping("/pengajuan-gadai-efek-dtls")
    public List<PengajuanGadaiEfekDtl> getAllPengajuanGadaiEfekDtls() {
        log.debug("REST request to get all PengajuanGadaiEfekDtls");
        return pengajuanGadaiEfekDtlRepository.findAll();
    }

    /**
     * {@code GET  /pengajuan-gadai-efek-dtls/:id} : get the "id" pengajuanGadaiEfekDtl.
     *
     * @param id the id of the pengajuanGadaiEfekDtl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pengajuanGadaiEfekDtl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pengajuan-gadai-efek-dtls/{id}")
    public ResponseEntity<PengajuanGadaiEfekDtl> getPengajuanGadaiEfekDtl(@PathVariable Long id) {
        log.debug("REST request to get PengajuanGadaiEfekDtl : {}", id);
        Optional<PengajuanGadaiEfekDtl> pengajuanGadaiEfekDtl = pengajuanGadaiEfekDtlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pengajuanGadaiEfekDtl);
    }

    /**
     * {@code DELETE  /pengajuan-gadai-efek-dtls/:id} : delete the "id" pengajuanGadaiEfekDtl.
     *
     * @param id the id of the pengajuanGadaiEfekDtl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pengajuan-gadai-efek-dtls/{id}")
    public ResponseEntity<Void> deletePengajuanGadaiEfekDtl(@PathVariable Long id) {
        log.debug("REST request to delete PengajuanGadaiEfekDtl : {}", id);
        pengajuanGadaiEfekDtlRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

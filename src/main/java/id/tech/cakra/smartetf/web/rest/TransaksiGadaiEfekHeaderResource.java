package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.domain.TransaksiGadaiEfekHeader;
import id.tech.cakra.smartetf.repository.TransaksiGadaiEfekHeaderRepository;
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
 * REST controller for managing {@link id.tech.cakra.smartetf.domain.TransaksiGadaiEfekHeader}.
 */
@RestController
@RequestMapping("/api")
public class TransaksiGadaiEfekHeaderResource {

    private final Logger log = LoggerFactory.getLogger(TransaksiGadaiEfekHeaderResource.class);

    private static final String ENTITY_NAME = "transaksiGadaiEfekHeader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransaksiGadaiEfekHeaderRepository transaksiGadaiEfekHeaderRepository;

    public TransaksiGadaiEfekHeaderResource(TransaksiGadaiEfekHeaderRepository transaksiGadaiEfekHeaderRepository) {
        this.transaksiGadaiEfekHeaderRepository = transaksiGadaiEfekHeaderRepository;
    }

    /**
     * {@code POST  /transaksi-gadai-efek-headers} : Create a new transaksiGadaiEfekHeader.
     *
     * @param transaksiGadaiEfekHeader the transaksiGadaiEfekHeader to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transaksiGadaiEfekHeader, or with status {@code 400 (Bad Request)} if the transaksiGadaiEfekHeader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transaksi-gadai-efek-headers")
    public ResponseEntity<TransaksiGadaiEfekHeader> createTransaksiGadaiEfekHeader(@Valid @RequestBody TransaksiGadaiEfekHeader transaksiGadaiEfekHeader) throws URISyntaxException {
        log.debug("REST request to save TransaksiGadaiEfekHeader : {}", transaksiGadaiEfekHeader);
        if (transaksiGadaiEfekHeader.getId() != null) {
            throw new BadRequestAlertException("A new transaksiGadaiEfekHeader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransaksiGadaiEfekHeader result = transaksiGadaiEfekHeaderRepository.save(transaksiGadaiEfekHeader);
        return ResponseEntity.created(new URI("/api/transaksi-gadai-efek-headers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transaksi-gadai-efek-headers} : Updates an existing transaksiGadaiEfekHeader.
     *
     * @param transaksiGadaiEfekHeader the transaksiGadaiEfekHeader to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transaksiGadaiEfekHeader,
     * or with status {@code 400 (Bad Request)} if the transaksiGadaiEfekHeader is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transaksiGadaiEfekHeader couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transaksi-gadai-efek-headers")
    public ResponseEntity<TransaksiGadaiEfekHeader> updateTransaksiGadaiEfekHeader(@Valid @RequestBody TransaksiGadaiEfekHeader transaksiGadaiEfekHeader) throws URISyntaxException {
        log.debug("REST request to update TransaksiGadaiEfekHeader : {}", transaksiGadaiEfekHeader);
        if (transaksiGadaiEfekHeader.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransaksiGadaiEfekHeader result = transaksiGadaiEfekHeaderRepository.save(transaksiGadaiEfekHeader);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, transaksiGadaiEfekHeader.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transaksi-gadai-efek-headers} : get all the transaksiGadaiEfekHeaders.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transaksiGadaiEfekHeaders in body.
     */
    @GetMapping("/transaksi-gadai-efek-headers")
    public List<TransaksiGadaiEfekHeader> getAllTransaksiGadaiEfekHeaders() {
        log.debug("REST request to get all TransaksiGadaiEfekHeaders");
        return transaksiGadaiEfekHeaderRepository.findAll();
    }

    /**
     * {@code GET  /transaksi-gadai-efek-headers/:id} : get the "id" transaksiGadaiEfekHeader.
     *
     * @param id the id of the transaksiGadaiEfekHeader to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transaksiGadaiEfekHeader, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transaksi-gadai-efek-headers/{id}")
    public ResponseEntity<TransaksiGadaiEfekHeader> getTransaksiGadaiEfekHeader(@PathVariable Long id) {
        log.debug("REST request to get TransaksiGadaiEfekHeader : {}", id);
        Optional<TransaksiGadaiEfekHeader> transaksiGadaiEfekHeader = transaksiGadaiEfekHeaderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(transaksiGadaiEfekHeader);
    }

    /**
     * {@code DELETE  /transaksi-gadai-efek-headers/:id} : delete the "id" transaksiGadaiEfekHeader.
     *
     * @param id the id of the transaksiGadaiEfekHeader to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transaksi-gadai-efek-headers/{id}")
    public ResponseEntity<Void> deleteTransaksiGadaiEfekHeader(@PathVariable Long id) {
        log.debug("REST request to delete TransaksiGadaiEfekHeader : {}", id);
        transaksiGadaiEfekHeaderRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

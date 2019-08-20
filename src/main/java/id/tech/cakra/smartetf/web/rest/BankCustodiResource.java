package id.tech.cakra.smartetf.web.rest;

import id.tech.cakra.smartetf.domain.BankCustodi;
import id.tech.cakra.smartetf.repository.BankCustodiRepository;
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
 * REST controller for managing {@link id.tech.cakra.smartetf.domain.BankCustodi}.
 */
@RestController
@RequestMapping("/api")
public class BankCustodiResource {

    private final Logger log = LoggerFactory.getLogger(BankCustodiResource.class);

    private static final String ENTITY_NAME = "bankCustodi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BankCustodiRepository bankCustodiRepository;

    public BankCustodiResource(BankCustodiRepository bankCustodiRepository) {
        this.bankCustodiRepository = bankCustodiRepository;
    }

    /**
     * {@code POST  /bank-custodis} : Create a new bankCustodi.
     *
     * @param bankCustodi the bankCustodi to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bankCustodi, or with status {@code 400 (Bad Request)} if the bankCustodi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bank-custodis")
    public ResponseEntity<BankCustodi> createBankCustodi(@Valid @RequestBody BankCustodi bankCustodi) throws URISyntaxException {
        log.debug("REST request to save BankCustodi : {}", bankCustodi);
        if (bankCustodi.getId() != null) {
            throw new BadRequestAlertException("A new bankCustodi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BankCustodi result = bankCustodiRepository.save(bankCustodi);
        return ResponseEntity.created(new URI("/api/bank-custodis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bank-custodis} : Updates an existing bankCustodi.
     *
     * @param bankCustodi the bankCustodi to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bankCustodi,
     * or with status {@code 400 (Bad Request)} if the bankCustodi is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bankCustodi couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bank-custodis")
    public ResponseEntity<BankCustodi> updateBankCustodi(@Valid @RequestBody BankCustodi bankCustodi) throws URISyntaxException {
        log.debug("REST request to update BankCustodi : {}", bankCustodi);
        if (bankCustodi.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BankCustodi result = bankCustodiRepository.save(bankCustodi);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bankCustodi.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bank-custodis} : get all the bankCustodis.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bankCustodis in body.
     */
    @GetMapping("/bank-custodis")
    public List<BankCustodi> getAllBankCustodis() {
        log.debug("REST request to get all BankCustodis");
        return bankCustodiRepository.findAll();
    }

    /**
     * {@code GET  /bank-custodis/:id} : get the "id" bankCustodi.
     *
     * @param id the id of the bankCustodi to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bankCustodi, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bank-custodis/{id}")
    public ResponseEntity<BankCustodi> getBankCustodi(@PathVariable Long id) {
        log.debug("REST request to get BankCustodi : {}", id);
        Optional<BankCustodi> bankCustodi = bankCustodiRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bankCustodi);
    }

    /**
     * {@code DELETE  /bank-custodis/:id} : delete the "id" bankCustodi.
     *
     * @param id the id of the bankCustodi to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bank-custodis/{id}")
    public ResponseEntity<Void> deleteBankCustodi(@PathVariable Long id) {
        log.debug("REST request to delete BankCustodi : {}", id);
        bankCustodiRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

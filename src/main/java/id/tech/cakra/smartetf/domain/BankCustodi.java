package id.tech.cakra.smartetf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A BankCustodi.
 */
@Entity
@Table(name = "bank_custodi")
public class BankCustodi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "kode_custodi", length = 20, nullable = false, unique = true)
    private String kodeCustodi;

    @NotNull
    @Size(max = 150)
    @Column(name = "nama_custodi", length = 150, nullable = false)
    private String namaCustodi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodeCustodi() {
        return kodeCustodi;
    }

    public BankCustodi kodeCustodi(String kodeCustodi) {
        this.kodeCustodi = kodeCustodi;
        return this;
    }

    public void setKodeCustodi(String kodeCustodi) {
        this.kodeCustodi = kodeCustodi;
    }

    public String getNamaCustodi() {
        return namaCustodi;
    }

    public BankCustodi namaCustodi(String namaCustodi) {
        this.namaCustodi = namaCustodi;
        return this;
    }

    public void setNamaCustodi(String namaCustodi) {
        this.namaCustodi = namaCustodi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankCustodi)) {
            return false;
        }
        return id != null && id.equals(((BankCustodi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BankCustodi{" +
            "id=" + getId() +
            ", kodeCustodi='" + getKodeCustodi() + "'" +
            ", namaCustodi='" + getNamaCustodi() + "'" +
            "}";
    }
}

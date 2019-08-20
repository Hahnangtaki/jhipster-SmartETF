package id.tech.cakra.smartetf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A EksekusiHeader.
 */
@Entity
@Table(name = "eksekusi_header")
public class EksekusiHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "no_eksekusi", length = 20, nullable = false, unique = true)
    private String noEksekusi;

    @Column(name = "tanggal_entri")
    private LocalDate tanggalEntri;

    @Column(name = "tanggal_trade")
    private LocalDate tanggalTrade;

    @Column(name = "tanggal_settle")
    private LocalDate tanggalSettle;

    @Column(name = "kode_broker")
    private String kodeBroker;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoEksekusi() {
        return noEksekusi;
    }

    public EksekusiHeader noEksekusi(String noEksekusi) {
        this.noEksekusi = noEksekusi;
        return this;
    }

    public void setNoEksekusi(String noEksekusi) {
        this.noEksekusi = noEksekusi;
    }

    public LocalDate getTanggalEntri() {
        return tanggalEntri;
    }

    public EksekusiHeader tanggalEntri(LocalDate tanggalEntri) {
        this.tanggalEntri = tanggalEntri;
        return this;
    }

    public void setTanggalEntri(LocalDate tanggalEntri) {
        this.tanggalEntri = tanggalEntri;
    }

    public LocalDate getTanggalTrade() {
        return tanggalTrade;
    }

    public EksekusiHeader tanggalTrade(LocalDate tanggalTrade) {
        this.tanggalTrade = tanggalTrade;
        return this;
    }

    public void setTanggalTrade(LocalDate tanggalTrade) {
        this.tanggalTrade = tanggalTrade;
    }

    public LocalDate getTanggalSettle() {
        return tanggalSettle;
    }

    public EksekusiHeader tanggalSettle(LocalDate tanggalSettle) {
        this.tanggalSettle = tanggalSettle;
        return this;
    }

    public void setTanggalSettle(LocalDate tanggalSettle) {
        this.tanggalSettle = tanggalSettle;
    }

    public String getKodeBroker() {
        return kodeBroker;
    }

    public EksekusiHeader kodeBroker(String kodeBroker) {
        this.kodeBroker = kodeBroker;
        return this;
    }

    public void setKodeBroker(String kodeBroker) {
        this.kodeBroker = kodeBroker;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EksekusiHeader)) {
            return false;
        }
        return id != null && id.equals(((EksekusiHeader) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EksekusiHeader{" +
            "id=" + getId() +
            ", noEksekusi='" + getNoEksekusi() + "'" +
            ", tanggalEntri='" + getTanggalEntri() + "'" +
            ", tanggalTrade='" + getTanggalTrade() + "'" +
            ", tanggalSettle='" + getTanggalSettle() + "'" +
            ", kodeBroker='" + getKodeBroker() + "'" +
            "}";
    }
}

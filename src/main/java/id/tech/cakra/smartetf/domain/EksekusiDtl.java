package id.tech.cakra.smartetf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EksekusiDtl.
 */
@Entity
@Table(name = "eksekusi_dtl")
public class EksekusiDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "no_eksekusi", length = 20, nullable = false)
    private String noEksekusi;

    @NotNull
    @Size(max = 20)
    @Column(name = "nomor_kontrak", length = 20, nullable = false)
    private String nomorKontrak;

    @NotNull
    @Size(max = 20)
    @Column(name = "kode_efek", length = 20, nullable = false)
    private String kodeEfek;

    @Min(value = 0L)
    @Column(name = "quantity")
    private Long quantity;

    @Min(value = 0L)
    @Column(name = "done_qty")
    private Long doneQty;

    @DecimalMin(value = "0")
    @Column(name = "done_amount")
    private Double doneAmount;

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

    public EksekusiDtl noEksekusi(String noEksekusi) {
        this.noEksekusi = noEksekusi;
        return this;
    }

    public void setNoEksekusi(String noEksekusi) {
        this.noEksekusi = noEksekusi;
    }

    public String getNomorKontrak() {
        return nomorKontrak;
    }

    public EksekusiDtl nomorKontrak(String nomorKontrak) {
        this.nomorKontrak = nomorKontrak;
        return this;
    }

    public void setNomorKontrak(String nomorKontrak) {
        this.nomorKontrak = nomorKontrak;
    }

    public String getKodeEfek() {
        return kodeEfek;
    }

    public EksekusiDtl kodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
        return this;
    }

    public void setKodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
    }

    public Long getQuantity() {
        return quantity;
    }

    public EksekusiDtl quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getDoneQty() {
        return doneQty;
    }

    public EksekusiDtl doneQty(Long doneQty) {
        this.doneQty = doneQty;
        return this;
    }

    public void setDoneQty(Long doneQty) {
        this.doneQty = doneQty;
    }

    public Double getDoneAmount() {
        return doneAmount;
    }

    public EksekusiDtl doneAmount(Double doneAmount) {
        this.doneAmount = doneAmount;
        return this;
    }

    public void setDoneAmount(Double doneAmount) {
        this.doneAmount = doneAmount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EksekusiDtl)) {
            return false;
        }
        return id != null && id.equals(((EksekusiDtl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EksekusiDtl{" +
            "id=" + getId() +
            ", noEksekusi='" + getNoEksekusi() + "'" +
            ", nomorKontrak='" + getNomorKontrak() + "'" +
            ", kodeEfek='" + getKodeEfek() + "'" +
            ", quantity=" + getQuantity() +
            ", doneQty=" + getDoneQty() +
            ", doneAmount=" + getDoneAmount() +
            "}";
    }
}

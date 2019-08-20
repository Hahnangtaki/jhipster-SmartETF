package id.tech.cakra.smartetf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PengajuanGadaiEfekDtl.
 */
@Entity
@Table(name = "pengajuan_gadai_efek_dtl")
public class PengajuanGadaiEfekDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "no_pengajuan_gadai", nullable = false)
    private String noPengajuanGadai;

    @NotNull
    @Column(name = "kode_efek", nullable = false)
    private String kodeEfek;

    @Min(value = 1L)
    @Column(name = "quantity")
    private Long quantity;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoPengajuanGadai() {
        return noPengajuanGadai;
    }

    public PengajuanGadaiEfekDtl noPengajuanGadai(String noPengajuanGadai) {
        this.noPengajuanGadai = noPengajuanGadai;
        return this;
    }

    public void setNoPengajuanGadai(String noPengajuanGadai) {
        this.noPengajuanGadai = noPengajuanGadai;
    }

    public String getKodeEfek() {
        return kodeEfek;
    }

    public PengajuanGadaiEfekDtl kodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
        return this;
    }

    public void setKodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
    }

    public Long getQuantity() {
        return quantity;
    }

    public PengajuanGadaiEfekDtl quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PengajuanGadaiEfekDtl)) {
            return false;
        }
        return id != null && id.equals(((PengajuanGadaiEfekDtl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PengajuanGadaiEfekDtl{" +
            "id=" + getId() +
            ", noPengajuanGadai='" + getNoPengajuanGadai() + "'" +
            ", kodeEfek='" + getKodeEfek() + "'" +
            ", quantity=" + getQuantity() +
            "}";
    }
}

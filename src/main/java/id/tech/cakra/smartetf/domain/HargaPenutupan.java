package id.tech.cakra.smartetf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A HargaPenutupan.
 */
@Entity
@Table(name = "harga_penutupan")
public class HargaPenutupan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "tanggal", nullable = false)
    private LocalDate tanggal;

    @NotNull
    @Size(max = 20)
    @Column(name = "kode_efek", length = 20, nullable = false)
    private String kodeEfek;

    @DecimalMin(value = "0")
    @Column(name = "harga")
    private Double harga;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public HargaPenutupan tanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
        return this;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getKodeEfek() {
        return kodeEfek;
    }

    public HargaPenutupan kodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
        return this;
    }

    public void setKodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
    }

    public Double getHarga() {
        return harga;
    }

    public HargaPenutupan harga(Double harga) {
        this.harga = harga;
        return this;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HargaPenutupan)) {
            return false;
        }
        return id != null && id.equals(((HargaPenutupan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "HargaPenutupan{" +
            "id=" + getId() +
            ", tanggal='" + getTanggal() + "'" +
            ", kodeEfek='" + getKodeEfek() + "'" +
            ", harga=" + getHarga() +
            "}";
    }
}

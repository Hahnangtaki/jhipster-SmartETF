package id.tech.cakra.smartetf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A HairCut.
 */
@Entity
@Table(name = "hair_cut")
public class HairCut implements Serializable {

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

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "nilai_hair_cut", nullable = false)
    private Double nilaiHairCut;

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

    public HairCut tanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
        return this;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getKodeEfek() {
        return kodeEfek;
    }

    public HairCut kodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
        return this;
    }

    public void setKodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
    }

    public Double getNilaiHairCut() {
        return nilaiHairCut;
    }

    public HairCut nilaiHairCut(Double nilaiHairCut) {
        this.nilaiHairCut = nilaiHairCut;
        return this;
    }

    public void setNilaiHairCut(Double nilaiHairCut) {
        this.nilaiHairCut = nilaiHairCut;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HairCut)) {
            return false;
        }
        return id != null && id.equals(((HairCut) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "HairCut{" +
            "id=" + getId() +
            ", tanggal='" + getTanggal() + "'" +
            ", kodeEfek='" + getKodeEfek() + "'" +
            ", nilaiHairCut=" + getNilaiHairCut() +
            "}";
    }
}

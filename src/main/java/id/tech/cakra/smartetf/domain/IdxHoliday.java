package id.tech.cakra.smartetf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A IdxHoliday.
 */
@Entity
@Table(name = "idx_holiday")
public class IdxHoliday implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "tanggal", nullable = false, unique = true)
    private LocalDate tanggal;

    @NotNull
    @Size(max = 150)
    @Column(name = "keterangan", length = 150, nullable = false)
    private String keterangan;

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

    public IdxHoliday tanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
        return this;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public IdxHoliday keterangan(String keterangan) {
        this.keterangan = keterangan;
        return this;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IdxHoliday)) {
            return false;
        }
        return id != null && id.equals(((IdxHoliday) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "IdxHoliday{" +
            "id=" + getId() +
            ", tanggal='" + getTanggal() + "'" +
            ", keterangan='" + getKeterangan() + "'" +
            "}";
    }
}

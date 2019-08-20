package id.tech.cakra.smartetf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A TipeEfek.
 */
@Entity
@Table(name = "tipe_efek")
public class TipeEfek implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "tipe_efek", length = 1, nullable = false, unique = true)
    private String tipeEfek;

    @Size(max = 20)
    @Column(name = "keterangan", length = 20)
    private String keterangan;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipeEfek() {
        return tipeEfek;
    }

    public TipeEfek tipeEfek(String tipeEfek) {
        this.tipeEfek = tipeEfek;
        return this;
    }

    public void setTipeEfek(String tipeEfek) {
        this.tipeEfek = tipeEfek;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public TipeEfek keterangan(String keterangan) {
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
        if (!(o instanceof TipeEfek)) {
            return false;
        }
        return id != null && id.equals(((TipeEfek) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TipeEfek{" +
            "id=" + getId() +
            ", tipeEfek='" + getTipeEfek() + "'" +
            ", keterangan='" + getKeterangan() + "'" +
            "}";
    }
}

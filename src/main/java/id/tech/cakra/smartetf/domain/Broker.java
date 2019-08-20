package id.tech.cakra.smartetf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Broker.
 */
@Entity
@Table(name = "broker")
public class Broker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "kode_broker", length = 20, nullable = false, unique = true)
    private String kodeBroker;

    @NotNull
    @Size(max = 150)
    @Column(name = "nama_broker", length = 150, nullable = false)
    private String namaBroker;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodeBroker() {
        return kodeBroker;
    }

    public Broker kodeBroker(String kodeBroker) {
        this.kodeBroker = kodeBroker;
        return this;
    }

    public void setKodeBroker(String kodeBroker) {
        this.kodeBroker = kodeBroker;
    }

    public String getNamaBroker() {
        return namaBroker;
    }

    public Broker namaBroker(String namaBroker) {
        this.namaBroker = namaBroker;
        return this;
    }

    public void setNamaBroker(String namaBroker) {
        this.namaBroker = namaBroker;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Broker)) {
            return false;
        }
        return id != null && id.equals(((Broker) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Broker{" +
            "id=" + getId() +
            ", kodeBroker='" + getKodeBroker() + "'" +
            ", namaBroker='" + getNamaBroker() + "'" +
            "}";
    }
}

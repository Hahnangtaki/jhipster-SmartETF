package id.tech.cakra.smartetf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Nasabah.
 */
@Entity
@Table(name = "nasabah")
public class Nasabah implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "kode_nasabah", length = 20, nullable = false, unique = true)
    private String kodeNasabah;

    @NotNull
    @Size(max = 150)
    @Column(name = "nama_nasabah", length = 150, nullable = false)
    private String namaNasabah;

    @NotNull
    @Size(max = 1)
    @Column(name = "tipe_nasabah", length = 1, nullable = false)
    private String tipeNasabah;

    @Size(max = 15)
    @Column(name = "sid", length = 15)
    private String sid;

    @Size(max = 14)
    @Column(name = "bank_sub_rek", length = 14)
    private String bankSubRek;

    @Size(max = 60)
    @Column(name = "alamat_1", length = 60)
    private String alamat1;

    @Size(max = 60)
    @Column(name = "alamat_2", length = 60)
    private String alamat2;

    @Size(max = 60)
    @Column(name = "alamat_3", length = 60)
    private String alamat3;

    @Size(max = 30)
    @Column(name = "no_telp", length = 30)
    private String noTelp;

    @Size(max = 30)
    @Column(name = "no_fax", length = 30)
    private String noFax;

    @Size(max = 1)
    @Column(name = "status_sub_rek", length = 1)
    private String statusSubRek;

    @Column(name = "status")
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodeNasabah() {
        return kodeNasabah;
    }

    public Nasabah kodeNasabah(String kodeNasabah) {
        this.kodeNasabah = kodeNasabah;
        return this;
    }

    public void setKodeNasabah(String kodeNasabah) {
        this.kodeNasabah = kodeNasabah;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public Nasabah namaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
        return this;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public String getTipeNasabah() {
        return tipeNasabah;
    }

    public Nasabah tipeNasabah(String tipeNasabah) {
        this.tipeNasabah = tipeNasabah;
        return this;
    }

    public void setTipeNasabah(String tipeNasabah) {
        this.tipeNasabah = tipeNasabah;
    }

    public String getSid() {
        return sid;
    }

    public Nasabah sid(String sid) {
        this.sid = sid;
        return this;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getBankSubRek() {
        return bankSubRek;
    }

    public Nasabah bankSubRek(String bankSubRek) {
        this.bankSubRek = bankSubRek;
        return this;
    }

    public void setBankSubRek(String bankSubRek) {
        this.bankSubRek = bankSubRek;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public Nasabah alamat1(String alamat1) {
        this.alamat1 = alamat1;
        return this;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public Nasabah alamat2(String alamat2) {
        this.alamat2 = alamat2;
        return this;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public Nasabah alamat3(String alamat3) {
        this.alamat3 = alamat3;
        return this;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public Nasabah noTelp(String noTelp) {
        this.noTelp = noTelp;
        return this;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getNoFax() {
        return noFax;
    }

    public Nasabah noFax(String noFax) {
        this.noFax = noFax;
        return this;
    }

    public void setNoFax(String noFax) {
        this.noFax = noFax;
    }

    public String getStatusSubRek() {
        return statusSubRek;
    }

    public Nasabah statusSubRek(String statusSubRek) {
        this.statusSubRek = statusSubRek;
        return this;
    }

    public void setStatusSubRek(String statusSubRek) {
        this.statusSubRek = statusSubRek;
    }

    public String getStatus() {
        return status;
    }

    public Nasabah status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Nasabah)) {
            return false;
        }
        return id != null && id.equals(((Nasabah) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Nasabah{" +
            "id=" + getId() +
            ", kodeNasabah='" + getKodeNasabah() + "'" +
            ", namaNasabah='" + getNamaNasabah() + "'" +
            ", tipeNasabah='" + getTipeNasabah() + "'" +
            ", sid='" + getSid() + "'" +
            ", bankSubRek='" + getBankSubRek() + "'" +
            ", alamat1='" + getAlamat1() + "'" +
            ", alamat2='" + getAlamat2() + "'" +
            ", alamat3='" + getAlamat3() + "'" +
            ", noTelp='" + getNoTelp() + "'" +
            ", noFax='" + getNoFax() + "'" +
            ", statusSubRek='" + getStatusSubRek() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}

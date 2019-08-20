package id.tech.cakra.smartetf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Efek.
 */
@Entity
@Table(name = "efek")
public class Efek implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "kode_efek", length = 20, nullable = false, unique = true)
    private String kodeEfek;

    @NotNull
    @Size(max = 150)
    @Column(name = "nama_efek", length = 150, nullable = false)
    private String namaEfek;

    @NotNull
    @Column(name = "tipe_efek", nullable = false)
    private String tipeEfek;

    @DecimalMin(value = "0")
    @Column(name = "last_closing_price")
    private Double lastClosingPrice;

    @Column(name = "last_closing_date")
    private LocalDate lastClosingDate;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "last_hair_cut", nullable = false)
    private Double lastHairCut;

    @Column(name = "last_hair_cut_date")
    private LocalDate lastHairCutDate;

    @Column(name = "status_gadai")
    private Boolean statusGadai;

    @Min(value = 0L)
    @Column(name = "jlh_efek_beredar")
    private Long jlhEfekBeredar;

    @DecimalMin(value = "0")
    @Column(name = "bmpk")
    private Double bmpk;

    @Size(max = 10)
    @Column(name = "bond_rating", length = 10)
    private String bondRating;

    @Column(name = "bond_maturity_date")
    private LocalDate bondMaturityDate;

    @Min(value = 1L)
    @Column(name = "satuan")
    private Long satuan;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodeEfek() {
        return kodeEfek;
    }

    public Efek kodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
        return this;
    }

    public void setKodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
    }

    public String getNamaEfek() {
        return namaEfek;
    }

    public Efek namaEfek(String namaEfek) {
        this.namaEfek = namaEfek;
        return this;
    }

    public void setNamaEfek(String namaEfek) {
        this.namaEfek = namaEfek;
    }

    public String getTipeEfek() {
        return tipeEfek;
    }

    public Efek tipeEfek(String tipeEfek) {
        this.tipeEfek = tipeEfek;
        return this;
    }

    public void setTipeEfek(String tipeEfek) {
        this.tipeEfek = tipeEfek;
    }

    public Double getLastClosingPrice() {
        return lastClosingPrice;
    }

    public Efek lastClosingPrice(Double lastClosingPrice) {
        this.lastClosingPrice = lastClosingPrice;
        return this;
    }

    public void setLastClosingPrice(Double lastClosingPrice) {
        this.lastClosingPrice = lastClosingPrice;
    }

    public LocalDate getLastClosingDate() {
        return lastClosingDate;
    }

    public Efek lastClosingDate(LocalDate lastClosingDate) {
        this.lastClosingDate = lastClosingDate;
        return this;
    }

    public void setLastClosingDate(LocalDate lastClosingDate) {
        this.lastClosingDate = lastClosingDate;
    }

    public Double getLastHairCut() {
        return lastHairCut;
    }

    public Efek lastHairCut(Double lastHairCut) {
        this.lastHairCut = lastHairCut;
        return this;
    }

    public void setLastHairCut(Double lastHairCut) {
        this.lastHairCut = lastHairCut;
    }

    public LocalDate getLastHairCutDate() {
        return lastHairCutDate;
    }

    public Efek lastHairCutDate(LocalDate lastHairCutDate) {
        this.lastHairCutDate = lastHairCutDate;
        return this;
    }

    public void setLastHairCutDate(LocalDate lastHairCutDate) {
        this.lastHairCutDate = lastHairCutDate;
    }

    public Boolean isStatusGadai() {
        return statusGadai;
    }

    public Efek statusGadai(Boolean statusGadai) {
        this.statusGadai = statusGadai;
        return this;
    }

    public void setStatusGadai(Boolean statusGadai) {
        this.statusGadai = statusGadai;
    }

    public Long getJlhEfekBeredar() {
        return jlhEfekBeredar;
    }

    public Efek jlhEfekBeredar(Long jlhEfekBeredar) {
        this.jlhEfekBeredar = jlhEfekBeredar;
        return this;
    }

    public void setJlhEfekBeredar(Long jlhEfekBeredar) {
        this.jlhEfekBeredar = jlhEfekBeredar;
    }

    public Double getBmpk() {
        return bmpk;
    }

    public Efek bmpk(Double bmpk) {
        this.bmpk = bmpk;
        return this;
    }

    public void setBmpk(Double bmpk) {
        this.bmpk = bmpk;
    }

    public String getBondRating() {
        return bondRating;
    }

    public Efek bondRating(String bondRating) {
        this.bondRating = bondRating;
        return this;
    }

    public void setBondRating(String bondRating) {
        this.bondRating = bondRating;
    }

    public LocalDate getBondMaturityDate() {
        return bondMaturityDate;
    }

    public Efek bondMaturityDate(LocalDate bondMaturityDate) {
        this.bondMaturityDate = bondMaturityDate;
        return this;
    }

    public void setBondMaturityDate(LocalDate bondMaturityDate) {
        this.bondMaturityDate = bondMaturityDate;
    }

    public Long getSatuan() {
        return satuan;
    }

    public Efek satuan(Long satuan) {
        this.satuan = satuan;
        return this;
    }

    public void setSatuan(Long satuan) {
        this.satuan = satuan;
    }

    public String getStatus() {
        return status;
    }

    public Efek status(String status) {
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
        if (!(o instanceof Efek)) {
            return false;
        }
        return id != null && id.equals(((Efek) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Efek{" +
            "id=" + getId() +
            ", kodeEfek='" + getKodeEfek() + "'" +
            ", namaEfek='" + getNamaEfek() + "'" +
            ", tipeEfek='" + getTipeEfek() + "'" +
            ", lastClosingPrice=" + getLastClosingPrice() +
            ", lastClosingDate='" + getLastClosingDate() + "'" +
            ", lastHairCut=" + getLastHairCut() +
            ", lastHairCutDate='" + getLastHairCutDate() + "'" +
            ", statusGadai='" + isStatusGadai() + "'" +
            ", jlhEfekBeredar=" + getJlhEfekBeredar() +
            ", bmpk=" + getBmpk() +
            ", bondRating='" + getBondRating() + "'" +
            ", bondMaturityDate='" + getBondMaturityDate() + "'" +
            ", satuan=" + getSatuan() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}

package id.tech.cakra.smartetf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EksekusiSummary.
 */
@Entity
@Table(name = "eksekusi_summary")
public class EksekusiSummary implements Serializable {

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
    @Column(name = "kode_efek", length = 20, nullable = false)
    private String kodeEfek;

    @DecimalMin(value = "0")
    @Column(name = "harga_jual")
    private Double hargaJual;

    @Min(value = 0L)
    @Column(name = "quantity")
    private Long quantity;

    @Min(value = 0L)
    @Column(name = "done_qty")
    private Long doneQty;

    @DecimalMin(value = "0")
    @Column(name = "amount")
    private Double amount;

    @DecimalMin(value = "0")
    @Column(name = "biaya")
    private Double biaya;

    @DecimalMin(value = "0")
    @Column(name = "net_amount")
    private Double netAmount;

    @Min(value = 0L)
    @Column(name = "alokasi_qty")
    private Long alokasiQty;

    @DecimalMin(value = "0")
    @Column(name = "aloksi_amount")
    private Double aloksiAmount;

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

    public EksekusiSummary noEksekusi(String noEksekusi) {
        this.noEksekusi = noEksekusi;
        return this;
    }

    public void setNoEksekusi(String noEksekusi) {
        this.noEksekusi = noEksekusi;
    }

    public String getKodeEfek() {
        return kodeEfek;
    }

    public EksekusiSummary kodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
        return this;
    }

    public void setKodeEfek(String kodeEfek) {
        this.kodeEfek = kodeEfek;
    }

    public Double getHargaJual() {
        return hargaJual;
    }

    public EksekusiSummary hargaJual(Double hargaJual) {
        this.hargaJual = hargaJual;
        return this;
    }

    public void setHargaJual(Double hargaJual) {
        this.hargaJual = hargaJual;
    }

    public Long getQuantity() {
        return quantity;
    }

    public EksekusiSummary quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getDoneQty() {
        return doneQty;
    }

    public EksekusiSummary doneQty(Long doneQty) {
        this.doneQty = doneQty;
        return this;
    }

    public void setDoneQty(Long doneQty) {
        this.doneQty = doneQty;
    }

    public Double getAmount() {
        return amount;
    }

    public EksekusiSummary amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBiaya() {
        return biaya;
    }

    public EksekusiSummary biaya(Double biaya) {
        this.biaya = biaya;
        return this;
    }

    public void setBiaya(Double biaya) {
        this.biaya = biaya;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public EksekusiSummary netAmount(Double netAmount) {
        this.netAmount = netAmount;
        return this;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public Long getAlokasiQty() {
        return alokasiQty;
    }

    public EksekusiSummary alokasiQty(Long alokasiQty) {
        this.alokasiQty = alokasiQty;
        return this;
    }

    public void setAlokasiQty(Long alokasiQty) {
        this.alokasiQty = alokasiQty;
    }

    public Double getAloksiAmount() {
        return aloksiAmount;
    }

    public EksekusiSummary aloksiAmount(Double aloksiAmount) {
        this.aloksiAmount = aloksiAmount;
        return this;
    }

    public void setAloksiAmount(Double aloksiAmount) {
        this.aloksiAmount = aloksiAmount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EksekusiSummary)) {
            return false;
        }
        return id != null && id.equals(((EksekusiSummary) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EksekusiSummary{" +
            "id=" + getId() +
            ", noEksekusi='" + getNoEksekusi() + "'" +
            ", kodeEfek='" + getKodeEfek() + "'" +
            ", hargaJual=" + getHargaJual() +
            ", quantity=" + getQuantity() +
            ", doneQty=" + getDoneQty() +
            ", amount=" + getAmount() +
            ", biaya=" + getBiaya() +
            ", netAmount=" + getNetAmount() +
            ", alokasiQty=" + getAlokasiQty() +
            ", aloksiAmount=" + getAloksiAmount() +
            "}";
    }
}

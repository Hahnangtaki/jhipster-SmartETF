package id.tech.cakra.smartetf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A PengajuanGadaiEfekHeader.
 */
@Entity
@Table(name = "pengajuan_gadai_efek_header")
public class PengajuanGadaiEfekHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "no_pengajuan_gadai", length = 20, nullable = false, unique = true)
    private String noPengajuanGadai;

    @Column(name = "tgl_entri")
    private LocalDate tglEntri;

    @Column(name = "tgl_efek_terima")
    private LocalDate tglEfekTerima;

    @Column(name = "kode_nasabah")
    private String kodeNasabah;

    @DecimalMin(value = "0")
    @Column(name = "nilai_pinjaman")
    private Double nilaiPinjaman;

    @Column(name = "counter_part_instruksi")
    private String counterPartInstruksi;

    @NotNull
    @Size(max = 1)
    @Column(name = "status", length = 1, nullable = false)
    private String status;

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

    public PengajuanGadaiEfekHeader noPengajuanGadai(String noPengajuanGadai) {
        this.noPengajuanGadai = noPengajuanGadai;
        return this;
    }

    public void setNoPengajuanGadai(String noPengajuanGadai) {
        this.noPengajuanGadai = noPengajuanGadai;
    }

    public LocalDate getTglEntri() {
        return tglEntri;
    }

    public PengajuanGadaiEfekHeader tglEntri(LocalDate tglEntri) {
        this.tglEntri = tglEntri;
        return this;
    }

    public void setTglEntri(LocalDate tglEntri) {
        this.tglEntri = tglEntri;
    }

    public LocalDate getTglEfekTerima() {
        return tglEfekTerima;
    }

    public PengajuanGadaiEfekHeader tglEfekTerima(LocalDate tglEfekTerima) {
        this.tglEfekTerima = tglEfekTerima;
        return this;
    }

    public void setTglEfekTerima(LocalDate tglEfekTerima) {
        this.tglEfekTerima = tglEfekTerima;
    }

    public String getKodeNasabah() {
        return kodeNasabah;
    }

    public PengajuanGadaiEfekHeader kodeNasabah(String kodeNasabah) {
        this.kodeNasabah = kodeNasabah;
        return this;
    }

    public void setKodeNasabah(String kodeNasabah) {
        this.kodeNasabah = kodeNasabah;
    }

    public Double getNilaiPinjaman() {
        return nilaiPinjaman;
    }

    public PengajuanGadaiEfekHeader nilaiPinjaman(Double nilaiPinjaman) {
        this.nilaiPinjaman = nilaiPinjaman;
        return this;
    }

    public void setNilaiPinjaman(Double nilaiPinjaman) {
        this.nilaiPinjaman = nilaiPinjaman;
    }

    public String getCounterPartInstruksi() {
        return counterPartInstruksi;
    }

    public PengajuanGadaiEfekHeader counterPartInstruksi(String counterPartInstruksi) {
        this.counterPartInstruksi = counterPartInstruksi;
        return this;
    }

    public void setCounterPartInstruksi(String counterPartInstruksi) {
        this.counterPartInstruksi = counterPartInstruksi;
    }

    public String getStatus() {
        return status;
    }

    public PengajuanGadaiEfekHeader status(String status) {
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
        if (!(o instanceof PengajuanGadaiEfekHeader)) {
            return false;
        }
        return id != null && id.equals(((PengajuanGadaiEfekHeader) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PengajuanGadaiEfekHeader{" +
            "id=" + getId() +
            ", noPengajuanGadai='" + getNoPengajuanGadai() + "'" +
            ", tglEntri='" + getTglEntri() + "'" +
            ", tglEfekTerima='" + getTglEfekTerima() + "'" +
            ", kodeNasabah='" + getKodeNasabah() + "'" +
            ", nilaiPinjaman=" + getNilaiPinjaman() +
            ", counterPartInstruksi='" + getCounterPartInstruksi() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}

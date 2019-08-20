package id.tech.cakra.smartetf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A TransaksiGadaiEfekHeader.
 */
@Entity
@Table(name = "transaksi_gadai_efek_header")
public class TransaksiGadaiEfekHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "no_kontrak", length = 20, nullable = false, unique = true)
    private String noKontrak;

    @NotNull
    @Column(name = "tgl_entri", nullable = false)
    private LocalDate tglEntri;

    @Column(name = "tgl_pencairan")
    private LocalDate tglPencairan;

    @Column(name = "tgl_jatuh_tempo")
    private LocalDate tglJatuhTempo;

    @Column(name = "no_pengajuan_gadai")
    private String noPengajuanGadai;

    @Column(name = "kode_nasabah")
    private String kodeNasabah;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "nilai_kewajiban", nullable = false)
    private Double nilaiKewajiban;

    @Column(name = "tgl_kirim_efek")
    private LocalDate tglKirimEfek;

    @Column(name = "counter_part_instruksi")
    private String counterPartInstruksi;

    @Column(name = "status")
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoKontrak() {
        return noKontrak;
    }

    public TransaksiGadaiEfekHeader noKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
        return this;
    }

    public void setNoKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
    }

    public LocalDate getTglEntri() {
        return tglEntri;
    }

    public TransaksiGadaiEfekHeader tglEntri(LocalDate tglEntri) {
        this.tglEntri = tglEntri;
        return this;
    }

    public void setTglEntri(LocalDate tglEntri) {
        this.tglEntri = tglEntri;
    }

    public LocalDate getTglPencairan() {
        return tglPencairan;
    }

    public TransaksiGadaiEfekHeader tglPencairan(LocalDate tglPencairan) {
        this.tglPencairan = tglPencairan;
        return this;
    }

    public void setTglPencairan(LocalDate tglPencairan) {
        this.tglPencairan = tglPencairan;
    }

    public LocalDate getTglJatuhTempo() {
        return tglJatuhTempo;
    }

    public TransaksiGadaiEfekHeader tglJatuhTempo(LocalDate tglJatuhTempo) {
        this.tglJatuhTempo = tglJatuhTempo;
        return this;
    }

    public void setTglJatuhTempo(LocalDate tglJatuhTempo) {
        this.tglJatuhTempo = tglJatuhTempo;
    }

    public String getNoPengajuanGadai() {
        return noPengajuanGadai;
    }

    public TransaksiGadaiEfekHeader noPengajuanGadai(String noPengajuanGadai) {
        this.noPengajuanGadai = noPengajuanGadai;
        return this;
    }

    public void setNoPengajuanGadai(String noPengajuanGadai) {
        this.noPengajuanGadai = noPengajuanGadai;
    }

    public String getKodeNasabah() {
        return kodeNasabah;
    }

    public TransaksiGadaiEfekHeader kodeNasabah(String kodeNasabah) {
        this.kodeNasabah = kodeNasabah;
        return this;
    }

    public void setKodeNasabah(String kodeNasabah) {
        this.kodeNasabah = kodeNasabah;
    }

    public Double getNilaiKewajiban() {
        return nilaiKewajiban;
    }

    public TransaksiGadaiEfekHeader nilaiKewajiban(Double nilaiKewajiban) {
        this.nilaiKewajiban = nilaiKewajiban;
        return this;
    }

    public void setNilaiKewajiban(Double nilaiKewajiban) {
        this.nilaiKewajiban = nilaiKewajiban;
    }

    public LocalDate getTglKirimEfek() {
        return tglKirimEfek;
    }

    public TransaksiGadaiEfekHeader tglKirimEfek(LocalDate tglKirimEfek) {
        this.tglKirimEfek = tglKirimEfek;
        return this;
    }

    public void setTglKirimEfek(LocalDate tglKirimEfek) {
        this.tglKirimEfek = tglKirimEfek;
    }

    public String getCounterPartInstruksi() {
        return counterPartInstruksi;
    }

    public TransaksiGadaiEfekHeader counterPartInstruksi(String counterPartInstruksi) {
        this.counterPartInstruksi = counterPartInstruksi;
        return this;
    }

    public void setCounterPartInstruksi(String counterPartInstruksi) {
        this.counterPartInstruksi = counterPartInstruksi;
    }

    public String getStatus() {
        return status;
    }

    public TransaksiGadaiEfekHeader status(String status) {
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
        if (!(o instanceof TransaksiGadaiEfekHeader)) {
            return false;
        }
        return id != null && id.equals(((TransaksiGadaiEfekHeader) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TransaksiGadaiEfekHeader{" +
            "id=" + getId() +
            ", noKontrak='" + getNoKontrak() + "'" +
            ", tglEntri='" + getTglEntri() + "'" +
            ", tglPencairan='" + getTglPencairan() + "'" +
            ", tglJatuhTempo='" + getTglJatuhTempo() + "'" +
            ", noPengajuanGadai='" + getNoPengajuanGadai() + "'" +
            ", kodeNasabah='" + getKodeNasabah() + "'" +
            ", nilaiKewajiban=" + getNilaiKewajiban() +
            ", tglKirimEfek='" + getTglKirimEfek() + "'" +
            ", counterPartInstruksi='" + getCounterPartInstruksi() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}

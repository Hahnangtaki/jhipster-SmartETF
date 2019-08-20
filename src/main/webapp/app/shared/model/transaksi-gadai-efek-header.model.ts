import { Moment } from 'moment';

export interface ITransaksiGadaiEfekHeader {
  id?: number;
  noKontrak?: string;
  tglEntri?: Moment;
  tglPencairan?: Moment;
  tglJatuhTempo?: Moment;
  noPengajuanGadai?: string;
  kodeNasabah?: string;
  nilaiKewajiban?: number;
  tglKirimEfek?: Moment;
  counterPartInstruksi?: string;
  status?: string;
}

export const defaultValue: Readonly<ITransaksiGadaiEfekHeader> = {};

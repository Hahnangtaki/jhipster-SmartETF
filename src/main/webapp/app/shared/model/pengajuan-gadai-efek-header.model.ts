import { Moment } from 'moment';

export interface IPengajuanGadaiEfekHeader {
  id?: number;
  noPengajuanGadai?: string;
  tglEntri?: Moment;
  tglEfekTerima?: Moment;
  kodeNasabah?: string;
  nilaiPinjaman?: number;
  counterPartInstruksi?: string;
  status?: string;
}

export const defaultValue: Readonly<IPengajuanGadaiEfekHeader> = {};

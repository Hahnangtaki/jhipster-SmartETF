import { Moment } from 'moment';

export interface IHargaPenutupan {
  id?: number;
  tanggal?: Moment;
  kodeEfek?: string;
  harga?: number;
}

export const defaultValue: Readonly<IHargaPenutupan> = {};

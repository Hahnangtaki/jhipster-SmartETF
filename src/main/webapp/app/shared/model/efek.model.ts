import { Moment } from 'moment';

export interface IEfek {
  id?: number;
  kodeEfek?: string;
  namaEfek?: string;
  tipeEfek?: string;
  lastClosingPrice?: number;
  lastClosingDate?: Moment;
  lastHairCut?: number;
  lastHairCutDate?: Moment;
  statusGadai?: boolean;
  jlhEfekBeredar?: number;
  bmpk?: number;
  bondRating?: string;
  bondMaturityDate?: Moment;
  satuan?: number;
  status?: string;
}

export const defaultValue: Readonly<IEfek> = {
  statusGadai: false
};

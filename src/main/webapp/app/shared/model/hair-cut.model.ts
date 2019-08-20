import { Moment } from 'moment';

export interface IHairCut {
  id?: number;
  tanggal?: Moment;
  kodeEfek?: string;
  nilaiHairCut?: number;
}

export const defaultValue: Readonly<IHairCut> = {};

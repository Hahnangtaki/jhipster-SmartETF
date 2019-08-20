import { Moment } from 'moment';

export interface IEksekusiHeader {
  id?: number;
  noEksekusi?: string;
  tanggalEntri?: Moment;
  tanggalTrade?: Moment;
  tanggalSettle?: Moment;
  kodeBroker?: string;
}

export const defaultValue: Readonly<IEksekusiHeader> = {};

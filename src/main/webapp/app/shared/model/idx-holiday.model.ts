import { Moment } from 'moment';

export interface IIdxHoliday {
  id?: number;
  tanggal?: Moment;
  keterangan?: string;
}

export const defaultValue: Readonly<IIdxHoliday> = {};

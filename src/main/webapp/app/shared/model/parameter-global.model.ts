import { Moment } from 'moment';

export interface IParameterGlobal {
  id?: number;
  prmId?: string;
  prmName?: string;
  prmTy?: string;
  appType?: string;
  intVal?: number;
  floatVal?: number;
  strVal?: string;
  dateVal?: Moment;
  show?: boolean;
  edit?: boolean;
}

export const defaultValue: Readonly<IParameterGlobal> = {
  show: false,
  edit: false
};

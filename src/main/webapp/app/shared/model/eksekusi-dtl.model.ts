export interface IEksekusiDtl {
  id?: number;
  noEksekusi?: string;
  nomorKontrak?: string;
  kodeEfek?: string;
  quantity?: number;
  doneQty?: number;
  doneAmount?: number;
}

export const defaultValue: Readonly<IEksekusiDtl> = {};

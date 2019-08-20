export interface IEksekusiSummary {
  id?: number;
  noEksekusi?: string;
  kodeEfek?: string;
  hargaJual?: number;
  quantity?: number;
  doneQty?: number;
  amount?: number;
  biaya?: number;
  netAmount?: number;
  alokasiQty?: number;
  aloksiAmount?: number;
}

export const defaultValue: Readonly<IEksekusiSummary> = {};

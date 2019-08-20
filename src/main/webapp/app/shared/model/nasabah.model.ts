export interface INasabah {
  id?: number;
  kodeNasabah?: string;
  namaNasabah?: string;
  tipeNasabah?: string;
  sid?: string;
  bankSubRek?: string;
  alamat1?: string;
  alamat2?: string;
  alamat3?: string;
  noTelp?: string;
  noFax?: string;
  statusSubRek?: string;
  status?: string;
}

export const defaultValue: Readonly<INasabah> = {};

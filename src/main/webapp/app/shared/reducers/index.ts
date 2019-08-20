import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import parameterGlobal, {
  ParameterGlobalState
} from 'app/entities/parameter-global/parameter-global.reducer';
// prettier-ignore
import tipeEfek, {
  TipeEfekState
} from 'app/entities/tipe-efek/tipe-efek.reducer';
// prettier-ignore
import nasabah, {
  NasabahState
} from 'app/entities/nasabah/nasabah.reducer';
// prettier-ignore
import efek, {
  EfekState
} from 'app/entities/efek/efek.reducer';
// prettier-ignore
import broker, {
  BrokerState
} from 'app/entities/broker/broker.reducer';
// prettier-ignore
import bankCustodi, {
  BankCustodiState
} from 'app/entities/bank-custodi/bank-custodi.reducer';
// prettier-ignore
import idxHoliday, {
  IdxHolidayState
} from 'app/entities/idx-holiday/idx-holiday.reducer';
// prettier-ignore
import hargaPenutupan, {
  HargaPenutupanState
} from 'app/entities/harga-penutupan/harga-penutupan.reducer';
// prettier-ignore
import hairCut, {
  HairCutState
} from 'app/entities/hair-cut/hair-cut.reducer';
// prettier-ignore
import pengajuanGadaiEfekHeader, {
  PengajuanGadaiEfekHeaderState
} from 'app/entities/pengajuan-gadai-efek-header/pengajuan-gadai-efek-header.reducer';
// prettier-ignore
import pengajuanGadaiEfekDtl, {
  PengajuanGadaiEfekDtlState
} from 'app/entities/pengajuan-gadai-efek-dtl/pengajuan-gadai-efek-dtl.reducer';
// prettier-ignore
import transaksiGadaiEfekHeader, {
  TransaksiGadaiEfekHeaderState
} from 'app/entities/transaksi-gadai-efek-header/transaksi-gadai-efek-header.reducer';
// prettier-ignore
import eksekusiHeader, {
  EksekusiHeaderState
} from 'app/entities/eksekusi-header/eksekusi-header.reducer';
// prettier-ignore
import eksekusiDtl, {
  EksekusiDtlState
} from 'app/entities/eksekusi-dtl/eksekusi-dtl.reducer';
// prettier-ignore
import eksekusiSummary, {
  EksekusiSummaryState
} from 'app/entities/eksekusi-summary/eksekusi-summary.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly parameterGlobal: ParameterGlobalState;
  readonly tipeEfek: TipeEfekState;
  readonly nasabah: NasabahState;
  readonly efek: EfekState;
  readonly broker: BrokerState;
  readonly bankCustodi: BankCustodiState;
  readonly idxHoliday: IdxHolidayState;
  readonly hargaPenutupan: HargaPenutupanState;
  readonly hairCut: HairCutState;
  readonly pengajuanGadaiEfekHeader: PengajuanGadaiEfekHeaderState;
  readonly pengajuanGadaiEfekDtl: PengajuanGadaiEfekDtlState;
  readonly transaksiGadaiEfekHeader: TransaksiGadaiEfekHeaderState;
  readonly eksekusiHeader: EksekusiHeaderState;
  readonly eksekusiDtl: EksekusiDtlState;
  readonly eksekusiSummary: EksekusiSummaryState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  parameterGlobal,
  tipeEfek,
  nasabah,
  efek,
  broker,
  bankCustodi,
  idxHoliday,
  hargaPenutupan,
  hairCut,
  pengajuanGadaiEfekHeader,
  pengajuanGadaiEfekDtl,
  transaksiGadaiEfekHeader,
  eksekusiHeader,
  eksekusiDtl,
  eksekusiSummary,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;

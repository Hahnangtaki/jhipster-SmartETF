import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ParameterGlobal from './parameter-global';
import TipeEfek from './tipe-efek';
import Nasabah from './nasabah';
import Efek from './efek';
import Broker from './broker';
import BankCustodi from './bank-custodi';
import IdxHoliday from './idx-holiday';
import HargaPenutupan from './harga-penutupan';
import HairCut from './hair-cut';
import PengajuanGadaiEfekHeader from './pengajuan-gadai-efek-header';
import PengajuanGadaiEfekDtl from './pengajuan-gadai-efek-dtl';
import TransaksiGadaiEfekHeader from './transaksi-gadai-efek-header';
import EksekusiHeader from './eksekusi-header';
import EksekusiDtl from './eksekusi-dtl';
import EksekusiSummary from './eksekusi-summary';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/parameter-global`} component={ParameterGlobal} />
      <ErrorBoundaryRoute path={`${match.url}/tipe-efek`} component={TipeEfek} />
      <ErrorBoundaryRoute path={`${match.url}/nasabah`} component={Nasabah} />
      <ErrorBoundaryRoute path={`${match.url}/efek`} component={Efek} />
      <ErrorBoundaryRoute path={`${match.url}/broker`} component={Broker} />
      <ErrorBoundaryRoute path={`${match.url}/bank-custodi`} component={BankCustodi} />
      <ErrorBoundaryRoute path={`${match.url}/idx-holiday`} component={IdxHoliday} />
      <ErrorBoundaryRoute path={`${match.url}/harga-penutupan`} component={HargaPenutupan} />
      <ErrorBoundaryRoute path={`${match.url}/hair-cut`} component={HairCut} />
      <ErrorBoundaryRoute path={`${match.url}/pengajuan-gadai-efek-header`} component={PengajuanGadaiEfekHeader} />
      <ErrorBoundaryRoute path={`${match.url}/pengajuan-gadai-efek-dtl`} component={PengajuanGadaiEfekDtl} />
      <ErrorBoundaryRoute path={`${match.url}/transaksi-gadai-efek-header`} component={TransaksiGadaiEfekHeader} />
      <ErrorBoundaryRoute path={`${match.url}/eksekusi-header`} component={EksekusiHeader} />
      <ErrorBoundaryRoute path={`${match.url}/eksekusi-dtl`} component={EksekusiDtl} />
      <ErrorBoundaryRoute path={`${match.url}/eksekusi-summary`} component={EksekusiSummary} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;

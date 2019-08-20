import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PengajuanGadaiEfekDtl from './pengajuan-gadai-efek-dtl';
import PengajuanGadaiEfekDtlDetail from './pengajuan-gadai-efek-dtl-detail';
import PengajuanGadaiEfekDtlUpdate from './pengajuan-gadai-efek-dtl-update';
import PengajuanGadaiEfekDtlDeleteDialog from './pengajuan-gadai-efek-dtl-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PengajuanGadaiEfekDtlUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PengajuanGadaiEfekDtlUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PengajuanGadaiEfekDtlDetail} />
      <ErrorBoundaryRoute path={match.url} component={PengajuanGadaiEfekDtl} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PengajuanGadaiEfekDtlDeleteDialog} />
  </>
);

export default Routes;

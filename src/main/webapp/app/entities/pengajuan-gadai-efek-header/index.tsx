import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PengajuanGadaiEfekHeader from './pengajuan-gadai-efek-header';
import PengajuanGadaiEfekHeaderDetail from './pengajuan-gadai-efek-header-detail';
import PengajuanGadaiEfekHeaderUpdate from './pengajuan-gadai-efek-header-update';
import PengajuanGadaiEfekHeaderDeleteDialog from './pengajuan-gadai-efek-header-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PengajuanGadaiEfekHeaderUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PengajuanGadaiEfekHeaderUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PengajuanGadaiEfekHeaderDetail} />
      <ErrorBoundaryRoute path={match.url} component={PengajuanGadaiEfekHeader} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PengajuanGadaiEfekHeaderDeleteDialog} />
  </>
);

export default Routes;

import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TransaksiGadaiEfekHeader from './transaksi-gadai-efek-header';
import TransaksiGadaiEfekHeaderDetail from './transaksi-gadai-efek-header-detail';
import TransaksiGadaiEfekHeaderUpdate from './transaksi-gadai-efek-header-update';
import TransaksiGadaiEfekHeaderDeleteDialog from './transaksi-gadai-efek-header-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TransaksiGadaiEfekHeaderUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TransaksiGadaiEfekHeaderUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TransaksiGadaiEfekHeaderDetail} />
      <ErrorBoundaryRoute path={match.url} component={TransaksiGadaiEfekHeader} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TransaksiGadaiEfekHeaderDeleteDialog} />
  </>
);

export default Routes;

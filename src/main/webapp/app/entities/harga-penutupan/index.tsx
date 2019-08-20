import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HargaPenutupan from './harga-penutupan';
import HargaPenutupanDetail from './harga-penutupan-detail';
import HargaPenutupanUpdate from './harga-penutupan-update';
import HargaPenutupanDeleteDialog from './harga-penutupan-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HargaPenutupanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HargaPenutupanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HargaPenutupanDetail} />
      <ErrorBoundaryRoute path={match.url} component={HargaPenutupan} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={HargaPenutupanDeleteDialog} />
  </>
);

export default Routes;

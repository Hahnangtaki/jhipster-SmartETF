import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Nasabah from './nasabah';
import NasabahDetail from './nasabah-detail';
import NasabahUpdate from './nasabah-update';
import NasabahDeleteDialog from './nasabah-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NasabahUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NasabahUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NasabahDetail} />
      <ErrorBoundaryRoute path={match.url} component={Nasabah} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={NasabahDeleteDialog} />
  </>
);

export default Routes;

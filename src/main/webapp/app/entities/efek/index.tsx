import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Efek from './efek';
import EfekDetail from './efek-detail';
import EfekUpdate from './efek-update';
import EfekDeleteDialog from './efek-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EfekUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EfekUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EfekDetail} />
      <ErrorBoundaryRoute path={match.url} component={Efek} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EfekDeleteDialog} />
  </>
);

export default Routes;

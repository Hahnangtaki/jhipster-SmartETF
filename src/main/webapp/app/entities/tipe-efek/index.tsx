import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TipeEfek from './tipe-efek';
import TipeEfekDetail from './tipe-efek-detail';
import TipeEfekUpdate from './tipe-efek-update';
import TipeEfekDeleteDialog from './tipe-efek-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TipeEfekUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TipeEfekUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TipeEfekDetail} />
      <ErrorBoundaryRoute path={match.url} component={TipeEfek} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TipeEfekDeleteDialog} />
  </>
);

export default Routes;

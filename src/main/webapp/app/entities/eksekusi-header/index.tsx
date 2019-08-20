import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EksekusiHeader from './eksekusi-header';
import EksekusiHeaderDetail from './eksekusi-header-detail';
import EksekusiHeaderUpdate from './eksekusi-header-update';
import EksekusiHeaderDeleteDialog from './eksekusi-header-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EksekusiHeaderUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EksekusiHeaderUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EksekusiHeaderDetail} />
      <ErrorBoundaryRoute path={match.url} component={EksekusiHeader} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EksekusiHeaderDeleteDialog} />
  </>
);

export default Routes;

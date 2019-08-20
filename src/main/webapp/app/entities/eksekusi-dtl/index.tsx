import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EksekusiDtl from './eksekusi-dtl';
import EksekusiDtlDetail from './eksekusi-dtl-detail';
import EksekusiDtlUpdate from './eksekusi-dtl-update';
import EksekusiDtlDeleteDialog from './eksekusi-dtl-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EksekusiDtlUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EksekusiDtlUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EksekusiDtlDetail} />
      <ErrorBoundaryRoute path={match.url} component={EksekusiDtl} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EksekusiDtlDeleteDialog} />
  </>
);

export default Routes;

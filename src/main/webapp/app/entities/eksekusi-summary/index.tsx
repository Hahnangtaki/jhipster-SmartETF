import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EksekusiSummary from './eksekusi-summary';
import EksekusiSummaryDetail from './eksekusi-summary-detail';
import EksekusiSummaryUpdate from './eksekusi-summary-update';
import EksekusiSummaryDeleteDialog from './eksekusi-summary-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EksekusiSummaryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EksekusiSummaryUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EksekusiSummaryDetail} />
      <ErrorBoundaryRoute path={match.url} component={EksekusiSummary} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EksekusiSummaryDeleteDialog} />
  </>
);

export default Routes;

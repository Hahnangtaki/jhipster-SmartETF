import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HairCut from './hair-cut';
import HairCutDetail from './hair-cut-detail';
import HairCutUpdate from './hair-cut-update';
import HairCutDeleteDialog from './hair-cut-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HairCutUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HairCutUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HairCutDetail} />
      <ErrorBoundaryRoute path={match.url} component={HairCut} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={HairCutDeleteDialog} />
  </>
);

export default Routes;

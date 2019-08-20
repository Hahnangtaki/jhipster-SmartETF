import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ParameterGlobal from './parameter-global';
import ParameterGlobalDetail from './parameter-global-detail';
import ParameterGlobalUpdate from './parameter-global-update';
import ParameterGlobalDeleteDialog from './parameter-global-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ParameterGlobalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ParameterGlobalUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ParameterGlobalDetail} />
      <ErrorBoundaryRoute path={match.url} component={ParameterGlobal} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ParameterGlobalDeleteDialog} />
  </>
);

export default Routes;

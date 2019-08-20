import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Broker from './broker';
import BrokerDetail from './broker-detail';
import BrokerUpdate from './broker-update';
import BrokerDeleteDialog from './broker-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BrokerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BrokerUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BrokerDetail} />
      <ErrorBoundaryRoute path={match.url} component={Broker} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={BrokerDeleteDialog} />
  </>
);

export default Routes;

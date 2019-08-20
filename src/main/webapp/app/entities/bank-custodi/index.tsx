import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BankCustodi from './bank-custodi';
import BankCustodiDetail from './bank-custodi-detail';
import BankCustodiUpdate from './bank-custodi-update';
import BankCustodiDeleteDialog from './bank-custodi-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BankCustodiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BankCustodiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BankCustodiDetail} />
      <ErrorBoundaryRoute path={match.url} component={BankCustodi} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={BankCustodiDeleteDialog} />
  </>
);

export default Routes;

import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import IdxHoliday from './idx-holiday';
import IdxHolidayDetail from './idx-holiday-detail';
import IdxHolidayUpdate from './idx-holiday-update';
import IdxHolidayDeleteDialog from './idx-holiday-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={IdxHolidayUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={IdxHolidayUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={IdxHolidayDetail} />
      <ErrorBoundaryRoute path={match.url} component={IdxHoliday} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={IdxHolidayDeleteDialog} />
  </>
);

export default Routes;

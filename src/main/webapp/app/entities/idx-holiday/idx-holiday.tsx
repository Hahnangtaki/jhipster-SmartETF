import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './idx-holiday.reducer';
import { IIdxHoliday } from 'app/shared/model/idx-holiday.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIdxHolidayProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class IdxHoliday extends React.Component<IIdxHolidayProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { idxHolidayList, match } = this.props;
    return (
      <div>
        <h2 id="idx-holiday-heading">
          Idx Holidays
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Idx Holiday
          </Link>
        </h2>
        <div className="table-responsive">
          {idxHolidayList && idxHolidayList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Tanggal</th>
                  <th>Keterangan</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {idxHolidayList.map((idxHoliday, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${idxHoliday.id}`} color="link" size="sm">
                        {idxHoliday.id}
                      </Button>
                    </td>
                    <td>
                      <TextFormat type="date" value={idxHoliday.tanggal} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{idxHoliday.keterangan}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${idxHoliday.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${idxHoliday.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${idxHoliday.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Idx Holidays found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ idxHoliday }: IRootState) => ({
  idxHolidayList: idxHoliday.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(IdxHoliday);

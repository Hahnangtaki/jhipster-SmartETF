import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './tipe-efek.reducer';
import { ITipeEfek } from 'app/shared/model/tipe-efek.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITipeEfekProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class TipeEfek extends React.Component<ITipeEfekProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { tipeEfekList, match } = this.props;
    return (
      <div>
        <h2 id="tipe-efek-heading">
          Tipe Efeks
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Tipe Efek
          </Link>
        </h2>
        <div className="table-responsive">
          {tipeEfekList && tipeEfekList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Tipe Efek</th>
                  <th>Keterangan</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {tipeEfekList.map((tipeEfek, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${tipeEfek.id}`} color="link" size="sm">
                        {tipeEfek.id}
                      </Button>
                    </td>
                    <td>{tipeEfek.tipeEfek}</td>
                    <td>{tipeEfek.keterangan}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${tipeEfek.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${tipeEfek.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${tipeEfek.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Tipe Efeks found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ tipeEfek }: IRootState) => ({
  tipeEfekList: tipeEfek.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TipeEfek);

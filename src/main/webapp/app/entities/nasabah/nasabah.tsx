import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './nasabah.reducer';
import { INasabah } from 'app/shared/model/nasabah.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INasabahProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Nasabah extends React.Component<INasabahProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { nasabahList, match } = this.props;
    return (
      <div>
        <h2 id="nasabah-heading">
          Nasabahs
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Nasabah
          </Link>
        </h2>
        <div className="table-responsive">
          {nasabahList && nasabahList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Kode Nasabah</th>
                  <th>Nama Nasabah</th>
                  <th>Tipe Nasabah</th>
                  <th>Sid</th>
                  <th>Bank Sub Rek</th>
                  <th>Alamat 1</th>
                  <th>Alamat 2</th>
                  <th>Alamat 3</th>
                  <th>No Telp</th>
                  <th>No Fax</th>
                  <th>Status Sub Rek</th>
                  <th>Status</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {nasabahList.map((nasabah, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${nasabah.id}`} color="link" size="sm">
                        {nasabah.id}
                      </Button>
                    </td>
                    <td>{nasabah.kodeNasabah}</td>
                    <td>{nasabah.namaNasabah}</td>
                    <td>{nasabah.tipeNasabah}</td>
                    <td>{nasabah.sid}</td>
                    <td>{nasabah.bankSubRek}</td>
                    <td>{nasabah.alamat1}</td>
                    <td>{nasabah.alamat2}</td>
                    <td>{nasabah.alamat3}</td>
                    <td>{nasabah.noTelp}</td>
                    <td>{nasabah.noFax}</td>
                    <td>{nasabah.statusSubRek}</td>
                    <td>{nasabah.status}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${nasabah.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${nasabah.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${nasabah.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Nasabahs found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ nasabah }: IRootState) => ({
  nasabahList: nasabah.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Nasabah);

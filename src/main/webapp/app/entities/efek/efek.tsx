import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './efek.reducer';
import { IEfek } from 'app/shared/model/efek.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEfekProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Efek extends React.Component<IEfekProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { efekList, match } = this.props;
    return (
      <div>
        <h2 id="efek-heading">
          Efeks
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Efek
          </Link>
        </h2>
        <div className="table-responsive">
          {efekList && efekList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Kode Efek</th>
                  <th>Nama Efek</th>
                  <th>Tipe Efek</th>
                  <th>Last Closing Price</th>
                  <th>Last Closing Date</th>
                  <th>Last Hair Cut</th>
                  <th>Last Hair Cut Date</th>
                  <th>Status Gadai</th>
                  <th>Jlh Efek Beredar</th>
                  <th>Bmpk</th>
                  <th>Bond Rating</th>
                  <th>Bond Maturity Date</th>
                  <th>Satuan</th>
                  <th>Status</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {efekList.map((efek, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${efek.id}`} color="link" size="sm">
                        {efek.id}
                      </Button>
                    </td>
                    <td>{efek.kodeEfek}</td>
                    <td>{efek.namaEfek}</td>
                    <td>{efek.tipeEfek}</td>
                    <td>{efek.lastClosingPrice}</td>
                    <td>
                      <TextFormat type="date" value={efek.lastClosingDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{efek.lastHairCut}</td>
                    <td>
                      <TextFormat type="date" value={efek.lastHairCutDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{efek.statusGadai ? 'true' : 'false'}</td>
                    <td>{efek.jlhEfekBeredar}</td>
                    <td>{efek.bmpk}</td>
                    <td>{efek.bondRating}</td>
                    <td>
                      <TextFormat type="date" value={efek.bondMaturityDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{efek.satuan}</td>
                    <td>{efek.status}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${efek.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${efek.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${efek.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Efeks found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ efek }: IRootState) => ({
  efekList: efek.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Efek);

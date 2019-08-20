import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './harga-penutupan.reducer';
import { IHargaPenutupan } from 'app/shared/model/harga-penutupan.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHargaPenutupanProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class HargaPenutupan extends React.Component<IHargaPenutupanProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { hargaPenutupanList, match } = this.props;
    return (
      <div>
        <h2 id="harga-penutupan-heading">
          Harga Penutupans
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Harga Penutupan
          </Link>
        </h2>
        <div className="table-responsive">
          {hargaPenutupanList && hargaPenutupanList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Tanggal</th>
                  <th>Kode Efek</th>
                  <th>Harga</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {hargaPenutupanList.map((hargaPenutupan, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${hargaPenutupan.id}`} color="link" size="sm">
                        {hargaPenutupan.id}
                      </Button>
                    </td>
                    <td>
                      <TextFormat type="date" value={hargaPenutupan.tanggal} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{hargaPenutupan.kodeEfek}</td>
                    <td>{hargaPenutupan.harga}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${hargaPenutupan.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${hargaPenutupan.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${hargaPenutupan.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Harga Penutupans found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ hargaPenutupan }: IRootState) => ({
  hargaPenutupanList: hargaPenutupan.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HargaPenutupan);

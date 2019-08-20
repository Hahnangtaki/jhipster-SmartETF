import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './eksekusi-dtl.reducer';
import { IEksekusiDtl } from 'app/shared/model/eksekusi-dtl.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEksekusiDtlProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class EksekusiDtl extends React.Component<IEksekusiDtlProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { eksekusiDtlList, match } = this.props;
    return (
      <div>
        <h2 id="eksekusi-dtl-heading">
          Eksekusi Dtls
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Eksekusi Dtl
          </Link>
        </h2>
        <div className="table-responsive">
          {eksekusiDtlList && eksekusiDtlList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>No Eksekusi</th>
                  <th>Nomor Kontrak</th>
                  <th>Kode Efek</th>
                  <th>Quantity</th>
                  <th>Done Qty</th>
                  <th>Done Amount</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {eksekusiDtlList.map((eksekusiDtl, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${eksekusiDtl.id}`} color="link" size="sm">
                        {eksekusiDtl.id}
                      </Button>
                    </td>
                    <td>{eksekusiDtl.noEksekusi}</td>
                    <td>{eksekusiDtl.nomorKontrak}</td>
                    <td>{eksekusiDtl.kodeEfek}</td>
                    <td>{eksekusiDtl.quantity}</td>
                    <td>{eksekusiDtl.doneQty}</td>
                    <td>{eksekusiDtl.doneAmount}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${eksekusiDtl.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${eksekusiDtl.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${eksekusiDtl.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Eksekusi Dtls found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ eksekusiDtl }: IRootState) => ({
  eksekusiDtlList: eksekusiDtl.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EksekusiDtl);

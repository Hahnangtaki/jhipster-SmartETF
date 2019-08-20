import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './eksekusi-summary.reducer';
import { IEksekusiSummary } from 'app/shared/model/eksekusi-summary.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEksekusiSummaryProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class EksekusiSummary extends React.Component<IEksekusiSummaryProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { eksekusiSummaryList, match } = this.props;
    return (
      <div>
        <h2 id="eksekusi-summary-heading">
          Eksekusi Summaries
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Eksekusi Summary
          </Link>
        </h2>
        <div className="table-responsive">
          {eksekusiSummaryList && eksekusiSummaryList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>No Eksekusi</th>
                  <th>Kode Efek</th>
                  <th>Harga Jual</th>
                  <th>Quantity</th>
                  <th>Done Qty</th>
                  <th>Amount</th>
                  <th>Biaya</th>
                  <th>Net Amount</th>
                  <th>Alokasi Qty</th>
                  <th>Aloksi Amount</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {eksekusiSummaryList.map((eksekusiSummary, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${eksekusiSummary.id}`} color="link" size="sm">
                        {eksekusiSummary.id}
                      </Button>
                    </td>
                    <td>{eksekusiSummary.noEksekusi}</td>
                    <td>{eksekusiSummary.kodeEfek}</td>
                    <td>{eksekusiSummary.hargaJual}</td>
                    <td>{eksekusiSummary.quantity}</td>
                    <td>{eksekusiSummary.doneQty}</td>
                    <td>{eksekusiSummary.amount}</td>
                    <td>{eksekusiSummary.biaya}</td>
                    <td>{eksekusiSummary.netAmount}</td>
                    <td>{eksekusiSummary.alokasiQty}</td>
                    <td>{eksekusiSummary.aloksiAmount}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${eksekusiSummary.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${eksekusiSummary.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${eksekusiSummary.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Eksekusi Summaries found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ eksekusiSummary }: IRootState) => ({
  eksekusiSummaryList: eksekusiSummary.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EksekusiSummary);

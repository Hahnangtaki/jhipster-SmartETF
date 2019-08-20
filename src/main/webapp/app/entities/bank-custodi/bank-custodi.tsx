import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './bank-custodi.reducer';
import { IBankCustodi } from 'app/shared/model/bank-custodi.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBankCustodiProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class BankCustodi extends React.Component<IBankCustodiProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { bankCustodiList, match } = this.props;
    return (
      <div>
        <h2 id="bank-custodi-heading">
          Bank Custodis
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Bank Custodi
          </Link>
        </h2>
        <div className="table-responsive">
          {bankCustodiList && bankCustodiList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Kode Custodi</th>
                  <th>Nama Custodi</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {bankCustodiList.map((bankCustodi, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${bankCustodi.id}`} color="link" size="sm">
                        {bankCustodi.id}
                      </Button>
                    </td>
                    <td>{bankCustodi.kodeCustodi}</td>
                    <td>{bankCustodi.namaCustodi}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${bankCustodi.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${bankCustodi.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${bankCustodi.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Bank Custodis found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ bankCustodi }: IRootState) => ({
  bankCustodiList: bankCustodi.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BankCustodi);

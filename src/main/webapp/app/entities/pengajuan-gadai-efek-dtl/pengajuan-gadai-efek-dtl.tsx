import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './pengajuan-gadai-efek-dtl.reducer';
import { IPengajuanGadaiEfekDtl } from 'app/shared/model/pengajuan-gadai-efek-dtl.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPengajuanGadaiEfekDtlProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class PengajuanGadaiEfekDtl extends React.Component<IPengajuanGadaiEfekDtlProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { pengajuanGadaiEfekDtlList, match } = this.props;
    return (
      <div>
        <h2 id="pengajuan-gadai-efek-dtl-heading">
          Pengajuan Gadai Efek Dtls
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Pengajuan Gadai Efek Dtl
          </Link>
        </h2>
        <div className="table-responsive">
          {pengajuanGadaiEfekDtlList && pengajuanGadaiEfekDtlList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>No Pengajuan Gadai</th>
                  <th>Kode Efek</th>
                  <th>Quantity</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {pengajuanGadaiEfekDtlList.map((pengajuanGadaiEfekDtl, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${pengajuanGadaiEfekDtl.id}`} color="link" size="sm">
                        {pengajuanGadaiEfekDtl.id}
                      </Button>
                    </td>
                    <td>{pengajuanGadaiEfekDtl.noPengajuanGadai}</td>
                    <td>{pengajuanGadaiEfekDtl.kodeEfek}</td>
                    <td>{pengajuanGadaiEfekDtl.quantity}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${pengajuanGadaiEfekDtl.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${pengajuanGadaiEfekDtl.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${pengajuanGadaiEfekDtl.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Pengajuan Gadai Efek Dtls found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ pengajuanGadaiEfekDtl }: IRootState) => ({
  pengajuanGadaiEfekDtlList: pengajuanGadaiEfekDtl.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PengajuanGadaiEfekDtl);

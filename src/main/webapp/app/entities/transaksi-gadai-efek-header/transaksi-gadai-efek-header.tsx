import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './transaksi-gadai-efek-header.reducer';
import { ITransaksiGadaiEfekHeader } from 'app/shared/model/transaksi-gadai-efek-header.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITransaksiGadaiEfekHeaderProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class TransaksiGadaiEfekHeader extends React.Component<ITransaksiGadaiEfekHeaderProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { transaksiGadaiEfekHeaderList, match } = this.props;
    return (
      <div>
        <h2 id="transaksi-gadai-efek-header-heading">
          Transaksi Gadai Efek Headers
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Transaksi Gadai Efek Header
          </Link>
        </h2>
        <div className="table-responsive">
          {transaksiGadaiEfekHeaderList && transaksiGadaiEfekHeaderList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>No Kontrak</th>
                  <th>Tgl Entri</th>
                  <th>Tgl Pencairan</th>
                  <th>Tgl Jatuh Tempo</th>
                  <th>No Pengajuan Gadai</th>
                  <th>Kode Nasabah</th>
                  <th>Nilai Kewajiban</th>
                  <th>Tgl Kirim Efek</th>
                  <th>Counter Part Instruksi</th>
                  <th>Status</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {transaksiGadaiEfekHeaderList.map((transaksiGadaiEfekHeader, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${transaksiGadaiEfekHeader.id}`} color="link" size="sm">
                        {transaksiGadaiEfekHeader.id}
                      </Button>
                    </td>
                    <td>{transaksiGadaiEfekHeader.noKontrak}</td>
                    <td>
                      <TextFormat type="date" value={transaksiGadaiEfekHeader.tglEntri} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={transaksiGadaiEfekHeader.tglPencairan} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={transaksiGadaiEfekHeader.tglJatuhTempo} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{transaksiGadaiEfekHeader.noPengajuanGadai}</td>
                    <td>{transaksiGadaiEfekHeader.kodeNasabah}</td>
                    <td>{transaksiGadaiEfekHeader.nilaiKewajiban}</td>
                    <td>
                      <TextFormat type="date" value={transaksiGadaiEfekHeader.tglKirimEfek} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{transaksiGadaiEfekHeader.counterPartInstruksi}</td>
                    <td>{transaksiGadaiEfekHeader.status}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${transaksiGadaiEfekHeader.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${transaksiGadaiEfekHeader.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${transaksiGadaiEfekHeader.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Transaksi Gadai Efek Headers found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ transaksiGadaiEfekHeader }: IRootState) => ({
  transaksiGadaiEfekHeaderList: transaksiGadaiEfekHeader.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TransaksiGadaiEfekHeader);

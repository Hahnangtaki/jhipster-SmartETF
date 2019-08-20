import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './transaksi-gadai-efek-header.reducer';
import { ITransaksiGadaiEfekHeader } from 'app/shared/model/transaksi-gadai-efek-header.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITransaksiGadaiEfekHeaderDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TransaksiGadaiEfekHeaderDetail extends React.Component<ITransaksiGadaiEfekHeaderDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { transaksiGadaiEfekHeaderEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            TransaksiGadaiEfekHeader [<b>{transaksiGadaiEfekHeaderEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="noKontrak">No Kontrak</span>
            </dt>
            <dd>{transaksiGadaiEfekHeaderEntity.noKontrak}</dd>
            <dt>
              <span id="tglEntri">Tgl Entri</span>
            </dt>
            <dd>
              <TextFormat value={transaksiGadaiEfekHeaderEntity.tglEntri} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="tglPencairan">Tgl Pencairan</span>
            </dt>
            <dd>
              <TextFormat value={transaksiGadaiEfekHeaderEntity.tglPencairan} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="tglJatuhTempo">Tgl Jatuh Tempo</span>
            </dt>
            <dd>
              <TextFormat value={transaksiGadaiEfekHeaderEntity.tglJatuhTempo} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="noPengajuanGadai">No Pengajuan Gadai</span>
            </dt>
            <dd>{transaksiGadaiEfekHeaderEntity.noPengajuanGadai}</dd>
            <dt>
              <span id="kodeNasabah">Kode Nasabah</span>
            </dt>
            <dd>{transaksiGadaiEfekHeaderEntity.kodeNasabah}</dd>
            <dt>
              <span id="nilaiKewajiban">Nilai Kewajiban</span>
            </dt>
            <dd>{transaksiGadaiEfekHeaderEntity.nilaiKewajiban}</dd>
            <dt>
              <span id="tglKirimEfek">Tgl Kirim Efek</span>
            </dt>
            <dd>
              <TextFormat value={transaksiGadaiEfekHeaderEntity.tglKirimEfek} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="counterPartInstruksi">Counter Part Instruksi</span>
            </dt>
            <dd>{transaksiGadaiEfekHeaderEntity.counterPartInstruksi}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{transaksiGadaiEfekHeaderEntity.status}</dd>
          </dl>
          <Button tag={Link} to="/entity/transaksi-gadai-efek-header" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/transaksi-gadai-efek-header/${transaksiGadaiEfekHeaderEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ transaksiGadaiEfekHeader }: IRootState) => ({
  transaksiGadaiEfekHeaderEntity: transaksiGadaiEfekHeader.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TransaksiGadaiEfekHeaderDetail);

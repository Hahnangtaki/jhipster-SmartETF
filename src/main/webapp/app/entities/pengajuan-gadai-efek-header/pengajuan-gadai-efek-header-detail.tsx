import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pengajuan-gadai-efek-header.reducer';
import { IPengajuanGadaiEfekHeader } from 'app/shared/model/pengajuan-gadai-efek-header.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPengajuanGadaiEfekHeaderDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PengajuanGadaiEfekHeaderDetail extends React.Component<IPengajuanGadaiEfekHeaderDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pengajuanGadaiEfekHeaderEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            PengajuanGadaiEfekHeader [<b>{pengajuanGadaiEfekHeaderEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="noPengajuanGadai">No Pengajuan Gadai</span>
            </dt>
            <dd>{pengajuanGadaiEfekHeaderEntity.noPengajuanGadai}</dd>
            <dt>
              <span id="tglEntri">Tgl Entri</span>
            </dt>
            <dd>
              <TextFormat value={pengajuanGadaiEfekHeaderEntity.tglEntri} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="tglEfekTerima">Tgl Efek Terima</span>
            </dt>
            <dd>
              <TextFormat value={pengajuanGadaiEfekHeaderEntity.tglEfekTerima} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="kodeNasabah">Kode Nasabah</span>
            </dt>
            <dd>{pengajuanGadaiEfekHeaderEntity.kodeNasabah}</dd>
            <dt>
              <span id="nilaiPinjaman">Nilai Pinjaman</span>
            </dt>
            <dd>{pengajuanGadaiEfekHeaderEntity.nilaiPinjaman}</dd>
            <dt>
              <span id="counterPartInstruksi">Counter Part Instruksi</span>
            </dt>
            <dd>{pengajuanGadaiEfekHeaderEntity.counterPartInstruksi}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{pengajuanGadaiEfekHeaderEntity.status}</dd>
          </dl>
          <Button tag={Link} to="/entity/pengajuan-gadai-efek-header" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/pengajuan-gadai-efek-header/${pengajuanGadaiEfekHeaderEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ pengajuanGadaiEfekHeader }: IRootState) => ({
  pengajuanGadaiEfekHeaderEntity: pengajuanGadaiEfekHeader.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PengajuanGadaiEfekHeaderDetail);

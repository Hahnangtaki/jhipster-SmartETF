import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './nasabah.reducer';
import { INasabah } from 'app/shared/model/nasabah.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INasabahDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class NasabahDetail extends React.Component<INasabahDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { nasabahEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Nasabah [<b>{nasabahEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="kodeNasabah">Kode Nasabah</span>
            </dt>
            <dd>{nasabahEntity.kodeNasabah}</dd>
            <dt>
              <span id="namaNasabah">Nama Nasabah</span>
            </dt>
            <dd>{nasabahEntity.namaNasabah}</dd>
            <dt>
              <span id="tipeNasabah">Tipe Nasabah</span>
            </dt>
            <dd>{nasabahEntity.tipeNasabah}</dd>
            <dt>
              <span id="sid">Sid</span>
            </dt>
            <dd>{nasabahEntity.sid}</dd>
            <dt>
              <span id="bankSubRek">Bank Sub Rek</span>
            </dt>
            <dd>{nasabahEntity.bankSubRek}</dd>
            <dt>
              <span id="alamat1">Alamat 1</span>
            </dt>
            <dd>{nasabahEntity.alamat1}</dd>
            <dt>
              <span id="alamat2">Alamat 2</span>
            </dt>
            <dd>{nasabahEntity.alamat2}</dd>
            <dt>
              <span id="alamat3">Alamat 3</span>
            </dt>
            <dd>{nasabahEntity.alamat3}</dd>
            <dt>
              <span id="noTelp">No Telp</span>
            </dt>
            <dd>{nasabahEntity.noTelp}</dd>
            <dt>
              <span id="noFax">No Fax</span>
            </dt>
            <dd>{nasabahEntity.noFax}</dd>
            <dt>
              <span id="statusSubRek">Status Sub Rek</span>
            </dt>
            <dd>{nasabahEntity.statusSubRek}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{nasabahEntity.status}</dd>
          </dl>
          <Button tag={Link} to="/entity/nasabah" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/nasabah/${nasabahEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ nasabah }: IRootState) => ({
  nasabahEntity: nasabah.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NasabahDetail);

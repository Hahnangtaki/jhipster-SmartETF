import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pengajuan-gadai-efek-dtl.reducer';
import { IPengajuanGadaiEfekDtl } from 'app/shared/model/pengajuan-gadai-efek-dtl.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPengajuanGadaiEfekDtlDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PengajuanGadaiEfekDtlDetail extends React.Component<IPengajuanGadaiEfekDtlDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pengajuanGadaiEfekDtlEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            PengajuanGadaiEfekDtl [<b>{pengajuanGadaiEfekDtlEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="noPengajuanGadai">No Pengajuan Gadai</span>
            </dt>
            <dd>{pengajuanGadaiEfekDtlEntity.noPengajuanGadai}</dd>
            <dt>
              <span id="kodeEfek">Kode Efek</span>
            </dt>
            <dd>{pengajuanGadaiEfekDtlEntity.kodeEfek}</dd>
            <dt>
              <span id="quantity">Quantity</span>
            </dt>
            <dd>{pengajuanGadaiEfekDtlEntity.quantity}</dd>
          </dl>
          <Button tag={Link} to="/entity/pengajuan-gadai-efek-dtl" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/pengajuan-gadai-efek-dtl/${pengajuanGadaiEfekDtlEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ pengajuanGadaiEfekDtl }: IRootState) => ({
  pengajuanGadaiEfekDtlEntity: pengajuanGadaiEfekDtl.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PengajuanGadaiEfekDtlDetail);

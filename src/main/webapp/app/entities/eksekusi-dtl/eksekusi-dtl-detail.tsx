import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './eksekusi-dtl.reducer';
import { IEksekusiDtl } from 'app/shared/model/eksekusi-dtl.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEksekusiDtlDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EksekusiDtlDetail extends React.Component<IEksekusiDtlDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { eksekusiDtlEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            EksekusiDtl [<b>{eksekusiDtlEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="noEksekusi">No Eksekusi</span>
            </dt>
            <dd>{eksekusiDtlEntity.noEksekusi}</dd>
            <dt>
              <span id="nomorKontrak">Nomor Kontrak</span>
            </dt>
            <dd>{eksekusiDtlEntity.nomorKontrak}</dd>
            <dt>
              <span id="kodeEfek">Kode Efek</span>
            </dt>
            <dd>{eksekusiDtlEntity.kodeEfek}</dd>
            <dt>
              <span id="quantity">Quantity</span>
            </dt>
            <dd>{eksekusiDtlEntity.quantity}</dd>
            <dt>
              <span id="doneQty">Done Qty</span>
            </dt>
            <dd>{eksekusiDtlEntity.doneQty}</dd>
            <dt>
              <span id="doneAmount">Done Amount</span>
            </dt>
            <dd>{eksekusiDtlEntity.doneAmount}</dd>
          </dl>
          <Button tag={Link} to="/entity/eksekusi-dtl" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/eksekusi-dtl/${eksekusiDtlEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ eksekusiDtl }: IRootState) => ({
  eksekusiDtlEntity: eksekusiDtl.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EksekusiDtlDetail);

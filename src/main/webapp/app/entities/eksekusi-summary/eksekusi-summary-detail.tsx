import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './eksekusi-summary.reducer';
import { IEksekusiSummary } from 'app/shared/model/eksekusi-summary.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEksekusiSummaryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EksekusiSummaryDetail extends React.Component<IEksekusiSummaryDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { eksekusiSummaryEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            EksekusiSummary [<b>{eksekusiSummaryEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="noEksekusi">No Eksekusi</span>
            </dt>
            <dd>{eksekusiSummaryEntity.noEksekusi}</dd>
            <dt>
              <span id="kodeEfek">Kode Efek</span>
            </dt>
            <dd>{eksekusiSummaryEntity.kodeEfek}</dd>
            <dt>
              <span id="hargaJual">Harga Jual</span>
            </dt>
            <dd>{eksekusiSummaryEntity.hargaJual}</dd>
            <dt>
              <span id="quantity">Quantity</span>
            </dt>
            <dd>{eksekusiSummaryEntity.quantity}</dd>
            <dt>
              <span id="doneQty">Done Qty</span>
            </dt>
            <dd>{eksekusiSummaryEntity.doneQty}</dd>
            <dt>
              <span id="amount">Amount</span>
            </dt>
            <dd>{eksekusiSummaryEntity.amount}</dd>
            <dt>
              <span id="biaya">Biaya</span>
            </dt>
            <dd>{eksekusiSummaryEntity.biaya}</dd>
            <dt>
              <span id="netAmount">Net Amount</span>
            </dt>
            <dd>{eksekusiSummaryEntity.netAmount}</dd>
            <dt>
              <span id="alokasiQty">Alokasi Qty</span>
            </dt>
            <dd>{eksekusiSummaryEntity.alokasiQty}</dd>
            <dt>
              <span id="aloksiAmount">Aloksi Amount</span>
            </dt>
            <dd>{eksekusiSummaryEntity.aloksiAmount}</dd>
          </dl>
          <Button tag={Link} to="/entity/eksekusi-summary" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/eksekusi-summary/${eksekusiSummaryEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ eksekusiSummary }: IRootState) => ({
  eksekusiSummaryEntity: eksekusiSummary.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EksekusiSummaryDetail);

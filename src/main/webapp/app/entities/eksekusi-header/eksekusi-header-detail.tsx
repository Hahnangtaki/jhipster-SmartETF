import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './eksekusi-header.reducer';
import { IEksekusiHeader } from 'app/shared/model/eksekusi-header.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEksekusiHeaderDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EksekusiHeaderDetail extends React.Component<IEksekusiHeaderDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { eksekusiHeaderEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            EksekusiHeader [<b>{eksekusiHeaderEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="noEksekusi">No Eksekusi</span>
            </dt>
            <dd>{eksekusiHeaderEntity.noEksekusi}</dd>
            <dt>
              <span id="tanggalEntri">Tanggal Entri</span>
            </dt>
            <dd>
              <TextFormat value={eksekusiHeaderEntity.tanggalEntri} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="tanggalTrade">Tanggal Trade</span>
            </dt>
            <dd>
              <TextFormat value={eksekusiHeaderEntity.tanggalTrade} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="tanggalSettle">Tanggal Settle</span>
            </dt>
            <dd>
              <TextFormat value={eksekusiHeaderEntity.tanggalSettle} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="kodeBroker">Kode Broker</span>
            </dt>
            <dd>{eksekusiHeaderEntity.kodeBroker}</dd>
          </dl>
          <Button tag={Link} to="/entity/eksekusi-header" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/eksekusi-header/${eksekusiHeaderEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ eksekusiHeader }: IRootState) => ({
  eksekusiHeaderEntity: eksekusiHeader.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EksekusiHeaderDetail);

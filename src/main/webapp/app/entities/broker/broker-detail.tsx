import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './broker.reducer';
import { IBroker } from 'app/shared/model/broker.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBrokerDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class BrokerDetail extends React.Component<IBrokerDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { brokerEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Broker [<b>{brokerEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="kodeBroker">Kode Broker</span>
            </dt>
            <dd>{brokerEntity.kodeBroker}</dd>
            <dt>
              <span id="namaBroker">Nama Broker</span>
            </dt>
            <dd>{brokerEntity.namaBroker}</dd>
          </dl>
          <Button tag={Link} to="/entity/broker" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/broker/${brokerEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ broker }: IRootState) => ({
  brokerEntity: broker.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BrokerDetail);

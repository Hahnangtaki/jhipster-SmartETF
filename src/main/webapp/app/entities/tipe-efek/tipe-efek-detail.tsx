import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tipe-efek.reducer';
import { ITipeEfek } from 'app/shared/model/tipe-efek.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITipeEfekDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TipeEfekDetail extends React.Component<ITipeEfekDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { tipeEfekEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            TipeEfek [<b>{tipeEfekEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tipeEfek">Tipe Efek</span>
            </dt>
            <dd>{tipeEfekEntity.tipeEfek}</dd>
            <dt>
              <span id="keterangan">Keterangan</span>
            </dt>
            <dd>{tipeEfekEntity.keterangan}</dd>
          </dl>
          <Button tag={Link} to="/entity/tipe-efek" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/tipe-efek/${tipeEfekEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ tipeEfek }: IRootState) => ({
  tipeEfekEntity: tipeEfek.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TipeEfekDetail);

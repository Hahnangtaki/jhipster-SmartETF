import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './hair-cut.reducer';
import { IHairCut } from 'app/shared/model/hair-cut.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHairCutDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class HairCutDetail extends React.Component<IHairCutDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { hairCutEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            HairCut [<b>{hairCutEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tanggal">Tanggal</span>
            </dt>
            <dd>
              <TextFormat value={hairCutEntity.tanggal} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="kodeEfek">Kode Efek</span>
            </dt>
            <dd>{hairCutEntity.kodeEfek}</dd>
            <dt>
              <span id="nilaiHairCut">Nilai Hair Cut</span>
            </dt>
            <dd>{hairCutEntity.nilaiHairCut}</dd>
          </dl>
          <Button tag={Link} to="/entity/hair-cut" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/hair-cut/${hairCutEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ hairCut }: IRootState) => ({
  hairCutEntity: hairCut.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HairCutDetail);

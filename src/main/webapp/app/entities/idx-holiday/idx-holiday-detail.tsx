import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './idx-holiday.reducer';
import { IIdxHoliday } from 'app/shared/model/idx-holiday.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IIdxHolidayDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class IdxHolidayDetail extends React.Component<IIdxHolidayDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { idxHolidayEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            IdxHoliday [<b>{idxHolidayEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tanggal">Tanggal</span>
            </dt>
            <dd>
              <TextFormat value={idxHolidayEntity.tanggal} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="keterangan">Keterangan</span>
            </dt>
            <dd>{idxHolidayEntity.keterangan}</dd>
          </dl>
          <Button tag={Link} to="/entity/idx-holiday" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/idx-holiday/${idxHolidayEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ idxHoliday }: IRootState) => ({
  idxHolidayEntity: idxHoliday.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(IdxHolidayDetail);

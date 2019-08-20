import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './efek.reducer';
import { IEfek } from 'app/shared/model/efek.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEfekDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EfekDetail extends React.Component<IEfekDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { efekEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Efek [<b>{efekEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="kodeEfek">Kode Efek</span>
            </dt>
            <dd>{efekEntity.kodeEfek}</dd>
            <dt>
              <span id="namaEfek">Nama Efek</span>
            </dt>
            <dd>{efekEntity.namaEfek}</dd>
            <dt>
              <span id="tipeEfek">Tipe Efek</span>
            </dt>
            <dd>{efekEntity.tipeEfek}</dd>
            <dt>
              <span id="lastClosingPrice">Last Closing Price</span>
            </dt>
            <dd>{efekEntity.lastClosingPrice}</dd>
            <dt>
              <span id="lastClosingDate">Last Closing Date</span>
            </dt>
            <dd>
              <TextFormat value={efekEntity.lastClosingDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="lastHairCut">Last Hair Cut</span>
            </dt>
            <dd>{efekEntity.lastHairCut}</dd>
            <dt>
              <span id="lastHairCutDate">Last Hair Cut Date</span>
            </dt>
            <dd>
              <TextFormat value={efekEntity.lastHairCutDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="statusGadai">Status Gadai</span>
            </dt>
            <dd>{efekEntity.statusGadai ? 'true' : 'false'}</dd>
            <dt>
              <span id="jlhEfekBeredar">Jlh Efek Beredar</span>
            </dt>
            <dd>{efekEntity.jlhEfekBeredar}</dd>
            <dt>
              <span id="bmpk">Bmpk</span>
            </dt>
            <dd>{efekEntity.bmpk}</dd>
            <dt>
              <span id="bondRating">Bond Rating</span>
            </dt>
            <dd>{efekEntity.bondRating}</dd>
            <dt>
              <span id="bondMaturityDate">Bond Maturity Date</span>
            </dt>
            <dd>
              <TextFormat value={efekEntity.bondMaturityDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="satuan">Satuan</span>
            </dt>
            <dd>{efekEntity.satuan}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{efekEntity.status}</dd>
          </dl>
          <Button tag={Link} to="/entity/efek" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/efek/${efekEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ efek }: IRootState) => ({
  efekEntity: efek.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EfekDetail);

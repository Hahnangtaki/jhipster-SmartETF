import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './bank-custodi.reducer';
import { IBankCustodi } from 'app/shared/model/bank-custodi.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBankCustodiDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class BankCustodiDetail extends React.Component<IBankCustodiDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { bankCustodiEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            BankCustodi [<b>{bankCustodiEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="kodeCustodi">Kode Custodi</span>
            </dt>
            <dd>{bankCustodiEntity.kodeCustodi}</dd>
            <dt>
              <span id="namaCustodi">Nama Custodi</span>
            </dt>
            <dd>{bankCustodiEntity.namaCustodi}</dd>
          </dl>
          <Button tag={Link} to="/entity/bank-custodi" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/bank-custodi/${bankCustodiEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ bankCustodi }: IRootState) => ({
  bankCustodiEntity: bankCustodi.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BankCustodiDetail);

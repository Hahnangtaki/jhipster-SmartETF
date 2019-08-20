import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './parameter-global.reducer';
import { IParameterGlobal } from 'app/shared/model/parameter-global.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IParameterGlobalDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ParameterGlobalDetail extends React.Component<IParameterGlobalDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { parameterGlobalEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            ParameterGlobal [<b>{parameterGlobalEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="prmId">Prm Id</span>
            </dt>
            <dd>{parameterGlobalEntity.prmId}</dd>
            <dt>
              <span id="prmName">Prm Name</span>
            </dt>
            <dd>{parameterGlobalEntity.prmName}</dd>
            <dt>
              <span id="prmTy">Prm Ty</span>
            </dt>
            <dd>{parameterGlobalEntity.prmTy}</dd>
            <dt>
              <span id="appType">App Type</span>
            </dt>
            <dd>{parameterGlobalEntity.appType}</dd>
            <dt>
              <span id="intVal">Int Val</span>
            </dt>
            <dd>{parameterGlobalEntity.intVal}</dd>
            <dt>
              <span id="floatVal">Float Val</span>
            </dt>
            <dd>{parameterGlobalEntity.floatVal}</dd>
            <dt>
              <span id="strVal">Str Val</span>
            </dt>
            <dd>{parameterGlobalEntity.strVal}</dd>
            <dt>
              <span id="dateVal">Date Val</span>
            </dt>
            <dd>
              <TextFormat value={parameterGlobalEntity.dateVal} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="show">Show</span>
            </dt>
            <dd>{parameterGlobalEntity.show ? 'true' : 'false'}</dd>
            <dt>
              <span id="edit">Edit</span>
            </dt>
            <dd>{parameterGlobalEntity.edit ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/parameter-global" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/parameter-global/${parameterGlobalEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ parameterGlobal }: IRootState) => ({
  parameterGlobalEntity: parameterGlobal.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ParameterGlobalDetail);

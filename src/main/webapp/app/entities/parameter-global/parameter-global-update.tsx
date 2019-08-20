import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './parameter-global.reducer';
import { IParameterGlobal } from 'app/shared/model/parameter-global.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IParameterGlobalUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IParameterGlobalUpdateState {
  isNew: boolean;
}

export class ParameterGlobalUpdate extends React.Component<IParameterGlobalUpdateProps, IParameterGlobalUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { parameterGlobalEntity } = this.props;
      const entity = {
        ...parameterGlobalEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/parameter-global');
  };

  render() {
    const { parameterGlobalEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.parameterGlobal.home.createOrEditLabel">Create or edit a ParameterGlobal</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : parameterGlobalEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="parameter-global-id">ID</Label>
                    <AvInput id="parameter-global-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="prmIdLabel" for="parameter-global-prmId">
                    Prm Id
                  </Label>
                  <AvField
                    id="parameter-global-prmId"
                    type="text"
                    name="prmId"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      minLength: { value: 20, errorMessage: 'This field is required to be at least 20 characters.' },
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="prmNameLabel" for="parameter-global-prmName">
                    Prm Name
                  </Label>
                  <AvField
                    id="parameter-global-prmName"
                    type="text"
                    name="prmName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 50, errorMessage: 'This field cannot be longer than 50 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="prmTyLabel" for="parameter-global-prmTy">
                    Prm Ty
                  </Label>
                  <AvField
                    id="parameter-global-prmTy"
                    type="text"
                    name="prmTy"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="appTypeLabel" for="parameter-global-appType">
                    App Type
                  </Label>
                  <AvField id="parameter-global-appType" type="text" name="appType" />
                </AvGroup>
                <AvGroup>
                  <Label id="intValLabel" for="parameter-global-intVal">
                    Int Val
                  </Label>
                  <AvField id="parameter-global-intVal" type="string" className="form-control" name="intVal" />
                </AvGroup>
                <AvGroup>
                  <Label id="floatValLabel" for="parameter-global-floatVal">
                    Float Val
                  </Label>
                  <AvField id="parameter-global-floatVal" type="string" className="form-control" name="floatVal" />
                </AvGroup>
                <AvGroup>
                  <Label id="strValLabel" for="parameter-global-strVal">
                    Str Val
                  </Label>
                  <AvField id="parameter-global-strVal" type="text" name="strVal" />
                </AvGroup>
                <AvGroup>
                  <Label id="dateValLabel" for="parameter-global-dateVal">
                    Date Val
                  </Label>
                  <AvField id="parameter-global-dateVal" type="date" className="form-control" name="dateVal" />
                </AvGroup>
                <AvGroup>
                  <Label id="showLabel" check>
                    <AvInput id="parameter-global-show" type="checkbox" className="form-control" name="show" />
                    Show
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="editLabel" check>
                    <AvInput id="parameter-global-edit" type="checkbox" className="form-control" name="edit" />
                    Edit
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/parameter-global" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  parameterGlobalEntity: storeState.parameterGlobal.entity,
  loading: storeState.parameterGlobal.loading,
  updating: storeState.parameterGlobal.updating,
  updateSuccess: storeState.parameterGlobal.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ParameterGlobalUpdate);

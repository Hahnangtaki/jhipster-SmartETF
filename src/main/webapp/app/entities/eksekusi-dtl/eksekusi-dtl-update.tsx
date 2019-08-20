import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './eksekusi-dtl.reducer';
import { IEksekusiDtl } from 'app/shared/model/eksekusi-dtl.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEksekusiDtlUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEksekusiDtlUpdateState {
  isNew: boolean;
}

export class EksekusiDtlUpdate extends React.Component<IEksekusiDtlUpdateProps, IEksekusiDtlUpdateState> {
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
      const { eksekusiDtlEntity } = this.props;
      const entity = {
        ...eksekusiDtlEntity,
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
    this.props.history.push('/entity/eksekusi-dtl');
  };

  render() {
    const { eksekusiDtlEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.eksekusiDtl.home.createOrEditLabel">Create or edit a EksekusiDtl</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : eksekusiDtlEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="eksekusi-dtl-id">ID</Label>
                    <AvInput id="eksekusi-dtl-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="noEksekusiLabel" for="eksekusi-dtl-noEksekusi">
                    No Eksekusi
                  </Label>
                  <AvField
                    id="eksekusi-dtl-noEksekusi"
                    type="text"
                    name="noEksekusi"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nomorKontrakLabel" for="eksekusi-dtl-nomorKontrak">
                    Nomor Kontrak
                  </Label>
                  <AvField
                    id="eksekusi-dtl-nomorKontrak"
                    type="text"
                    name="nomorKontrak"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kodeEfekLabel" for="eksekusi-dtl-kodeEfek">
                    Kode Efek
                  </Label>
                  <AvField
                    id="eksekusi-dtl-kodeEfek"
                    type="text"
                    name="kodeEfek"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="quantityLabel" for="eksekusi-dtl-quantity">
                    Quantity
                  </Label>
                  <AvField
                    id="eksekusi-dtl-quantity"
                    type="string"
                    className="form-control"
                    name="quantity"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="doneQtyLabel" for="eksekusi-dtl-doneQty">
                    Done Qty
                  </Label>
                  <AvField
                    id="eksekusi-dtl-doneQty"
                    type="string"
                    className="form-control"
                    name="doneQty"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="doneAmountLabel" for="eksekusi-dtl-doneAmount">
                    Done Amount
                  </Label>
                  <AvField
                    id="eksekusi-dtl-doneAmount"
                    type="string"
                    className="form-control"
                    name="doneAmount"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/eksekusi-dtl" replace color="info">
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
  eksekusiDtlEntity: storeState.eksekusiDtl.entity,
  loading: storeState.eksekusiDtl.loading,
  updating: storeState.eksekusiDtl.updating,
  updateSuccess: storeState.eksekusiDtl.updateSuccess
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
)(EksekusiDtlUpdate);

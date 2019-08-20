import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './eksekusi-summary.reducer';
import { IEksekusiSummary } from 'app/shared/model/eksekusi-summary.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEksekusiSummaryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEksekusiSummaryUpdateState {
  isNew: boolean;
}

export class EksekusiSummaryUpdate extends React.Component<IEksekusiSummaryUpdateProps, IEksekusiSummaryUpdateState> {
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
      const { eksekusiSummaryEntity } = this.props;
      const entity = {
        ...eksekusiSummaryEntity,
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
    this.props.history.push('/entity/eksekusi-summary');
  };

  render() {
    const { eksekusiSummaryEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.eksekusiSummary.home.createOrEditLabel">Create or edit a EksekusiSummary</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : eksekusiSummaryEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="eksekusi-summary-id">ID</Label>
                    <AvInput id="eksekusi-summary-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="noEksekusiLabel" for="eksekusi-summary-noEksekusi">
                    No Eksekusi
                  </Label>
                  <AvField
                    id="eksekusi-summary-noEksekusi"
                    type="text"
                    name="noEksekusi"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kodeEfekLabel" for="eksekusi-summary-kodeEfek">
                    Kode Efek
                  </Label>
                  <AvField
                    id="eksekusi-summary-kodeEfek"
                    type="text"
                    name="kodeEfek"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="hargaJualLabel" for="eksekusi-summary-hargaJual">
                    Harga Jual
                  </Label>
                  <AvField
                    id="eksekusi-summary-hargaJual"
                    type="string"
                    className="form-control"
                    name="hargaJual"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="quantityLabel" for="eksekusi-summary-quantity">
                    Quantity
                  </Label>
                  <AvField
                    id="eksekusi-summary-quantity"
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
                  <Label id="doneQtyLabel" for="eksekusi-summary-doneQty">
                    Done Qty
                  </Label>
                  <AvField
                    id="eksekusi-summary-doneQty"
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
                  <Label id="amountLabel" for="eksekusi-summary-amount">
                    Amount
                  </Label>
                  <AvField
                    id="eksekusi-summary-amount"
                    type="string"
                    className="form-control"
                    name="amount"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="biayaLabel" for="eksekusi-summary-biaya">
                    Biaya
                  </Label>
                  <AvField
                    id="eksekusi-summary-biaya"
                    type="string"
                    className="form-control"
                    name="biaya"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="netAmountLabel" for="eksekusi-summary-netAmount">
                    Net Amount
                  </Label>
                  <AvField
                    id="eksekusi-summary-netAmount"
                    type="string"
                    className="form-control"
                    name="netAmount"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="alokasiQtyLabel" for="eksekusi-summary-alokasiQty">
                    Alokasi Qty
                  </Label>
                  <AvField
                    id="eksekusi-summary-alokasiQty"
                    type="string"
                    className="form-control"
                    name="alokasiQty"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="aloksiAmountLabel" for="eksekusi-summary-aloksiAmount">
                    Aloksi Amount
                  </Label>
                  <AvField
                    id="eksekusi-summary-aloksiAmount"
                    type="string"
                    className="form-control"
                    name="aloksiAmount"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/eksekusi-summary" replace color="info">
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
  eksekusiSummaryEntity: storeState.eksekusiSummary.entity,
  loading: storeState.eksekusiSummary.loading,
  updating: storeState.eksekusiSummary.updating,
  updateSuccess: storeState.eksekusiSummary.updateSuccess
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
)(EksekusiSummaryUpdate);

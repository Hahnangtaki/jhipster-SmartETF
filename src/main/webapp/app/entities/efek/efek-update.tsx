import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './efek.reducer';
import { IEfek } from 'app/shared/model/efek.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEfekUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEfekUpdateState {
  isNew: boolean;
}

export class EfekUpdate extends React.Component<IEfekUpdateProps, IEfekUpdateState> {
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
      const { efekEntity } = this.props;
      const entity = {
        ...efekEntity,
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
    this.props.history.push('/entity/efek');
  };

  render() {
    const { efekEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.efek.home.createOrEditLabel">Create or edit a Efek</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : efekEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="efek-id">ID</Label>
                    <AvInput id="efek-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="kodeEfekLabel" for="efek-kodeEfek">
                    Kode Efek
                  </Label>
                  <AvField
                    id="efek-kodeEfek"
                    type="text"
                    name="kodeEfek"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="namaEfekLabel" for="efek-namaEfek">
                    Nama Efek
                  </Label>
                  <AvField
                    id="efek-namaEfek"
                    type="text"
                    name="namaEfek"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 150, errorMessage: 'This field cannot be longer than 150 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tipeEfekLabel" for="efek-tipeEfek">
                    Tipe Efek
                  </Label>
                  <AvField
                    id="efek-tipeEfek"
                    type="text"
                    name="tipeEfek"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lastClosingPriceLabel" for="efek-lastClosingPrice">
                    Last Closing Price
                  </Label>
                  <AvField
                    id="efek-lastClosingPrice"
                    type="string"
                    className="form-control"
                    name="lastClosingPrice"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lastClosingDateLabel" for="efek-lastClosingDate">
                    Last Closing Date
                  </Label>
                  <AvField id="efek-lastClosingDate" type="date" className="form-control" name="lastClosingDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="lastHairCutLabel" for="efek-lastHairCut">
                    Last Hair Cut
                  </Label>
                  <AvField
                    id="efek-lastHairCut"
                    type="string"
                    className="form-control"
                    name="lastHairCut"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      max: { value: 100, errorMessage: 'This field cannot be more than 100.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lastHairCutDateLabel" for="efek-lastHairCutDate">
                    Last Hair Cut Date
                  </Label>
                  <AvField id="efek-lastHairCutDate" type="date" className="form-control" name="lastHairCutDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusGadaiLabel" check>
                    <AvInput id="efek-statusGadai" type="checkbox" className="form-control" name="statusGadai" />
                    Status Gadai
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="jlhEfekBeredarLabel" for="efek-jlhEfekBeredar">
                    Jlh Efek Beredar
                  </Label>
                  <AvField
                    id="efek-jlhEfekBeredar"
                    type="string"
                    className="form-control"
                    name="jlhEfekBeredar"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="bmpkLabel" for="efek-bmpk">
                    Bmpk
                  </Label>
                  <AvField
                    id="efek-bmpk"
                    type="string"
                    className="form-control"
                    name="bmpk"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="bondRatingLabel" for="efek-bondRating">
                    Bond Rating
                  </Label>
                  <AvField
                    id="efek-bondRating"
                    type="text"
                    name="bondRating"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="bondMaturityDateLabel" for="efek-bondMaturityDate">
                    Bond Maturity Date
                  </Label>
                  <AvField id="efek-bondMaturityDate" type="date" className="form-control" name="bondMaturityDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="satuanLabel" for="efek-satuan">
                    Satuan
                  </Label>
                  <AvField
                    id="efek-satuan"
                    type="string"
                    className="form-control"
                    name="satuan"
                    validate={{
                      min: { value: 1, errorMessage: 'This field should be at least 1.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="efek-status">
                    Status
                  </Label>
                  <AvField
                    id="efek-status"
                    type="text"
                    name="status"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/efek" replace color="info">
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
  efekEntity: storeState.efek.entity,
  loading: storeState.efek.loading,
  updating: storeState.efek.updating,
  updateSuccess: storeState.efek.updateSuccess
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
)(EfekUpdate);

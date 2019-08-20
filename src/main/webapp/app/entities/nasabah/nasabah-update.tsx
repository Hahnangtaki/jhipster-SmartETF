import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './nasabah.reducer';
import { INasabah } from 'app/shared/model/nasabah.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INasabahUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface INasabahUpdateState {
  isNew: boolean;
}

export class NasabahUpdate extends React.Component<INasabahUpdateProps, INasabahUpdateState> {
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
      const { nasabahEntity } = this.props;
      const entity = {
        ...nasabahEntity,
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
    this.props.history.push('/entity/nasabah');
  };

  render() {
    const { nasabahEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.nasabah.home.createOrEditLabel">Create or edit a Nasabah</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : nasabahEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="nasabah-id">ID</Label>
                    <AvInput id="nasabah-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="kodeNasabahLabel" for="nasabah-kodeNasabah">
                    Kode Nasabah
                  </Label>
                  <AvField
                    id="nasabah-kodeNasabah"
                    type="text"
                    name="kodeNasabah"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="namaNasabahLabel" for="nasabah-namaNasabah">
                    Nama Nasabah
                  </Label>
                  <AvField
                    id="nasabah-namaNasabah"
                    type="text"
                    name="namaNasabah"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 150, errorMessage: 'This field cannot be longer than 150 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tipeNasabahLabel" for="nasabah-tipeNasabah">
                    Tipe Nasabah
                  </Label>
                  <AvField
                    id="nasabah-tipeNasabah"
                    type="text"
                    name="tipeNasabah"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="sidLabel" for="nasabah-sid">
                    Sid
                  </Label>
                  <AvField
                    id="nasabah-sid"
                    type="text"
                    name="sid"
                    validate={{
                      maxLength: { value: 15, errorMessage: 'This field cannot be longer than 15 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="bankSubRekLabel" for="nasabah-bankSubRek">
                    Bank Sub Rek
                  </Label>
                  <AvField
                    id="nasabah-bankSubRek"
                    type="text"
                    name="bankSubRek"
                    validate={{
                      maxLength: { value: 14, errorMessage: 'This field cannot be longer than 14 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="alamat1Label" for="nasabah-alamat1">
                    Alamat 1
                  </Label>
                  <AvField
                    id="nasabah-alamat1"
                    type="text"
                    name="alamat1"
                    validate={{
                      maxLength: { value: 60, errorMessage: 'This field cannot be longer than 60 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="alamat2Label" for="nasabah-alamat2">
                    Alamat 2
                  </Label>
                  <AvField
                    id="nasabah-alamat2"
                    type="text"
                    name="alamat2"
                    validate={{
                      maxLength: { value: 60, errorMessage: 'This field cannot be longer than 60 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="alamat3Label" for="nasabah-alamat3">
                    Alamat 3
                  </Label>
                  <AvField
                    id="nasabah-alamat3"
                    type="text"
                    name="alamat3"
                    validate={{
                      maxLength: { value: 60, errorMessage: 'This field cannot be longer than 60 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="noTelpLabel" for="nasabah-noTelp">
                    No Telp
                  </Label>
                  <AvField
                    id="nasabah-noTelp"
                    type="text"
                    name="noTelp"
                    validate={{
                      maxLength: { value: 30, errorMessage: 'This field cannot be longer than 30 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="noFaxLabel" for="nasabah-noFax">
                    No Fax
                  </Label>
                  <AvField
                    id="nasabah-noFax"
                    type="text"
                    name="noFax"
                    validate={{
                      maxLength: { value: 30, errorMessage: 'This field cannot be longer than 30 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusSubRekLabel" for="nasabah-statusSubRek">
                    Status Sub Rek
                  </Label>
                  <AvField
                    id="nasabah-statusSubRek"
                    type="text"
                    name="statusSubRek"
                    validate={{
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="nasabah-status">
                    Status
                  </Label>
                  <AvField id="nasabah-status" type="text" name="status" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/nasabah" replace color="info">
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
  nasabahEntity: storeState.nasabah.entity,
  loading: storeState.nasabah.loading,
  updating: storeState.nasabah.updating,
  updateSuccess: storeState.nasabah.updateSuccess
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
)(NasabahUpdate);

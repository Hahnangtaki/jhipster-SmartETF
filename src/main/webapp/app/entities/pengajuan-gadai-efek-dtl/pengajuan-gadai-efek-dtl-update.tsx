import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './pengajuan-gadai-efek-dtl.reducer';
import { IPengajuanGadaiEfekDtl } from 'app/shared/model/pengajuan-gadai-efek-dtl.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPengajuanGadaiEfekDtlUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPengajuanGadaiEfekDtlUpdateState {
  isNew: boolean;
}

export class PengajuanGadaiEfekDtlUpdate extends React.Component<IPengajuanGadaiEfekDtlUpdateProps, IPengajuanGadaiEfekDtlUpdateState> {
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
      const { pengajuanGadaiEfekDtlEntity } = this.props;
      const entity = {
        ...pengajuanGadaiEfekDtlEntity,
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
    this.props.history.push('/entity/pengajuan-gadai-efek-dtl');
  };

  render() {
    const { pengajuanGadaiEfekDtlEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.pengajuanGadaiEfekDtl.home.createOrEditLabel">Create or edit a PengajuanGadaiEfekDtl</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : pengajuanGadaiEfekDtlEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="pengajuan-gadai-efek-dtl-id">ID</Label>
                    <AvInput id="pengajuan-gadai-efek-dtl-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="noPengajuanGadaiLabel" for="pengajuan-gadai-efek-dtl-noPengajuanGadai">
                    No Pengajuan Gadai
                  </Label>
                  <AvField
                    id="pengajuan-gadai-efek-dtl-noPengajuanGadai"
                    type="text"
                    name="noPengajuanGadai"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kodeEfekLabel" for="pengajuan-gadai-efek-dtl-kodeEfek">
                    Kode Efek
                  </Label>
                  <AvField
                    id="pengajuan-gadai-efek-dtl-kodeEfek"
                    type="text"
                    name="kodeEfek"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="quantityLabel" for="pengajuan-gadai-efek-dtl-quantity">
                    Quantity
                  </Label>
                  <AvField
                    id="pengajuan-gadai-efek-dtl-quantity"
                    type="string"
                    className="form-control"
                    name="quantity"
                    validate={{
                      min: { value: 1, errorMessage: 'This field should be at least 1.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/pengajuan-gadai-efek-dtl" replace color="info">
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
  pengajuanGadaiEfekDtlEntity: storeState.pengajuanGadaiEfekDtl.entity,
  loading: storeState.pengajuanGadaiEfekDtl.loading,
  updating: storeState.pengajuanGadaiEfekDtl.updating,
  updateSuccess: storeState.pengajuanGadaiEfekDtl.updateSuccess
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
)(PengajuanGadaiEfekDtlUpdate);

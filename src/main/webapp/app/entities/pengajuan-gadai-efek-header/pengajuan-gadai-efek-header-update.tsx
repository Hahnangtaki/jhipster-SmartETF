import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './pengajuan-gadai-efek-header.reducer';
import { IPengajuanGadaiEfekHeader } from 'app/shared/model/pengajuan-gadai-efek-header.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPengajuanGadaiEfekHeaderUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPengajuanGadaiEfekHeaderUpdateState {
  isNew: boolean;
}

export class PengajuanGadaiEfekHeaderUpdate extends React.Component<
  IPengajuanGadaiEfekHeaderUpdateProps,
  IPengajuanGadaiEfekHeaderUpdateState
> {
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
      const { pengajuanGadaiEfekHeaderEntity } = this.props;
      const entity = {
        ...pengajuanGadaiEfekHeaderEntity,
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
    this.props.history.push('/entity/pengajuan-gadai-efek-header');
  };

  render() {
    const { pengajuanGadaiEfekHeaderEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.pengajuanGadaiEfekHeader.home.createOrEditLabel">Create or edit a PengajuanGadaiEfekHeader</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : pengajuanGadaiEfekHeaderEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="pengajuan-gadai-efek-header-id">ID</Label>
                    <AvInput id="pengajuan-gadai-efek-header-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="noPengajuanGadaiLabel" for="pengajuan-gadai-efek-header-noPengajuanGadai">
                    No Pengajuan Gadai
                  </Label>
                  <AvField
                    id="pengajuan-gadai-efek-header-noPengajuanGadai"
                    type="text"
                    name="noPengajuanGadai"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tglEntriLabel" for="pengajuan-gadai-efek-header-tglEntri">
                    Tgl Entri
                  </Label>
                  <AvField id="pengajuan-gadai-efek-header-tglEntri" type="date" className="form-control" name="tglEntri" />
                </AvGroup>
                <AvGroup>
                  <Label id="tglEfekTerimaLabel" for="pengajuan-gadai-efek-header-tglEfekTerima">
                    Tgl Efek Terima
                  </Label>
                  <AvField id="pengajuan-gadai-efek-header-tglEfekTerima" type="date" className="form-control" name="tglEfekTerima" />
                </AvGroup>
                <AvGroup>
                  <Label id="kodeNasabahLabel" for="pengajuan-gadai-efek-header-kodeNasabah">
                    Kode Nasabah
                  </Label>
                  <AvField id="pengajuan-gadai-efek-header-kodeNasabah" type="text" name="kodeNasabah" />
                </AvGroup>
                <AvGroup>
                  <Label id="nilaiPinjamanLabel" for="pengajuan-gadai-efek-header-nilaiPinjaman">
                    Nilai Pinjaman
                  </Label>
                  <AvField
                    id="pengajuan-gadai-efek-header-nilaiPinjaman"
                    type="string"
                    className="form-control"
                    name="nilaiPinjaman"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="counterPartInstruksiLabel" for="pengajuan-gadai-efek-header-counterPartInstruksi">
                    Counter Part Instruksi
                  </Label>
                  <AvField id="pengajuan-gadai-efek-header-counterPartInstruksi" type="text" name="counterPartInstruksi" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="pengajuan-gadai-efek-header-status">
                    Status
                  </Label>
                  <AvField
                    id="pengajuan-gadai-efek-header-status"
                    type="text"
                    name="status"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/pengajuan-gadai-efek-header" replace color="info">
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
  pengajuanGadaiEfekHeaderEntity: storeState.pengajuanGadaiEfekHeader.entity,
  loading: storeState.pengajuanGadaiEfekHeader.loading,
  updating: storeState.pengajuanGadaiEfekHeader.updating,
  updateSuccess: storeState.pengajuanGadaiEfekHeader.updateSuccess
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
)(PengajuanGadaiEfekHeaderUpdate);

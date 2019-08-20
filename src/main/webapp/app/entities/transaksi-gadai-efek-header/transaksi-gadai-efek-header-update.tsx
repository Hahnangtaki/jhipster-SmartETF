import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './transaksi-gadai-efek-header.reducer';
import { ITransaksiGadaiEfekHeader } from 'app/shared/model/transaksi-gadai-efek-header.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITransaksiGadaiEfekHeaderUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITransaksiGadaiEfekHeaderUpdateState {
  isNew: boolean;
}

export class TransaksiGadaiEfekHeaderUpdate extends React.Component<
  ITransaksiGadaiEfekHeaderUpdateProps,
  ITransaksiGadaiEfekHeaderUpdateState
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
      const { transaksiGadaiEfekHeaderEntity } = this.props;
      const entity = {
        ...transaksiGadaiEfekHeaderEntity,
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
    this.props.history.push('/entity/transaksi-gadai-efek-header');
  };

  render() {
    const { transaksiGadaiEfekHeaderEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.transaksiGadaiEfekHeader.home.createOrEditLabel">Create or edit a TransaksiGadaiEfekHeader</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : transaksiGadaiEfekHeaderEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="transaksi-gadai-efek-header-id">ID</Label>
                    <AvInput id="transaksi-gadai-efek-header-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="noKontrakLabel" for="transaksi-gadai-efek-header-noKontrak">
                    No Kontrak
                  </Label>
                  <AvField
                    id="transaksi-gadai-efek-header-noKontrak"
                    type="text"
                    name="noKontrak"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tglEntriLabel" for="transaksi-gadai-efek-header-tglEntri">
                    Tgl Entri
                  </Label>
                  <AvField
                    id="transaksi-gadai-efek-header-tglEntri"
                    type="date"
                    className="form-control"
                    name="tglEntri"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tglPencairanLabel" for="transaksi-gadai-efek-header-tglPencairan">
                    Tgl Pencairan
                  </Label>
                  <AvField id="transaksi-gadai-efek-header-tglPencairan" type="date" className="form-control" name="tglPencairan" />
                </AvGroup>
                <AvGroup>
                  <Label id="tglJatuhTempoLabel" for="transaksi-gadai-efek-header-tglJatuhTempo">
                    Tgl Jatuh Tempo
                  </Label>
                  <AvField id="transaksi-gadai-efek-header-tglJatuhTempo" type="date" className="form-control" name="tglJatuhTempo" />
                </AvGroup>
                <AvGroup>
                  <Label id="noPengajuanGadaiLabel" for="transaksi-gadai-efek-header-noPengajuanGadai">
                    No Pengajuan Gadai
                  </Label>
                  <AvField id="transaksi-gadai-efek-header-noPengajuanGadai" type="text" name="noPengajuanGadai" />
                </AvGroup>
                <AvGroup>
                  <Label id="kodeNasabahLabel" for="transaksi-gadai-efek-header-kodeNasabah">
                    Kode Nasabah
                  </Label>
                  <AvField id="transaksi-gadai-efek-header-kodeNasabah" type="text" name="kodeNasabah" />
                </AvGroup>
                <AvGroup>
                  <Label id="nilaiKewajibanLabel" for="transaksi-gadai-efek-header-nilaiKewajiban">
                    Nilai Kewajiban
                  </Label>
                  <AvField
                    id="transaksi-gadai-efek-header-nilaiKewajiban"
                    type="string"
                    className="form-control"
                    name="nilaiKewajiban"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tglKirimEfekLabel" for="transaksi-gadai-efek-header-tglKirimEfek">
                    Tgl Kirim Efek
                  </Label>
                  <AvField id="transaksi-gadai-efek-header-tglKirimEfek" type="date" className="form-control" name="tglKirimEfek" />
                </AvGroup>
                <AvGroup>
                  <Label id="counterPartInstruksiLabel" for="transaksi-gadai-efek-header-counterPartInstruksi">
                    Counter Part Instruksi
                  </Label>
                  <AvField id="transaksi-gadai-efek-header-counterPartInstruksi" type="text" name="counterPartInstruksi" />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel" for="transaksi-gadai-efek-header-status">
                    Status
                  </Label>
                  <AvField id="transaksi-gadai-efek-header-status" type="text" name="status" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/transaksi-gadai-efek-header" replace color="info">
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
  transaksiGadaiEfekHeaderEntity: storeState.transaksiGadaiEfekHeader.entity,
  loading: storeState.transaksiGadaiEfekHeader.loading,
  updating: storeState.transaksiGadaiEfekHeader.updating,
  updateSuccess: storeState.transaksiGadaiEfekHeader.updateSuccess
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
)(TransaksiGadaiEfekHeaderUpdate);

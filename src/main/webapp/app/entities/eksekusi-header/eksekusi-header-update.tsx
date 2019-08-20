import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './eksekusi-header.reducer';
import { IEksekusiHeader } from 'app/shared/model/eksekusi-header.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEksekusiHeaderUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEksekusiHeaderUpdateState {
  isNew: boolean;
}

export class EksekusiHeaderUpdate extends React.Component<IEksekusiHeaderUpdateProps, IEksekusiHeaderUpdateState> {
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
      const { eksekusiHeaderEntity } = this.props;
      const entity = {
        ...eksekusiHeaderEntity,
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
    this.props.history.push('/entity/eksekusi-header');
  };

  render() {
    const { eksekusiHeaderEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.eksekusiHeader.home.createOrEditLabel">Create or edit a EksekusiHeader</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : eksekusiHeaderEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="eksekusi-header-id">ID</Label>
                    <AvInput id="eksekusi-header-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="noEksekusiLabel" for="eksekusi-header-noEksekusi">
                    No Eksekusi
                  </Label>
                  <AvField
                    id="eksekusi-header-noEksekusi"
                    type="text"
                    name="noEksekusi"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="tanggalEntriLabel" for="eksekusi-header-tanggalEntri">
                    Tanggal Entri
                  </Label>
                  <AvField id="eksekusi-header-tanggalEntri" type="date" className="form-control" name="tanggalEntri" />
                </AvGroup>
                <AvGroup>
                  <Label id="tanggalTradeLabel" for="eksekusi-header-tanggalTrade">
                    Tanggal Trade
                  </Label>
                  <AvField id="eksekusi-header-tanggalTrade" type="date" className="form-control" name="tanggalTrade" />
                </AvGroup>
                <AvGroup>
                  <Label id="tanggalSettleLabel" for="eksekusi-header-tanggalSettle">
                    Tanggal Settle
                  </Label>
                  <AvField id="eksekusi-header-tanggalSettle" type="date" className="form-control" name="tanggalSettle" />
                </AvGroup>
                <AvGroup>
                  <Label id="kodeBrokerLabel" for="eksekusi-header-kodeBroker">
                    Kode Broker
                  </Label>
                  <AvField id="eksekusi-header-kodeBroker" type="text" name="kodeBroker" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/eksekusi-header" replace color="info">
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
  eksekusiHeaderEntity: storeState.eksekusiHeader.entity,
  loading: storeState.eksekusiHeader.loading,
  updating: storeState.eksekusiHeader.updating,
  updateSuccess: storeState.eksekusiHeader.updateSuccess
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
)(EksekusiHeaderUpdate);

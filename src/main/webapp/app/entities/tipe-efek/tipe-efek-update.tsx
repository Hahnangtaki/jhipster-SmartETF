import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './tipe-efek.reducer';
import { ITipeEfek } from 'app/shared/model/tipe-efek.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITipeEfekUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITipeEfekUpdateState {
  isNew: boolean;
}

export class TipeEfekUpdate extends React.Component<ITipeEfekUpdateProps, ITipeEfekUpdateState> {
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
      const { tipeEfekEntity } = this.props;
      const entity = {
        ...tipeEfekEntity,
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
    this.props.history.push('/entity/tipe-efek');
  };

  render() {
    const { tipeEfekEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.tipeEfek.home.createOrEditLabel">Create or edit a TipeEfek</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : tipeEfekEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="tipe-efek-id">ID</Label>
                    <AvInput id="tipe-efek-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="tipeEfekLabel" for="tipe-efek-tipeEfek">
                    Tipe Efek
                  </Label>
                  <AvField
                    id="tipe-efek-tipeEfek"
                    type="text"
                    name="tipeEfek"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      minLength: { value: 1, errorMessage: 'This field is required to be at least 1 characters.' },
                      maxLength: { value: 1, errorMessage: 'This field cannot be longer than 1 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="keteranganLabel" for="tipe-efek-keterangan">
                    Keterangan
                  </Label>
                  <AvField
                    id="tipe-efek-keterangan"
                    type="text"
                    name="keterangan"
                    validate={{
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/tipe-efek" replace color="info">
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
  tipeEfekEntity: storeState.tipeEfek.entity,
  loading: storeState.tipeEfek.loading,
  updating: storeState.tipeEfek.updating,
  updateSuccess: storeState.tipeEfek.updateSuccess
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
)(TipeEfekUpdate);

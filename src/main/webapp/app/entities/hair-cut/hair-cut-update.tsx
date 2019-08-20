import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './hair-cut.reducer';
import { IHairCut } from 'app/shared/model/hair-cut.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHairCutUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IHairCutUpdateState {
  isNew: boolean;
}

export class HairCutUpdate extends React.Component<IHairCutUpdateProps, IHairCutUpdateState> {
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
      const { hairCutEntity } = this.props;
      const entity = {
        ...hairCutEntity,
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
    this.props.history.push('/entity/hair-cut');
  };

  render() {
    const { hairCutEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.hairCut.home.createOrEditLabel">Create or edit a HairCut</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : hairCutEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="hair-cut-id">ID</Label>
                    <AvInput id="hair-cut-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="tanggalLabel" for="hair-cut-tanggal">
                    Tanggal
                  </Label>
                  <AvField
                    id="hair-cut-tanggal"
                    type="date"
                    className="form-control"
                    name="tanggal"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="kodeEfekLabel" for="hair-cut-kodeEfek">
                    Kode Efek
                  </Label>
                  <AvField
                    id="hair-cut-kodeEfek"
                    type="text"
                    name="kodeEfek"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nilaiHairCutLabel" for="hair-cut-nilaiHairCut">
                    Nilai Hair Cut
                  </Label>
                  <AvField
                    id="hair-cut-nilaiHairCut"
                    type="string"
                    className="form-control"
                    name="nilaiHairCut"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      max: { value: 100, errorMessage: 'This field cannot be more than 100.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/hair-cut" replace color="info">
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
  hairCutEntity: storeState.hairCut.entity,
  loading: storeState.hairCut.loading,
  updating: storeState.hairCut.updating,
  updateSuccess: storeState.hairCut.updateSuccess
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
)(HairCutUpdate);

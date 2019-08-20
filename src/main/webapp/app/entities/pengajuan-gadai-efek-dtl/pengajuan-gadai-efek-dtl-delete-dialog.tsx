import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IPengajuanGadaiEfekDtl } from 'app/shared/model/pengajuan-gadai-efek-dtl.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './pengajuan-gadai-efek-dtl.reducer';

export interface IPengajuanGadaiEfekDtlDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PengajuanGadaiEfekDtlDeleteDialog extends React.Component<IPengajuanGadaiEfekDtlDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.pengajuanGadaiEfekDtlEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { pengajuanGadaiEfekDtlEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>Confirm delete operation</ModalHeader>
        <ModalBody id="smartEtfApp.pengajuanGadaiEfekDtl.delete.question">
          Are you sure you want to delete this PengajuanGadaiEfekDtl?
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />
            &nbsp; Cancel
          </Button>
          <Button id="jhi-confirm-delete-pengajuanGadaiEfekDtl" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />
            &nbsp; Delete
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ pengajuanGadaiEfekDtl }: IRootState) => ({
  pengajuanGadaiEfekDtlEntity: pengajuanGadaiEfekDtl.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PengajuanGadaiEfekDtlDeleteDialog);

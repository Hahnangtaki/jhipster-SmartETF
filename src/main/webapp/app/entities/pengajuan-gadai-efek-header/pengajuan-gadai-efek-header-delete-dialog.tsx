import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IPengajuanGadaiEfekHeader } from 'app/shared/model/pengajuan-gadai-efek-header.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './pengajuan-gadai-efek-header.reducer';

export interface IPengajuanGadaiEfekHeaderDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PengajuanGadaiEfekHeaderDeleteDialog extends React.Component<IPengajuanGadaiEfekHeaderDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.pengajuanGadaiEfekHeaderEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { pengajuanGadaiEfekHeaderEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>Confirm delete operation</ModalHeader>
        <ModalBody id="smartEtfApp.pengajuanGadaiEfekHeader.delete.question">
          Are you sure you want to delete this PengajuanGadaiEfekHeader?
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />
            &nbsp; Cancel
          </Button>
          <Button id="jhi-confirm-delete-pengajuanGadaiEfekHeader" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />
            &nbsp; Delete
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ pengajuanGadaiEfekHeader }: IRootState) => ({
  pengajuanGadaiEfekHeaderEntity: pengajuanGadaiEfekHeader.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PengajuanGadaiEfekHeaderDeleteDialog);

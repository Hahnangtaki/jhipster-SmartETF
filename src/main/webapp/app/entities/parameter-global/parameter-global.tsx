import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './parameter-global.reducer';
import { IParameterGlobal } from 'app/shared/model/parameter-global.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IParameterGlobalProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ParameterGlobal extends React.Component<IParameterGlobalProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { parameterGlobalList, match } = this.props;
    return (
      <div>
        <h2 id="parameter-global-heading">
          Parameter Globals
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Parameter Global
          </Link>
        </h2>
        <div className="table-responsive">
          {parameterGlobalList && parameterGlobalList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Prm Id</th>
                  <th>Prm Name</th>
                  <th>Prm Ty</th>
                  <th>App Type</th>
                  <th>Int Val</th>
                  <th>Float Val</th>
                  <th>Str Val</th>
                  <th>Date Val</th>
                  <th>Show</th>
                  <th>Edit</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {parameterGlobalList.map((parameterGlobal, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${parameterGlobal.id}`} color="link" size="sm">
                        {parameterGlobal.id}
                      </Button>
                    </td>
                    <td>{parameterGlobal.prmId}</td>
                    <td>{parameterGlobal.prmName}</td>
                    <td>{parameterGlobal.prmTy}</td>
                    <td>{parameterGlobal.appType}</td>
                    <td>{parameterGlobal.intVal}</td>
                    <td>{parameterGlobal.floatVal}</td>
                    <td>{parameterGlobal.strVal}</td>
                    <td>
                      <TextFormat type="date" value={parameterGlobal.dateVal} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{parameterGlobal.show ? 'true' : 'false'}</td>
                    <td>{parameterGlobal.edit ? 'true' : 'false'}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${parameterGlobal.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${parameterGlobal.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${parameterGlobal.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Parameter Globals found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ parameterGlobal }: IRootState) => ({
  parameterGlobalList: parameterGlobal.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ParameterGlobal);

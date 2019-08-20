import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBankCustodi, defaultValue } from 'app/shared/model/bank-custodi.model';

export const ACTION_TYPES = {
  FETCH_BANKCUSTODI_LIST: 'bankCustodi/FETCH_BANKCUSTODI_LIST',
  FETCH_BANKCUSTODI: 'bankCustodi/FETCH_BANKCUSTODI',
  CREATE_BANKCUSTODI: 'bankCustodi/CREATE_BANKCUSTODI',
  UPDATE_BANKCUSTODI: 'bankCustodi/UPDATE_BANKCUSTODI',
  DELETE_BANKCUSTODI: 'bankCustodi/DELETE_BANKCUSTODI',
  RESET: 'bankCustodi/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBankCustodi>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type BankCustodiState = Readonly<typeof initialState>;

// Reducer

export default (state: BankCustodiState = initialState, action): BankCustodiState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BANKCUSTODI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BANKCUSTODI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_BANKCUSTODI):
    case REQUEST(ACTION_TYPES.UPDATE_BANKCUSTODI):
    case REQUEST(ACTION_TYPES.DELETE_BANKCUSTODI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_BANKCUSTODI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BANKCUSTODI):
    case FAILURE(ACTION_TYPES.CREATE_BANKCUSTODI):
    case FAILURE(ACTION_TYPES.UPDATE_BANKCUSTODI):
    case FAILURE(ACTION_TYPES.DELETE_BANKCUSTODI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_BANKCUSTODI_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BANKCUSTODI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_BANKCUSTODI):
    case SUCCESS(ACTION_TYPES.UPDATE_BANKCUSTODI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_BANKCUSTODI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/bank-custodis';

// Actions

export const getEntities: ICrudGetAllAction<IBankCustodi> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BANKCUSTODI_LIST,
  payload: axios.get<IBankCustodi>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IBankCustodi> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BANKCUSTODI,
    payload: axios.get<IBankCustodi>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IBankCustodi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BANKCUSTODI,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBankCustodi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BANKCUSTODI,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBankCustodi> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BANKCUSTODI,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

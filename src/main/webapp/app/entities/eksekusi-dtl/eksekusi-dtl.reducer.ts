import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEksekusiDtl, defaultValue } from 'app/shared/model/eksekusi-dtl.model';

export const ACTION_TYPES = {
  FETCH_EKSEKUSIDTL_LIST: 'eksekusiDtl/FETCH_EKSEKUSIDTL_LIST',
  FETCH_EKSEKUSIDTL: 'eksekusiDtl/FETCH_EKSEKUSIDTL',
  CREATE_EKSEKUSIDTL: 'eksekusiDtl/CREATE_EKSEKUSIDTL',
  UPDATE_EKSEKUSIDTL: 'eksekusiDtl/UPDATE_EKSEKUSIDTL',
  DELETE_EKSEKUSIDTL: 'eksekusiDtl/DELETE_EKSEKUSIDTL',
  RESET: 'eksekusiDtl/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEksekusiDtl>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EksekusiDtlState = Readonly<typeof initialState>;

// Reducer

export default (state: EksekusiDtlState = initialState, action): EksekusiDtlState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EKSEKUSIDTL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EKSEKUSIDTL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_EKSEKUSIDTL):
    case REQUEST(ACTION_TYPES.UPDATE_EKSEKUSIDTL):
    case REQUEST(ACTION_TYPES.DELETE_EKSEKUSIDTL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_EKSEKUSIDTL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EKSEKUSIDTL):
    case FAILURE(ACTION_TYPES.CREATE_EKSEKUSIDTL):
    case FAILURE(ACTION_TYPES.UPDATE_EKSEKUSIDTL):
    case FAILURE(ACTION_TYPES.DELETE_EKSEKUSIDTL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_EKSEKUSIDTL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_EKSEKUSIDTL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_EKSEKUSIDTL):
    case SUCCESS(ACTION_TYPES.UPDATE_EKSEKUSIDTL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_EKSEKUSIDTL):
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

const apiUrl = 'api/eksekusi-dtls';

// Actions

export const getEntities: ICrudGetAllAction<IEksekusiDtl> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_EKSEKUSIDTL_LIST,
  payload: axios.get<IEksekusiDtl>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEksekusiDtl> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EKSEKUSIDTL,
    payload: axios.get<IEksekusiDtl>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEksekusiDtl> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EKSEKUSIDTL,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEksekusiDtl> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EKSEKUSIDTL,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEksekusiDtl> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EKSEKUSIDTL,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

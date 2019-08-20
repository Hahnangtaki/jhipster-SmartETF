import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPengajuanGadaiEfekDtl, defaultValue } from 'app/shared/model/pengajuan-gadai-efek-dtl.model';

export const ACTION_TYPES = {
  FETCH_PENGAJUANGADAIEFEKDTL_LIST: 'pengajuanGadaiEfekDtl/FETCH_PENGAJUANGADAIEFEKDTL_LIST',
  FETCH_PENGAJUANGADAIEFEKDTL: 'pengajuanGadaiEfekDtl/FETCH_PENGAJUANGADAIEFEKDTL',
  CREATE_PENGAJUANGADAIEFEKDTL: 'pengajuanGadaiEfekDtl/CREATE_PENGAJUANGADAIEFEKDTL',
  UPDATE_PENGAJUANGADAIEFEKDTL: 'pengajuanGadaiEfekDtl/UPDATE_PENGAJUANGADAIEFEKDTL',
  DELETE_PENGAJUANGADAIEFEKDTL: 'pengajuanGadaiEfekDtl/DELETE_PENGAJUANGADAIEFEKDTL',
  RESET: 'pengajuanGadaiEfekDtl/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPengajuanGadaiEfekDtl>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PengajuanGadaiEfekDtlState = Readonly<typeof initialState>;

// Reducer

export default (state: PengajuanGadaiEfekDtlState = initialState, action): PengajuanGadaiEfekDtlState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKDTL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKDTL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PENGAJUANGADAIEFEKDTL):
    case REQUEST(ACTION_TYPES.UPDATE_PENGAJUANGADAIEFEKDTL):
    case REQUEST(ACTION_TYPES.DELETE_PENGAJUANGADAIEFEKDTL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKDTL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKDTL):
    case FAILURE(ACTION_TYPES.CREATE_PENGAJUANGADAIEFEKDTL):
    case FAILURE(ACTION_TYPES.UPDATE_PENGAJUANGADAIEFEKDTL):
    case FAILURE(ACTION_TYPES.DELETE_PENGAJUANGADAIEFEKDTL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKDTL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKDTL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PENGAJUANGADAIEFEKDTL):
    case SUCCESS(ACTION_TYPES.UPDATE_PENGAJUANGADAIEFEKDTL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PENGAJUANGADAIEFEKDTL):
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

const apiUrl = 'api/pengajuan-gadai-efek-dtls';

// Actions

export const getEntities: ICrudGetAllAction<IPengajuanGadaiEfekDtl> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKDTL_LIST,
  payload: axios.get<IPengajuanGadaiEfekDtl>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPengajuanGadaiEfekDtl> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKDTL,
    payload: axios.get<IPengajuanGadaiEfekDtl>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPengajuanGadaiEfekDtl> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PENGAJUANGADAIEFEKDTL,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPengajuanGadaiEfekDtl> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PENGAJUANGADAIEFEKDTL,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPengajuanGadaiEfekDtl> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PENGAJUANGADAIEFEKDTL,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

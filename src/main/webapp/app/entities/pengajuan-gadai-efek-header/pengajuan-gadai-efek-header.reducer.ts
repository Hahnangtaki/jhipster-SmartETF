import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPengajuanGadaiEfekHeader, defaultValue } from 'app/shared/model/pengajuan-gadai-efek-header.model';

export const ACTION_TYPES = {
  FETCH_PENGAJUANGADAIEFEKHEADER_LIST: 'pengajuanGadaiEfekHeader/FETCH_PENGAJUANGADAIEFEKHEADER_LIST',
  FETCH_PENGAJUANGADAIEFEKHEADER: 'pengajuanGadaiEfekHeader/FETCH_PENGAJUANGADAIEFEKHEADER',
  CREATE_PENGAJUANGADAIEFEKHEADER: 'pengajuanGadaiEfekHeader/CREATE_PENGAJUANGADAIEFEKHEADER',
  UPDATE_PENGAJUANGADAIEFEKHEADER: 'pengajuanGadaiEfekHeader/UPDATE_PENGAJUANGADAIEFEKHEADER',
  DELETE_PENGAJUANGADAIEFEKHEADER: 'pengajuanGadaiEfekHeader/DELETE_PENGAJUANGADAIEFEKHEADER',
  RESET: 'pengajuanGadaiEfekHeader/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPengajuanGadaiEfekHeader>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PengajuanGadaiEfekHeaderState = Readonly<typeof initialState>;

// Reducer

export default (state: PengajuanGadaiEfekHeaderState = initialState, action): PengajuanGadaiEfekHeaderState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKHEADER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKHEADER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PENGAJUANGADAIEFEKHEADER):
    case REQUEST(ACTION_TYPES.UPDATE_PENGAJUANGADAIEFEKHEADER):
    case REQUEST(ACTION_TYPES.DELETE_PENGAJUANGADAIEFEKHEADER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKHEADER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKHEADER):
    case FAILURE(ACTION_TYPES.CREATE_PENGAJUANGADAIEFEKHEADER):
    case FAILURE(ACTION_TYPES.UPDATE_PENGAJUANGADAIEFEKHEADER):
    case FAILURE(ACTION_TYPES.DELETE_PENGAJUANGADAIEFEKHEADER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKHEADER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKHEADER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PENGAJUANGADAIEFEKHEADER):
    case SUCCESS(ACTION_TYPES.UPDATE_PENGAJUANGADAIEFEKHEADER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PENGAJUANGADAIEFEKHEADER):
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

const apiUrl = 'api/pengajuan-gadai-efek-headers';

// Actions

export const getEntities: ICrudGetAllAction<IPengajuanGadaiEfekHeader> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKHEADER_LIST,
  payload: axios.get<IPengajuanGadaiEfekHeader>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPengajuanGadaiEfekHeader> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PENGAJUANGADAIEFEKHEADER,
    payload: axios.get<IPengajuanGadaiEfekHeader>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPengajuanGadaiEfekHeader> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PENGAJUANGADAIEFEKHEADER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPengajuanGadaiEfekHeader> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PENGAJUANGADAIEFEKHEADER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPengajuanGadaiEfekHeader> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PENGAJUANGADAIEFEKHEADER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

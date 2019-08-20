import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITransaksiGadaiEfekHeader, defaultValue } from 'app/shared/model/transaksi-gadai-efek-header.model';

export const ACTION_TYPES = {
  FETCH_TRANSAKSIGADAIEFEKHEADER_LIST: 'transaksiGadaiEfekHeader/FETCH_TRANSAKSIGADAIEFEKHEADER_LIST',
  FETCH_TRANSAKSIGADAIEFEKHEADER: 'transaksiGadaiEfekHeader/FETCH_TRANSAKSIGADAIEFEKHEADER',
  CREATE_TRANSAKSIGADAIEFEKHEADER: 'transaksiGadaiEfekHeader/CREATE_TRANSAKSIGADAIEFEKHEADER',
  UPDATE_TRANSAKSIGADAIEFEKHEADER: 'transaksiGadaiEfekHeader/UPDATE_TRANSAKSIGADAIEFEKHEADER',
  DELETE_TRANSAKSIGADAIEFEKHEADER: 'transaksiGadaiEfekHeader/DELETE_TRANSAKSIGADAIEFEKHEADER',
  RESET: 'transaksiGadaiEfekHeader/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITransaksiGadaiEfekHeader>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TransaksiGadaiEfekHeaderState = Readonly<typeof initialState>;

// Reducer

export default (state: TransaksiGadaiEfekHeaderState = initialState, action): TransaksiGadaiEfekHeaderState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TRANSAKSIGADAIEFEKHEADER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TRANSAKSIGADAIEFEKHEADER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TRANSAKSIGADAIEFEKHEADER):
    case REQUEST(ACTION_TYPES.UPDATE_TRANSAKSIGADAIEFEKHEADER):
    case REQUEST(ACTION_TYPES.DELETE_TRANSAKSIGADAIEFEKHEADER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TRANSAKSIGADAIEFEKHEADER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TRANSAKSIGADAIEFEKHEADER):
    case FAILURE(ACTION_TYPES.CREATE_TRANSAKSIGADAIEFEKHEADER):
    case FAILURE(ACTION_TYPES.UPDATE_TRANSAKSIGADAIEFEKHEADER):
    case FAILURE(ACTION_TYPES.DELETE_TRANSAKSIGADAIEFEKHEADER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRANSAKSIGADAIEFEKHEADER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRANSAKSIGADAIEFEKHEADER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TRANSAKSIGADAIEFEKHEADER):
    case SUCCESS(ACTION_TYPES.UPDATE_TRANSAKSIGADAIEFEKHEADER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TRANSAKSIGADAIEFEKHEADER):
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

const apiUrl = 'api/transaksi-gadai-efek-headers';

// Actions

export const getEntities: ICrudGetAllAction<ITransaksiGadaiEfekHeader> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TRANSAKSIGADAIEFEKHEADER_LIST,
  payload: axios.get<ITransaksiGadaiEfekHeader>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITransaksiGadaiEfekHeader> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TRANSAKSIGADAIEFEKHEADER,
    payload: axios.get<ITransaksiGadaiEfekHeader>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITransaksiGadaiEfekHeader> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TRANSAKSIGADAIEFEKHEADER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITransaksiGadaiEfekHeader> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TRANSAKSIGADAIEFEKHEADER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITransaksiGadaiEfekHeader> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TRANSAKSIGADAIEFEKHEADER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

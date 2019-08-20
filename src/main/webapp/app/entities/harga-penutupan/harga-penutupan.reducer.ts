import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHargaPenutupan, defaultValue } from 'app/shared/model/harga-penutupan.model';

export const ACTION_TYPES = {
  FETCH_HARGAPENUTUPAN_LIST: 'hargaPenutupan/FETCH_HARGAPENUTUPAN_LIST',
  FETCH_HARGAPENUTUPAN: 'hargaPenutupan/FETCH_HARGAPENUTUPAN',
  CREATE_HARGAPENUTUPAN: 'hargaPenutupan/CREATE_HARGAPENUTUPAN',
  UPDATE_HARGAPENUTUPAN: 'hargaPenutupan/UPDATE_HARGAPENUTUPAN',
  DELETE_HARGAPENUTUPAN: 'hargaPenutupan/DELETE_HARGAPENUTUPAN',
  RESET: 'hargaPenutupan/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHargaPenutupan>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type HargaPenutupanState = Readonly<typeof initialState>;

// Reducer

export default (state: HargaPenutupanState = initialState, action): HargaPenutupanState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_HARGAPENUTUPAN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HARGAPENUTUPAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HARGAPENUTUPAN):
    case REQUEST(ACTION_TYPES.UPDATE_HARGAPENUTUPAN):
    case REQUEST(ACTION_TYPES.DELETE_HARGAPENUTUPAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_HARGAPENUTUPAN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HARGAPENUTUPAN):
    case FAILURE(ACTION_TYPES.CREATE_HARGAPENUTUPAN):
    case FAILURE(ACTION_TYPES.UPDATE_HARGAPENUTUPAN):
    case FAILURE(ACTION_TYPES.DELETE_HARGAPENUTUPAN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_HARGAPENUTUPAN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HARGAPENUTUPAN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HARGAPENUTUPAN):
    case SUCCESS(ACTION_TYPES.UPDATE_HARGAPENUTUPAN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HARGAPENUTUPAN):
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

const apiUrl = 'api/harga-penutupans';

// Actions

export const getEntities: ICrudGetAllAction<IHargaPenutupan> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_HARGAPENUTUPAN_LIST,
  payload: axios.get<IHargaPenutupan>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IHargaPenutupan> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HARGAPENUTUPAN,
    payload: axios.get<IHargaPenutupan>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHargaPenutupan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HARGAPENUTUPAN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHargaPenutupan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HARGAPENUTUPAN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHargaPenutupan> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HARGAPENUTUPAN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

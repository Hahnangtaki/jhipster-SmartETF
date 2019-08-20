import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INasabah, defaultValue } from 'app/shared/model/nasabah.model';

export const ACTION_TYPES = {
  FETCH_NASABAH_LIST: 'nasabah/FETCH_NASABAH_LIST',
  FETCH_NASABAH: 'nasabah/FETCH_NASABAH',
  CREATE_NASABAH: 'nasabah/CREATE_NASABAH',
  UPDATE_NASABAH: 'nasabah/UPDATE_NASABAH',
  DELETE_NASABAH: 'nasabah/DELETE_NASABAH',
  RESET: 'nasabah/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INasabah>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type NasabahState = Readonly<typeof initialState>;

// Reducer

export default (state: NasabahState = initialState, action): NasabahState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_NASABAH_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NASABAH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_NASABAH):
    case REQUEST(ACTION_TYPES.UPDATE_NASABAH):
    case REQUEST(ACTION_TYPES.DELETE_NASABAH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_NASABAH_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NASABAH):
    case FAILURE(ACTION_TYPES.CREATE_NASABAH):
    case FAILURE(ACTION_TYPES.UPDATE_NASABAH):
    case FAILURE(ACTION_TYPES.DELETE_NASABAH):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_NASABAH_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NASABAH):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_NASABAH):
    case SUCCESS(ACTION_TYPES.UPDATE_NASABAH):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_NASABAH):
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

const apiUrl = 'api/nasabahs';

// Actions

export const getEntities: ICrudGetAllAction<INasabah> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_NASABAH_LIST,
  payload: axios.get<INasabah>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<INasabah> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NASABAH,
    payload: axios.get<INasabah>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<INasabah> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NASABAH,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INasabah> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NASABAH,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<INasabah> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NASABAH,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

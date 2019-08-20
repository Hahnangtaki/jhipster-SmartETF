import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITipeEfek, defaultValue } from 'app/shared/model/tipe-efek.model';

export const ACTION_TYPES = {
  FETCH_TIPEEFEK_LIST: 'tipeEfek/FETCH_TIPEEFEK_LIST',
  FETCH_TIPEEFEK: 'tipeEfek/FETCH_TIPEEFEK',
  CREATE_TIPEEFEK: 'tipeEfek/CREATE_TIPEEFEK',
  UPDATE_TIPEEFEK: 'tipeEfek/UPDATE_TIPEEFEK',
  DELETE_TIPEEFEK: 'tipeEfek/DELETE_TIPEEFEK',
  RESET: 'tipeEfek/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITipeEfek>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TipeEfekState = Readonly<typeof initialState>;

// Reducer

export default (state: TipeEfekState = initialState, action): TipeEfekState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TIPEEFEK_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TIPEEFEK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TIPEEFEK):
    case REQUEST(ACTION_TYPES.UPDATE_TIPEEFEK):
    case REQUEST(ACTION_TYPES.DELETE_TIPEEFEK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TIPEEFEK_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TIPEEFEK):
    case FAILURE(ACTION_TYPES.CREATE_TIPEEFEK):
    case FAILURE(ACTION_TYPES.UPDATE_TIPEEFEK):
    case FAILURE(ACTION_TYPES.DELETE_TIPEEFEK):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TIPEEFEK_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TIPEEFEK):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TIPEEFEK):
    case SUCCESS(ACTION_TYPES.UPDATE_TIPEEFEK):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TIPEEFEK):
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

const apiUrl = 'api/tipe-efeks';

// Actions

export const getEntities: ICrudGetAllAction<ITipeEfek> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TIPEEFEK_LIST,
  payload: axios.get<ITipeEfek>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITipeEfek> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TIPEEFEK,
    payload: axios.get<ITipeEfek>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITipeEfek> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TIPEEFEK,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITipeEfek> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TIPEEFEK,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITipeEfek> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TIPEEFEK,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

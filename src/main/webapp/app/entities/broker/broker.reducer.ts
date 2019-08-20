import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBroker, defaultValue } from 'app/shared/model/broker.model';

export const ACTION_TYPES = {
  FETCH_BROKER_LIST: 'broker/FETCH_BROKER_LIST',
  FETCH_BROKER: 'broker/FETCH_BROKER',
  CREATE_BROKER: 'broker/CREATE_BROKER',
  UPDATE_BROKER: 'broker/UPDATE_BROKER',
  DELETE_BROKER: 'broker/DELETE_BROKER',
  RESET: 'broker/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBroker>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type BrokerState = Readonly<typeof initialState>;

// Reducer

export default (state: BrokerState = initialState, action): BrokerState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BROKER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BROKER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_BROKER):
    case REQUEST(ACTION_TYPES.UPDATE_BROKER):
    case REQUEST(ACTION_TYPES.DELETE_BROKER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_BROKER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BROKER):
    case FAILURE(ACTION_TYPES.CREATE_BROKER):
    case FAILURE(ACTION_TYPES.UPDATE_BROKER):
    case FAILURE(ACTION_TYPES.DELETE_BROKER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_BROKER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BROKER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_BROKER):
    case SUCCESS(ACTION_TYPES.UPDATE_BROKER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_BROKER):
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

const apiUrl = 'api/brokers';

// Actions

export const getEntities: ICrudGetAllAction<IBroker> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BROKER_LIST,
  payload: axios.get<IBroker>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IBroker> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BROKER,
    payload: axios.get<IBroker>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IBroker> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BROKER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBroker> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BROKER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBroker> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BROKER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

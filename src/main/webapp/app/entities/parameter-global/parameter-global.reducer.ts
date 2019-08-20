import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IParameterGlobal, defaultValue } from 'app/shared/model/parameter-global.model';

export const ACTION_TYPES = {
  FETCH_PARAMETERGLOBAL_LIST: 'parameterGlobal/FETCH_PARAMETERGLOBAL_LIST',
  FETCH_PARAMETERGLOBAL: 'parameterGlobal/FETCH_PARAMETERGLOBAL',
  CREATE_PARAMETERGLOBAL: 'parameterGlobal/CREATE_PARAMETERGLOBAL',
  UPDATE_PARAMETERGLOBAL: 'parameterGlobal/UPDATE_PARAMETERGLOBAL',
  DELETE_PARAMETERGLOBAL: 'parameterGlobal/DELETE_PARAMETERGLOBAL',
  RESET: 'parameterGlobal/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IParameterGlobal>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ParameterGlobalState = Readonly<typeof initialState>;

// Reducer

export default (state: ParameterGlobalState = initialState, action): ParameterGlobalState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PARAMETERGLOBAL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PARAMETERGLOBAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PARAMETERGLOBAL):
    case REQUEST(ACTION_TYPES.UPDATE_PARAMETERGLOBAL):
    case REQUEST(ACTION_TYPES.DELETE_PARAMETERGLOBAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_PARAMETERGLOBAL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PARAMETERGLOBAL):
    case FAILURE(ACTION_TYPES.CREATE_PARAMETERGLOBAL):
    case FAILURE(ACTION_TYPES.UPDATE_PARAMETERGLOBAL):
    case FAILURE(ACTION_TYPES.DELETE_PARAMETERGLOBAL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_PARAMETERGLOBAL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PARAMETERGLOBAL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PARAMETERGLOBAL):
    case SUCCESS(ACTION_TYPES.UPDATE_PARAMETERGLOBAL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PARAMETERGLOBAL):
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

const apiUrl = 'api/parameter-globals';

// Actions

export const getEntities: ICrudGetAllAction<IParameterGlobal> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PARAMETERGLOBAL_LIST,
  payload: axios.get<IParameterGlobal>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IParameterGlobal> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PARAMETERGLOBAL,
    payload: axios.get<IParameterGlobal>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IParameterGlobal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PARAMETERGLOBAL,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IParameterGlobal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PARAMETERGLOBAL,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IParameterGlobal> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PARAMETERGLOBAL,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

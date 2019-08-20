import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEksekusiSummary, defaultValue } from 'app/shared/model/eksekusi-summary.model';

export const ACTION_TYPES = {
  FETCH_EKSEKUSISUMMARY_LIST: 'eksekusiSummary/FETCH_EKSEKUSISUMMARY_LIST',
  FETCH_EKSEKUSISUMMARY: 'eksekusiSummary/FETCH_EKSEKUSISUMMARY',
  CREATE_EKSEKUSISUMMARY: 'eksekusiSummary/CREATE_EKSEKUSISUMMARY',
  UPDATE_EKSEKUSISUMMARY: 'eksekusiSummary/UPDATE_EKSEKUSISUMMARY',
  DELETE_EKSEKUSISUMMARY: 'eksekusiSummary/DELETE_EKSEKUSISUMMARY',
  RESET: 'eksekusiSummary/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEksekusiSummary>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EksekusiSummaryState = Readonly<typeof initialState>;

// Reducer

export default (state: EksekusiSummaryState = initialState, action): EksekusiSummaryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EKSEKUSISUMMARY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EKSEKUSISUMMARY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_EKSEKUSISUMMARY):
    case REQUEST(ACTION_TYPES.UPDATE_EKSEKUSISUMMARY):
    case REQUEST(ACTION_TYPES.DELETE_EKSEKUSISUMMARY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_EKSEKUSISUMMARY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EKSEKUSISUMMARY):
    case FAILURE(ACTION_TYPES.CREATE_EKSEKUSISUMMARY):
    case FAILURE(ACTION_TYPES.UPDATE_EKSEKUSISUMMARY):
    case FAILURE(ACTION_TYPES.DELETE_EKSEKUSISUMMARY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_EKSEKUSISUMMARY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_EKSEKUSISUMMARY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_EKSEKUSISUMMARY):
    case SUCCESS(ACTION_TYPES.UPDATE_EKSEKUSISUMMARY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_EKSEKUSISUMMARY):
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

const apiUrl = 'api/eksekusi-summaries';

// Actions

export const getEntities: ICrudGetAllAction<IEksekusiSummary> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_EKSEKUSISUMMARY_LIST,
  payload: axios.get<IEksekusiSummary>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEksekusiSummary> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EKSEKUSISUMMARY,
    payload: axios.get<IEksekusiSummary>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEksekusiSummary> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EKSEKUSISUMMARY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEksekusiSummary> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EKSEKUSISUMMARY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEksekusiSummary> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EKSEKUSISUMMARY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

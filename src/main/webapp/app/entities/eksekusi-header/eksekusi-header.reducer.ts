import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEksekusiHeader, defaultValue } from 'app/shared/model/eksekusi-header.model';

export const ACTION_TYPES = {
  FETCH_EKSEKUSIHEADER_LIST: 'eksekusiHeader/FETCH_EKSEKUSIHEADER_LIST',
  FETCH_EKSEKUSIHEADER: 'eksekusiHeader/FETCH_EKSEKUSIHEADER',
  CREATE_EKSEKUSIHEADER: 'eksekusiHeader/CREATE_EKSEKUSIHEADER',
  UPDATE_EKSEKUSIHEADER: 'eksekusiHeader/UPDATE_EKSEKUSIHEADER',
  DELETE_EKSEKUSIHEADER: 'eksekusiHeader/DELETE_EKSEKUSIHEADER',
  RESET: 'eksekusiHeader/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEksekusiHeader>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EksekusiHeaderState = Readonly<typeof initialState>;

// Reducer

export default (state: EksekusiHeaderState = initialState, action): EksekusiHeaderState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EKSEKUSIHEADER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EKSEKUSIHEADER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_EKSEKUSIHEADER):
    case REQUEST(ACTION_TYPES.UPDATE_EKSEKUSIHEADER):
    case REQUEST(ACTION_TYPES.DELETE_EKSEKUSIHEADER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_EKSEKUSIHEADER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EKSEKUSIHEADER):
    case FAILURE(ACTION_TYPES.CREATE_EKSEKUSIHEADER):
    case FAILURE(ACTION_TYPES.UPDATE_EKSEKUSIHEADER):
    case FAILURE(ACTION_TYPES.DELETE_EKSEKUSIHEADER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_EKSEKUSIHEADER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_EKSEKUSIHEADER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_EKSEKUSIHEADER):
    case SUCCESS(ACTION_TYPES.UPDATE_EKSEKUSIHEADER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_EKSEKUSIHEADER):
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

const apiUrl = 'api/eksekusi-headers';

// Actions

export const getEntities: ICrudGetAllAction<IEksekusiHeader> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_EKSEKUSIHEADER_LIST,
  payload: axios.get<IEksekusiHeader>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEksekusiHeader> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EKSEKUSIHEADER,
    payload: axios.get<IEksekusiHeader>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEksekusiHeader> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EKSEKUSIHEADER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEksekusiHeader> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EKSEKUSIHEADER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEksekusiHeader> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EKSEKUSIHEADER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

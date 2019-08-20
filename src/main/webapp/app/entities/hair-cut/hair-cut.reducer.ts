import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHairCut, defaultValue } from 'app/shared/model/hair-cut.model';

export const ACTION_TYPES = {
  FETCH_HAIRCUT_LIST: 'hairCut/FETCH_HAIRCUT_LIST',
  FETCH_HAIRCUT: 'hairCut/FETCH_HAIRCUT',
  CREATE_HAIRCUT: 'hairCut/CREATE_HAIRCUT',
  UPDATE_HAIRCUT: 'hairCut/UPDATE_HAIRCUT',
  DELETE_HAIRCUT: 'hairCut/DELETE_HAIRCUT',
  RESET: 'hairCut/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHairCut>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type HairCutState = Readonly<typeof initialState>;

// Reducer

export default (state: HairCutState = initialState, action): HairCutState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_HAIRCUT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HAIRCUT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HAIRCUT):
    case REQUEST(ACTION_TYPES.UPDATE_HAIRCUT):
    case REQUEST(ACTION_TYPES.DELETE_HAIRCUT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_HAIRCUT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HAIRCUT):
    case FAILURE(ACTION_TYPES.CREATE_HAIRCUT):
    case FAILURE(ACTION_TYPES.UPDATE_HAIRCUT):
    case FAILURE(ACTION_TYPES.DELETE_HAIRCUT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_HAIRCUT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HAIRCUT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HAIRCUT):
    case SUCCESS(ACTION_TYPES.UPDATE_HAIRCUT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HAIRCUT):
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

const apiUrl = 'api/hair-cuts';

// Actions

export const getEntities: ICrudGetAllAction<IHairCut> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_HAIRCUT_LIST,
  payload: axios.get<IHairCut>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IHairCut> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HAIRCUT,
    payload: axios.get<IHairCut>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHairCut> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HAIRCUT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHairCut> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HAIRCUT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHairCut> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HAIRCUT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

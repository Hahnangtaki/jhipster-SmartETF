import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IIdxHoliday, defaultValue } from 'app/shared/model/idx-holiday.model';

export const ACTION_TYPES = {
  FETCH_IDXHOLIDAY_LIST: 'idxHoliday/FETCH_IDXHOLIDAY_LIST',
  FETCH_IDXHOLIDAY: 'idxHoliday/FETCH_IDXHOLIDAY',
  CREATE_IDXHOLIDAY: 'idxHoliday/CREATE_IDXHOLIDAY',
  UPDATE_IDXHOLIDAY: 'idxHoliday/UPDATE_IDXHOLIDAY',
  DELETE_IDXHOLIDAY: 'idxHoliday/DELETE_IDXHOLIDAY',
  RESET: 'idxHoliday/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IIdxHoliday>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type IdxHolidayState = Readonly<typeof initialState>;

// Reducer

export default (state: IdxHolidayState = initialState, action): IdxHolidayState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_IDXHOLIDAY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_IDXHOLIDAY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_IDXHOLIDAY):
    case REQUEST(ACTION_TYPES.UPDATE_IDXHOLIDAY):
    case REQUEST(ACTION_TYPES.DELETE_IDXHOLIDAY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_IDXHOLIDAY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_IDXHOLIDAY):
    case FAILURE(ACTION_TYPES.CREATE_IDXHOLIDAY):
    case FAILURE(ACTION_TYPES.UPDATE_IDXHOLIDAY):
    case FAILURE(ACTION_TYPES.DELETE_IDXHOLIDAY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_IDXHOLIDAY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_IDXHOLIDAY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_IDXHOLIDAY):
    case SUCCESS(ACTION_TYPES.UPDATE_IDXHOLIDAY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_IDXHOLIDAY):
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

const apiUrl = 'api/idx-holidays';

// Actions

export const getEntities: ICrudGetAllAction<IIdxHoliday> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_IDXHOLIDAY_LIST,
  payload: axios.get<IIdxHoliday>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IIdxHoliday> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_IDXHOLIDAY,
    payload: axios.get<IIdxHoliday>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IIdxHoliday> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_IDXHOLIDAY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IIdxHoliday> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_IDXHOLIDAY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IIdxHoliday> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_IDXHOLIDAY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

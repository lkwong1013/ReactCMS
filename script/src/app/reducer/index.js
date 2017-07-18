import { combineReducers } from "redux"

import setting from "./settingReducer"
import country from "./countryReducer"

export default combineReducers({
  setting, country,
})

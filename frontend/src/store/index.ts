import {applyMiddleware, createStore, Store} from "redux";
import thunk from "redux-thunk";
import reducer from "./reducer";

const store: Store<UserStoreState, UserStoreAction> & {
    dispatch: DispatchType
} = createStore(reducer, applyMiddleware(thunk));

export default store;
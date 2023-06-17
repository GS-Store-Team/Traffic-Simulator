import axios from "axios";
import {RestApplicationClient} from "./rest-client";

const axiosWithInterceptors = axios

axiosWithInterceptors.interceptors?.response.use(
    response => response,
    error => {
        if(error.code === "ERR_BAD_REQUEST") {
            window.dispatchEvent(new Event('NETWORK_ERROR'))
        }
        return error
    }
)

export const restClient = new RestApplicationClient(axios)
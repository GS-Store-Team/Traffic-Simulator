import axios from "axios";
import {RestApplicationClient} from "./rest-client";

const axiosWithInterceptors = axios
axiosWithInterceptors.defaults.baseURL = 'http://localhost:8080/';

axiosWithInterceptors.interceptors?.response.use(
    response => response.data,
    error => {
        if(error.code === "ERR_NETWORK") {
            window.dispatchEvent(new Event('ERR_NETWORK'))
        }
        return error.data
    }
)

export const restClient = new RestApplicationClient(axios)
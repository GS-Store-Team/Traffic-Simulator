import axios from "axios";

const httpConfig = {
    headers: {
        "Content-Type": "application/json",
    }
}

export default class Service{
    static async sendRoad(road){
        const response = await axios.post("http://localhost:8080/map/roads", road, httpConfig);
        console.log("response");
        console.log(response);
    }
}
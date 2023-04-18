import axios from "axios";

const httpConfig = {
    headers: {
        "Content-Type": "application/json",
    }
}

export default class API {
    static async sendRoad(road){
        return await axios.post("http://localhost:8080/map/roads", road, httpConfig);
    }

    static async getMapConfig(){
        return await axios.get("http://localhost:8080/state/config",httpConfig);
    }

    static async getMapState(){
        return await axios.get("http://localhost:8080/state",httpConfig);
    }

    static async startSimulation(){
        return await axios.get("http://localhost:8080/state/run",httpConfig);
    }

    static async playSimulation(){
        return await axios.get("http://localhost:8080/state/run/play",httpConfig);
    }

    static async stopSimulation(){
        return await axios.get("http://localhost:8080/state/run/stop",httpConfig);
    }
}
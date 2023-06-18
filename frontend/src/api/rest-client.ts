/* tslint:disable */
/* eslint-disable */

export interface HttpClient {

    request<R>(requestConfig: { method: string; url: string; queryParams?: any; data?: any; copyFn?: (data: R) => R; }): RestResponse<R>;
}

export class RestApplicationClient {

    constructor(protected httpClient: HttpClient) {
    }

    /**
     * HTTP GET /map
     * Java method: com.traffic_simulator.controllers.MapConfigController.getMap
     */
    getMap(): RestResponse<FullMapDTO> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`map` });
    }

    /**
     * HTTP GET /map/areas
     * Java method: com.traffic_simulator.controllers.MapConfigController.getAreas
     */
    getAreas(): RestResponse<AreasPlacement> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`map/areas` });
    }

    /**
     * HTTP DELETE /map/areas/versions/{versionId}
     * Java method: com.traffic_simulator.controllers.MapConfigController.removeAreaVersion
     */
    removeAreaVersion(versionId: number): RestResponse<FullMapDTO> {
        return this.httpClient.request({ method: "DELETE", url: uriEncoding`map/areas/versions/${versionId}` });
    }

    /**
     * HTTP POST /map/areas/versions/{versionId}/lock/{locked}
     * Java method: com.traffic_simulator.controllers.MapConfigController.configureAreaVersion
     */
    configureAreaVersion(versionId: number, locked: boolean): RestResponse<FullMapDTO> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`map/areas/versions/${versionId}/lock/${locked}` });
    }

    /**
     * HTTP POST /map/areas/{areaId}/versions/{versionName}
     * Java method: com.traffic_simulator.controllers.MapConfigController.addAreaVersion
     */
    addAreaVersion(areaId: number, versionName: string): RestResponse<FullMapDTO> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`map/areas/${areaId}/versions/${versionName}` });
    }

    /**
     * HTTP DELETE /map/buildings/{id}
     * Java method: com.traffic_simulator.controllers.MapConfigController.deleteBuilding
     */
    deleteBuilding(id: number): RestResponse<FullMapDTO> {
        return this.httpClient.request({ method: "DELETE", url: uriEncoding`map/buildings/${id}` });
    }

    /**
     * HTTP DELETE /map/roads/{id}
     * Java method: com.traffic_simulator.controllers.MapConfigController.deleteRoad
     */
    deleteRoad(id: number): RestResponse<FullMapDTO> {
        return this.httpClient.request({ method: "DELETE", url: uriEncoding`map/roads/${id}` });
    }

    /**
     * HTTP POST /map/{areaVersion}/buildings
     * Java method: com.traffic_simulator.controllers.MapConfigController.addBuilding
     */
    addBuilding(areaVersion: number, arg1: BuildingDTO): RestResponse<FullMapDTO> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`map/${areaVersion}/buildings`, data: arg1 });
    }

    /**
     * HTTP POST /map/{areaVersion}/parking
     * Java method: com.traffic_simulator.controllers.MapConfigController.addParking
     */
    addParking(areaVersion: number, arg1: ParkingDTO): RestResponse<FullMapDTO> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`map/${areaVersion}/parking`, data: arg1 });
    }

    /**
     * HTTP DELETE /map/{areaVersion}/parking/{id}
     * Java method: com.traffic_simulator.controllers.MapConfigController.deleteParking
     */
    deleteParking(areaVersion: number, id: number): RestResponse<FullMapDTO> {
        return this.httpClient.request({ method: "DELETE", url: uriEncoding`map/${areaVersion}/parking/${id}` });
    }

    /**
     * HTTP POST /map/{areaVersion}/roads
     * Java method: com.traffic_simulator.controllers.MapConfigController.addRoad
     */
    addRoad(areaVersion: number, arg1: RoadDTO): RestResponse<FullMapDTO> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`map/${areaVersion}/roads`, data: arg1 });
    }

    /**
     * HTTP POST /simulation/destroy
     * Java method: com.traffic_simulator.controllers.SimulationController.destroy
     */
    destroy(): RestResponse<void> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`simulation/destroy` });
    }

    /**
     * HTTP POST /simulation/play
     * Java method: com.traffic_simulator.controllers.SimulationController.play
     */
    play(): RestResponse<void> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`simulation/play` });
    }

    /**
     * HTTP POST /simulation/run
     * Java method: com.traffic_simulator.controllers.SimulationController.run
     */
    run(): RestResponse<void> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`simulation/run` });
    }

    /**
     * HTTP GET /simulation/state
     * Java method: com.traffic_simulator.controllers.SimulationController.state
     */
    state(): RestResponse<SimulationStateDTO> {
        return this.httpClient.request({ method: "GET", url: uriEncoding`simulation/state` });
    }

    /**
     * HTTP POST /simulation/stop
     * Java method: com.traffic_simulator.controllers.SimulationController.stop
     */
    stop(): RestResponse<void> {
        return this.httpClient.request({ method: "POST", url: uriEncoding`simulation/stop` });
    }
}

export interface AreaDTO {
    canRun: boolean;
    id: number;
    label: string;
    versions: AreaVersionDTO[];
}

export interface AreaVersionDTO {
    areaId: number;
    buildings: BuildingDTO[];
    created: Date;
    edited: Date;
    id: number;
    label: string;
    locked: boolean;
    roads: RoadDTO[];
    usr: UserDTO;
    valid: boolean;
}

export interface AreasPlacement {
    elems: Elem[];
}

export interface BuildingDTO {
    id?: number;
    inFlow: number;
    label: string;
    location: PointDTO;
    outFlow: number;
    parking?: ParkingDTO;
    residents: number;
    type: BuildingType;
    valid: boolean;
}

export interface CarDTO {
    coordinates: Coordinates;
    id: number;
}

export interface Coordinates {
    x: number;
    y: number;
}

export interface Elem {
    areaVersionId: number;
    cellIds: number[];
}

export interface FullMapDTO {
    areas: AreaDTO[];
    version: number;
}

export interface ParkingDTO {
    capacity: number;
    id: number;
    location: Point;
    valid: boolean;
}

export interface Point {
    id: number;
    x: number;
    y: number;
}

export interface PointDTO {
    id?: number;
    x: number;
    y: number;
}

export interface RoadDTO {
    end: PointDTO;
    forward: number;
    id?: number;
    reverse: number;
    start: PointDTO;
    valid: boolean;
}

export interface SimulationStateDTO {
    areaVersionDTO: AreaVersionDTO[];
    cars: CarDTO[];
}

export interface UserDTO {
    id: number;
    name: string;
}

export type BuildingType = "LIVING" | "WORKING" | "SHOP" | "ENTERTAINMENT" | "CONNECTION";

export type RestResponse<R> = Promise<R>;

export type RoadLoad = "FREE" | "LIGHT" | "MIDDLING" | "HEAVY" | "DEAD";

export type TrafficLight = "RED" | "YELLOW" | "GREEN";

function uriEncoding(template: TemplateStringsArray, ...substitutions: any[]): string {
    let result = "";
    for (let i = 0; i < substitutions.length; i++) {
        result += template[i];
        result += encodeURIComponent(substitutions[i]);
    }
    result += template[template.length - 1];
    return result;
}

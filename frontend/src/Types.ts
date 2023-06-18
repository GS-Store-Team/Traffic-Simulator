import {SimulationStateDTO} from "./api/rest-client";

export type Size = {
    width: number
    height: number
}

export type SimulationState = "NOT_STARTED" | "STARTED" | "STOPPED"

export type Queue = {
    queue: SimulationStateDTO[]
    position: number
}
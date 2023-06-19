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

export type Points = {
    forward: {
        first: {
            start: { x: number, y: number },
            end: { x: number, y: number }
        },
        second: {
            start: { x: number, y: number },
            end: { x: number, y: number }
        },
        third: {
            start: { x: number, y: number },
            end: { x: number, y: number }
        },
        fourth: {
            start: { x: number, y: number },
            end: { x: number, y: number }
        },
    },
    reverse: {
        first: {
            start: { x: number, y: number },
            end: { x: number, y: number }
        },
        second: {
            start: { x: number, y: number },
            end: { x: number, y: number }
        },
        third: {
            start: { x: number, y: number },
            end: { x: number, y: number }
        },
        fourth: {
            start: { x: number, y: number },
            end: { x: number, y: number }
        },
    }
}
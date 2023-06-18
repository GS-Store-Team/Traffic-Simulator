import {Header} from "../components/Header";
import {RunnerToolbar} from "../components/RunnerToolbar";
import {BaseStage} from "../components/stage/BaseStage";
import React, {createContext, useCallback, useEffect, useMemo, useState} from "react";
import {SimulationStateDTO} from "../api/rest-client";
import {Queue, SimulationState} from "../Types";
import {restClient} from "../api/axios.config";
import {MIN_FPS, STATE_QUEUE_SIZE} from "../Constants";

type RunnerContextType = {
    state?: SimulationStateDTO
    prev():void
    prevAvailable:boolean
    next():void
    nextAvailable:boolean
    current: number
    size: number
}

export const RunnerContext = createContext<RunnerContextType>({
    state: undefined,
    prev(){},
    next() {},
    prevAvailable: false,
    nextAvailable: false,
    current:0,
    size: 0
})
export const Runner = () => {
    const [state, setState] = useState<SimulationStateDTO>()
    const [stateStatus, setStateStatus] = useState<SimulationState>("NOT_STARTED")
    const [stateQueue, setStateQueue] = useState<Queue>({queue: [], position: -1})
    const [timer, setTimer] = useState<NodeJS.Timeout>()
    const [fps, setFps] = useState<number>(MIN_FPS)

    const fetchNewState = useCallback(() => {
        restClient.state().then(res => {
            setState(res)
            stateQueue.queue.push(res)
            if(stateQueue.queue.length > STATE_QUEUE_SIZE) {
                stateQueue.queue.pop()
            }
            stateQueue.position = stateQueue.queue.length - 1
            setStateQueue(stateQueue)
        })
    },[stateQueue])


    useEffect(() => {
        clearTimeout(timer)
        if(stateStatus === "STARTED"){
            const t = setInterval(fetchNewState, 1000 / fps)
            setTimer(t)
        }
    }, [stateStatus, fps, fetchNewState])

    const handleRun = useCallback((map: Map<number, number>) => {
        restClient.run().then(() => {
            setStateStatus("STARTED")
        })
    }, [])

    const handlePlay = useCallback(() => {
        restClient.play().then(() => {
            setStateStatus("STARTED")
        })
    }, [])
    const handleStop = useCallback(() => {
        restClient.stop().then(() => {
            setStateStatus("STOPPED")
        })
    }, [])
    const handleDestroy = useCallback(() => {
        restClient.destroy().then(() => {
            setStateStatus("NOT_STARTED")
            setStateQueue({queue: [], position: -1})
        })
    }, [])

    const handlePrev = useCallback(() => {
        if(stateQueue.position > 0){
            if(stateStatus !== "STOPPED"){
                handleStop()
            }
            setStateQueue(prevState => ({...prevState, position: prevState.position - 1}))
            setState(stateQueue.queue[stateQueue.position - 1])
        }
    }, [handleStop, stateQueue, stateStatus])

    const handleNext = useCallback(() => {
        if(stateStatus !== "STOPPED"){
            return
        }
        if(stateQueue.position !== stateQueue.queue.length - 1){
            setStateQueue(prevState => ({...prevState, position: prevState.position + 1}))
            setState(stateQueue.queue[stateQueue.position + 1])
        } else {
            fetchNewState()
        }
    }, [fetchNewState, stateQueue.position, stateQueue.queue, stateStatus])

    const context = useMemo(() => ({
        state,
        prev: handlePrev,
        next: handleNext,
        prevAvailable: stateQueue.position > 0,
        nextAvailable: stateStatus === "STOPPED",
        current: stateQueue.position,
        size: stateQueue.queue.length
    }), [handleNext, handlePrev, state, stateQueue.position, stateQueue.queue.length, stateStatus])

    return (
        <RunnerContext.Provider value={context}>
            <Header page={"runner"}/>
            <RunnerToolbar state={state}
                           simulationState={stateStatus}
                           onRun={handleRun}
                           onPlay={handlePlay}
                           onStop={handleStop}
                           onDestroy={handleDestroy}
                           fps={fps}
                           setFps={setFps}
            />
            <BaseStage/>
        </RunnerContext.Provider>
    )
}
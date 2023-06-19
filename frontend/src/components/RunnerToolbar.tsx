import React, {FC, useCallback, useState} from "react";
import {Styled as S} from "./Components.styled";
import {Styled as S1} from "./default/modal/Modal.styled";
import {FlexRow} from "./default/Flex.styled";
import {Btn} from "./default/Btn";
import {Icon} from "./default/Icon";
import {Modal} from "./default/modal/Modal";
import {SimulationStateDTO} from "../api/rest-client";
import {SimulationState} from "../Types";
import {StateManager} from "./StateManager";
import {MAX_FPS, MIN_FPS} from "../Constants";

interface RunnerToolbarProps {
    state?: SimulationStateDTO
    simulationState: SimulationState
    onRun(map: Map<number, number>):void
    onStop():void
    onPlay():void
    onDestroy():void
    fps: number
    setFps(fps:number): void
}
export const RunnerToolbar: FC<RunnerToolbarProps> = ({state, simulationState, onPlay, onRun, onStop, onDestroy, fps, setFps}) => {
    const [configureSimulation, setConfigureSimulation] = useState<boolean>(false)

    const handleStartSimulation = useCallback(() => setConfigureSimulation(true), [])
    const handleCloseConfiguration = useCallback(() => setConfigureSimulation(false), [])
    const handleApplyConfiguration = useCallback(() => {
        setConfigureSimulation(false)
        onRun(new Map<number, number>());
    }, [onRun])

    const handleSetFps = useCallback((e: React.FormEvent<HTMLInputElement>) => setFps(Number.parseInt(e.currentTarget.value)), [setFps])

    return (
        <S.Toolbar>
            {simulationState === "NOT_STARTED" ? <Btn success onClick={handleStartSimulation}><FlexRow gap={"7px"}><Icon img={"play"}/>START SIMULATION</FlexRow></Btn> : <div/>}
            {simulationState !== "NOT_STARTED" &&
                <FlexRow gap={"3em"}>
                    <FlexRow gap={"10px"}>
                        <S.Slider onChange={handleSetFps} type={"range"} min={MIN_FPS} max={MAX_FPS} defaultValue={fps}/>
                        <span>{fps} fps</span>
                    </FlexRow>
                    <StateManager />
                    <FlexRow gap={"1em"} style={{justifyItems: "center"}}>
                        <Icon img={"play"} disabled={simulationState === "STARTED"} onClick={onPlay}/>
                        <Icon img={"pause"} disabled={simulationState === "STOPPED"} onClick={onStop}/>
                        <Icon img={"red-square"} onClick={onDestroy}/>
                    </FlexRow>
                </FlexRow>
            }

            {configureSimulation &&
                <Modal onDecline={handleCloseConfiguration}
                       onAccept={handleApplyConfiguration}
                >
                    <S1.Title>CONFIGURE RUNNING VERSIONS</S1.Title>
                    <S1.Body>
                        {}
                        <S1.Row></S1.Row>
                        <S1.Row></S1.Row>
                        <S1.Row></S1.Row>
                    </S1.Body>
                </Modal>
            }
        </S.Toolbar>
    )
}
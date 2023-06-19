import React, {FC, useCallback, useContext, useEffect, useMemo, useState} from "react";
import {Styled as S} from "./Components.styled";
import {Styled as S1} from "./default/modal/Modal.styled";
import {FlexColumn, FlexRow} from "./default/Flex.styled";
import {Btn} from "./default/Btn";
import {Icon} from "./default/Icon";
import {Modal} from "./default/modal/Modal";
import {AreaDTO, FullMapDTO, SimulationStateDTO} from "../api/rest-client";
import {SimulationState} from "../Types";
import {StateManager} from "./StateManager";
import {MAX_FPS, MIN_FPS} from "../Constants";
import {restClient} from "../api/axios.config";
import {BootstrapContext} from "./contexts/BootstrapContextProvider";
import { Form } from "react-bootstrap";

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
    const { myId } = useContext(BootstrapContext)
    const [configureSimulation, setConfigureSimulation] = useState<boolean>(false)
    const [map, setMap] = useState<FullMapDTO>()
    const [areaIdVersionIdMap, setAreaIdVersionIdMap] = useState<Map<number, number>>(new Map())

    useEffect(() => {
        restClient.getMap().then(setMap)
    }, [])

    const handleStartSimulation = useCallback(() => setConfigureSimulation(true), [])
    const handleCloseConfiguration = useCallback(() => setConfigureSimulation(false), [])
    const handleApplyConfiguration = useCallback(() => {
        setConfigureSimulation(false)
        onRun(areaIdVersionIdMap);
    }, [areaIdVersionIdMap, onRun])

    const handleSetFps = useCallback((e: React.FormEvent<HTMLInputElement>) => setFps(Number.parseInt(e.currentTarget.value)), [setFps])

    const canRun = useMemo(() => map && map.areas.flatMap(a => a.versions).some(v => !v.locked || (v.usr.id === myId && v.valid)), [map, myId])

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
                       disableAccept={!canRun || areaIdVersionIdMap.size === 0}
                       $height={canRun ? "700px" : "400px"}
                >
                    <S1.Title>CONFIGURE RUNNING VERSIONS</S1.Title>
                    <S1.Body>
                        { canRun && map?.areas.map(a => <AreaRow key={a.id} area={a} myId={myId} map={areaIdVersionIdMap} setMap={setAreaIdVersionIdMap}/>)}
                        {!canRun &&
                            <>
                                <S1.Row>Unable to start simulation due to there is no appropriate area versions.</S1.Row>
                                <S1.Row>To be able to run simulation create version first.</S1.Row>
                            </>
                        }
                    </S1.Body>
                </Modal>
            }
        </S.Toolbar>
    )
}

interface AreaRowProps {
    area: AreaDTO
    myId: number
    map: Map<number, number>
    setMap(map: Map<number, number>):void
}

const AreaRow : FC<AreaRowProps> = ({area, myId, map, setMap}) => {

    const appropriateVersions = useMemo(() => area.versions.filter(v => !v.locked || (v.usr.id === myId && v.valid)),[area.versions, myId])

    const handleSelectNone = useCallback(() => {
        map.delete(area.id)
        setMap(new Map(map))
    }, [area.id, map, setMap])

    const handleSelectVersion = useCallback((versionId: number) => {
        map.set(area.id, versionId)
        setMap(new Map(map))
    }, [area.id, map, setMap])

    return (
        appropriateVersions.length > 0 ?
            <FlexRow gap={"1em"} style={{marginBottom: "30px"}}>
                <S.AreaConfigHeading>{area.label.toUpperCase()}</S.AreaConfigHeading>
                <FlexRow gap={"10px"}>
                    <Form.Check inline id="switch2" className="pl-5">
                        <Form.Check.Input checked={!map.has(area.id)} onChange={handleSelectNone}/>
                        <Form.Check.Label><strong>none</strong></Form.Check.Label>
                    </Form.Check>
                    <FlexColumn style={{gap: "0"}}>
                    {appropriateVersions.map(v =>
                        <Form.Check key={v.id} inline id="switch2" className="pl-5">
                            <Form.Check.Input checked={map.get(area.id) === v.id} onChange={() => handleSelectVersion(v.id)}/>
                            <Form.Check.Label>{v.label}</Form.Check.Label>
                        </Form.Check>
                    )}
                    </FlexColumn>
                </FlexRow>
            </FlexRow> : <></>
    )
}
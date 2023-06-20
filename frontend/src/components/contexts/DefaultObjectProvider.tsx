import {BuildingDTO, RoadDTO} from "../../api/rest-client";
import React, {createContext, FC, PropsWithChildren, useCallback, useMemo, useState} from "react";
import {Styled as S} from "../default/modal/Modal.styled";
import {InfoModal} from "../default/modal/InfoModal";
import {BuildingConfig} from "../supportive/BuildingConfig";
import {RoadConfig} from "../supportive/RoadConfig";

const dRoad: RoadDTO = {
    start: {x: 0, y: 0},
    end: {x: 0, y: 0},
    forward: 1,
    reverse: 1,
    valid: true
}

const dBuilding: BuildingDTO = {
    location: { x: 0, y: 0 },
    valid: true,
    inFlow: 0,
    outFlow: 0,
    label: '',
    residents: 10,
    type: "LIVING",
    parking: undefined
}

type DefaultObjectContextType = {
    dRoad: RoadDTO
    dBuilding: BuildingDTO
    configure():void
}

export const DefaultObjectContext = createContext<DefaultObjectContextType>({dRoad, dBuilding, configure(){}})

export const DefaultObjectProvider: FC<PropsWithChildren> = ({children}) => {
    const [defaultRoad, setDefaultRoad] = useState<RoadDTO>(dRoad)
    const [defaultBuilding, setDefaultBuilding] = useState<BuildingDTO>(dBuilding)
    const [configure, setConfigure] = useState<boolean>(false)

    const handleConfigure = useCallback(() => setConfigure(true), [])
    const handleAcceptConfigure = useCallback(() => {
        setConfigure(false)
    }, [])

    const context: DefaultObjectContextType = useMemo(() => ({dRoad: defaultRoad, dBuilding: defaultBuilding, configure: handleConfigure}), [defaultBuilding, defaultRoad, handleConfigure])

    return (
        <DefaultObjectContext.Provider value={context}>
            {children}
            {configure &&
                <InfoModal onOk={handleAcceptConfigure}
                           $width={"750px"}
                           $height={"750px"}
                           title={"CONFIGURE DEFAULT OBJECTS"}
                >
                    <>
                        <S.Row>DEFAULT ROAD</S.Row>
                        <RoadConfig road={defaultRoad} onchange={setDefaultRoad}/>
                        <S.Row/>
                        <S.Row/>
                        <S.Row>DEFAULT BUILDING</S.Row>
                        <BuildingConfig building={defaultBuilding} onChange={setDefaultBuilding}/>
                    </>
                </InfoModal>
            }
        </DefaultObjectContext.Provider>
    )
}
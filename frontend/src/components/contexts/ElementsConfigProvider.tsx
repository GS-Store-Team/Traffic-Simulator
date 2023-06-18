import {createContext, FC, PropsWithChildren, useCallback, useState} from "react";
import {BuildingDTO, FullMapDTO, RoadDTO} from "../../api/rest-client";
import {Modal} from "../default/modal/Modal";
import {Styled as S} from "../default/modal/Modal.styled";
import {restClient} from "../../api/axios.config";

type ElementsConfigContextType = {
    setBuilding(building: BuildingDTO):void
    setRoad(road: RoadDTO):void
}

export const ElementsConfigContext = createContext<ElementsConfigContextType>({
    setBuilding() {},
    setRoad() {}
})

interface ElementsConfigProps{
    setMap(map: FullMapDTO):void;
    areaVersionId?: number
}

export const ElementsConfigProvider : FC<PropsWithChildren<ElementsConfigProps>> = ({setMap, areaVersionId, children}) => {
    const [building, setBuilding] = useState<BuildingDTO>()
    const [road, setRoad] = useState<RoadDTO>()

    const handleAcceptBuilding = useCallback(() => building && areaVersionId && restClient.addBuilding(areaVersionId, building).then(setMap), [areaVersionId, building, setMap])
    const handleAcceptRoad = useCallback(() => road && areaVersionId && restClient.addRoad(areaVersionId, road).then(setMap), [areaVersionId, road, setMap])

    const handleDeclineBuilding = useCallback(() => setBuilding(undefined), [])
    const handleDeclineRoad = useCallback(() => setRoad(undefined), [])

    return (
        <ElementsConfigContext.Provider value={{setBuilding, setRoad}}>
            {children}
            {building &&
                <Modal onAccept={handleAcceptBuilding} onDecline={handleDeclineBuilding}>
                    <S.Title>CONFIGURE BUILDING</S.Title>
                </Modal>
            }
            {road &&
                <Modal onAccept={handleAcceptRoad} onDecline={handleDeclineRoad}>
                    <S.Title>CONFIGURE ROAD</S.Title>
                </Modal>
            }
        </ElementsConfigContext.Provider>
    )
}
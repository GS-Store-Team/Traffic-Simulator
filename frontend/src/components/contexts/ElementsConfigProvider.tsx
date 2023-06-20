import {createContext, FC, PropsWithChildren, useCallback, useState} from "react";
import {BuildingDTO, FullMapDTO, RoadDTO} from "../../api/rest-client";
import {Modal} from "../default/modal/Modal";
import {Styled as S} from "../Components.styled";
import {Styled as S1} from "../default/modal/Modal.styled";
import {restClient} from "../../api/axios.config";
import {FlexRow} from "../default/Flex.styled";
import {Icon} from "../default/Icon";
import {BuildingConfig} from "../supportive/BuildingConfig";
import {RoadConfig} from "../supportive/RoadConfig";
import {Btn} from "../default/Btn";

type ElementsConfigContextType = {
    configureBuilding(building?: BuildingDTO):void
    configureRoad(road?: RoadDTO):void
    viewBuilding(building?: BuildingDTO): void,
    viewRoad(road?: RoadDTO):void,
}

export const ElementsConfigContext = createContext<ElementsConfigContextType>({
    configureBuilding() {},
    configureRoad() {},
    viewBuilding() {},
    viewRoad() {},
})

interface ElementsConfigProps{
    setMap(map: FullMapDTO):void;
    areaVersionId?: number
}

export const ElementsConfigProvider : FC<PropsWithChildren<ElementsConfigProps>> = ({setMap, areaVersionId, children}) => {
    const [configureBuilding, setConfigureBuilding] = useState<BuildingDTO>()
    const [configureRoad, setConfigureRoad] = useState<RoadDTO>()
    const [viewBuilding, setViewBuilding] = useState<BuildingDTO>()
    const [viewRoad, setViewRoad] = useState<RoadDTO>()

    const handleAcceptBuilding = useCallback(() => {
        if(configureBuilding && areaVersionId !== undefined) {
            restClient.addBuilding(areaVersionId, configureBuilding).then(setMap)
        }
        setConfigureBuilding(undefined)
    }, [areaVersionId, configureBuilding, setMap])
    const handleAcceptRoad = useCallback(() => configureRoad && areaVersionId && restClient.addRoad(areaVersionId, configureRoad).then(setMap), [areaVersionId, configureRoad, setMap])
    const handleDeclineBuilding = useCallback(() => setConfigureBuilding(undefined), [])
    const handleDeclineRoad = useCallback(() => setConfigureRoad(undefined), [])
    const handleRemoveBuilding = useCallback(() => {
        if(configureBuilding?.id !== undefined) {
            restClient.deleteBuilding(configureBuilding.id).then(setMap)
        }
        setConfigureBuilding(undefined)
    }, [configureBuilding, setMap])

    const context: ElementsConfigContextType = {configureBuilding: setConfigureBuilding, configureRoad: setConfigureRoad, viewBuilding: setViewBuilding, viewRoad:setViewRoad}

    return (
        <ElementsConfigContext.Provider value={context}>
            {children}
            {configureBuilding &&
                <Modal onAccept={handleAcceptBuilding} onDecline={handleDeclineBuilding} $width={"720px"}>
                    <S1.Title>CONFIGURE BUILDING</S1.Title>
                    <S1.Body>
                        <S1.Row><FlexRow $justifyContent={"flex-end"}><Btn onClick={handleRemoveBuilding} danger><Icon img={"trash-bin"}/></Btn></FlexRow></S1.Row>
                        <BuildingConfig building={configureBuilding} onChange={setConfigureBuilding}/>
                    </S1.Body>
                </Modal>
            }
            {configureRoad &&
                <Modal onAccept={handleAcceptRoad} onDecline={handleDeclineRoad}>
                    <S1.Title>CONFIGURE ROAD</S1.Title>
                    <S1.Body>

                        <RoadConfig road={configureRoad} onchange={setConfigureRoad}/>
                    </S1.Body>
                </Modal>
            }
            {viewBuilding &&
                <S.PreviewBlock>
                    <FlexRow gap={"1em"}>{viewBuilding.label.toUpperCase()} <Icon img={viewBuilding.valid ? "ok" : "blocked"}/></FlexRow>
                    <FlexRow $justifyContent={"flex-end"}><div style={{width: "60px"}}>x: {viewBuilding.location.x}</div></FlexRow>
                    <FlexRow $justifyContent={"flex-end"}><div style={{width: "60px"}}>y: {viewBuilding.location.y}</div></FlexRow>
                    <div style={{height: "20px"}} />
                    <FlexRow $justifyContent={"space-between"}><div>type:</div><div>{viewBuilding.type}</div></FlexRow>
                    <FlexRow $justifyContent={"space-between"}><div>residents:</div><div>{viewBuilding.residents}</div></FlexRow>
                    <FlexRow $justifyContent={"space-between"}><div>in flow:</div><div>{viewBuilding.inFlow}</div></FlexRow>
                    <FlexRow $justifyContent={"space-between"}><div>out flow:</div><div>{viewBuilding.outFlow}</div></FlexRow>
                </S.PreviewBlock>
            }
            {viewRoad &&
                <S.PreviewBlock>
                </S.PreviewBlock>
            }
        </ElementsConfigContext.Provider>
    )
}
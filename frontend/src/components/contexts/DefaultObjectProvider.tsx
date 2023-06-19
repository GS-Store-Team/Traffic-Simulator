import {BuildingDTO, BuildingType, RoadDTO} from "../../api/rest-client";
import React, {createContext, FC, PropsWithChildren, useCallback, useMemo, useState} from "react";
import {Styled as S} from "../default/modal/Modal.styled";
import {FlexRow} from "../default/Flex.styled";
import {RadioCheckbox} from "../default/RadioCheckbox";
import {InfoModal} from "../default/modal/InfoModal";

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

    const handleSelectForward = useCallback((option: string) => setDefaultRoad(prevState => ({...prevState, forward: Number.parseInt(option)})), [])
    const handleSelectReverse = useCallback((option: string) => setDefaultRoad(prevState => ({...prevState, reverse: Number.parseInt(option)})), [])
    const handleSelectBuildingType = useCallback((option: string) => setDefaultBuilding(prevState => ({...prevState, type: option as BuildingType})), [])
    const handleSelectResidents = useCallback((option: string) => setDefaultBuilding(prevState => ({...prevState, residents: Number.parseInt(option)})), [])
    const handleSelectInFlow = useCallback((option: string) => setDefaultBuilding(prevState => ({...prevState, inFlow: Number.parseInt(option)})), [])
    const handleSelectOutFlow = useCallback((option: string) => setDefaultBuilding(prevState => ({...prevState, outFlow: Number.parseInt(option)})), [])


    return (
        <DefaultObjectContext.Provider value={context}>
            {children}
            {configure &&
                <InfoModal onOk={handleAcceptConfigure}
                           $width={"720px"}
                           $height={"700px"}
                           title={"CONFIGURE DEFAULT OBJECTS"}
                >
                    <>
                        <S.Row>DEFAULT ROAD</S.Row>
                        <S.Row>
                            <FlexRow>
                                <span style={{width: "100px"}}>forward</span>
                                <RadioCheckbox current={`${defaultRoad.forward}`} options={['1', '2', '3', '4']} onChange={handleSelectForward}/>
                            </FlexRow>
                        </S.Row>
                        <S.Row>
                            <FlexRow>
                                <span style={{width: "100px"}}>reverse</span>
                                <RadioCheckbox current={`${defaultRoad.reverse}`} options={['1', '2', '3', '4']} onChange={handleSelectReverse}/>
                            </FlexRow>
                        </S.Row>
                        <S.Row/>
                        <S.Row/>
                        <S.Row>DEFAULT BUILDING</S.Row>
                        <S.Row>
                            <FlexRow>
                                <span style={{width: "120px"}}>building type</span>
                                <RadioCheckbox current={defaultBuilding.type} options={['LIVING', 'WORKING', 'SHOP', 'ENTERTAINMENT']} onChange={handleSelectBuildingType}/>
                            </FlexRow>
                        </S.Row>
                        { defaultBuilding.type === 'LIVING' &&
                            <S.Row>
                                <FlexRow>
                                    <span style={{width: "120px"}}>residents</span>
                                    <RadioCheckbox current={`${defaultBuilding.residents}`} options={['10', '20', '50', '100', '250', '500', '1000']} onChange={handleSelectResidents}/>
                                </FlexRow>
                            </S.Row>
                        }
                        <S.Row>
                            <FlexRow>
                                <span style={{width: "120px"}}>in flow</span>
                                <RadioCheckbox current={`${defaultBuilding.inFlow}`} options={['0', '1', '3', '5', '7', '9', '11', '15', '20']} onChange={handleSelectInFlow}/>
                            </FlexRow>
                        </S.Row>
                        <S.Row>
                            <FlexRow>
                                <span style={{width: "120px"}}>out flow</span>
                                <RadioCheckbox current={`${defaultBuilding.outFlow}`} options={['0', '1', '3', '5', '7', '9', '11', '15', '20']} onChange={handleSelectOutFlow}/>
                            </FlexRow>
                        </S.Row>
                    </>
                </InfoModal>
            }
        </DefaultObjectContext.Provider>
    )
}
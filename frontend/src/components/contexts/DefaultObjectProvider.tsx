import {BuildingDTO, RoadDTO} from "../../api/rest-client";
import {createContext, FC, PropsWithChildren, useMemo, useState} from "react";

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
    residents: 0,
    type: "LIVING",
    parking: undefined
}

type DefaultObjectContextType = {
    dRoad: RoadDTO
    dBuilding: BuildingDTO
}

export const DefaultObjectContext = createContext<DefaultObjectContextType>({dRoad, dBuilding})

export const DefaultObjectProvider: FC<PropsWithChildren> = ({children}) => {
    const [defaultRoad, setDefaultRoad] = useState<RoadDTO>(dRoad)
    const [defaultBuilding, setDefaultBuilding] = useState<BuildingDTO>(dBuilding)

    const context: DefaultObjectContextType = useMemo(() => ({dRoad: defaultRoad, dBuilding: defaultBuilding}), [defaultBuilding, defaultRoad])

    return (
        <DefaultObjectContext.Provider value={context}>
            {children}
        </DefaultObjectContext.Provider>
    )
}
import {Header} from "../components/Header";
import {EditorToolbar} from "../components/EditorToolbar";
import {createContext, useCallback, useEffect, useState} from "react";
import {AreaDTO, AreaVersionDTO, FullMapDTO} from "../api/rest-client";
import {restClient} from "../api/axios.config";
import {BaseStage} from "../components/stage/BaseStage";

type EditorContextType = {
    map: FullMapDTO | null
    setMap(map: FullMapDTO):void;
    area?: AreaDTO
    setAreaId(id?:number):void
    version?: AreaVersionDTO
    setVersionId(id:number):void
}

export const EditorContext = createContext<EditorContextType>({
    map:null,
    setMap(){},
    setAreaId() {},
    setVersionId() {}
})

export const Editor = () => {

    const [map, setMap] = useState<FullMapDTO | null>(null)
    const [area, setArea] = useState<AreaDTO>()
    const [version, setVersion] = useState<AreaVersionDTO>()

    useEffect(() => {
        restClient.getMap().then(response => setMap(response))
    }, [])

    const handleSelectArea = useCallback((id: number) => {
        setArea(map?.areas.find(a => a.id === id))
        setVersion(undefined)
    }, [map])
    const handleSelectVersion = useCallback((id: number) => area && setVersion(area.versions.find(v => v.id === id)), [area])

    const handleSetMap = useCallback((map: FullMapDTO) => {
        setMap(map)
        setArea(map.areas.find(a => a.id === area?.id))
        setVersion(undefined)
    }, [area])

    const context: EditorContextType = {
        map,
        setMap: handleSetMap,
        area,
        setAreaId: handleSelectArea,
        version,
        setVersionId: handleSelectVersion
    }

    return (
        <EditorContext.Provider value={context}>
            <Header page={"editor"}/>
            <EditorToolbar/>
            <BaseStage />
        </EditorContext.Provider>
    )
}
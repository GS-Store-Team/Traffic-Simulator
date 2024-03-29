import {Header} from "../components/Header";
import {EditorToolbar} from "../components/EditorToolbar";
import {createContext, useCallback, useEffect, useState} from "react";
import {AreaDTO, AreaVersionDTO, FullMapDTO} from "../api/rest-client";
import {restClient} from "../api/axios.config";
import {BaseStage} from "../components/stage/BaseStage";
import {ElementsConfigProvider} from "../components/contexts/ElementsConfigProvider";
import {Elements} from "../components/stage/Elements";

type EditorContextType = {
    map: FullMapDTO | null
    setMap(map: FullMapDTO, hard?: boolean): void;
    area?: AreaDTO
    setAreaId(id?: number): void
    version?: AreaVersionDTO
    setVersionId(id: number): void
}

export const EditorContext = createContext<EditorContextType>({
    map: null,
    setMap() {
    },
    setAreaId() {
    },
    setVersionId() {
    }
})

export const Editor = () => {

    const [map, setMap] = useState<FullMapDTO | null>(null)
    const [area, setArea] = useState<AreaDTO>()
    const [version, setVersion] = useState<AreaVersionDTO>()

    useEffect(() => {
        if(map && area){
            const renewedArea = map?.areas.find(a => a.id === area.id)
            setArea(renewedArea)
            if(renewedArea && version){
                setVersion(renewedArea.versions.find(v => v.id === version.id))
            }
        }
    }, [area, map, version])

    useEffect(() => {
        restClient.getMap().then(setMap)
    }, [])

    const handleSelectArea = useCallback((id: number) => {
        setArea(map?.areas.find(a => a.id === id))
        setVersion(undefined)
    }, [map])
    const handleSelectVersion = useCallback((id: number) => area && setVersion(area.versions.find(v => v.id === id)), [area])

    const handleSetMap = useCallback((map: FullMapDTO, hard: boolean = false) => {
        setMap(map)
        if (hard) {
            setArea(map.areas.find(a => a.id === area?.id))
            setVersion(undefined)
        }
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
            <ElementsConfigProvider setMap={setMap} areaVersionId={version?.id}>
                <Header page={"editor"}/>
                <EditorToolbar/>
                <BaseStage>
                    { version &&
                        <Elements buildings={version.buildings}
                                  roads={version.roads}
                                  cars={[]}
                        />
                    }
                </BaseStage>
            </ElementsConfigProvider>
        </EditorContext.Provider>
    )
}
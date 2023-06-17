import {Header} from "../components/Header";
import {Toolbar} from "../components/Toolbar";
import {createContext, useEffect, useState} from "react";
import {FullMapDTO} from "../api/rest-client";
import {restClient} from "../api/axios.config";
import {BaseStage} from "../components/stage/BaseStage";

type EditorContextType = {
    map: FullMapDTO | null
    setMap(map: FullMapDTO):void;
    area?: number
    setArea(id:number):void
}

export const EditorContext = createContext<EditorContextType>({
    map:null,
    setMap(){},
    setArea() {}
})

export const Editor = () => {

    const [map, setMap] = useState<FullMapDTO | null>(null)
    const [area, setArea] = useState<number>()

    useEffect(() => {
        restClient.getMap().then(setMap)
    }, [])

    const context: EditorContextType = {
        map,
        setMap,
        area,
        setArea
    }

    return (
        <EditorContext.Provider value={context}>
            <Header page={"editor"}/>
            <Toolbar page={"editor"}/>
            <BaseStage />
        </EditorContext.Provider>
    )
}
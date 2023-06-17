import {Header} from "../components/Header";
import {Toolbar} from "../components/Toolbar";
import {createContext, useEffect, useState} from "react";
import {FullMapDTO} from "../api/rest-client";
import {restClient} from "../api/axios.config";

type EditorContextType = {
    map: FullMapDTO | null
    setMap(map: FullMapDTO):void;
}

const EditorContext = createContext<EditorContextType>({map:null, setMap(){}})

export const Editor = () => {

    const [map, setMap] = useState<FullMapDTO | null>(null)

    useEffect(() => {
        restClient.getMap().then(setMap)
    }, [])

    const context: EditorContextType = { map, setMap }

    return (
        <EditorContext.Provider value={context}>
            <Header page={"editor"}/>
            <Toolbar page={"editor"}/>
        </EditorContext.Provider>
    )
}
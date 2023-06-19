import {createContext, FC, PropsWithChildren, useEffect, useState} from "react";
import {restClient} from "../../api/axios.config";

type BootstrapContextType = {
    myId: number
}

export const BootstrapContext = createContext<BootstrapContextType>({myId: -1})

export const BootstrapContextProvider : FC<PropsWithChildren> = ({children}) => {
    const [myId, setMyId] = useState<number>(-1)

    useEffect(() => {
        restClient.me().then(setMyId)
    }, [])

    const context = {myId}

    return (
        <BootstrapContext.Provider value={context}>
            {children}
        </BootstrapContext.Provider>
    )
}

import {useEffect, useLayoutEffect, useRef} from "react";

export function useOutsideClick(ref, handler, attached= true){
    const latestHandler = useLatest(handler);

    useEffect(() => {
        if(!attached) return;
        const clickHandler = (e) => {
            if(!ref.current) return;
            if(!ref.current.contains(e.target)){
                latestHandler.current();
            }
        }
        document.addEventListener("mousedown", clickHandler)
        return () => {
            document.removeEventListener("mousedown", clickHandler);
        }
    },[ref, latestHandler, attached])
}

export function useLatest(value){
    const valueRef = useRef(value);

    useLayoutEffect(() => {
        valueRef.current = value;
    }, [value])

    return valueRef;
}
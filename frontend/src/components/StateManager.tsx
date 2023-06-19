import {FlexRow} from "./default/Flex.styled";
import {Icon} from "./default/Icon";
import React, {useContext} from "react";
import {RunnerContext} from "../pages/Runner";

export const StateManager = () => {
    const {next, prev, prevAvailable, nextAvailable, current, size} = useContext(RunnerContext)

    if(size === 0) {
        return (<div/>)
    }
    return (
        <FlexRow gap={"1em"} style={{justifyItems: "center"}}>
            <span style={{margin: "auto"}} onClick={prev}><Icon img={"arrow"} size={15} disabled={!prevAvailable}/></span>
            <span>{current + 1}/{size}</span>
            <span style={{transform: "rotate(180deg)", margin:"auto"}} onClick={next}><Icon img={"arrow"} size={15} disabled={!nextAvailable}/></span>
        </FlexRow>
    )
}
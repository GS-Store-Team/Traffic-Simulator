import React, {FC, useCallback} from "react";
import {RoadDTO} from "../../api/rest-client";
import {Styled as S} from "../default/modal/Modal.styled";
import {FlexRow} from "../default/Flex.styled";
import {RadioCheckbox} from "../default/RadioCheckbox";

interface RoadConfigProps {
    road: RoadDTO
    onchange(road: RoadDTO): void
}

export const RoadConfig : FC<RoadConfigProps> = ({road, onchange}) => {

    const handleSelectForward = useCallback((option: string) => onchange({...road, forward: Number.parseInt(option)}), [onchange, road])
    const handleSelectReverse = useCallback((option: string) => onchange({...road, reverse: Number.parseInt(option)}), [onchange, road])

    return(
        <>
            <S.Row>
                <FlexRow>
                    <span style={{width: "100px"}}>forward</span>
                    <RadioCheckbox current={`${road.forward}`} options={['1', '2', '3', '4']} onChange={handleSelectForward}/>
                </FlexRow>
            </S.Row>
            <S.Row>
                <FlexRow>
                    <span style={{width: "100px"}}>reverse</span>
                    <RadioCheckbox current={`${road.reverse}`} options={['1', '2', '3', '4']} onChange={handleSelectReverse}/>
                </FlexRow>
            </S.Row>
        </>
    )
}
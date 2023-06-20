import React, {FC, useCallback} from "react";
import {BuildingDTO, BuildingType} from "../../api/rest-client";
import {Styled as S} from "../default/modal/Modal.styled";
import {FlexRow} from "../default/Flex.styled";
import {RadioCheckbox} from "../default/RadioCheckbox";
import {Input} from "../default/Form";

interface BuildingConfigProps {
    building: BuildingDTO
    onChange(building: BuildingDTO): void
}

export const BuildingConfig : FC<BuildingConfigProps> = ({building, onChange}) => {

    const handleSelectBuildingType = useCallback((option: string) => onChange({...building, type: option as BuildingType}), [building, onChange])
    const handleSelectResidents = useCallback((option: string) => onChange({...building, residents: Number.parseInt(option)}), [building, onChange])
    const handleSelectInFlow = useCallback((option: string) => onChange({...building, inFlow: Number.parseInt(option)}), [building, onChange])
    const handleSelectOutFlow = useCallback((option: string) => onChange({...building, outFlow: Number.parseInt(option)}), [building, onChange])
    const handleChangeInput = useCallback((e : React.ChangeEvent<HTMLInputElement>) => onChange({...building, label: e.currentTarget.value.substring(0, 20)}), [building, onChange])

    return (
        <>
            <S.Row>
                <FlexRow style={{alignItems: "center"}}>
                <span style={{width: "120px"}}>label</span>
                <Input value={building.label} onChange={handleChangeInput} style={{width: "220px"}} />
                </FlexRow>
            </S.Row>
            <S.Row>
                <FlexRow>
                    <span style={{width: "120px"}}>building type</span>
                    <RadioCheckbox current={building.type} options={['LIVING', 'WORKING', 'SHOP', 'ENTERTAINMENT']} onChange={handleSelectBuildingType}/>
                </FlexRow>
            </S.Row>
            { building.type === 'LIVING' &&
                <S.Row>
                    <FlexRow>
                        <span style={{width: "120px"}}>residents</span>
                        <RadioCheckbox current={`${building.residents}`} options={['10', '20', '50', '100', '250', '500', '1000']} onChange={handleSelectResidents}/>
                    </FlexRow>
                </S.Row>
            }
            <S.Row>
                <FlexRow>
                    <span style={{width: "120px"}}>in flow</span>
                    <RadioCheckbox current={`${building.inFlow}`} options={['0', '1', '3', '5', '7', '9', '11', '15', '20']} onChange={handleSelectInFlow}/>
                </FlexRow>
            </S.Row>
            <S.Row>
                <FlexRow>
                    <span style={{width: "120px"}}>out flow</span>
                    <RadioCheckbox current={`${building.outFlow}`} options={['0', '1', '3', '5', '7', '9', '11', '15', '20']} onChange={handleSelectOutFlow}/>
                </FlexRow>
            </S.Row>
        </>
    )
}
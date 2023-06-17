import React, {FC, useCallback} from "react";
import {Styled as S} from "./../Components.styled"
import home from "../../ui/home.png"
import minus from "../../ui/minus.png"
import plus from "../../ui/plus.png"
import settings from "../../ui/settings.png"

export type IconType =
    "home" | "minus" | "plus" | "settings"

function getPng(title : IconType){
    switch (title){
        case "home": return home
        case "minus": return minus
        case "plus": return plus
        case "settings": return settings
    }
}

interface IconProps {
    img: IconType
    onClick: () => void
    disabled?: boolean
    size?: number
}

export const Icon : FC<IconProps> = ({img, onClick, disabled = false, size = 22}) => {
    const handleClick = useCallback(() => !disabled && onClick(), [disabled, onClick])
    return(
        <S.Icon $disabled={disabled} style={{width: `${size}px`, height: `${size}px`, maxWidth: `${size}px`, maxHeight: `${size}px`, cursor: disabled? 'not-allowed':'pointer'}} onClick={handleClick}>
            <img style={{width: "100%", height: "100%"}} draggable={false} src={getPng(img)} alt={":("}/>
        </S.Icon>
    )
}
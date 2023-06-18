import React, {FC, useCallback} from "react";
import {Styled as S} from "./../Components.styled"
import home from "../../ui/home.png"
import minus from "../../ui/minus.png"
import plus from "../../ui/plus.png"
import settings from "../../ui/settings.png"
import trashBin from "../../ui/trash-bin.png"
import lock from "../../ui/lock.png"
import publish from "../../ui/publish.png"
import ok from "../../ui/ok.png"
import blocked from "../../ui/blocked.png"

export type IconType =
    "home" | "minus" | "plus" | "settings" | "trash-bin" | "lock" | "publish" | "ok" | "blocked"

function getPng(title : IconType){
    switch (title){
        case "home": return home
        case "minus": return minus
        case "plus": return plus
        case "settings": return settings
        case "trash-bin": return trashBin
        case "lock": return lock
        case "publish": return publish
        case "ok": return ok
        case "blocked": return blocked
    }
}

interface IconProps {
    img: IconType
    onClick?: () => void
    disabled?: boolean
    size?: number
}

export const Icon : FC<IconProps> = ({img, onClick, disabled = false, size = 22}) => {
    const handleClick = useCallback(() => !disabled && onClick && onClick(), [disabled, onClick])
    return(
        <S.Icon $disabled={disabled} style={{width: `${size}px`, height: `${size}px`, maxWidth: `${size}px`, maxHeight: `${size}px`, cursor: disabled? 'not-allowed':'pointer'}} onClick={handleClick}>
            <img style={{width: "100%", height: "100%"}} draggable={false} src={getPng(img)} alt={":("}/>
        </S.Icon>
    )
}
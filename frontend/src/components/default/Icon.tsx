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
import reset from "../../ui/reset.png"
import play from "../../ui/play.png"
import pause from "../../ui/pause.png"
import redSquare from "../../ui/redSquare.png"
import arrow from "../../ui/arrow.png"
import work from "../../ui/work.png"
import shop from "../../ui/shop.png"
import entertainment from "../../ui/entertainment.png"

export type IconType =
    "home" | "minus" | "plus" | "settings" | "trash-bin" | "lock" | "publish" | "ok" | "blocked" |
    "reset" | "play" | "pause" | "red-square" | "arrow" | "work" | "shop" | "entertainment"

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
        case "pause": return pause
        case "play": return play
        case "reset": return reset
        case "red-square": return redSquare
        case "arrow": return arrow
        case "work": return work
        case "shop": return shop
        case "entertainment": return entertainment
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
        <S.Icon $disabled={disabled} style={{width: `${size}px`, height: `${size}px`, minWidth: `${size}px`, minHeight: `${size}px`, cursor: disabled? 'not-allowed':'pointer'}} onClick={handleClick}>
            <img style={{width: `${size}px`, height: `${size}px`}} draggable={false} src={getPng(img)} alt={":("}/>
        </S.Icon>
    )
}
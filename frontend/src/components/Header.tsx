import {FC, useCallback, useContext, useMemo} from "react";
import { useNavigate } from "react-router-dom";
import {Styled as S} from "./Components.styled";
import {Icon} from "./default/Icon";
import {StageContext} from "../App";
import {INITIAL_SCALE, MAX_SCALE, MIN_SCALE, SCALE_BY} from "../Constants";
interface HeaderProps {
    page: "runner" | "editor"
}

export const Header : FC<HeaderProps> = ({page}) => {
    const { scale, setScale, setCoordinates, coordinates } = useContext(StageContext)

    const navigate = useNavigate();

    const handleClickRunner = useCallback(() => page !== "runner" && navigate("/runner"), [navigate, page])
    const handleClickEditor = useCallback(() => page !== "editor" && navigate("/editor"), [navigate, page])

    const handleClickPointer = useCallback(() => {
        setScale(1)
        setCoordinates({x: 0, y: 0})
    }, [setCoordinates, setScale])

    const handleClickPlus = useCallback(() => setScale(scale * SCALE_BY), [scale, setScale])
    const handleClickMinus = useCallback(() => setScale(scale / SCALE_BY), [scale, setScale])

    const homeDisabled = useMemo(() => scale === INITIAL_SCALE && coordinates.x === 0 && coordinates.y === 0, [coordinates.x, coordinates.y, scale])

    return (
        <S.Header>
            <S.HeaderTitle selected={page === "editor"} onClick={handleClickEditor}>EDITOR</S.HeaderTitle>
            <S.HeaderTitle selected={page === "runner"} onClick={handleClickRunner}>RUNNER</S.HeaderTitle>
            <S.Navigation>
                <Icon img={"home"} disabled={homeDisabled} onClick={handleClickPointer}/>
                <Icon img={"plus"} disabled={scale > MAX_SCALE} onClick={handleClickPlus}/>
                <Icon img={"minus"} disabled={scale < MIN_SCALE} onClick={handleClickMinus}/>
            </S.Navigation>
        </S.Header>
    )
}
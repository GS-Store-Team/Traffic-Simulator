import {FC, useCallback} from "react";
import { useNavigate } from "react-router-dom";
import {Styled as S} from "./Components.styled";
interface HeaderProps {
    page: "runner" | "editor"
}

export const Header : FC<HeaderProps> = ({page}) => {
    const navigate = useNavigate();

    const handleClickRunner = useCallback(() => page !== "runner" && navigate("/runner"), [navigate, page])
    const handleClickEditor = useCallback(() => page !== "editor" && navigate("/editor"), [navigate, page])

    return (
        <S.Header>
            <S.HeaderTitle selected={page === "editor"} onClick={handleClickEditor}>EDITOR</S.HeaderTitle>
            <S.HeaderTitle selected={page === "runner"} onClick={handleClickRunner}>RUNNER</S.HeaderTitle>
        </S.Header>
    )
}
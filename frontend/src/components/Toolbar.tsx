import {FC} from "react";
import {Styled as S} from "./Components.styled";

interface ToolbarProps {
    page: "runner" | "editor"
}

export const Toolbar : FC<ToolbarProps> = ({page}) => {

    return (
        <S.Toolbar>
            { page === "editor" &&
                <>
                </>
            }
            { page === "runner" &&
                <>
                </>
            }
        </S.Toolbar>
    )
}
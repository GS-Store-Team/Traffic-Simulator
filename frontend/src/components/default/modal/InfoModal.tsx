import React, {FC, PropsWithChildren, useRef} from "react";
import {Property} from "csstype";
import {useOutsideClick} from "../../../hooks/useOutsideClick";
import {Styled as S} from "./Modal.styled";
import {Btn} from "../Btn";

interface IInfoModal{
    title:string
    message?:string
    onOk:()=>void
    $width?: Property.Width;
    $height?: Property.Height;
}
export const InfoModal : FC<PropsWithChildren<IInfoModal>> = ({children, title, message, onOk, $width, $height}) =>{
    const ref = useRef<HTMLDivElement>(null)

    useOutsideClick(ref, onOk)

    return(
        <S.ModalBackground>
            <S.Modal ref={ref} $height={$height} $width={$width}>
                <S.Title>{title}</S.Title>
                <S.Body>
                    {message && <S.Text>{message}</S.Text>}
                    {children}
                </S.Body>
                <S.Buttons>
                    <Btn secondary style={{width: "90px"}} onClick={onOk}>OK</Btn>
                </S.Buttons>
            </S.Modal>
        </S.ModalBackground>
    )
}
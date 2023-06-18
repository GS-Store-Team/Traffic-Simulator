import React from 'react';
import {Property} from "csstype";
import {Styled as S} from "./Modal.styled";
import {Btn} from "../Btn";

interface IModal{
    onAccept: () => void;
    onDecline: () => void;
    disableAccept?: boolean;
    children?: React.ReactNode
    $width?: Property.Width;
    $height?: Property.Height;
}

export const Modal : React.FC<IModal> = ({onAccept, onDecline, children, $width, $height, disableAccept= false}) => {
    return (
        <S.ModalBackground>
            <S.Modal $height={$height} $width={$width}>
                {children}
                <S.Buttons>
                    <Btn secondary onClick={onDecline} style={{width: "90px"}}>DECLINE</Btn>
                    <Btn primary disabled={disableAccept} style={{width: "90px"}} onClick={onAccept}>ACCEPT</Btn>
                </S.Buttons>
            </S.Modal>
        </S.ModalBackground>
    )
}
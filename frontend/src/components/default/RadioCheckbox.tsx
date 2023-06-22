import React, {FC} from "react";
import {Form} from "react-bootstrap";

interface RadioCheckboxProps {
    current?: string
    options: string[]
    onChange(option: string):void
}

export const RadioCheckbox: FC<RadioCheckboxProps> = ({current, options, onChange}) => {
    return (
        <>
            {options.map((o, index) =>
                <Form.Check key={index} inline id="switch2" className="pl-5">
                    <Form.Check.Input checked={current === o} onChange={() => onChange(o)}/>
                    <Form.Check.Label>{o}</Form.Check.Label>
                </Form.Check>
            )}
        </>
    )
}
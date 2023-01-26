import React, {useContext, useEffect, useState} from 'react';
import {Context} from "../../router/AppRouter.jsx";
import {Stage} from "react-konva";
import classes from "./mystage.module.css";
import pointer from "../../UI/images/pointer.png";
import plus from "../../UI/images/plus.png";
import minus from "../../UI/images/minus.png";

export const MyStage = ({layers}) => {
    function getWindowDimensions() {
        const { innerWidth: width, innerHeight: height } = window;
        return {
            width,
            height
        };
    }
    // eslint-disable-next-line no-restricted-globals
    const [width, setWidth] = useState(window.innerWidth);
    // eslint-disable-next-line no-restricted-globals
    const [height, setHeight] = useState(innerHeight-120);
    const [offset, setOffset] = useState({x:0,y:0})

    useEffect(() => {
        function handleWindowResize() {
            setWidth(getWindowDimensions().width);
            setHeight(getWindowDimensions().height-120);
        }
        window.addEventListener('resize', handleWindowResize);
        return () => {
            window.removeEventListener('resize', handleWindowResize);
        };
    }, []);

    useEffect(()=>{
        setStage({
            scale: 1,
            x: width/2,
            y: height/2
        });
    },[width, height]);

    const {setScale, scale} = useContext(Context);
    const scaleBy = 1.10;
    const [stage, setStage] = useState({
        scale: 1,
        x: width/2,
        y: height/2
    });
    const defaultPosition = () => {
        setScale(1);
        setStage({scale: 1, x: width/2, y: height/2});
    }
    const upscale = () => {
        setScale(stage.scale*scaleBy*scaleBy);
        setStage({scale: stage.scale*scaleBy*scaleBy, x: stage.x, y: stage.y});
    }
    const downscale = () => {
        setScale(stage.scale/scaleBy/scaleBy);
        setStage({scale: stage.scale/scaleBy/scaleBy, x: stage.x, y: stage.y});
    }

    const handleWheel = (e) => {
        e.evt.preventDefault();
        const stage = e.target.getStage();
        const oldScale = stage.scaleX();

        const mousePointTo = {
            x: stage.getPointerPosition().x / oldScale - stage.x() / oldScale,
            y: stage.getPointerPosition().y / oldScale - stage.y() / oldScale
        };

        const newScale = e.evt.deltaY < 0 ? oldScale * scaleBy : oldScale / scaleBy;

        setStage({
            scale: newScale,
            x: (stage.getPointerPosition().x / newScale - mousePointTo.x) * newScale,
            y: (stage.getPointerPosition().y / newScale - mousePointTo.y) * newScale
        });
        setScale(newScale);
    };

    const {setShift} = useContext(Context);

    const handleDrag = (e) =>{
        if(e.target.constructor.name === "Stage") {
            setOffset({
                x: (-e.target._lastPos.x + width / 2),
                y: (-e.target._lastPos.y + height / 2)
            })
            setShift({
                x: (-e.target._lastPos.x + width / 2),
                y: (-e.target._lastPos.y + height / 2)
            })
        }
    }

    return (
        <div>
            <Stage
                x={stage.x}
                y={stage.y}
                offsetX={width/2}
                offsetY={height/2}
                width={width}
                height={height+20}
                scaleX={stage.scale}
                scaleY={stage.scale}
                onWheel={(e) => handleWheel(e)}
                draggable={true}
                onDragEnd={(e) => handleDrag(e)}
                >
                {layers.map((l)=>l)}
            </Stage>
            <img onClick={defaultPosition}
                 className={classes.my__pointer}
                 src={pointer}
                 alt={".."}
            />
            <img onClick={upscale}
                 className={classes.my__plus}
                 src={plus}
                 alt={".."}
            />
            <img onClick={downscale}
                 className={classes.my__minus}
                 src={minus}
                 alt={".."}
            />
        </div>
    );
};
import React, {useCallback, useContext, useEffect, useState} from "react";
import {Styled as S} from "./Components.styled";
import {Styled as S1} from "./default/modal/Modal.styled";
import {EditorContext} from "../pages/Editor";
import DropdownItem from "react-bootstrap/DropdownItem";
import {Button, DropdownButton} from "react-bootstrap";
import {Icon} from "./default/Icon";
import {FlexRow} from "./default/Flex.styled";
import {restClient} from "../api/axios.config";
import {Modal} from "./default/modal/Modal";
import {Input} from "./default/Form";
import {Btn} from "./default/Btn";
import {DefaultObjectContext} from "./contexts/DefaultObjectProvider";

export const EditorToolbar = () => {
    const { map, setMap, area, setAreaId, version, setVersionId } = useContext(EditorContext)
    const {dBuilding, dRoad, configure} = useContext(DefaultObjectContext)

    const [newVersionDialog, setNewVersionDialog] = useState<boolean>(false)
    const [value, setValue] = useState<string>('')
    const [versionAsync, setVersionAsync] = useState<{id: number}>()

    useEffect(() => {
        if(versionAsync){
            setVersionId(versionAsync.id)
        }
    }, [setVersionId, versionAsync])

    const handleAddVersion = useCallback(() => setNewVersionDialog(true), [])
    const handleDeclineAddVersion = useCallback(() => setNewVersionDialog(false), [])
    const addVersion = useCallback(() => {
        setNewVersionDialog(false)
        if(area) {
            restClient.addAreaVersion(area.id, value).then(setMap)
        }
    }, [area, setMap, value])
    const handleChangeInput = useCallback((e: React.FormEvent<HTMLInputElement>) => setValue(e.currentTarget.value), [])

    const invalid = value.length < 2 || value.length > 20

    const handleDeleteVersion = useCallback(() => version && restClient.removeAreaVersion(version.id).then(setMap),[setMap, version])

    const handlePublishVersion = useCallback(() => {
        if(!version) {
            return
        }
        restClient.configureAreaVersion(version.id, !version.locked).then(response => {
            setMap(response)
            setVersionAsync({id: version.id})
        })
    }, [setMap, version])

    const handleAddBuilding = useCallback(() => version && restClient.addBuilding(version.id, dBuilding).then(res => setMap(res, true)), [dBuilding, setMap, version])
    const handleAddRoad = useCallback(() => version && restClient.addRoad(version.id, dRoad).then(res => setMap(res, true)), [dRoad, setMap, version])

    if(!map){
        return <S.Toolbar/>
    }

    return (
        <S.Toolbar>
            {area && <S.AreaHeading>{area.label}</S.AreaHeading>}

            <FlexRow gap={"1em"}>
                <DropdownButton title={area ? area.label.toUpperCase() : "FULL MAP"}>
                    <DropdownItem onClick={() => setAreaId(undefined)}>FULL MAP</DropdownItem>
                    {map.areas.map(a => <DropdownItem key={a.id} onClick={() => setAreaId(a.id)}>{a.label.toUpperCase()}</DropdownItem>)}
                </DropdownButton>
                {area && <Button onClick={handleAddVersion}><FlexRow gap={"7px"}><Icon img={"plus"}/> ADD VERSION</FlexRow></Button>}
                {area && area.versions.length > 0 &&
                    <DropdownButton title={version ? version.label.toUpperCase() : "select version"}>
                        {area.versions.map(v => <DropdownItem key={v.id} onClick={() => setVersionId(v.id)}>{v.label.toUpperCase()}</DropdownItem>)}
                    </DropdownButton>
                }
            </FlexRow>

            { version &&
                <>
                    <FlexRow gap={"1em"}>
                        <Btn info onClick={handleAddBuilding}><FlexRow gap={"7px"}><Icon img={"plus"}/>ADD BUILDING</FlexRow></Btn>
                        <Btn info onClick={handleAddRoad}><FlexRow gap={"7px"}><Icon img={"plus"}/>ADD ROAD</FlexRow></Btn>
                        <Btn secondary onClick={configure}><Icon img={"settings"}/></Btn>
                        { version.locked ?
                            <Btn danger onClick={handlePublishVersion}><FlexRow gap={"7px"}><Icon img={"lock"}/>LOCKED</FlexRow></Btn>
                            : <Btn success onClick={handlePublishVersion}><FlexRow gap={"7px"}><Icon img={"ok"} />PUBLISHED</FlexRow></Btn>
                        }
                        <Btn danger onClick={handleDeleteVersion}><FlexRow gap={"7px"}><Icon img={"trash-bin"}/>DELETE VERSION</FlexRow></Btn>
                    </FlexRow>

                    <S.VersionInfo>
                        <span style={{width: "100%", overflow: "hidden", textOverflow:"ellipsis", whiteSpace: "nowrap"}}>{version.label.toUpperCase()}</span>
                        <FlexRow gap={"10px"}>visibility: <Icon img={version.locked ? "lock" : "ok"}/></FlexRow>
                        <FlexRow gap={"10px"}>valid: <Icon img={version.valid ? "ok" : "blocked"}/></FlexRow>
                        <span>roads count: {version.roads.length}</span>
                        <span>buildings count: {version.buildings.length}</span>
                    </S.VersionInfo>
                </>
            }

            {newVersionDialog &&
                <Modal onDecline={handleDeclineAddVersion}
                       onAccept={addVersion}
                       $height={"400px"}
                       disableAccept={invalid}
                >
                    <S1.Title>CREATE NEW VERSION</S1.Title>
                    <S1.Body>
                        <S1.Row>Provide label to create new version of area: '{area?.label}'</S1.Row>
                        <Input value={value} onChange={handleChangeInput} invalid={invalid}/>
                    </S1.Body>
                </Modal>
            }
        </S.Toolbar>
    )
}
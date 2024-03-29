import styled from "styled-components";
import {HEIGHT_SHIFT} from "./stage/BaseStage";

const Header = styled.div`
  background-color: rgba(0, 169, 143, 0.8);
  height: 60px;
  display: flex;
  align-items: center;
  gap: 3em;
  padding: 0 40px;
`

const HeaderTitle = styled.div<{ selected: boolean}>`
  cursor: ${({selected}) => selected? "not-allowed" : "pointer"};
  font-size: 22px;
  color: ${({selected}) => !selected && "rgb(126,126,126)"};
  
  &:hover {
    text-decoration: underline;
  }
`

const Toolbar = styled.div`
  height: 60px;
  background-color: rgba(168, 143, 0, 0.2);
  display: flex;
  align-items: center;
  padding: 0 20px;
  justify-content: space-between;
`

const Navigation = styled.div`
  position: absolute;
  right: 10px;
  top: 150px;
  display: flex;
  flex-direction: column;
  gap: 5px;
  z-index: 1;
`

const Icon = styled.div<{$disabled: boolean}> `
  display: flex;
  opacity: ${({$disabled}) => $disabled ? 0.4 : 0.7};
  &:hover{
    opacity: ${({$disabled}) => $disabled ? 0.4 : 1};
  }
`

const VersionInfo = styled.div`
  position: absolute;
  bottom: 10px;
  right: 10px;
  background-color: rgba(0,0,0,.2);
  z-index: 1;
  padding: 10px 20px;
  display: flex;
  flex-direction: column;
  border-radius: 5px;
  align-items: flex-end;
`

const AreaHeading = styled.div`
  position: absolute;
  z-index: 1;
  font-size: 30px;
  top: ${HEIGHT_SHIFT}px;
  left: 50%;
  transform: translateX(-50%);
`

const Slider = styled.input`
  background: linear-gradient(to right, rgba(0, 169, 143, 0.8) 0%, red 100%);
  width: 80px;
  border-radius: 2px;
  margin: auto;
  height: 4px;
  accent-color: rebeccapurple;
  box-shadow: 0 0 3px black;
  -webkit-appearance: none;
  cursor: pointer;
`

const AreaConfigHeading = styled.span`
  font-size: 20px;
  width: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
`

const PreviewBlock = styled.div`
  min-width: 250px;
  position: absolute;
  left: 10px;
  bottom: 10px;
  background-color: rgba(0,0,0,.2);
  border-radius: 5px;
  padding:  10px 20px;
  z-index: 1;
`

const PreviewCoords = styled.div`
  position: absolute;
  left: 10px;
  top: ${HEIGHT_SHIFT + 10}px;
  background-color: rgba(0,0,0,.2);
  border-radius: 5px;
  padding:  10px 20px;
  z-index: 1;
  display: flex;
  gap: 1em;
`

export const Styled = {
    Header,
    HeaderTitle,
    Toolbar,
    Navigation,
    Icon,
    VersionInfo,
    AreaHeading,
    Slider,
    AreaConfigHeading,
    PreviewBlock,
    PreviewCoords,
}
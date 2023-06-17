import styled from "styled-components";

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
`

export const Styled = {
    Header,
    HeaderTitle,
    Toolbar,
}
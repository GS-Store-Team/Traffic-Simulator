import {Container} from "pixi.js";
import {PanelUI} from "./common/PanelUI";

export class MainUI extends Container {

    constructor() {
        super();
    }

    public init() : void {
        this.addChild(new PanelUI(this.x, this.y, 0.9, 0.9));
    }
}
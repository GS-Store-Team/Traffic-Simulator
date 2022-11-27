import {Container} from "pixi.js";
import {AbstractData} from "../AbstractData";

export interface Updatable extends Container {

    update(): AbstractData;
}
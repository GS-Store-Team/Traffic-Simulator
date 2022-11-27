import {Graphics, Sprite} from "pixi.js";

export class PanelUI extends Sprite {
    private _posX: number;
    private _posY: number;
    private _widthProportion: number;
    private _heightProportion: number;

    constructor(posX : number, posY : number, widthProportion : number, heightProportion : number) {
        super();
        this._posX = posX;
        this._posY = posY;
        this._widthProportion = widthProportion;
        this._heightProportion = heightProportion;
        this.init();
    }

    private init() : void {
        this.x = this._posX;
        this.y = this._posY;
        this.width = window.innerWidth * this._widthProportion;
        this.width = window.innerHeight * this._heightProportion;
        this.mask = new Graphics()
            .beginFill(0xf0f0f0)
            .drawRect(this.x, this.y, this.width, this.height)
            .endFill();

    }

    public update() : void {

    }
}
import {Container, Sprite} from "pixi.js";
import {MainUI} from "./UI/MainUI";

export class Scene extends Container {
    private readonly screenWidth: number;
    private readonly screenHeight: number;

    // We promoted clampy to a member of the class
    private clampy: Sprite;
    private mainUI: MainUI;

    constructor(screenWidth: number, screenHeight: number) {
        super(); // Mandatory! This calls the superclass constructor.

        // see how members of the class need `this.`?
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        // Now clampy is a class member, we will be able to use it in another methods!
        this.clampy = Sprite.from("clampy.png");

        this.clampy.anchor.set(0.5);
        this.clampy.x = this.screenWidth / 2;
        this.clampy.y = this.screenHeight / 2;
        this.addChild(this.clampy);

        this.mainUI = new MainUI();
        this.addChild(this.mainUI);
        this.mainUI.init();


    }
}
"use strict";
// tsc --module amd TestShape.ts
exports.__esModule = true;
var circle = require("./Circle");
var triangle = require("./Triangle");
function drawAllShapes(shapeToDraw) {
    shapeToDraw.draw();
}
drawAllShapes(new circle.Circle());
drawAllShapes(new triangle.Triangle());

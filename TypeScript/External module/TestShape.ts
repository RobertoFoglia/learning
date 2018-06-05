// two way to compile with two module manager
// tsc --module commonjs TestShape.ts (it works)
// tsc --module amd TestShape.ts

import shape = require("./IShape");
import circle = require("./Circle");
import triangle = require("./Triangle");

function drawAllShapes(shapeToDraw: shape.IShape) {
  shapeToDraw.draw();
}

drawAllShapes(new circle.Circle());
drawAllShapes(new triangle.Triangle());

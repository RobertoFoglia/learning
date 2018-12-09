"use strict";
exports.__esModule = true;
var Circle = /** @class */ (function () {
    function Circle() {
    }
    Circle.prototype.draw = function () {
        console.log("Cirlce is drawn (external module)");
    };
    return Circle;
}());
exports.Circle = Circle;

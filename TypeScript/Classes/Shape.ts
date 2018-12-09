class Shape {
  Area: number;

  constructor(a: number) {
    this.Area = a;
  }

  disp() {
    console.log('I am your dad');
  }
}

class Circle extends Shape {
  disp(): void {
    console.log('Area of the circle:  ' + this.Area);
  }
}

var obj = new Circle(223);
obj.disp();

var objFather = obj as Shape;
objFather.disp();

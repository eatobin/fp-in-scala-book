def execTwoFunctions(f1: (Int, Int) => Int,
                     f2: (Int, Int) => Int,
                     a: Int,
                     b: Int): (Int, Int) =
  (f1(a, b), f2(a, b))

def sum(x: Int, y: Int): Int = x + y
def multiply(x: Int, y: Int): Int = x * y

val results = execTwoFunctions(sum, multiply, 2, 10)
println(results)

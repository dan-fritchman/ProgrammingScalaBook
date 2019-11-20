
package ch02 {
  package example {
    package pkg1 {
      class Class1 {
        def m = "m11"
      }
    }
    package pkg2 {
      class Class2 {
        def m = "m21"

        def makeClass1 = {
          new pkg1.Class1
        }
      }
    }
    package pkg3.sub.subsub {
      class Class3 {
        def m = "m31"
      }
    }
  }
}

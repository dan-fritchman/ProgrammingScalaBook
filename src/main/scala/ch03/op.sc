import scala.language.postfixOps

val one = 1.toString
println(one)

def isEven(n: Int) = (n % 2) == 0
// This some ugly shit.
List(1, 2, 3, 4) filter isEven foreach println
// Equal to this:
List(1, 2, 3, 4).filter(isEven).foreach(println)

val l = List('b', 'c', 'd')
val l2 = 'a' :: l
println(l2)

if (2 + 2 == 5) {
  println("Hello from 1984")
} else if (2 + 2 == 3) {
  println("Hello from remedial math class")
} else {
  println("Hello from a non-Orwellian future.")
}

val configFile = new java.io.File("somefile.txt")

val configFilePath = if (configFile.exists()) {
  configFile.getAbsolutePath()
} else {
  configFile.createNewFile()
  configFile.getAbsolutePath
}

val dogBreeds = List("pug", "terrier", "great dane")
for (breed <- dogBreeds)
  println(breed)

for (i <- 1 to 10) println(i)

for (breed <- dogBreeds if breed.contains("p"))
  println(breed)

for (breed <- dogBreeds
     if breed.contains("e")
     if breed.contains("t"))
  println(breed)

val filteredBreeds = for {
  breed <- dogBreeds
  if breed.contains("e")
  if breed.contains("t")
} yield breed
println(filteredBreeds)

for {
  breed <- dogBreeds
  upcasedBreed = breed.toUpperCase()
} println(upcasedBreed)

val dogBreedOptions = List(
  Some("pug"),
  Some("doberman"),
  None,
  Some("great dane")
)
println("First pass: ")
for {
  breedOption <- dogBreedOptions
  breed <- breedOption
  upcasedBreed = breed.toUpperCase()
} println(upcasedBreed)

println("Second pass: ")
for {
  Some(breed) <- dogBreedOptions
  upcasedBreed = breed.toUpperCase()
} println(upcasedBreed)


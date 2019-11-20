val name = "Buck Trends"
println(s"Hello, $name")

val gross = 100000F
val net = 64000F
val percent = (net / gross) * 100
println(f"$$${gross}%.2f vs. $$${net}%.2f or ${percent}%.1f%%")

val i = 200
println(f"${i}%.2f")
val d = 100.22
//println(f"${d}%2d") // Type Mismatch

val s = "%02d: name = %s".format(5, "dan")
val n1 = "Dan F"
println(s"123\n$n1\n456")
println(raw"123\n$n1\n456")


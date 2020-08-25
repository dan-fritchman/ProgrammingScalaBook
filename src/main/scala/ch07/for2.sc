val ignoreRegex = """^\s*(#.*|\s*)$""".r
val kvRegex = """^\s*([^=]+)\s*=\s*([^#]+)\s*.*$""".r

val properties =
  """
    |# Book Properties
    |
    |book.name = Programming Scala, Second Edition # A Comment
    |book.authors = Dean Wampler and Alex Payne
    |book.publisher = O'Reilly
    |book.publication-year = 2014
    |""".stripMargin

val kvPairs = for {
  prop <- properties.split("\n")
  if ignoreRegex.findFirstIn(prop) == None
  kvRegex(key, value) = prop
} yield (key.trim, value.trim)


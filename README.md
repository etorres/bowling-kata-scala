# Bowling Kata

2. We also recommend you also include the SuperSafe Community Edition Scala compiler plugin, which will flag errors in your ScalaTest (and Scalactic) code at compile time, by first adding this line to ~/.sbt/1.0/global.sbt:

resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"
3. Then adding the following line to project/plugins.sbt:

addSbtPlugin("com.artima.supersafe" % "sbtplugin" % "1.1.8")

(defproject eightball "0.1.0"
  :description "GroupMe bot for telling the future."
  :url "http://eightballgroupme.herokuapp.com"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-jetty-adapter "1.7.1"]
                 [environ "1.1.0"]
                 [camel-snake-kebab "0.4.0"]]
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.3.1"]]
  :hooks [environ.leiningen.hooks]
  :uberjar-name "eightball-standalone.jar"
  :profiles {:production {:env {:production true}}})

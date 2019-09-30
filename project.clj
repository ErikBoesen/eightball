(defproject eightball "0.1.0"
  :description "GroupMe bot for telling the future."
  :url "http://eightballgroupme.herokuapp.com"
  :main eightball.server
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [cheshire "5.3.1"]
                 [ring/ring-json "0.2.0"]
                 [ring/ring-jetty-adapter "1.2.1"]
                 [compojure "1.1.6"]
                 [clj-http "3.10.0"]]
  :min-lein-version "2.0.0"
  :plugins [[lein-ring "0.8.10"]]
  :uberjar-name "eightball-standalone.jar"
  :profiles {:production {:env {:production true}}})

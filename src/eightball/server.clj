(ns eightball.server
  (:require [ring.adapter.jetty :as jetty]
            [eightball.handler :as handler])
  (:gen-class))

(defn -main
  [& [port]]
  (let [port (Integer. (or port (System/getenv "PORT") 3000))]
    (jetty/run-jetty #'handler/app {:port  port
                                    :join? false})))

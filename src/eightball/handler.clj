(ns eightball.handler
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]
            [ring.util.response :refer [response content-type]]
            [cheshire.core :as cheshire]
            [compojure.core :refer :all]
            [clj-http.client :as client])

(defn json [form]
  (-> form
    cheshire/encode
    response
    (content-type "application/json; charset=utf-8")))


(def responses ["It is certain." "It is decidedly so." "Without a doubt." "Yes - definitely." "You may rely on it."
                "As I see it, yes." "Most likely." "Outlook good." "Yes." "Signs point to yes."
                "Reply hazy, try again." "Ask again later." "Better not tell you now." "Cannot predict now." "Concentrate and ask again."
                "Don't count on it." "My reply is no." "My sources say no." "Outlook not so good." "Very doubtful."])

(defn get-id [gid]
  (get
    (cheshire/parse-string
      (client/get (str "https://mebots.herokuapp.com/api/bot/eightball/instance/" gid)
                  {:accept :json :query-params {"token" (System/getenv "BOT_TOKEN")}}))
    "id"))

(defn post-message [message, gid]
  (client/post "https://api.groupme.com/v3/bots/post"
               {:form-params {:text message
                              :bot_id (get-id gid)}
                :content-type :json}

(defroutes app-routes
  (GET "/" [] "Hello! I'm Eight Ball, an omniscient GroupMe bot.")

  (POST "/pizza" {data :params} :params)

  ; serve public resources
  (route/resources "/")

  (route/not-found "Not found."))

(def app
  (->
    app-routes
    handler/site
    middleware/wrap-json-body
    middleware/wrap-json-params
    #_middleware/wrap-json-response))

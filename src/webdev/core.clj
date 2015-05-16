(ns webdev.core
  (require [ring.adapter.jetty :as jetty]
           [ring.middleware.reload :refer [wrap-reload]]
           [compojure.core :refer :all]
           [compojure.route :as route]))

(defn greet
  "Greets stuff"
  [request]
  {:status 200
   :body "Hello, World!"})

(defroutes app
  (GET "/" [] greet))

(defn -main
  "Simple Ring / Jetty server"
  [port-number]
  (jetty/run-jetty app
                   {:port (Integer. port-number)}))

(defn -dev-main
  "Dev server, reloads"
  [port-number]
  (jetty/run-jetty (wrap-reload #'app)
                   {:port (Integer. port-number)}))

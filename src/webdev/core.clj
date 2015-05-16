(ns webdev.core
  (require [ring.adapter.jetty :as jetty]))

(defn greet
  "Greets stuff"
  [request]
  (if (= "/" (:uri request))
    {:status 200
     :body "Hello, World!"
     :headers {}}
    {:status 404
     :body "Not found"
     :headers {}}))

(defn -main
  "Simple Ring / Jetty server"
  [port-number]
  (jetty/run-jetty greet
    {:port (Integer. port-number)}))

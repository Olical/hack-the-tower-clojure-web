(ns webdev.core
  (require [ring.adapter.jetty :as jetty]))

(defn -main
  "Simple Ring / Jetty server"
  [port-number]
  (jetty/run-jetty
    (fn [request] {:status 200
                   :body "Hello, World!"
                   :headers {}})
    {:port (Integer. port-number)}))

(ns webdev.core
  (require [webdev.item.model :as items])
  (require [ring.adapter.jetty :as jetty]
           [ring.middleware.reload :refer [wrap-reload]]
           [compojure.core :refer :all]
           [compojure.route :as route]))

(def db (System/getenv "DATABASE_URL"))

(defn greet
  "Greets stuff"
  [request]
  {:status 200
   :body "Hello, World!"})

(def operands
  {"+" +
   "-" -
   "*" *
   ":" /})

(defn calc
  "Calculates things"
  [a op b]
  ((let [f (get operands op)]
    (if f
      {:status 200
       :body (str (f a b))}
      {:status 404
       :body "Operator not found"}))))

(defroutes app
  (GET "/" [] greet)
  (GET "/calc/:a/:op/:b" [a op b] (calc (bigint a) op (bigint b))))

(defn -main
  "Dev server, reloads"
  [port-number]
  (items/create-table db)
  (jetty/run-jetty (wrap-reload #'app)
                   {:port (Integer. port-number)}))

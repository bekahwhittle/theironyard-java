(ns clojure-purchases.core
  (:require [clojure.string :as str]
            [compojure.core :as c]
            [ring.adapter.jetty :as j]
            [hiccup.core :as h])
  (:gen-class))

(def file-name "purchases.csv")

(defn read-purchases [& args]
  (let [purchases (str/split-lines(slurp file-name))
        purchases (map (fn [line]
                         (str/split line #","))
                    purchases)
        header (first purchases)
        purchases (rest purchases)
        purchases (map (fn [line]
                         (zipmap header line))
                    purchases)]
     purchases))

(defn purchases-html [category]
  (let [purchases (read-purchases)
        purchases (filter (fn [purchases]
                            (or (nil? category)
                                (= category (get purchases "category"))))
                    purchases)]
    [:ol 
     (map (fn [purchases]
            [:li (str
                   (get purchases "customer_id")
                   " "
                   (get purchases "date")
                   " "
                   (get purchases "credit_card")
                   " "
                   (get purchases "cvv")
                   " "
                   (get purchases "category"))])         
       purchases)]))
                   

(c/defroutes app
  (c/GET "/" []   
    (h/html [:html [:body
                    [:a {:href "/Alcohol"} "Alcohol"]
                    " "
                    [:a {:href "/Furniture"} "Furniture"]
                    " "
                    [:a {:href "/Toiletries"} "Toiletries"]
                    " "
                    [:a {:href "/Shoes"} "Shoes"]
                    " "
                    [:a {:href "/Jewelry"} "Jewelry"] 
                    (purchases-html nil)]])) 
  (c/GET "/:category" [category]  
    (h/html [:html [:body (purchases-html category)]])))

(defn -main []
  (j/run-jetty app {:port 3000}))
    

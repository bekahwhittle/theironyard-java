(set-env!
  :source-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.8.0"]
                  [nightlight "1.1.0" :scope "test"]
                  [ring "1.4.0"]
                  [hiccup "1.0.5"]
                  [compojure "1.5.1"]])

(require '[nightlight.boot :refer [nightlight]])

(deftask build []
  (comp
    (aot :namespace '#{clojure-cards.core})
    (pom :project 'clojure-cards
         :version "1.0.0")
    (uber)
    (jar :main 'clojure-cards.core)
    (target)))

(deftask run []
  (comp
    (wait)
    (nightlight :port 4000)))


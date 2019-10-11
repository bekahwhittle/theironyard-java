(ns clojure-maze.core
  (:gen-class))

(def size 10)

(defn create-rooms[]
  (vec
    (for [row (range 0 size)]
      (vec
        (for [col (range 0 size)]
          {:row row 
           :col col
           :visited? false
           :bottom? true
           :right? true
           :end? false})))))

(defn possible-neighbors [rooms row col]
  (vec
    (filter
      (fn [room]
        (and room (= false (:visited? room))))
      [(get-in rooms [(- row 1) col])
       (get-in rooms [(+ row 1) col])
       (get-in rooms [row (- col 1)])
       (get-in rooms [row (+ col 1)])])))

(defn random-neighbor [rooms row col]
  (let [neighbors (possible-neighbors rooms row col)]
    (if (pos? (count neighbors))
      (rand-nth neighbors)
      nil)))

(defn tear-down [rooms old-row old-col new-row new-col]
  (cond
    (< new-row old-row) ; up
    (assoc-in rooms [new-row new-col :bottom?] false)
    (> new-row old-row) ; down
    (assoc-in rooms [old-row old-col :bottom?] false)
    (< new-col old-col) ; left
    (assoc-in rooms [new-row new-col :right?] false)
    (> new-col old-col) ; right
    (assoc-in rooms [old-row old-col :right?] false)))

(def dead-end? (atom false))

(defn create-maze [rooms row col]
  (let [rooms (assoc-in rooms [row col :visited?] true)
        next-room (random-neighbor rooms row col)]
    (if next-room
      (let [rooms (tear-down rooms row col (:row next-room) (:col next-room))]
        (create-maze rooms (:row next-room) (:col next-room)))
      (if (= false (deref dead-end?))
        (do
          (reset! dead-end? true)
          (assoc-in rooms [row col :end?] true))
        rooms))))
        
        
    
    
(defn -main [& args]
  (reset! dead-end? false)
  (let [rooms (create-rooms)
        rooms (create-maze rooms 0 0)]
    (doseq [row rooms]
      (print " _"))
    (println)
    (doseq [row rooms]
      (print "|")
      (doseq [room row]
        (cond (and (= 0 (:row room))
                 (= 0 (:col room)))
          (print "o")
          (:end? room) 
          (print "x")
          (:bottom? room)
          (print "_")
          :else (print " "))
        (if (:right? room)
          (print "|")
          (print " ")))
      (println))))


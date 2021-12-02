(ns aoc2021.puzzle04
  (:require
   [aoc2021.puzzle03 :as p]))

(def initial-position {:x 0 :y 0 :aim 0})

(defn convert-movement [pos [dir len]]
  (let [length (Integer/parseInt len)]
    (condp = dir
      "up" (merge pos {:aim (+ (:aim pos)
                               (* -1 length))})
      "down" (merge pos {:aim (+ (:aim pos)
                                 length)})
      "forward" (merge pos {:x (+ (:x pos)
                                  length)
                            :y (+ (:y pos)
                                  (* (:aim pos) length))}))))

(defn product [position]
  (* (:x position) (:y position)))

(defn solve-puzzle [input-data]
  (product
   (reduce convert-movement initial-position
           (p/parse-input input-data))))

(solve-puzzle p/input)

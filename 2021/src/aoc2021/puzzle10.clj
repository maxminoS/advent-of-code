(ns aoc2021.puzzle10
  (:require
   [aoc2021.puzzle09 :as p]))

(defn get-line-gradient [[start-point stop-point]]
  (let [[start-x start-y] start-point
        [stop-x stop-y] stop-point]
    (/ (- stop-y start-y) (- stop-x start-x))))

(def perfect-diagonal? #(= 1 (Math/abs (get-line-gradient %))))

(p/solve-puzzle p/input)

(ns aoc2021.puzzle01
  (:require
   [clojure.string :as str]))

(def demo-input [199
                 200
                 208
                 210
                 200
                 207
                 240
                 269
                 260
                 263])
;; Example:
;; [199 200 208 210 200 207 240 269 260 263]
;; [200 208 210 200 207 240 269 260 263 0]
;; [true true true false true true true false true false]
;; Count: 7

(def input
  (map #(Integer/parseInt %)
       (str/split-lines
        (slurp "resources/input/day-01.txt"))))

(defn solve-puzzle [input-data]
  (count (filter identity
                 (map <
                      input-data
                      (concat (rest input-data) [0])))))

(solve-puzzle input)

(ns aoc2021.puzzle11
  (:require
   [clojure.string :as str]))

(def demo-input [3 4 3 1 2])

(def input (map #(Integer/parseInt %)
                (-> "resources/input/day-06.txt"
                    slurp
                    str/trim
                    (str/split #","))))

(defn fish-cycle [data-input]
  (let [new-cycle (map dec data-input)
        new-fishes-count (->> new-cycle
                              (filter neg?)
                              count)
        corrected-cycle (map #(if (neg? %1) 6 %1) new-cycle)
        new-fishes-vec (vec (take new-fishes-count (repeat 8)))]
    (flatten (cons corrected-cycle new-fishes-vec))))

(defn fish-growth [days data-input]
  (if (zero? days) data-input
      (fish-growth (dec days) (fish-cycle data-input))))

(defn solve-puzzle [data-input]
  (count (fish-growth 80 data-input)))

(solve-puzzle input)

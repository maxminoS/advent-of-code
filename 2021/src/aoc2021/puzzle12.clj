(ns aoc2021.puzzle12
  (:require
   [aoc2021.puzzle11 :as p]))

(defn solve-puzzle [data-input]
  (count (p/fish-growth 256 data-input)))

(solve-puzzle p/demo-input)

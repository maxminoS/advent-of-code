(ns aoc2021.puzzle14
  (:require
   [aoc2021.puzzle13 :as p]))

(defn gaussian-sum
  "Sum of 1 to n"
  [n]
  (/ (* n (inc n)) 2))

(defn calc-new-fuel [pos sorted-input]
  (reduce + (map #(* (gaussian-sum (Math/abs (- (first %) pos))) (second %))
                 sorted-input)))

(defn solve-puzzle [data-input]
  (let [sorted-input (p/sort-by-frequency data-input)
        pos-by-freq (map key sorted-input)
        num-seq (take (apply max pos-by-freq) (iterate inc (apply min pos-by-freq)))
        total-fuels (map #(calc-new-fuel % sorted-input) num-seq)
        least-fuel (apply min total-fuels)]
    least-fuel))

(solve-puzzle p/input)

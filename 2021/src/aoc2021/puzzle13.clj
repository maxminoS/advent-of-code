(ns aoc2021.puzzle13
  (:require
   [clojure.string :as str]))

(def demo-input [16 1 2 0 4 2 7 1 2 14])

(def input (map #(Integer/parseInt %)
                (-> "resources/input/day-07.txt"
                    slurp
                    str/trim
                    (str/split #","))))

(defn sort-by-frequency [data-input]
  (->> data-input
       frequencies
       (sort #(> (second %1) (second %2)))))

(defn calc-fuel [pos sorted-input]
  (reduce + (map #(* (Math/abs (- (first %) pos)) (second %))
                 sorted-input)))

(defn solve-puzzle [data-input]
  (let [sorted-input (sort-by-frequency data-input)
        pos-by-frequency (map key sorted-input)
        total-fuels (map #(calc-fuel % sorted-input) pos-by-frequency)
        least-fuel (apply min total-fuels)]
    least-fuel))

(solve-puzzle input)

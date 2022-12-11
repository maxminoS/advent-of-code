(ns aoc2022.day01
  (:require
   [clojure.string :as str]))

(def demo-input "1000
2000
3000

4000

5000
6000

7000
8000
9000

10000")

(def input (slurp "resources/input/day-01.txt"))

(defn parse-input [input-data]
  (let [each (str/split input-data #"\n\n")
        str-each (map str/split-lines each)
        int-each (map (fn [x] (map #(Integer/parseInt %) x)) str-each)
        sum-each (map #(apply + %) int-each)]
    sum-each))

(defn solve-puzzle-1 [input-data]
  (->> (parse-input input-data)
       (apply max)))

(solve-puzzle-1 input)

(defn solve-puzzle-2 [input-data]
  (->> (parse-input input-data)
       (sort >)
       (take 3)
       (apply +)))

(solve-puzzle-2 input)

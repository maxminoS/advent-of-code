(ns aoc2022.day04
  (:require
   [clojure.string :as str]
   [clojure.set :as set]))

(def demo-input "2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8")

(def input (slurp "resources/input/day-04.txt"))

(defn parse-input [input-data]
  (->> (str/split-lines input-data)
       (map #(str/split % #","))
       (map #(map (fn [x] (str/split x #"-")) %))
       flatten
       (map #(Integer/parseInt %))
       (partition 2)
       (map #(set (range (first %) (inc (second %)))))
       (partition 2)))

(defn solve-puzzle-1 [input-data]
  (->> (parse-input input-data)
       (map (fn [x]
              (let [fully-contained-amount (min (count (first x)) (count (last x)))
                    common-items (apply set/intersection x)]
                (= fully-contained-amount (count common-items)))))
       (filter true?)
       count))

(solve-puzzle-1 input)

(defn solve-puzzle-2 [input-data]
  (->> (parse-input input-data)
       (map #(count (apply set/intersection %)))
       (filter pos?)
       count))

(solve-puzzle-2 input)

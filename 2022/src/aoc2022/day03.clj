(ns aoc2022.day03
  (:require
   [clojure.string :as str]
   [clojure.set :as set]))

(def demo-input "vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw")

(def input (slurp "resources/input/day-03.txt"))

(defn parse-input [input-data]
  (map #(let [half (/ (count %) 2)
              first-half-set (set (take half %))
              second-half-set (set (take half (reverse %)))
              common-item (set/intersection first-half-set second-half-set)]
          (first common-item))
       (str/split-lines input-data)))

(defn get-priority-score [ascii]
  (if (> (int ascii) 90)
    (- (int ascii) 96)
    (- (int ascii) 38)))

(defn solve-puzzle-1 [input-data]
  (->> (parse-input input-data)
       (map #(get-priority-score %))
       (apply +)))

(solve-puzzle-1 input)

(defn parse-input-2 [input-data]
  (let [partitioned-set (partition 3 (map #(set %) (str/split-lines input-data)))
        common-item (map #(first (apply set/intersection %)) partitioned-set)]
    common-item))

(defn solve-puzzle-2 [input-data]
  (->> (parse-input-2 input-data)
       (map #(get-priority-score %))
       (apply +)))

(solve-puzzle-2 input)

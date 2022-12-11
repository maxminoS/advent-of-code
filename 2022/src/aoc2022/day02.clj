(ns aoc2022.day02
  (:require
   [clojure.string :as str]))

(def demo-input "A Y
B X
C Z")

;; Opponent - You
;;
;; 1 - A - X - Rock
;; 2 - B - Y - Paper
;; 3 - C - Z - Scissors
;;
;; 0 - Loss
;; 3 - Draw
;; 6 - Win

(def input (slurp "resources/input/day-02.txt"))

(def shape-map {"A" "1" "B" "2" "C" "3"
               "X" "1" "Y" "2" "Z" "3"})

(def result-map {1 {1 3 2 6 3 0}
                 2 {1 0 2 3 3 6}
                 3 {1 6 2 0 3 3}})

(defn parse-input [input-data shape-mapping]
  (let [point-replace (str/replace input-data #"[ABCXYZ]" #(shape-mapping %))
        split-map (map #(str/split % #" ") (str/split-lines point-replace))
        int-map (map (fn [x] (map #(Integer/parseInt %) x)) split-map)]
    int-map))

(defn get-score [opp you]
  (let [shape-score you
        result-score ((result-map opp) you)
        total-score (+ shape-score result-score)]
    total-score))

(defn solve-puzzle-1 [input-data]
  (->> (parse-input input-data shape-map)
       (map #(apply get-score %))
       (apply +)))

(solve-puzzle-1 demo-input)


(def opp-result-map {"A" "1" "B" "2" "C" "3"
                     "X" "0" "Y" "3" "Z" "6"})

(def your-shape-map {1 {0 3 3 1 6 2}
                     2 {0 1 3 2 6 3}
                     3 {0 2 3 3 6 1}})

(defn solve-puzzle-2 [input-data]
  (->> (parse-input input-data opp-result-map)
       (map #(list (first %) ((your-shape-map (first %)) (last %))))
       (map #(apply get-score %))
       (apply +)))

(solve-puzzle-2 input)

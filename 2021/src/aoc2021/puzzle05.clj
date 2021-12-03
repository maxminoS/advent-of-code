(ns aoc2021.puzzle05
  (:require
   [clojure.string :as str]))

(def demo-input ["00100"
                 "11110"
                 "10110"
                 "10111"
                 "10101"
                 "01111"
                 "00111"
                 "11100"
                 "10000"
                 "11001"
                 "00010"
                 "01010"])

;; ["00100" "11110" "10110"]
;; [[0 0 1 0 0] [1 1 1 1 0] [1 0 1 1 0]]

(def input
  (str/split-lines
   (slurp "resources/input/day-03.txt")))

(defn parse-input [input-data]
  (map (fn [string]
         (map #(Integer/parseInt %)
              (str/split string #"")))
       input-data))

(defn binary-map
  ;; Example: [1 2 4 8 16]
  ([] (binary-map 1))
  ([number] (lazy-seq (cons number (binary-map (* 2 number))))))

(defn reverse-binary-map [length]
  (reverse (take length (binary-map))))

(defn bin->dec [bin]
  (reduce + (map * bin (reverse-binary-map (count bin)))))

(defn most-common-item [item]
  (key (apply max-key val (frequencies item))))

(defn least-common-item [item]
  (key (apply min-key val (frequencies item))))

(defn nth-item-map [seq n]
  (map #(nth % n) seq))

(defn gamma-fn
  ([data-input] (gamma-fn data-input 0))
  ([data-input n] (lazy-seq (cons (most-common-item (nth-item-map data-input n))
                                  (gamma-fn data-input (inc n))))))

(defn epsilon-fn
  ([data-input] (epsilon-fn data-input 0))
  ([data-input n] (lazy-seq (cons (least-common-item (nth-item-map data-input n))
                                  (epsilon-fn data-input (inc n))))))

(defn gamma-rate [data-input]
  (take (count (first data-input)) (gamma-fn data-input)))

(defn epsilon-rate [data-input]
  (take (count (first data-input)) (epsilon-fn data-input)))

(defn power-consumption [data-input]
  (* (bin->dec (gamma-rate data-input))
     (bin->dec (epsilon-rate data-input))))

(defn solve-puzzle [data-input]
  (power-consumption (parse-input data-input)))

(solve-puzzle input)

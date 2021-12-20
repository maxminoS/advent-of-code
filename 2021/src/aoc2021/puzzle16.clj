(ns aoc2021.puzzle16
  (:require
   [aoc2021.puzzle15 :as p]
   [clojure.set :as set]
   [clojure.string :as str]))

(defn parse-input [data-input]
  (map
   (fn [input-line]
     (map #(str/split % #" ")
          (map str/trim
               (str/split input-line #"\|"))))
   (str/split-lines data-input)))

(defn superset? [superset subset]
  (every? true? (map #(.contains superset %)
                     subset)))

(defn decode-signal [signals]
  (let [sorted-signals (map sort signals)
        signal-counts (map count signals)
        one (nth sorted-signals (.indexOf signal-counts 2))
        four (nth sorted-signals (.indexOf signal-counts 4))
        seven (nth sorted-signals (.indexOf signal-counts 3))
        eight (nth sorted-signals (.indexOf signal-counts 7))
        three (nth sorted-signals (.indexOf (map #(if (and (= (count %) 5)
                                                           (superset? % one)) true nil)
                                                 sorted-signals) true))
        six (nth sorted-signals (.indexOf (map #(if (and (= (count %) 6)
                                                         (not (superset? % one))) true nil)
                                               sorted-signals) true))
        nine (nth sorted-signals (.indexOf (map #(if (and (= (count %) 6)
                                                          (superset? % one)
                                                          (superset? % three)) true nil)
                                                sorted-signals) true))
        zero (nth sorted-signals (.indexOf (map #(if (and (= (count %) 6)
                                                          (superset? % one)
                                                          (not (superset? % three))) true nil)
                                                sorted-signals) true))
        top-right (seq (set/difference (set eight) (set six)))
        two (nth sorted-signals (.indexOf (map #(if (and (= (count %) 5)
                                                         (not (superset? % one))
                                                         (superset? % top-right)) true nil)
                                               sorted-signals) true))
        five (nth sorted-signals (.indexOf (map #(if (and (= (count %) 5)
                                                          (not (superset? % one))
                                                          (not (superset? % top-right))) true nil)
                                                sorted-signals) true))]
    [zero one two three four five six seven eight nine]))

(defn decode-values [values decoder]
  (->> (map #(str (.indexOf decoder (sort %)))
            values)
       str/join
       Integer/parseInt))

(defn solve-puzzle [data-input]
  (reduce + (map
             #(decode-values (second %) (decode-signal (first %)))
             (parse-input data-input))))

(solve-puzzle p/input)

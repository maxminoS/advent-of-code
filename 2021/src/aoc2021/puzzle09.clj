(ns aoc2021.puzzle09
  (:require
   [clojure.string :as str]))

(def demo-input ["0,9 -> 5,9"
                 "8,0 -> 0,8"
                 "9,4 -> 3,4"
                 "2,2 -> 2,1"
                 "7,0 -> 7,4"
                 "6,4 -> 2,0"
                 "0,9 -> 2,9"
                 "3,4 -> 1,4"
                 "0,0 -> 8,8"
                 "5,5 -> 8,2"])

(def input
  (str/split-lines (slurp "resources/input/day-05.txt")))

(defn parse-coordinate [coord-string]
  (map #(Integer/parseInt %) (str/split coord-string #",")))

(defn parse-line [line-string]
  (let [line-string (str/split line-string #" ")
        start-string (parse-coordinate (first line-string))
        stop-string (parse-coordinate (last line-string))]
    [start-string stop-string]))

(defn line-direction [[start-point stop-point]]
  (let [[start-x start-y] start-point
        [stop-x stop-y] stop-point]
    (condp = 0
      (- start-x stop-x) :vertical
      (- start-y stop-y) :horizontal
      :diagonal)))

(defn gen-zero-map [x y]
  (vec (take (inc y) (repeat (vec (take (inc x) (repeat 0)))))))

(defn get-largest-coordinate [axis line-coords]
  (let [coord-axis (condp = axis
                     :x first
                     :y second)]
    (apply max (flatten
                (map (fn [line]
                       (cons (coord-axis (first line))
                             [(coord-axis (second line))]))
                     line-coords)))))

(defn generate-board [points]
  (apply gen-zero-map
         (map (fn [axis]
                (get-largest-coordinate axis points))
              [:x :y])))

(defn get-line-points [[start-point stop-point]]
  (let [direction (line-direction [start-point stop-point])
        [start-x start-y] start-point
        [stop-x stop-y] stop-point
        point-seq #(take (inc (Math/abs (- %1 %2)))
                         (iterate inc (min %1 %2)))]
    (condp = direction
      :horizontal (map #(eval [% start-y])
                       (point-seq start-x stop-x))
      :vertical (map #(eval [start-x %])
                     (point-seq start-y stop-y))
      :diagonal nil)))

(defn plot-point [board-map point]
  (let [point-value (get-in board-map (reverse point))
        new-map (assoc-in board-map (reverse point)
                          (inc point-value))]
    new-map))

(defn plot-line [board-map line-coords]
  (let [line-points (get-line-points line-coords)]
    (reduce plot-point board-map line-points)))

(defn solve-puzzle [data-input]
  (let [lines-coords (map parse-line data-input)
        board-map (generate-board lines-coords)
        plotted-map (reduce plot-line board-map lines-coords)]
    (->> plotted-map
         flatten
         (filter #(> % 1))
         count)))

(solve-puzzle input)

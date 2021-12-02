(ns aoc2021.puzzle03
  (:require
   [clojure.string :as str]))

(def direction-keys [:x :y])

(def demo-input
  ["forward 5"
   "down 5"
   "forward 8"
   "up 3"
   "down 8"
   "forward 2"])

;; ["forward 5" "down 5" "forward 8" "up 3" "down 8" "forward 2"]
;; [{:x 5} {:y 5} {:x 8} {:y -3} {:y 8} {:x 2}]
;; (15 10)

(def input
  (str/split-lines
   (slurp "resources/input/day-02.txt")))

(defn parse-input [input-data]
  (map #(str/split % #" ")
       input-data))

(defn convert-direction
  "Convert word directions into coordinate"
  [[dir len]]
  (let [length (Integer/parseInt len)]
    (condp = dir
      "up" {:y (* -1 length)}
      "down" {:y length}
      "forward" {:x length})))

(defn sum-of-key
  "Sum of all numbers of a keyword in a map"
  [key seq]
  (reduce + (filter number? (map key seq))))

(defn displacement [data]
  (map
   #(sum-of-key %
                (map convert-direction
                     data))
   direction-keys))

(defn solve-puzzle [input-data]
  (reduce *
          (displacement
           (parse-input input-data))))

(solve-puzzle input)

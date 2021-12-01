(ns aoc2021.puzzle02
  (:require
   [aoc2021.puzzle01 :as p]))

;; Example:
;; [199 200 208 210 200 207 240 269]
;; [200 208 210 200 207 240 269 260]
;; [208 210 200 207 240 269 260 263]

;; [607 618 618 617 647 716 769 792]

(defn measurement-window [input-data x]
  (apply map +
         (map #(take
                (- (count input-data) (dec x)) ;; New length
                (drop % input-data))
              (range x))))

(p/solve-puzzle
 (measurement-window p/input 3))

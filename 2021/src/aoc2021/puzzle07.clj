(ns aoc2021.puzzle07
  (:require
   [clojure.string :as str]))

(def demo-input ["7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1"
                 "22 13 17 11  0
 8  2 23  4 24
21  9 14 16  7
 6 10  3 18  5
 1 12 20 15 19"
                 " 3 15  0  2 22
 9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6"
                 "14 21 17 24  4
10 16 15  9 19
18  8 23 26 20
22 11 13  6  5
 2  0 12  3  7"])

;; Numbers: [7 4 9 5 11 17 23 2 0 14 21 24 10 16 13 6 15 25 12 22 18 20 8 19 3 26 1]
;; Board 1: [22 13 17 11 0 8 2 23 4 24 21 9 14 16 7 6 10 3 18 5 1 12 20 15 19]

(def input (str/split (slurp "resources/input/day-04.txt") #"\n\n"))

(defn parse-draw-numbers [numbers-data-input]
  (str/split numbers-data-input #","))

(defn parse-boards [boards-data-input]
  (map #(str/split (str/trim %) #"\s+") boards-data-input))

(defn draw [board number]
  (let [index (.indexOf board number)]
    (if (= index -1) board
        (assoc board index false))))

(defn win? [board]
  (let [board-length (int (Math/sqrt (count board)))
        board-pad (take (count board) (iterate inc 1))
        horizontal-win-indexes (partition board-length board-pad)
        vertical-win-indexes (map #(flatten (partition 1 board-length
                                                       (nthrest board-pad %)))
                                  (take board-length (iterate inc 0)))
        win-indexes (concat horizontal-win-indexes vertical-win-indexes)]
    (some true?
          (map #(every? false? %)
               (map (fn [win-row]
                      (map #(nth board (dec %)) win-row))
                    win-indexes)))))

(defn play-bingo [draw-numbers boards]
  (let [new-boards (map #(draw % (first draw-numbers)) boards)
        wins (map #(win? %) new-boards)]
    (if (some true? wins)
      (cons (first draw-numbers)
            [(nth new-boards (.indexOf wins true))])
      (if (empty? (rest draw-numbers)) nil
          (play-bingo (rest draw-numbers) new-boards)))))

(defn get-board-score [board number]
  (* (reduce + (filter number? board))
     number))

(defn solve-puzzle [data-input]
  (let [draw-numbers (parse-draw-numbers (first data-input))
        boards (parse-boards (rest data-input))
        game (play-bingo draw-numbers boards)
        final-draw (Integer/parseInt (first game))
        won-board (map #(when (string? %) (Integer/parseInt %))
                       (second game))]
    (get-board-score won-board final-draw)))

(solve-puzzle input)

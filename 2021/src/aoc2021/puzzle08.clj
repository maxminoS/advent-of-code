(ns aoc2021.puzzle08
  (:require
   [aoc2021.puzzle07 :as p]))

(defn lose-bingo [draw-numbers boards]
  (let [new-boards (map #(p/draw % (first draw-numbers)) boards)
        wins (map #(p/win? %) new-boards)
        remaining-boards (map #(nth new-boards %)
                              (keep-indexed #(when (nil? %2) %1) wins))]
    (if (= (count remaining-boards) 1)
      (cons (second draw-numbers)
            [(p/draw (first remaining-boards) (second draw-numbers))])
      (lose-bingo (rest draw-numbers) remaining-boards))))

(defn solve-puzzle [data-input]
  (let [draw-numbers (p/parse-draw-numbers (first data-input))
        boards (p/parse-boards (rest data-input))
        game (lose-bingo draw-numbers boards)
        final-draw (Integer/parseInt (first game))
        lost-board (map #(when (string? %) (Integer/parseInt %))
                        (second game))]
    (p/get-board-score lost-board final-draw)))

(solve-puzzle p/input)

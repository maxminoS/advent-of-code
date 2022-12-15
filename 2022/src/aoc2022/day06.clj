(ns aoc2022.day06)

(def demo-input-0 "mjqjpqmgbljsphdztnvjfqwrcgsmlb")
(def demo-input-1 "bvwbjplbgvbhsrlpgdmjqwftvncz")
(def demo-input-2 "nppdvjthqldpwncqszvftbrmjlhg")
(def demo-input-3 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
(def demo-input-4 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")

(def input (slurp "resources/input/day-06.txt"))

(defn solve-puzzle-1 [input-data]
  (let [char-amt 4]
    (loop [curr-id 0]
      (let [selected (take char-amt (drop curr-id input-data))]
        (if (= (+ char-amt curr-id) (count input-data))
          0
          (if (= (count selected) (count (set selected)))
            (+ char-amt curr-id)
            (recur (inc curr-id))))))))

(solve-puzzle-1 input)

(defn solve-puzzle-2 [input-data]
  (let [char-amt 14]
    (loop [curr-id 0]
      (let [selected (take char-amt (drop curr-id input-data))]
        (if (= (+ char-amt curr-id) (count input-data))
          0
          (if (= (count selected) (count (set selected)))
            (+ char-amt curr-id)
            (recur (inc curr-id))))))))

(solve-puzzle-2 input)

(ns aoc2022.day05
  (:require
   [clojure.string :as str]))

(def demo-input "    [D]
[N] [C]
[Z] [M] [P]
 1   2   3

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2")

(def input (slurp "resources/input/day-05.txt"))

(defn transpose-matrix [mat]
  (map reverse (apply mapv vector mat)))

(defn pad-crates [seq len]
  (concat seq (repeat (- len (count seq)) \space)))

(defn parse-crates [crates stacks]
  (->> crates
       str/split-lines
       pop
       (map #(map (fn [x] (second x)) (partition 3 4 %)) )
       (map #(pad-crates % stacks))
       transpose-matrix
       (map #(filterv (fn [x] (not (= x \space))) %))))

(defn parse-instructions [instructions]
  (->> instructions
       str/split-lines
       (map #(take-nth 2 (rest (str/split % #" "))))
       flatten
       (map #(Integer/parseInt %))
       (partition 3)))

(defn arrange [crates [move from to]]
  (let [from-stack (nth crates (dec from))
        crates-to-move (reverse (take move (reverse (nth crates (dec from)))))
        from-stack-moved (take (- (count from-stack) move) from-stack)                                  ; M
        to-stack-moved (concat (nth crates (dec to)) (reverse crates-to-move))
        moved (-> (vec crates)
                   (assoc (dec from) from-stack-moved)
                   (assoc (dec to) to-stack-moved))]
    moved))

(defn parse-input [input-data]
  (let [parsed (str/split input-data #"\n\n")
        stacks (-> (first parsed) str/split-lines last str/trim (str/split #"\s+") last Integer/parseInt)
        crates (parse-crates (first parsed) stacks)
        instructions (parse-instructions (last parsed))]
    [crates instructions]))

(defn get-top-crates [final-crates-pos]
  (str/join "" (map last final-crates-pos)))

(defn solve-puzzle-1 [input-data]
  (let [parsed (parse-input input-data)
        crates (first parsed)
        instructions (last parsed)
        final-pos (loop [curr-crate crates
                         inst-id 0]
                    (if (= (count instructions) inst-id)
                      curr-crate
                      (recur (arrange curr-crate (nth instructions inst-id))
                             (inc inst-id))))]
    (get-top-crates final-pos)))

(solve-puzzle-1 input)

(defn arrange-2 [crates [move from to]]
  (let [from-stack (nth crates (dec from))
        crates-to-move (reverse (take move (reverse (nth crates (dec from)))))
        from-stack-moved (take (- (count from-stack) move) from-stack)                                  ; M
        to-stack-moved (concat (nth crates (dec to)) crates-to-move)
        moved (-> (vec crates)
                   (assoc (dec from) from-stack-moved)
                   (assoc (dec to) to-stack-moved))]
    moved))

(defn solve-puzzle-2 [input-data]
  (let [parsed (parse-input input-data)
        crates (first parsed)
        instructions (last parsed)
        final-pos (loop [curr-crate crates
                         inst-id 0]
                    (if (= (count instructions) inst-id)
                      curr-crate
                      (recur (arrange-2 curr-crate (nth instructions inst-id))
                             (inc inst-id))))]
    (get-top-crates final-pos)))

(solve-puzzle-2 input)

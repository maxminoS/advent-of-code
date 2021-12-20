(ns aoc2021.puzzle15
  (:require
   [clojure.string :as str]))

(def demo-input "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce")

(def input (slurp "resources/input/day-08.txt"))

(defn get-output-vals [input-line]
  (str/split
   (last
    (map str/trim
         (str/split input-line #"\|")))
   #" "))

(defn solve-puzzle [data-input]
  (let [output-vals (->> data-input
                         str/split-lines
                         (map get-output-vals)
                         flatten)
        output-counts (map count output-vals)]
    (->> output-counts
         (map
          #(condp = %
             2 true ;; -> 1
             4 true ;; -> 4
             3 true ;; -> 7
             7 true ;; -> 8
             nil))
         (filter true?)
         count)))

(solve-puzzle input)

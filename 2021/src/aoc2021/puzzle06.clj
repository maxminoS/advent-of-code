(ns aoc2021.puzzle06
  (:require
   [aoc2021.puzzle05 :as p]))

(defn oxygen-criteria [bits]
  (if (reduce = (map val (frequencies bits))) 1
      (p/most-common-item bits)))

(defn carbon-dioxide-criteria [bits]
  (if (reduce = (map val (frequencies bits))) 0
      (p/least-common-item bits)))

(defn oxygen-bit-fn
  ([data-input] (oxygen-bit-fn data-input 0))
  ([data-input n]
   (lazy-seq
    (cons (oxygen-criteria (p/nth-item-map data-input n))
          (oxygen-bit-fn data-input (inc n))))))

(defn carbon-dioxide-bit-fn
  ([data-input] (carbon-dioxide-bit-fn data-input 0))
  ([data-input n]
   (lazy-seq
    (cons (carbon-dioxide-criteria (p/nth-item-map data-input n))
          (carbon-dioxide-bit-fn data-input (inc n))))))

(defn oxygen-bits [data-input]
  (take (count (first data-input)) (oxygen-bit-fn data-input)))

(defn carbon-dioxide-bits [data-input]
  (take (count (first data-input)) (carbon-dioxide-bit-fn data-input)))

(defn oxygen-rating-fn
  ([data-input] (oxygen-rating-fn data-input 0))
  ([data-input n]
   (if (or (= 1 (count data-input))
           (> n (count (first data-input))))
     data-input
     (oxygen-rating-fn
      (filter #(= (nth (oxygen-bits data-input) n)
                  (nth % n))
              data-input)
      (inc n)))))

(defn carbon-dioxide-rating-fn
  ([data-input] (carbon-dioxide-rating-fn data-input 0))
  ([data-input n]
   (if (or (= 1 (count data-input))
           (> n (count (first data-input))))
     data-input
     (carbon-dioxide-rating-fn
      (filter #(= (nth (carbon-dioxide-bits data-input) n)
                  (nth % n))
              data-input)
      (inc n)))))

(defn oxygen-generator-rating [data-input]
  (->> data-input
       oxygen-rating-fn
       flatten
       p/bin->dec))

(defn carbon-dioxide-scrubber-rating [data-input]
  (->> data-input
       carbon-dioxide-rating-fn
       flatten
       p/bin->dec))

(defn life-support-rating [data-input]
  (* (oxygen-generator-rating data-input)
     (carbon-dioxide-scrubber-rating data-input)))

(defn solve-puzzle [data-input]
  (life-support-rating (p/parse-input data-input)))

(solve-puzzle p/input)

(ns cheque-parser.core
  (:gen-class)
  (:require [clojure.string :as str]))

(declare convert-to-words)

(def units-to-word
  {0 ""
   1 "one"
   2 "two"
   3 "three"
   4 "four"
   5 "five"
   6 "six"
   7 "seven"
   8 "eight"
   9 "nine"})

(def teens-to-word
  {10 "ten"
   11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"})

(def tens-to-word
  {20 "twenty"
   30 "thirty"
   40 "forty"
   50 "fifty"
   60 "sixty"
   70 "seventy"
   80 "eighty"
   90 "ninety"})

(def base-ten-units
  {1000000000 "billion"
   1000000 "million"
   1000 "thousand"
   100 "hundred"})

(def divisors-to-words (->
                        (sorted-map-by >)
                        (into base-ten-units)
                        (into tens-to-word)
                        (into teens-to-word)
                        (into units-to-word)))

(defn quotmod
  "Given a number and a divisor, return the quotient and modulus."
  [n d]
  (let [quotient (quot n d)
        modulus (mod n d)]
    [quotient modulus]))

(defn largest-divisor
  "Given a number, returns the largest number in divisors-to-words that is less than or equal to the number."
  [number]
  (ffirst (filter #(>= number (first %)) divisors-to-words)))

(defn hundreds?
  "True if number is completely divisable by 100"
  [number]
  (zero? (mod number 100)))

(defn- build-words
  [number divisor words]
  "Convert the number to words and append to a vector of existing words."
  (let [vec-words (vec words)]
   (if (hundreds? divisor)
      (conj (conj vec-words (convert-to-words number)) (divisors-to-words divisor))
      (conj vec-words (divisors-to-words divisor)))))

(defn convert-to-words
  "Convert a number to it's word representation up to billions."
  [number]
  (loop [n number
         words []]
    (if (zero? n)
      (clojure.string/join " " words)
      (let [divisor (largest-divisor n)
            [quotient modulus] (quotmod n divisor)]
        (recur
         modulus
         (build-words quotient divisor words))))))


(defn- convert-with-and
  "Convert a number with an 'and' separator prior to the remainder after the hundreds."
  [number]
  (let [modulus-of-100 (mod number 100)
        number-in-hundreds (- number modulus-of-100)]
    (str
     (convert-to-words number-in-hundreds)
     " and "
     (convert-to-words (- number number-in-hundreds)))))

(defn convert
  "Convert a number to words."
  [number]
  (if (or
       (<= number 100)
       (hundreds? number))
    (convert-to-words number)
    (convert-with-and number)))

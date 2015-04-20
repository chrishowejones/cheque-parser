(ns cheque-parser.core
  (:gen-class))

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
  (into (sorted-map-by >)
        {1000000000 "billion"
         1000000 "million"
         1000 "thousand"
         100 "hundred"}))

(def last-base-ten-value
  (first (last base-ten-units)))

(defn quotmod [n d]
  (let [quotient (quot n d)
        modulus (mod n d)]
    [quotient (if (= modulus 0) nil modulus) ]))

(def divisors-to-words (->
                        (sorted-map-by >)
                        (into tens-to-word)
                        (into teens-to-word)
                        (into units-to-word)))

(declare convert)

(defn tens
  "Convert int to words"
  [n]
  (let [[tens units] (quotmod n 10)]
    (condp = tens
      1 (teens-to-word n)
      0 (units-to-word n)
      (str (tens-to-word (* 10 tens)) (when units (str " " (convert units)))))))
(
  defn convert [n]
  (let [[a b] (quotmod n 1000)]
    (cond
      (>= a 1) (str (tens a) " thousand" (when b (str (convert b))))
      (>= b 100) (let [[hs ts] (quotmod b 100)]
                   (str (tens hs) " hundred" (when ts (str " and " (convert ts)))))
      (= a 0) (tens n))))


(defn number-to-words
  "Looks up a number value and returns the word for the number"
  [num]
  (divisors-to-words num))

(defn base-ten-to-word
  "Looks up the suffix word for base ten units."
  [num]
  (clojure.string/join (base-ten-units num)))

(defn multiple-of-ten-words
  [quotient words unit]
  (if (zero? quotient)
    words
    (conj words (str (number-to-words quotient) " " (base-ten-to-word unit)))))

(defn convert-base-ten
  "Converts a number divisable by base ten units down to 100 to a string"
  [num]
  (loop [n num
         base-units base-ten-units
         words []]
    (if (or (empty? base-units) (nil? n) (zero? n))
      (clojure.string/join " " words)
      (let [unit (ffirst base-units)
            [quotient modulus] (quotmod n unit)]
        (recur modulus
               (into {} (rest base-units))
               (if (< quotient last-base-ten-value)
                 (multiple-of-ten-words quotient words unit)
                 (cons (convert-base-ten quotient) (vector (base-ten-to-word unit)))))))))

(defn new-convert [num]
  (str
   (if (> num last-base-ten-value)
     (let [remainder (mod num last-base-ten-value)]
      (str
       (convert-base-ten num)
       (if (not (zero? remainder))
         (str
          " and "
          (number-to-words remainder)))))
     (number-to-words num))))

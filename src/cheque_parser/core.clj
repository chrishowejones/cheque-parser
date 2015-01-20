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


(defn quotmod [n d]
  (let [quotient (quot n d)
        modulus (mod n d)]
    [quotient (if (= modulus 0) nil modulus) ]))


(defn tens
  "Convert int to words"
  [n]
  (let [[tens units] (quotmod n 10)]
   (condp = tens
     1 (teens-to-word n)
     0 (units-to-word n)
     (str (tens-to-word (* 10 tens)) (when units (str " " (convert units)))))))

(defn convert [n]
  (let [[a b] (quotmod n 1000)]
    (cond
      (>= a 1) (str (tens a) " thousand" (when b (str (convert b))))
      (>= b 100) (let [[hs ts] (quotmod b 100)]
                   (str (tens hs) " hundred" (when ts (str " and " (convert ts)))))
      (= a 0) (tens n))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

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

(def tens
  {20 "twenty"
   30 "thirty"
   40 "forty"
   50 "fifty"
   60 "sixty"
   70 "seventy"
   80 "eighty"
   90 "ninety"})

(defn tens-to-word
  [n]
  (tens n))

(defn quotmod [n d]
        [(quot n d) (mod n d)])


(defn convert
  "Convert int to words"
  [n]
  (let [[q m] (quotmod n 10)]
   (condp = q
     1 (teens-to-word n)
     0 (units-to-word n)
     (clojure.string/trim
      (clojure.string/join " " (vector (tens-to-word (* 10 q)) (convert m)))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

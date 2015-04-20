(ns cheque-parser.core-test
  (:require [midje.sweet :refer :all]
            [cheque-parser.core :refer :all]))

(fact "1 returns one"
      (convert 1) => "one")

(fact "2 returns two"
      (convert 2) => "two")

(fact "3 return three"
      (convert 3) => "three")

(fact "4 return four"
      (convert 4) => "four")

(fact "9 return nine"
      (convert 9) => "nine")

(fact "10 returns ten"
      (convert 10) => "ten")

(fact "11 returns eleven"
      (convert 11) => "eleven")

(fact "12 returns twelve"
      (convert 12) => "twelve")

(fact "13 returns thirteen"
      (convert 13) => "thirteen")

(fact "14 returns fourteen"
      (convert 14) => "fourteen")

(fact "19 returns nineteen"
      (convert 19) => "nineteen")

(fact "20 returns twenty"
      (convert 20) => "twenty")

(fact "21 returns twenty one"
      (convert 21) => "twenty one")

(fact "22 returns twenty two"
      (convert 22) => "twenty two")

(fact "29 returns twenty nine"
      (convert 29) => "twenty nine")

(fact "30 returns thirty"
      (convert 30) => "thirty")

(fact "99 returns ninety nine"
      (convert 99) => "ninety nine")


(fact "100 returns one hundred"
      (convert 100) => "one hundred")

(fact "101 returns one hundred and one"
      (convert 101) => "one hundred and one")

(fact "102 returns one hundred and two"
      (convert 102) => "one hundred and two")

(fact "110 returns one hundred and ten"
      (convert 110) => "one hundred and ten")

(fact "199 returns one hundred and ninety nine"
      (convert 199) => "one hundred and ninety nine")

(fact "999 returns nine hundred and ninety nine"
      (convert 999) => "nine hundred and ninety nine")

(fact "1000 returns one thousand"
      (convert 1000) => "one thousand")

(fact "quotmod supplied with dividend and divisor returns a vector of quotient and modulus"
      (quotmod 1 10) => [0 1]
      (quotmod 3 10) => [0 3]
      (quotmod 15 10) => [1 5]
      (quotmod 28 10) => [2 8]
      (quotmod 88 10) => [8 8]
      (quotmod 100 100) => [1 nil]
      (quotmod 100000 1000) => [100 nil])


(facts "largest-divisor returns the largest divisor from divisor-to-words for a given nubmer."
       (fact "largest-divisor returns 70 for 70"
             (largest-divisor 70) => 70)
       (fact "largest-divisor returns 99 for 90"
             (largest-divisor 99) => 90)
       (fact "largest-divisor returns 13 for 13"
             (largest-divisor 13) => 13))


(facts "number-to-words returns words for numbers"
 (fact "number-to-words returns one for 1"
       (number-to-words 1) => "one")
 (fact "number-to-words return nine for 9"
       (number-to-words 9) => "nine")
 (fact "number-to-words returns eleven for 11"
       (number-to-words 11) => "eleven")
 (fact "number-to-words returns nineteen for 19"
       (number-to-words 19) => "nineteen")
 (fact "number-to-words returns twenty for 20"
       (number-to-words 20) => "twenty")
 (fact "number-to-words returns twenty for 90"
       (number-to-words 90) => "ninety"))

(facts "number-to-words returns words for numbers - covering tens and units"
       (fact "number-to-words returns 'twenty one' for 21"
             (number-to-words 21) => "twenty one")
       (fact "number-to-words returns 'seventy eight' for 78"
             (number-to-words 78) => "seventy eight")
       (fact "number-to-words returns 'ninety nine' for 99"
             (number-to-words 99) => "ninety nine"))

(facts "base-ten-to-word returns words for base ten unit"
 (fact "base-ten-to-word returns hundred for 100"
       (base-ten-to-word 100) => "hundred")
 (fact "base-ten-to-word returns hundred for 1000"
       (base-ten-to-word 1000) => "thousand")
 (fact "base-ten-to-word returns hundred for 1000000"
       (base-ten-to-word 1000000) => "million"))

(fact "multiple-of-ten-words returns a sequence of words indicating the number and base of ten"
      (multiple-of-ten-words 1 [] 100) => ["one hundred"]
      (multiple-of-ten-words 9 [] 100) => ["nine hundred"]
      (multiple-of-ten-words 10 [] 1000) => ["ten thousand"]
      (multiple-of-ten-words 90 [] 1000) => ["ninety thousand"]
      (multiple-of-ten-words 5 ["one thousand"] 100) => ["one thousand" "five hundred"])


(facts "convert-base-ten returns the multiple for each base as a string"
       (fact "convert-base-ten returns 'one hundred' for 100"
             (convert-base-ten 100) => "one hundred")
       (fact "convert-base-ten returns 'thirteen thousand' for 13000"
             (convert-base-ten 13000) => "thirteen thousand")
       (fact "convert-base-ten returns 'one thousand one hundred' for 1100"
             (convert-base-ten 1600) => "one thousand six hundred")
       (fact "convert-base-ten returns 'three hundred thousand' for 300000"
             (convert-base-ten 300000) => "three hundred thousand")
       (fact "convert-base-ten returns 'ninety million' for 90000000"
             (convert-base-ten 90000000) => "ninety million")
       (fact "convert-base-ten returns 'three hundred million' for 90000000"
             (convert-base-ten 300000000) => "three hundred million")
       (fact "convert-base-ten returns 'five billion' for 5000000000"
             (convert-base-ten 5000000000) => "five billion")
       (fact "convert-base-ten returns 'three hundred million billion' for 300000000000"
             (convert-base-ten 300000000000) => "three hundred billion")
       (fact "convert-base-ten returns 'three hundred million billion' for 300000000000"
             (convert-base-ten 900000000000000000) => "nine hundred million billion")
       (fact "convert-base-ten returns 'two thousand two hundred' for 2200"
             (convert-base-ten 2200) => "two thousand two hundred")
       (fact "convert-base-ten returns 'twenty thousand two hundred' for 20200"
             (convert-base-ten 20200) => "twenty thousand two hundred")
       (fact "convert-base-ten returns 'two hundred thousand two hundred' for 200200"
             (convert-base-ten 200200) => "two hundred thousand two hundred"))

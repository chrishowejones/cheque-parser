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

(fact "quotmod supplied with dividend and divisor returns a vector of quotient and modulus"
      (quotmod 1 10) => [0 1]
      (quotmod 3 10) => [0 3]
      (quotmod 15 10) => [1 5]
      (quotmod 28 10) => [2 8]
      (quotmod 88 10) => [8 8])
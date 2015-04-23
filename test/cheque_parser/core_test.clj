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

(fact "1415 returns one thousand four hundred and fifteen"
      (convert 1415) => "one thousand four hundred and fifteen")

(fact "3004926 returns three million four thousand nine hundred and twenty six"
      (convert 3004926) => "three million four thousand nine hundred and twenty six")

(fact "45100000 returns 'forty five million one hundred thousand'"
      (convert 45100000) => "forty five million one hundred thousand")

(fact "7968345123 returns 'seven billion nine hundred sixty eight million three hundred forty five thousand one hundred and twenty three'"
      (convert 7968345123) => "seven billion nine hundred sixty eight million three hundred forty five thousand one hundred and twenty three")

(fact "quotmod supplied with dividend and divisor returns a vector of quotient and modulus"
      (quotmod 1 10) => [0 1]
      (quotmod 3 10) => [0 3]
      (quotmod 15 10) => [1 5]
      (quotmod 28 10) => [2 8]
      (quotmod 88 10) => [8 8]
      (quotmod 100 100) => [1 0]
      (quotmod 100000 1000) => [100 0])


(facts "largest-divisor returns the largest divisor from divisor-to-words for a given nubmer."
       (fact "largest-divisor returns 70 for 70"
             (largest-divisor 70) => 70)
       (fact "largest-divisor returns 99 for 90"
             (largest-divisor 99) => 90)
       (fact "largest-divisor returns 13 for 13"
             (largest-divisor 13) => 13))

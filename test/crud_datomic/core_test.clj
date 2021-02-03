(ns crud-datomic.core-test
  (:require [clojure.test :refer :all]
            [crud-datomic.core :refer :all]))

(deftest new-model-test
  (testing "testing to creating a new objetc"
    (is (= 0 1))))

(ns crud-datomic.core
  (:require [crud-datomic.model :as model]
            [crud-datomic.db :as db])
  (:use clojure.pprint))

;;Creating db
(pprint (db/creating-database))

;;Creating connection
;;(pprint (db/connect))

;;Creating schema
;;(db/create-schema (db/connect))

;;Drop database
;(db/drop-database))

;Inserts
(defn- add-tasks []
  (let [task1 (model/new-task "Task 1","Description of the task 1", "To Do",true, (java.util.Date.), (java.util.Date.))
        task2 (model/new-task "Task 2","Description of the task 2", "To Do",false, (java.util.Date.), (java.util.Date.))
        task3 (model/new-task "Task 3","Description of the task 3 ", "Done",false, (java.util.Date.), (java.util.Date.))
        task4 (model/new-task "Task 4","Description of the task 4 ", "Doing",false, (java.util.Date.), (java.util.Date.))]
        (db/insert-item-many (db/connect) [task1, task2, task3, task4])))

(add-tasks)

(pprint (db/query-all-tasks (db/get-db (db/connect))))
(pprint (db/query-all-tasks-all-attributs (db/get-db (db/connect))))
(pprint (db/query-tasks-by-name (db/get-db (db/connect)) "Task 1"))
(pprint (db/query-tasks-by-name-and-status (db/get-db (db/connect)) "Task 1" "To Do"))

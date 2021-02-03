(ns crud-datomic.db
   (:require [datomic.api :as db])
   (:use clojure.pprint))

(def db-url "datomic:dev://localhost:4334/todolist")

(defn creating-database []
  (db/create-database db-url))

(defn connect []
  (db/connect db-url))

(defn drop-database []
  (db/delete-database db-url))

(def schema [{:db/ident :task/name
               :db/valueType :db.type/string
               :db/cardinality :db.cardinality/one
               :db/unique     :db.unique/identity
               :db/doc "Name the task"
              }

              {:db/ident :task/description
               :db/valueType :db.type/string
               :db/cardinality :db.cardinality/one
               ; :db/unique     :db.unique/identity
               :db/doc "Description the task"
              }

              {:db/ident :task/status
               :db/valueType :db.type/string
               :db/cardinality :db.cardinality/one
               ; :db/unique     :db.unique/identity
               :db/doc "Status the task"
              }

              {:db/ident :task/is-priority
               :db/valueType :db.type/boolean
               :db/cardinality :db.cardinality/one
               ; :db/unique     :db.unique/identity
               :db/doc "Indication if task is priority or not"
              }

              {:db/ident :task/date-creating
               :db/valueType :db.type/instant
               :db/cardinality :db.cardinality/one
               ; :db/unique     :db.unique/identity
               :db/doc "Date of create task"
              }

              {:db/ident :task/date-conclusion
               :db/valueType :db.type/instant
               :db/cardinality :db.cardinality/one
               ; :db/unique     :db.unique/identity
               :db/doc "Date of conclusion task"}])

(defn create-schema [conn]
  (db/transact conn schema))

(defn insert-item-one [conn task]
  (db/transact conn [task]))

(defn insert-item-many [conn tasks]
    (db/transact conn tasks))

(defn get-db [conn]
  (db/db conn))

(defn query-all-tasks [db-snapshot]
  (db/q '[:find ?entity
                :where [?entity :task/name ]] db-snapshot))

(defn query-all-tasks-all-attributs [db-snapshot]
        (db/q '[:find (pull ?entity [*])
               :where [?entity :task/name ]] db-snapshot))

(defn query-tasks-by-name [db-snapshot name]
      (db/q '[:find ?entity
              :in $ ?name
              :where [?entity :task/name ?name ]] db-snapshot name))

;retorna um mapa com campos especificos  para retornar tudo usar *
(defn query-tasks-by-name-and-status [db-snapshot, name, status]
                    (db/q '[:find (pull ?entity [:db/id
                                                 :task/name
                                                 :task/status
                                                 :task/is-priority
                                                 :task/date-conclusion])
                            :in $ ?name ?status
                            :where [?entity :task/name ?name ]
                                   [?entity :task/status ?status ]] db-snapshot, name, status))

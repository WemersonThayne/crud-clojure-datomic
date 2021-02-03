(ns crud-datomic.model)

(defn new-task [name, description, status, is-priority, date-creating, date-conclusion]
  { :task/name name
    :task/description description
    :task/status status
    :task/is-priority is-priority
    :task/date-creating date-creating
    :task/date-conclusion date-conclusion})

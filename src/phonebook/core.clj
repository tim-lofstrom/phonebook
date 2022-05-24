(ns phonebook.core
  (:require [clojure.string :as str]))

;; (require '[clojure.tools.namespace.repl :refer [refresh-all refresh]]) (refresh)

(declare actions)

(defn dsa [] "DWS")

(def phonebook-state (atom {}))

(defn help []
  (let [action-names (map name (keys actions))]
    (doseq [item action-names] (println item))))

(defn add [name number]
  (swap! phonebook-state (fn [pb] (assoc pb name number))))

(defn lookup [name]
  (println (get @phonebook-state name)))

(def actions
  {:help help :add add :lookup lookup})

(defn handle-input [input]
  (let [args (str/split input #" ") action (keyword (first args))]
    (apply (action actions) (rest args))))

(defn phonebook []
  (loop []
    (print "phonebook>") (flush)
    (let [input (read-line)]
      (if (= input "q")
        (println "exit")
        (do (handle-input input) (recur))))))

(defn -main []
  (phonebook))
(ns phonebook.core :require [clojure.string :as str])

(def phonebook-state (atom {}))

(defn help []
  (let [action-names (map name (keys actions))]
    (doseq [item action-names]
      (println item))))

(defn add [name number]
  (swap! phonebook-state (fn [pb] (assoc pb name number))))

(def actions {:help help :add add})

(defn handle-input [input]
(let [args (str/split input #" ")])
  (let [action (keyword (first args))]
    ((action actions))))

(defn phonebook []
  (loop []
    (print "phonebook>") (flush)
    (let [input (read-line)]
      (if (= input "q")
        (println "exit")
        (do (handle-input input) (recur))))))

(defn -main []
  (phonebook))
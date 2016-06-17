(ns reagent-button.core
  (:require [reagent.core :as r]))

(def ReactNative (js/require "react-native"))
(def styleSheet (r/adapt-react-class (.-StyleSheet ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def touchableOpacity (r/adapt-react-class (.-TouchableOpacity ReactNative)))
(def text (r/adapt-react-class (.-Text ReactNative)))

(def ^:const system-opacity 0.2)

(defn render-group []
  {:flexDirection  "row"
   :justifyContent "space-between"
   :alignItems     "center"})

(defn render-style [disabled]
  {:color (if disabled "#dcdcdc" "#007aff" )
   :fontFamily  ".HelveticaNeueInterface-MediumP4"
   :fontSize    17
   :fontWeight  "bold"
   :textAlign   "center"})

(defn- render-touchable-opacity [disabled]
  {:activeOpacity (if disabled 1 system-opacity)})

; first remove children
(defn my-button [props & children]
  (println (count children))
  (println "and the children is" children)
  (let [disable (if (nil? (:disable props)) false (:disable props))]
    (fn []
      (case (count children)
        0 nil
        1 [touchableOpacity (render-touchable-opacity disable)
           [text (render-style disable) children ]]
        ;else rendered as group todo but it need to set props here
        [view (render-group) children]))))
package router

import (
	"github.com/gorilla/mux"
	"habit-service/controller"
)

func Router() *mux.Router {

	router := mux.NewRouter()
	router.HandleFunc("/v1/habit", controller.GetHabit).Methods("GET")
	router.HandleFunc("/v1/habit", controller.InsertHabit).Methods("POST")
	router.HandleFunc("/v1/habit", controller.UpdateHabit).Methods("PUT")
	router.HandleFunc("/v1/habit", controller.DeleteHabit).Methods("DELETE")
	return router
}

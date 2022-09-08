package controller

import (
	"encoding/json"
	"habit-service/dto"
	"habit-service/service"
	"log"
	"net/http"
)

func setHeaders(w http.ResponseWriter) {
	w.Header().Set("Content-Type", "application/json")
	w.Header().Set("Access-Control-Allow-Origin", "*")
	w.Header().Set("Access-Control-Allow-Headers", "Content-Type")
}

func InsertHabit(responseWriter http.ResponseWriter, req *http.Request) {
	setHeaders(responseWriter)
	responseWriter.Header().Set("Access-Control-Allow-Methods", "POST")

	log.Println("Inserting habit")

	var newHabitReq dto.HabitRequest
	_ = json.NewDecoder(req.Body).Decode(&newHabitReq)

	result, err := service.InsertHabit(newHabitReq)
	if err != nil {
		error := dto.Error{Code: http.StatusInternalServerError, Message: "Habit not found"}
		responseWriter.WriteHeader(http.StatusInternalServerError)
		json.NewEncoder(responseWriter).Encode(error)
	}
	responseWriter.WriteHeader(http.StatusCreated)
	json.NewEncoder(responseWriter).Encode(result)
}
func UpdateHabit(responseWriter http.ResponseWriter, req *http.Request) {
	setHeaders(responseWriter)
	responseWriter.Header().Set("Access-Control-Allow-Methods", "PUT")

	log.Println("Updating habit")
}
func DeleteHabit(responseWriter http.ResponseWriter, req *http.Request) {
	setHeaders(responseWriter)
	responseWriter.Header().Set("Access-Control-Allow-Methods", "DELETE")

	log.Println("Deleting habit")
}
func GetHabit(responseWriter http.ResponseWriter, req *http.Request) {
	setHeaders(responseWriter)
	responseWriter.Header().Set("Access-Control-Allow-Methods", "GET")

	log.Println("Getting habit")

	var habitID dto.HabitIDRequest
	_ = json.NewDecoder(req.Body).Decode(&habitID)
	habit, err := service.GetHabit(habitID)
	if err != nil {
		error := dto.Error{Code: http.StatusNotFound, Message: "Habit not found"}
		json.NewEncoder(responseWriter).Encode(error)
	} else {
		responseWriter.WriteHeader(http.StatusOK)
		json.NewEncoder(responseWriter).Encode(habit)
	}
}

package controller

import (
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
}
